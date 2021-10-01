## Online Bookstore API - Spring Boot

This is a sample backend Rest API application.This project demonstrates where the user can perform CRUD and checkout operations.

### Technologies

* Java 1.8
* Spring
* Spring Boot
* H2 Database (in-memory)
* Maven Build Tool [Download][Maven]

:exclamation: Make sure Java and Maven installed and set in the system path before proceeding to build & deployments.

### My Assumption

* Apply discount against book type only if there is any promotional code value in checkout API.

### Source Code Download

If you have a GIT in your system, you can clone the application using below command else use the direct download link. 

> git clone https://github.com/mohancse1707/online-bookstore-service.git

[Direct Download][Direct Download]

## Build & Deployment

Let's get into build and deployment steps. 

Open the Terminal then Navigate to ```online-bookstore-service``` and execute below command 

> mvn clean install

> mvn spring-boot:run

### Docker Build

* Docker file created in root directory with simple build scripts.

### Database Access

Hit this Datasource URL in a browser: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  

To connect data source use below credentials   
JDBC URL  : `jdbc:h2:mem:testdb`  
User Name : `sa`  
Password  :   


### Discounts Setup

|Book Type          |Discounts in %                    |
|-------------------|:---------------------------------|
|FICTION             |``15``|    
|COMIC               |``0`` |   
|ACTION               |``10`` |   
|THRILLER               |``10`` |   
|TECHNOLOGY               |``0`` |   
|DRAMA               |``5`` |   
|POETRY               |``20`` |  
|MEDIA               |``25`` |  
|OTHERS               |``30`` |  

### Sample JSON to add book

```json
{
    "name": "The Lord of the Rings",
    "description": "One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them",
    "author": " J.R.R. Tolkien",
    "type": "FICTION",
    "price": 1000,
    "isbn": "98-9874-2345-134"
}
```

```json
{
    "name": "Crime and Punishment",
    "description": "Raskolnikov, a destitute and desperate former student, wanders through the slums of St Petersburg and commits a random murder without remorse or regret.",
    "author": "Fyodor Dostoevsky, David McDuff ",
    "type": "FICTION",
    "price": 2000,
    "isbn": "19-5435-3261-000"
}
```

```json
{
    "name": "Ms. Marvel",
    "description": "Marvel Comics presents the new Ms. Marvel, the groundbreaking heroine that has become an international sensation",
    "author": "G. Willow Wilson",
    "type": "COMIC",
    "price": 6000,
    "isbn": "19-5435-3261-000"
}
```

```json
{
    "name": "Batman: The Dark Knight Returns",
    "description": "This masterpiece of modern comics storytelling brings to vivid life a dark world and an even darker man.",
    "author": "Frank Miller",
    "type": "COMIC",
    "price": 3200,
    "isbn": "19-212-875-09876"
}
```

```json
{
    "name": "Treasure Island",
    "description": "Treasure Island",
    "author": "Robert Louis Stevenson",
    "type": "FICTION",
    "price": 1500,
    "isbn": "19-5435-3261-000"
}
```
### Sample JSON for update book API

```json
{
    "id": 1,
    "name": "The Lord of the Rings UPDATE",
    "description": "UPDATE DES",
    "author": " J.R.R. Tolkien",
    "type": "FICTION",
    "price": 23000,
    "isbn": "98-9874-2345-134"
}
```
### Application Testing in Postman

* Exported All endpoints from postman as JSON format located in the root directory of this application, simply take the file and import in your postman.

    - `Online Book Store.postman_collection.json`  

### Sample Postman Screen Shot

Add Book

![Server2](https://mohankumarrathinam.com//images/blog/spring/online-book/add.png)

Update Book

![Server2](https://mohankumarrathinam.com//images/blog/spring/online-book/update.png)

Get All Books

![Server2](https://mohankumarrathinam.com//images/blog/spring/online-book/all.png)

Delete Book

![Server2](https://mohankumarrathinam.com//images/blog/spring/online-book/delete.png)

Checkout Book / Books

![Server2](https://mohankumarrathinam.com//images/blog/spring/online-book/checkout.png)

[Direct Download]: https://github.com/mohancse1707/online-bookstore-service/archive/main.zip
[Maven]:https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.6.3/apache-maven-3.6.3-bin.zip
