# Toy Robot Code Challenge Project

This project is a solution to the Toy Robot Code Challenge 2020 written by Stephen Katulka as a response to his technical interview.

## Commands
This application reads commands line-by-line from Standard Input to control the toy robot in the following formats:

 - REPORT
   - The `REPORT` command returns the current position and facing of the robot.
   - The format is `X,Y,DIRECTION`
   - If the robot has not yet been placed on the table, `X` and `Y` will be zero, and `DIRECTION` will be `null` (as `0,0,null`) 
 - PLACE
   - The `PLACE` command puts the Toy Robot somewhere on the 5x5 table.
   - The format is `PLACE [X], [Y], [DIRECTION]` 
     - `X` and `Y` must both be valid coordinates from 0-4
     - `DIRECTION` may be one of `NORTH`,`SOUTH`,`EAST`, or `WEST`
     - White space may be omitted around the commas but at least one space is required after the word `PLACE`
 - MOVE
   - The `MOVE` command attempts to move the robot one space in the direction it currently faces.
   - If the robot has not yet been placed on the table, this command will be ignored.
   - If the move would cause the robot to fall off the table, this command will be ignored.
 - LEFT and RIGHT
   - The `LEFT` and `RIGHT` commands turn the robot in the respective direction, changing the direction it faces by 90 degrees
   - If the robot has not yet been placed on the table, this command will be ignored.

No other commands will be accepted. They must be entered one command per line and are case insensitive. Any incorrectly formatted commands will be ignored.

## Setup and Running the Application
This application is designed to be built with Maven and running the Maven build produces an executable jar file. Once the jar file is built it may be run as an executable jar in your installed Java VM.

- To build the file, make sure you have Maven 3+ installed on your system. 
  - Open a command line, shell, or terminal application 
  - Go to the main directory of this project
  - Enter the command `mvn clean install`

- To run the application:
  - Open a command line, shell or terminal application
  - Go to the main directory of this project
  - Navigate into the `/target/` directory
  - Find the `toy-robot-1.0.jar` file
  - Enter the command `java -jar toy-robot-1.0.jar`
  - Type the commands to the application at the cursor, hitting `Enter` after each one
  - When finished, close the shell application or enter the command `EXIT`