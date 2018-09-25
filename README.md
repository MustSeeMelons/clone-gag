CloneGag, hehe.
===

A Java clone of 9Gag using:
- Spring 4
- Spting Security 4
- Hibernate 5
- PostgreSQL

This is more of a back-end exercise, so the client side is Bootstrap in jsp's with embedded js, not so pretty, but works good enough.

The REST endpoint never tell you what went wrong, must look at the logs for that.

What does it support?
- user registration/login/logout
- new post addition
- voting on posts
- commenting on posts
- replying to comments
- voting on comments
- basic fresh page
- user upvote page
- user post page

Prerequisites:
-
- Maven
- Java 8+
- Docker

How to use:
-
- mvn clean install -P{profile}
  - where profile is docker-local or docker-dev 
- docker compose up
- goto: http://{host}:8080/cloneGag/post
  - where host is localhost (docker-local) or what is in the other profile

Next up?
-
- Tests?
- User profiles?
- User activity feed?
- Notifications?
- GIF's?
- Post deletion?
