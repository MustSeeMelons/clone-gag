FROM tomcat:9.0
MAINTAINER strautins.janis@gmail.com

COPY target/cloneGag.war /usr/local/tomcat/webapps/

# So that we can access manager
COPY tomcat-users.xml /usr/local/tomcat/conf/

CMD ["catalina.sh", "run"]