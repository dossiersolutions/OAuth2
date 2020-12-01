# OAuth 2 / JWT

## backend-app + frontend-app
Runs on spring boot framework. Contains few controllers for adding guests in th eguest
list. User must be registered if they would like to add guest. It runs on postgresql DB.
During login user is Authenticated, and will receive access token. With that token
each server request is Authorized, it's added to the server request header.

## backend-app
1) application.properties file contains all data needed for postgresql database creation.
So, it's necessary to create database with parameters username/password locally with
goal to start the app.

2) Flyway script is located in folder db.migration which will create new table my_users
and add one user. Other script can be added as well for inserting you as a user.

3) Run the main method in DemoJpaApplication.java


## frontend-app
React app with components, action creators and redux.

1) Run npm install to install all dependencies

2) Run npm start to start react app locally

3) Login using username/password which is previously inserted in database