# ps-climbers-server
Server application for software system for supporting climbing competition.

## How to run
1. Execute "mvn clean install" in [climber-common project](https://github.com/dejanostojic/ps-climbers-common)
2. Execute "mvn clean package" in root of the repository
3. Execute "java -jar target/climbers-boot-1.0-SNAPSHOT-jar-with-dependencies.jar" from the root of the repository

### Change db settings
In order to change db setting use following options:
* Change default configuration in source code: src/main/resources/hikariConfig.properties and execute steps 1,2,3
* Executre "java **-DhikariConfig=/home/user/asistent/climbersHikariConfig.properties** -jar climbers-boot/target/climbers-boot-1.0-SNAPSHOT-jar-with-dependencies.jar" from the root of the repository providing your own hikariConfig

##  Documentation
Documentation for the project can be found [here](https://fonbgacrs-my.sharepoint.com/:w:/g/personal/do20090246_student_fon_bg_ac_rs/EYqGcUJZDwpLm5SBVUwCRJwBro4hG2Oj4lpAxwkJQUf4hw?e=laQtoR)

   
