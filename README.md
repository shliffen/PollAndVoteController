# PollAndVoteController
Sample of Spring REST Application which can manipulate Polls and collect Votes and results

**How to run?**
You can run it from the dist folder - by running first on make.sh/make.bat (for building a project using Maven and creating a "uber"-jar),
and then run with QuickPoll.bat / QuickPoll.sh:

1. After launch, import to the Postman the "Quick-Poll Application.postman_collection" (located in the docs folder)

2. Then we can send requests at will

3. In every case, I have prepared three ready-made polls in the NewPolls.doc - from where they can be copied and pasted.

4. When there was a desire to terminate the client's work - in the launch window, press "CTRL + C" to terminate the program

5. To open the documentation in a browser - please open "http://localhost:8080/swagger-ui.html"

6. Three user roles are implemented: ADMIN (login-admin, password-admin), VISITOR (login-user, password-visitor), STAFF (login-worker, password-staff) - to demonstrate possible user roles.

7. In order not to remember passwords and logins once again, in the requests "CreateVote", "Create Poll" and "GetVoteComputeResult" in the authorization tab filled in fields are prepared for different roles.

**Can I see how it works on the video?**
Link to the video with the "presentation" (RU): https://www.youtube.com/watch?v=D4Ho_rc_Ndw 


**Version and additional Software**
Jdk version 1.8, tested on Java 14, requires Postman as front-end

**Technologies used**
Java + Maven + Spring Boot MVC + REST + H2 (as Database) + Postman (as FrontEnd) + JUnit+Mockito

**What realized inside**
* Sample of Spring REST application which can manipulate (create, update, delete) Polls, collect and calculate Votes inside Polls
* 3 main roles for users with different permissions ADMIN (can do anything with Polls and Votes), STAFF(can see just a results), VISITOR (just voting). Role distribution
* Versioning. There are 3 versions of controlles and sure, endpoints: first - simple controllers that implement functionality; second - Pagination is additionally implemented; third - implemented OAuth2 and permissions
* Documentation realized by using Swagger
* H2 used as database
* Application receives data on port 8080
* The tests are implemented using Mockito and JUnit
