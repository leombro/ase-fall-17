package com.leombro.toydoodle;

import org.springframework.web.bind.annotation.*;
import di.unipi.ase.doodle.*;

import java.util.HashMap;

/**
 *  A Spring controller class for the RESTful ToyDoodle service.
 */
@RestController
public class DoodleController {

    /**
     *  Counter for the opened doodles.
     */
    private int id = 0;

    /**
     *  Keeps track of opened doodles as [id, doodle]
     */
    private HashMap<Integer, Doodle> doodles = new HashMap<>();

    /**
     * Creates a new doodle from JSON.
     *
     * The implementation guarantees that a doodle is created if and only if the JSON contains
     * both the "title" and "options" fields (ignoring any other field).
     *
     * @param d The Doodle parsed by the Spring framework from the JSON body request.
     * @return The ID of the newly created doodle, or -1 if there's been an error.
     */
    @RequestMapping(value="/doodles", method = RequestMethod.PUT)
    public long createDoodle(@RequestBody Doodle d) {
        if (d != null && d.getTitle() != null && d.getOptions() != null) {
            doodles.put(id, new Doodle(d));
            return id++;
        } else
            return -1;
    }

    /**
     * Retrieves all currently opened doodles.
     *
     * @return A list of pairs [id, Doodle] representing the currently opened doodles.
     */
    @RequestMapping(value="/doodles", method=RequestMethod.GET)
    public HashMap<Integer, Doodle> getOpenDoodles() {
        return doodles;
    }

    /**
     * Retrieves the doodle identified by id.
     *
     * @param id the ID of the Doodle to be retrieved.
     * @return The desired Doodle, or null if the ID is not a valid one.
     */
    @RequestMapping(value="/doodles/{id}", method=RequestMethod.GET)
    public Doodle getDoodle(@PathVariable("id") int id) {
        return doodles.get(id);
    }

    /**
     * Deletes the doodle identified by id.
     *
     * @param id The ID of the Doodle to be deleted.
     */
    @RequestMapping(value="/doodles/{id}", method=RequestMethod.DELETE)
    public void deleteDoodle(@PathVariable("id") int id) {
        doodles.remove(id);
    }

    /**
     * Creates a new vote in the doodle identified by id and returns the name of the voter.
     *
     * The implementation guarantees that this method can be called by an user only to cast
     * its first vote; it cannot be used to modify an already-existing vote.
     * Moreover, it also checks that the JSON contains the "name" field, in order to
     * avoid the bug where a JSON request with valid "option" but no "name" gets inserted
     * into the doodle as "null" (see report).
     *
     * @param id The ID of the Doodle in which the vote must be casted.
     * @param v The Vote object parsed by the Spring framework from the JSON body request.
     * @return The string containing the name of the voter if the vote has been successfully casted,
     *         or null otherwise.
     */
    @RequestMapping(value="/doodles/{id}/vote", method=RequestMethod.PUT)
    public String putVote(@PathVariable("id") int id, @RequestBody Vote v) {
        Doodle d = doodles.get(id);
        String name = v.getName();
        return (d != null
                && name != null
                && d.findPreviousVote(name) == null) ? d.addVote(v) : null;
    }

    /**
     * Returns the option voted by user name in the doodle identified by id.
     *
     * @param id The ID of the Doodle in which the user has casted its vote.
     * @param name The name of the user.
     * @return The string containing the option voted by the user, or null if the user hasn't voted or the
     *         ID does not refer to an existing Doodle.
     */
    @RequestMapping(value="/doodles/{id}/vote/{name}", method=RequestMethod.GET)
    public String getVotedOption(@PathVariable("id") int id, @PathVariable("name") String name) {
        Doodle d = doodles.get(id);
        return (d != null) ? d.findPreviousVote(name) : null;
    }

    /**
     * Posts an update as a new JSON vote to the vote of name in doodle id, and returns the name of the voter.
     *
     * The implementation guarantees that this method can be called by an user only to update
     * an already-existing vote; it cannot be used to add a new vote.
     * Moreover, it also checks that the name in the URI corresponds to the "name" field of the JSON request.
     *
     * @param id The ID of the Doodle in which the user has casted its vote.
     * @param name The name of the user.
     * @param v The new vote to be casted, an object parsed by the Spring framework from the JSON body request.
     * @return The string containing the name of the voter if the vote has been successfully casted,
     *         or null otherwise.
     */
    @RequestMapping(value="/doodles/{id}/vote/{name}", method=RequestMethod.POST)
    public String updateVote(@PathVariable("id") int id,
                             @PathVariable("name") String name,
                             @RequestBody Vote v) {
        Doodle d = doodles.get(id);
        String voteName = v.getName();
        return (d != null
                && name.equals(voteName)
                && d.findPreviousVote(voteName) != null) ? d.addVote(v) : null;
    }

    /**
     * Deletes the vote of name from the Doodle identified by id.
     *
     * @param id The ID of the Doodle in which the user has casted its vote.
     * @param name The name of the user.
     */
    @RequestMapping(value="/doodles/{id}/vote/{name}", method=RequestMethod.DELETE)
    public void deleteVote(@PathVariable("id") int id, @PathVariable("name") String name) {
        Doodle d = doodles.get(id);
        if (d != null) {
            d.removeVote(name);
        }
    }
}
