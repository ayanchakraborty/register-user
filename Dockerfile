FROM java:8
VOLUME /tmp
EXPOSE 8080
COPY ./registration-web/target/user-registration-web.jar user-registration-web.jar
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.2.1/wait /wait
RUN chmod +x /wait
CMD /wait && java -jar user-registration-web.jar