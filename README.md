# Interview Template

This is an interview template project. It's designed to give you a starting point for the technical interview. It's a
fully working application which you will be able to make requests to. The interviewers will provide you with a custom
scenario that they wish for you to implement using some existing functionality already provided for.

## Instructions

1. Clone the project in your preferred IDE.
2. Create a new branch in the format of `<yyyy-mm-dd>_<your name>` e.g. `2021-02-02_rick_hunter`
3. Ensure that you have received the scenario that the interviewers wish to see you implement
4. Share your screen so that the interviewers can see you working.
5. Solve the scenario provided in a manner that you would usually do in a work environment, and push your branch 
to GitHub when done.

## Startup
The following subheadings describe how to start the application for each 
applicable area. Server-side developers need only focus on the Server, while
client-side developers need only focus on Client. A full-stack developer will
be dealing with both.

### Server
To start up the server navigate to the `server` directory and do the following:

* Run `./mvnw spring-boot:run` (There's a `mvnw.cmd` equivalent command for Windows machines)

If you wish to avail of debugging, navigate to the `InterviewApplication.java` file
and run it from there.

### Client

To start the client. Navigate to the `client` directory and do the following:
* Run `npm install`
* Run `npm run start`

## Database

To access the database you will need to navigate to `http://localhost:8080/h2`. Connection details are as follows:

|Name|Value|
|----|---|
|Driver Class| org.h2.Driver|
|JDBC Url|jdbc:h2:~/interview|
|Username|sa|
|Password||

