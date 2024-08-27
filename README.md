# TaskAppSpringBoot
It's  my project 

Task Management Web Application
Cindy Zacarias

Overview
This Spring Boot web application allows users to manage tasks with features such as creating, editing, deleting, and viewing tasks. The application uses Thymeleaf for templating and provides a simple, user-friendly interface for task management.

Technologies Used
•	Spring Boot: Backend framework for Java.
•	Thymeleaf: Templating engine for rendering HTML views.
•	JPA/Hibernate: For object-relational mapping and database interaction.
•	MySQL: (or any other relational database) for persistent data storage.
•	Bootstrap: For styling and responsive design.
•	JavaScript (jQuery): For dynamic front-end functionality.
•	Docker: For containerizing the application and ensuring consistent environments across different stages of development and deployment.

Application Structure
The application follows a typical MVC (Model-View-Controller) structure:
1.	Model (Entity): 
o	Task: Represents the task entity with fields such as id, title, description, date, dateTask, and completed.
2.	Repository: 
o	TaskRepository: Extends JpaRepository and provides CRUD operations for the Task entity.
3.	Controller: 
o	TaskController: Handles HTTP requests related to tasks, such as creating, editing, deleting, and viewing tasks.
4.	Views (Thymeleaf Templates): 
o	tasks.html: Displays the list of tasks.
o	task_form.html: Provides the form for creating and editing tasks.



Entity Class (Task)
Fields:
•	id: Unique identifier for the task.
•	title: Title of the task (max 128 characters).
•	description: Description of the task (max 256 characters).
•	date: Date when the task was created.
•	dateTask: Scheduled date for the task.
•	completed: Boolean flag indicating if the task is completed.

Controller Class (TaskController)
Key Methods:
•	getAll(): Retrieves and displays all tasks, optionally filtered by a keyword.
•	addTask(): Displays the form for creating a new task.
•	saveTask(): Saves a new or updated task.
•	editTask(): Displays the form for editing an existing task.
•	deleteTask(): Deletes a task by its ID.
•	updateTaskCompletedStatus(): Updates the completion status of a task.

Docker Configuration
The project includes a Docker setup to containerize the application. This ensures a consistent environment for running the application across different stages, such as development, testing, and production.

Dockerfile
FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]





Running the Project
To run the project, ensure that you have Java 8+ and Maven installed. After cloning the repository, use the following command:
mvn spring-boot:run

The application will be available at http://localhost:8080/tasks.
Alternatively, if you want to run the project using Docker:
Build the Docker image: docker build -t task-management-app .
Run the Docker container: docker run -p 8080:8080 task-management-app

The application will be accessible at http://localhost:8080/tasks.

Dependencies and Configuration
•	Spring Boot Starter Web: For creating web applications.
•	Spring Boot Starter Thymeleaf: For using Thymeleaf as the template engine.
•	Spring Boot Starter Data JPA: For working with JPA/Hibernate.
•	H2 Database: In-memory database for storing tasks (default configuration).

Conclusion
This project is a simple task management system built with Spring Boot. It can be expanded to include user authentication, external database integration, and more advanced filtering and sorting features. The Docker integration provides an easy way to deploy the application in different environments.
