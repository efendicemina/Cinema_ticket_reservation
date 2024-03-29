# Movie ticket reservation
A JavaFX application, created with Maven, for reserving movie tickets. It is a fictional movie theater The Silver Screen.

## Features
- Login and registration for users
- Admin panel for managing movies
- Admin panel for easy access to all information related to movies, reservations and users
- Ability to book tickets as a user
- Seat selection and confirmation upon booking
- Connection to a database (included dump file in repository)
- CLI for admin users

## Getting Started 
- Download the repository
- Ensure that JDK 19 is installed on your system
- Ensure that JavaFX SDK 19 is installed on your system
- Install Maven if it is not already installed

## Build and run GUI
- Navigate to project and execute command mvn clean install
- Run project with command mvn clean javafx:run

## Build and run CLI
- Navigate to project and execute command mvn clean install -P cli-app
- Run project with command java -jar target/projekat1-cli-jar-with-dependencies.jar

## Prerequisites
- JDK 19
- Maven
- JavaFX SDK 19
- MySQL (for running the included database)

## Screenshots
### Login window
![Alt Text](./src/main/resources/screenshots/login.png)

### Registration window
![Alt Text](./src/main/resources/screenshots/registration.png)

### Admin panel
![Alt Text](./src/main/resources/screenshots/admin.png)

### Add movie window
![Alt Text](./src/main/resources/screenshots/add.png)

### Update movie window
![Alt Text](./src/main/resources/screenshots/update.png)

### Delete movie window
![Alt Text](./src/main/resources/screenshots/delete.png)

### About admin panel window
![Alt Text](./src/main/resources/screenshots/about_admin.png)

### User panel window
![Alt Text](./src/main/resources/screenshots/user.png)

### Booking ticket window
![Alt Text](./src/main/resources/screenshots/booking.png)

### About The Silver Screen
![Alt Text](./src/main/resources/screenshots/about_user.png)

## Author
- Emina Efendic
