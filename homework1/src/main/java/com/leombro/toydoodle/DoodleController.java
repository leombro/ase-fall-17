package com.leombro.toydoodle;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class DoodleController {
    // Counts the opened doodles
    private int id = 0;

    // Keeps track of opened doodles as <id, doodle>
    private HashMap<Integer, Doodle> doodles = new HashMap<>();

    @RequestMapping(value="/doodles", method=RequestMethod.PUT)
    public long createDoodle(@RequestBody Doodle d) {
        doodles.put(id, new Doodle(d));
        id++;
        return id - 1;
    }

    @RequestMapping(value="/doodles", method=RequestMethod.GET)
    public HashMap<Integer, Doodle> getOpenDoodles() {
        return doodles;
    }

    @RequestMapping(value="/doodles/{id}", method=RequestMethod.GET)
    public Doodle getDoodle(@PathVariable("id") int id) {
        return doodles.get(id);
    }

    @RequestMapping(value="/doodles/{id}", method=RequestMethod.DELETE)
    public void deleteDoodle(@PathVariable("id") int id) {
        doodles.remove(id);
    }

    @RequestMapping(value="/doodles/{id}/vote", method=RequestMethod.PUT)
    public String putVote(@PathVariable("id") int id, @RequestBody Vote v) {
        Doodle d = doodles.get(id);
        if (d != null) {
            d.addVote(v);
            return v.getName();
        }
        return null;
    }

    @RequestMapping(value="/doodles/{id}/vote/{name}", method=RequestMethod.GET)
    public String getVotedOption(@PathVariable("id") int id, @PathVariable("name") String name) {
        Doodle d = doodles.get(id);
        if (d != null) {
            return d.findPreviousVote(name);
        }
        return null;
    }

    @RequestMapping(value="/doodles/{id}/vote/{name}", method=RequestMethod.POST)
    public String updateVote(@PathVariable("id") int id,
                             @PathVariable("name") String name,
                             @RequestBody Vote v) {
        Doodle d = doodles.get(id);
        if (d != null) {
            d.addVote(v);
            return v.getName();
        }
        return null;
    }

    @RequestMapping(value="/doodles/{id}/vote/{name}", method=RequestMethod.DELETE)
    public void deleteVote(@PathVariable("id") int id, @PathVariable("name") String name) {
        Doodle d = doodles.get(id);
        if (d != null) {
            d.removeVote(name);
        }
    }
}
