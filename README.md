# CS6310-A682-SW
# Steps to Run the App
-  Browse to the Parent folder where docker-compose file is there
-  Type in docker-compose up --build
-  This will build and start the jar and Mysql Structure


#Once App starts 
    docker ps 
This will list all the running docker containers. Select the id for mysql

    docker exec -it 171c7246132c /bin/bash


#if MySql Fails to start

docker system prune --volumes