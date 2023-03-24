# todo_webapp
Very basic todo list app, it's my first Java web application using Spring Boot,
Hibernate/ JPA and MySQL DB (H2 DB optional), Spring Security. I also used Bootstrap, JQuery and
Spring form tag library for the JSP files.

Thank you to 28minutes.com!

MySQL Database using Docker:

docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:8-oracle
