# Course Search Project - [LearnLoop]

## 1. Introduction

### 1.1. Context
Gamification is one of the most effective ways to increase interest in any subject. It introduces students to a new way of learning that makes every step of the lesson enjoyable and exciting. It's a teaching method that works well regardless of a student's age because we all enjoy that rush of dopamine when we play video games.

Game-based learning is more than just making games for students to play; it is also about designing learning activities that can gradually introduce concepts and guide users toward an end goal. Traditional games can include mechanics such as competition, points, incentives, and feedback loops. These ideas are becoming increasingly popular in higher education and libraries as a way to engage students in learning.

### 1.2. Purpose of the Project
Learning a new subject can be overwhelming at first. Gamification of learning helps motivate students of all ages by transforming abstract concepts into something more concrete and attainable. As students begin the lesson, they know what is expected of them and which tools to use, encouraging them to continue with the lesson.

Our aim is to develop a system that enables students to learn through gamification. Starting from this idea, our team envisioned a platform for everyone who wants to learn, practice, and surpass themselves—a social learning platform named **LearnLoop**.

### 1.3. Problem
Traditional teaching methods are becoming less effective, as students often find them boring. This leads to a decrease in interest in school and an increase in dropout rates. One effective way to counter this is by fostering encouragement and engagement in the learning process. Encouragement can unlock untapped potential in learners of all ages, especially those who struggle.

With many students relying on eLearning, it’s more important than ever to boost learner motivation through stimulation and engagement.

### 1.4. Our Solution - LearnLoop
The goal of our social learning platform, **LearnLoop**, is to provide a space for individuals to learn and collaborate with others. It includes features such as online courses, interactive learning tools, and networking opportunities to foster community and knowledge sharing. The platform aims to facilitate the process of gaining new skills and knowledge, contributing to personal and professional development.

#### Key Features:

1. **Advanced Search Capability**
    - Users can quickly search for courses, topics, and resources using a fast and intuitive search engine powered by **Elasticsearch**.
    - The search function will include filters for course level/difficulty, topics, and ratings to help users find relevant content efficiently.
    - Elasticsearch will enable full-text search, improving the user experience by providing instant, relevant search results.

2. **Progress Tracking**
    - Users will have a dashboard that displays their learning progress across various courses.
    - The system will track completed lessons, in-progress courses, and upcoming tasks or quizzes, providing insights into the user's learning journey.

### 1.5. Elasticsearch
To enhance the user experience and improve the platform's search capabilities, we plan to integrate **Elasticsearch** as our search engine.

- **Fast and Relevant Search**: Users can quickly search for courses, discussion threads, or specific topics using Elasticsearch's full-text search capabilities.
- **Personalized Recommendations**: By analyzing user activity and search patterns, Elasticsearch can help provide personalized content suggestions, enhancing user engagement.
- **Scalability**: Elasticsearch is highly scalable, making it suitable for handling large volumes of data as the platform grows.
- **Analytics**: Elasticsearch can be used to analyze user behavior on the platform, such as popular search queries and topics of interest, allowing us to refine and improve the platform continuously.

This integration will ensure that users can easily find the content they need, making their learning experience efficient.

### 1.6. Swagger
- **Documentation**: API documentation and testing interface

---

## 2. REST API Endpoints

The project includes a well-defined REST API for managing courses and comments, with Swagger documentation for seamless integration. Below are the endpoints provided by the API:

### 2.1. **Courses Endpoints**

#### POST `/api/courses`
- **Summary**: Add a new course.
- **Description**: Creates and indexes a new course in Elasticsearch.
- **Request Body**:
  ```json
  {
      "id": "string",
      "name": "string",
      "description": "string",
      "content": "string"
  }
  ```
- **Response**: 201 CREATED: The course has been successfully added.

#### GET  `/api/courses/{id}`
- **Summary**:  Get a course by ID.
- **Description**: Retrieves details of a course by its unique ID.
- **Response**: 200 OK: Returns course details; 404 NOT FOUND: Course not found.

#### POST `/api/courses`
- **Summary**: Add a new course.
- **Description**: Creates and indexes a new course in Elasticsearch.
- **Request Body**: JSON object with course details.
- **Response**:
   - 201 CREATED: The course has been successfully added.

#### GET `/api/courses/{id}`
- **Summary**: Get a course by ID.
- **Description**: Retrieves details of a course by its unique ID.
- **Response**:
   - 200 OK: Returns course details.
   - 404 NOT FOUND: Course not found.

#### GET `/api/courses/search`
- **Summary**: Search courses.
- **Description**: Searches for courses containing a keyword in their name, description, or content.
- **Query Parameter**:
   - `keyword` (string): The keyword to search for.
- **Response**:
   - 200 OK: List of matching courses.

#### DELETE `/api/courses/{id}`
- **Summary**: Delete a course by ID.
- **Description**: Removes a course by its unique ID.
- **Response**:
   - 204 NO CONTENT: Course deleted.
   - 404 NOT FOUND: Course not found.

---

### 2.2. **Comments Endpoints**

#### POST `/api/comments`
- **Summary**: Add a new comment.
- **Description**: Creates and indexes a comment for a course in Elasticsearch.
- **Request Body**: JSON object with comment details.
- **Response**:
   - 201 CREATED: The comment has been successfully added.

#### GET `/api/comments/course/{courseId}`
- **Summary**: Get comments for a course.
- **Description**: Retrieves all comments associated with a specific course ID.
- **Response**:
   - 200 OK: List of comments for the course.

#### DELETE `/api/comments/{id}`
- **Summary**: Delete a comment by ID.
- **Description**: Deletes a specific comment using its ID.
- **Response**:
   - 204 NO CONTENT: Comment deleted.
   - 404 NOT FOUND: Comment not found.

---

### 2.3. **Advanced Elasticsearch Endpoints**

#### GET `/api/courses/advanced-search`
- **Summary**: Advanced course search.
- **Description**: Searches courses with additional filters such as:
   - `level` (string): e.g., beginner, intermediate, advanced.
   - `category` (string): e.g., programming, design.
   - `rating` (float): Minimum course rating.
- **Response**:
   - 200 OK: List of courses matching the filters.

#### GET `/api/comments/search`
- **Summary**: Search comments.
- **Description**: Searches comments based on keywords or filters like:
   - `courseId` (string): Filter comments by course ID.
   - `author` (string): Filter by comment author.
- **Response**:
   - 200 OK: List of matching comments.

