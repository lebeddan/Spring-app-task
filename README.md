## Spring-app-task

App is using a PostgreSQL database and is required to install PostgreSQL.
App using three tables: Candidates that contain info about candidate(name, email), Technologies that contain name of technology and CandidatesTechnologies has 
composed key of two previous tables, skill level and note.

# Usage

The app has been tested on Postman(http://localhost:8080/) and here is some examples:

- GET all candidates -> http://localhost:8080/candidates
- GET specified candidate -> http://localhost:8080/candidates/candidateId
- ADD candidate -> http://localhost:8080/candidate and body {"name": "Daniil", "email": "dan@example.com"}
- GET all technologies ->  http://localhost:8080/technologies
- GET specified technology -> http://localhost:8080/technologies/technologyId
- ADD technology -> http://localhost:8080/technologies and body {"name": "Java"}
- ADD technology to candidate -> http://localhost:8080/candidates/candidateId/technologies and body {"technologyId": 1, "skillLevel": 5, 
                                                                                                      "note" : "good"}
- CHANGE tecnhology of candidate -> http://localhost:8080/candidates/candidateId/technologies/technologyId and your changes
