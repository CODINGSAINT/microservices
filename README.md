# Welcome to Micoservices by Coding Saint!

The new buzzword in industry is  **microservices**. This repository is the source code for the step by step tutorial at online course [Spring REST API and Microserivces](https://www.udemy.com/course/spring-boot-and-rest-api/?couponCode=SPRING50) . You can follow above link and can get the step by step video reference at 90% discount. Alternatively you can use coupon code **SPRING50** 
[https://www.udemy.com/course/spring-boot-and-rest-api/](https://www.udemy.com/course/spring-boot-and-rest-api/)



# Table of Content 

 -   Introduction
 - Advantages and Drawbacks of Microservices
 - User Service
 - ToDo Service
 - Feign Declarative REST Client
 -  Service Discovery in Microservices
 -  Use of Config Server
 -  Zuul API gateway
 -  Distributed Tracing
 -  Zipkins UI to view traces
 - Hystrix Fault tolerance

## Introduction

From inception of software design what needed to be improved which brought this new concept . We need to understand a work “**Monolith**”

![ Monolith ](https://raw.github.com/CODINGSAINT/microservices/master/images/monolith.png)
**Monolith** refers a kind of software which keeps all the component required by it within itself. Say we have to create a E Commerce we need to have User authentication, product services, cart management, payment integration ,recommendation service etc. A monolith is one application which has all of them embedded to it.
### Microservices

Microservices as certainly a buzz word all around. We have been in an era where distributed and scaleable systems have need of the hour. As the business grows the number the pressure on systems also increases. In order to keep up with increased load we scale our application. In this process we end up scaling entire monolith. In monolith we have all modules added to single application. While our load will be for certain modules ,we still end up scaling entire application.
#### Scaling Monolith
![enter image description here](https://raw.github.com/CODINGSAINT/microservices/master/images/scalingMonolith.png)

In above example , if we have a E commerce service as monolith, it is expected to have more hits at product and search modules rather cart and delivery tracking modules but when we scale up we will end up scaling all of the modules. This will increase memory and heap size and will require unnecessary resources.  
Microservices solves exact same problem. In microservices we have well defined modules as a separate service all of them talking independently via HTTP/MQ or any protocol. Now once we notice increase in product services and search module, we will scale only that component.
#### Microservices scaling
![enter image description here](https://raw.github.com/CODINGSAINT/microservices/master/images/Microservice_benefits.png)
## Advantages and Drawbacks of Microservice
### Advantages of Microservices

1.  Improved fault tolerance
2.  Easy to understand
3.  No vendor or technology lock in
4.  Easy to add new modules
##### Improved fault tolerance

Microservices helps you to be highly available. Since once we adopt microservices , we move towards distributed systems , In case of a monolith if a the system or network is down typically entire system is down. In Monolith as we know services are tightly coupled , error at one service will impact other one too. In Microservices , we design in a way that even if one component is down , entire service will not be impacted. We can use fault tolerance and write code for fallbacks. For example if we have fallback for some critical modules , in case of the service being down fallbacks will be invoked , say in a e-commerce microservice recommendation-service is down, we can have fallbacks with static recommendations. It improves our fault tolerance.

##### Easy to understand

Each module is granular. It has it’s own domain, All it has to do is to take care of itself. Obviously it is easy to understand a smaller piece than entire monolith system4

##### No vendor or technology lock in

The is no vendor lock in , Each module can have it’s own database, a different programming language and still communicate via HTTP REST protocol , or messaging queues etc.

##### Easy to add new modules

Once a new feature is required we don’t have to write in same monolith with a fear of breaking changes. We can easily spin up a new microservices and it doesn’t have to break existing system.

All your files and folders are presented as a tree in the file explorer. You can switch from one to another by clicking a file in the tree.
### Drawbacks of Microservices

1.  Distributed systems are complex to understand
2.  Multiple database make it complex to maintain transactional consistency.
3.  Tough to test
4.  Could be tough to deploy

##### Distributed systems are complex to understand

Distributed system are complex. Tough a individual module could be very easy to understand but how it fit in entire big microservice landscape ,interacting with each other could be tough to understand.

##### Multiple database make it complex to maintain transactional consistency.

This is one of the most talked about problem of a microservice architecture . In a monolith , it is one database where all modules related tables are there . It is easy to maintain transactional consistency . For example , if one the operation fails we can easily rollback entire transaction. In microservices , all services have different databases. Many a times one operation requires interaction with multiple services. To guarantee a transaction in all databases over different servers it not possible. To avoid it we use eventual consistency but still transaction consistency is complex.We need to design fail over scenarios well in advance.

##### Tough to test

One single database , one application , single source to to test is obviously easy than to test one operation where multiple miroservices with multiple database are interacting.

##### Could be tough to deploy

So many microservices , and over that one depending on another , with so many different technology stack is challenging for sure.

Now after reading above , if you are designing microservices you must ask is it required. Do I have to deal with so many transaction and incming requests that we need to decompose monolith. Will I have infrastructure ready at will to scale microservices or new one. If your answer is yes, please go ahead but always remember ***MICROSERVICES are not silver bullet.***

## User Service 
This microservice will help us to create user and call another microservice i.e. TODO microservice to get all the todos assigned to user and return user along with its todos.
 - A simple microservice . 
 - Manages users
	 - Create /Update/Delete User 
    
User service in this tutorial runs on port **8082**.
### Important APIs of User Service
 
NAME| URL |  BODY| METHOD TYPE| RESPONSE
|--|--|--|--|--|--|
CREATE USER | http://localhost:8082/v1/user | ``{"firstName":"Ray","lastName":"mcfallen",	"userName":"ray","email":"ray@codingsaint.com"} ``  |POST |``{'id':2,"firstName":"Ray","lastName":"mcfallen",	"userName":"ray","email":"ray@codingsaint.com"} ``
GET USER WITH TASK | http://localhost:8082/v1/user/2/tasks |  |GET |``{"firstName":"Ray","lastName":"mcfallen",	"userName":"ray","email":"ray@codingsaint.com"} ``


## TODO Service
ToDo Service will be used to create task for users, It create task and assign it to particular user based on user id.

### Important APIs of User Service
 
NAME| URL |  BODY| METHOD TYPE| RESPONSE
|--|--|--|--|--|--|
CREATE TASK | http://localhost:8083/task | `` { "name":"Create Micoservice Couse : Config Server", "description":"Create Video tutorial task service",   "isDone":false,   "targetDate":"2019-08-05",   "userId":2,   "categories":[      {"name":"Microservice Tutorial"}]} ``  |POST |``{ "id":2,"name":"Create Micoservice Couse : Config Server", "description":"Create Video tutorial task service",   "isDone":false,   "targetDate":"2019-08-05",   "userId":2,   "categories":[      {"name":"Microservice Tutorial"}]} ``
GET Tasks of a User | http://localhost:8083/user/2/tasks |  |GET |``{ "id":2,"name":"Create Micoservice Couse : Config Server", "description":"Create Video tutorial task service",   "isDone":false,   "targetDate":"2019-08-05",   "userId":2,   "categories":[      {"name":"Microservice Tutorial"}]} ``

## Feign Declarative REST Client

Feign Declarative REST client helps us to remove boilerplate code of REST API.
We will use it in **User Service**  to remove rest call to task service where we had been using rest template.
In order to do that 

 1. add openfeign at pom.xml
```
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
 2. Add annotation at User application main class
 ```
 @EnableFeignClients
 ```
3. Create a Service class with annotation @FeignClient and method for Feign as below
```
@Service
@FeignClient(name="task-service" ,url="http://localhost:8083/")
public interface TaskService {
@RequestMapping("user/{id}/tasks")
ResponseEntity<List<Task> > userTasks(@PathVariable ("id") Long userId);
}
```
 4. Remove Rest call at Controller and user TaskService method
 ```
ResponseEntity<List<Task>> tasks =taskService.userTasks(id);
 ```
 
