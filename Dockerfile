FROM openjdk:11
ADD target/abn-amro-test.jar abn-amro-test.jar 
ENTRYPOINT [ "java", "-jar", "abn-amro-test.jar" ]
EXPOSE 9095