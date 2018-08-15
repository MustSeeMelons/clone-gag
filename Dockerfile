FROM tomcat:8.0
MAINTAINER strautins.janis@gmail.com

ADD target/cloneGag.war /usr/local/tomcat/webapps/
# So that we can access manager
COPY tomcat-users.xml /usr/local/tomcat/conf/

CMD ["catalina.sh", "run"]