# Homework 1

## Assignment

Fred and Barney need your help to implement and deploy a Toy Doodle service.  They already have some (simple) code (provided by the teacher, ed.) for local doodles and they want to make a RESTful service out of it by means of Spring Boot. Once deployed to Heroku, the service should provide the API reported below. 

URI | Method | Description
--- | ------ | -----------
`/doodles` | PUT | It creates a new Doodle from JSON.
`/doodles` | GET | It retrieves all currently open Doodles.
`/doodles/{id}` | GET | It retrieves the Doodle identified by `id`.
`/doodles/{id}` | DELETE | It deletes the Doodle identified by `id`.
`/doodles/{id}/vote` | PUT | It creates a new vote in the Doodle identified by `id`. It inputs a JSON for the vote and it returns the name of the voter.
`/doodles/{id}/vote/{name}` | GET | It returns the option voted by user `name` in the Doodle identified by `id`.
`/doodles/{id}/vote/{name}` | POST | It posts an update as a new JSON vote to the vote of `name` in poll `id`. It returns the name of the voter.
`/doodles/{id}/vote/{name}` | DELETE | It deletes the vote of `name` from the Doodle identified by `id`.

Implement the service and deploy it to Heroku. Test the version you deployed to Heroku with Postman. Write a short report (300 words at most) containing (1) the URL of your Heroku dyno and (2) the snapshots of your tests (for all provided functionalities), while performing all above operations.  Upload to Moodle both the report and your solution code. 

### Learning Outcomes
- [x] Learn how to use Spring Boot annotations to build RESTful services.
- [x] Get familiar with RESTful APIs and HTTP methods. 
- [x] Test RESTful APIs with an HTTP client. 
- [x] Learn basic functioning of Heroku.

## Solution outline

### Deployment
The application has been deployed to the Heroku service [here](
https://leombro-ase-lab-17-hw1.herokuapp.com).

### Implementation choices
I decided to treat the two classes provided by the teachers (`doodle.java` and `vote.java`) as an “external library”, without changing their source code. Through testing on a “basic” version of my implementation and on the https://toydoodle.herokuapp.com app, I noticed some incorrect behavior; here’s a list of them, together with my proposed fixes.

1. When performing `PUT /doodles/` with a JSON body request that contains only the `title` field, the app throws a `NullPointerException` instead of ignoring the request gracefully.  This behavior is due to the fact that the constructors for the Doodle class iterate on the list of options in order to build the list of votes. The `Doodle(Doodle doodle)` constructor copies the list of options from `doodle` without testing for `null`, so in the abovementioned scenario it tries to iterate on a `null` object (`doodle.java`, line 28).
	* **Solution**: Check that the Spring-generated `Doodle` object has both the `title` and `options` fields non-`null`.
1. The `PUT /doodles/{id}/vote` operation can modify an already-existing vote. (It shouldn’t.)
	* **Solution**: Check that the name indicated in the JSON body request hasn’t voted yet.
1. When performing `PUT /doodles/{id}/vote` with a JSON body request that doesn’t contain the `name` field, the app inserts a vote anyway (with `name = null`); the `addVote(Vote vote)` method doesn’t check whether `vote.getName()` returns `null` (`doodle.java`, line 49).
	* **Solution**: Check that the `name` field is present in the JSON body request.
1. The `POST /doodles/{id}/vote/{name}` operation can insert a new vote. (It shouldn’t.)
	* **Solution**: Check that the name indicated in the JSON body request has already voted.
1. A “malicious” user can update another user’s vote by means of an incorrect JSON body request when performing the `POST /doodles/{id}/vote/{name}` operation.
Example: user “john” modifies the vote of another user “jack” by sending the JSON request `{ 'name': 'jack', 'option' : '…' }` in a `POST /doodles/{id}/vote/john` operation.
	* **Solution**: Check that both the JSON's and Vote's `name` fields coincide.

Of course, other good programming practices (ex. testing for `null` before invoking an object's method) have been adopted.

