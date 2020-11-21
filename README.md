# CS6310-A6-82 Group Project Implementation.

# Prerequisites:
- A computer running Docker 
- Internet connectivity. 
- The ability to run commands at a command prompt.
- Firefox web browser.
Broadly speaking, a system and user capable of handling the work done for Assignment 4 will be able to deploy our application submission for Assignment 6.

# Usage Instructions
To deploy and use the system, please follow these steps.

## Step by Step Installation
1. Unzip the provided source code into a new folder.
1. Open a command prompt and navigate to the folder unzipped in the previous step.
1. Execute the following command at the prompt:
        
        docker-compose up --build

1. Wait for the system to come up. The following steps will happen, and a scrolling log will show on the terminal window:
    1. The build container will be created with Alpine Linux and JDK 15. 
    1. The source code will be copied to this build container and compiled to produce JAR files.
    1. A web application container will be created with Alpine Linux and JRE 15 with the Hotspot VM.
    1. The JAR files compiled by the build container will be copied via the docker COPY command to the web application container.
    1. A database container will be created with Alpine Linux and MySQL.
    1. The database container will be run, exposing the default MySQL port of :3306.
    1. The web application container will be run, exposing port :8080 for HTTP connections.

## Using the StreamingWars Application
1. Start the Firefox browser on your computer. 
1. Browse to http://localhost:8080 using Firefox.

## Shutdown and uninstall
1. Logout from the application and close the browser.
1. Press Ctrl-C in the terminal window to terminate the docker containers.
1. Run the following command to delete the containers recently created:
    docker system prune --volumes
1. Delete the folder with the application and the zip file.
