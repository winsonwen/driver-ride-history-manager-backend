#  Database Connection
1. going to src/main/resources/application.yml 
2. Changing the datasource settings to connect your database, and the table will be generated automatically 
       username: root
       password: root
       url: jdbc:mysql://localhost:3306/test
# Run back-end application:
    run src/main/java/driver/ride/manager/DriverRideHistoryManagerBackendApplication.java

# Run front-end application
    ng serve

# feature
    Login
    Registration  
    Logout
    Create New Driver Ride History Record
    Delete record
    Sort records by Driver Name/Departing Date/Returning Date/Departing Miles/Returning Miles
