FROM 192.168.1.252/qyzh-v1/jdk1.8
RUN mkdir -p /tmp
ADD earthworm-ipsp-foundation-app/target/earthworm-ipsp-foundation-app.jar /tmp
EXPOSE 8089
CMD ["java","-jar","/tmp/earthworm-ipsp-foundation-app.jar"]




