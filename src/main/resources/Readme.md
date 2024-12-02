### Start Elastic search

```bash 
cd elasticsearch-8.9.0/
bin/elasticsearch
```
 Check ES
```bash 
 curl -X GET http://localhost:9200
```

### Start Kibana
```bash 
cd elasticsearch-8.9.0/
bin/elasticsearch
```
```bash 
cd kibana-8.9.0/
bin/kibana
```
Access kibana
```bash 
http://localhost:5601/app/home
```

### Structure indexes

COURSE INDEX
``` bash
PUT /courses
{
"settings": {
"number_of_shards": 1,
"number_of_replicas": 1
},
"mappings": {
"properties": {
"name": {
"type": "text"
},
"description": {
"type": "text"
},
"category": {
"type": "keyword"
},
"level": {
"type": "keyword"
},
"language": {
"type": "keyword"
},
"content": {
"type": "text"
},
"reviews": {
"properties": {
"average_rating": {
"type": "float"
},
"total_reviews": {
"type": "integer"
}
}
},
"professor": {
"type": "keyword"
},
"number_of_students": {
"type": "integer"
}
}
}
}
```
REVIEWS INDEX
``` bash
PUT /course_comments
{
  "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 1
  },
  "mappings": {
    "properties": {
      "course_id": {
        "type": "keyword"
      },
      "user_id": {
        "type": "keyword"
      },
      "comment": {
        "type": "text"
      },
      "timestamp": {
        "type": "date"
      },
      "rating": {
        "type": "float"
      }
    }
  }
}

```

Add course and comment in elastic search 
```bash
PUT /courses/_doc/1
{
  "name": "Java Basics",
  "description": "Learn Java programming from scratch",
  "category": "Programming",
  "level": "Beginner",
  "language": "English",
  "content": "Introduction to Java, OOP concepts, Collections",
  "reviews": {
    "average_rating": 4.5,
    "total_reviews": 120
  },
  "professor": "John Doe",
  "number_of_students": 250
}



PUT /course_comments/_doc/1
{
  "course_id": "1",
  "user_id": "user123",
  "comment": "Great course for beginners!",
  "timestamp": "2024-11-23T12:00:00Z",
  "rating": 5.0
}


```
## SWAGGER

### ACCESS URL

```bash
http://localhost:8080/swagger-ui.html

```

swagger endpoint
 ``` bash
http://localhost:8082/swagger-ui/index.html#/
```

models
```bash
{
"id": "4",
"name": "Introduction to Machine Learning",
"description": "Learn the basics of Machine Learning and its applications.",
"category": "computer science",
"level": "beginner",
"language": "english",
"content": "This course covers supervised and unsupervised learning, basic algorithms, and real-world applications.",
"professor": "Dr. Alex Green",
"numberOfStudents": 250,
"comments": [
{
"id": "comment1",
"courseId": "2",
"userId": "user123",
"comment": "This course is fantastic! It explains everything clearly.",
"timestamp": "2024-12-01T10:15:30",
"rating": 4.8
},
{
"id": "comment2",
"courseId": "2",
"userId": "user456",
"comment": "Good course, but it could include more examples.",
"timestamp": "2024-12-01T12:20:45",
"rating": 4.2
}
]
}

```

