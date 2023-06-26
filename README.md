# basic-spring-app

In the Book and Author classes, you will notice the @Entity annotation. That is because it indicates that an instance 
of this class will be linked to a table in the database. In each class, there is also an @Id and @GenerateValue 
annotations to the id's of the books and authors. That is because the @Id indicates that the id is the primary key
for the entity. The @GenerateValue generates an automatic value to the id column in each table. In the Book class, 
there is also a @ManyToOne annotation over the author. This indicates that the many books can be associated to one 
author. Similarly, in the Author class, you will see that the books are labeled with a @OneToMany annotation, 
indicating that an author can be associated with many book entities, and the cascade operation indicates that the 
CRUD operations should be associates with the book entity. The mappedBy attribute indicates that inverse side of the 
relationship. Because these two classes are instances, they should have an empty constructor as well. 

The Author and Book repositories have to be extended from JpaRepository per instructions and they each take in an 
instance of their corresponding model. The JpaRepository is an extension of the CRUD Repository which have specific to 
API methods. No additional coding is required for this part as it is all that is needed to access those methods. All    
that really needs to be specified are the parameters for which model the repository is for as well as the type of Id,   
in this case being JpaRepository<Book, Long> and JpaRepository<Author, Long>.

In the Author and Book Controllers is where it gets a little tricky. Each class is labeled with a @Controller 
annotation to indicate they are controllers. The @RequestMapping represents the HTTP mapping the controller. The 
@GetMapping annotation is a version of @RequestMapping (instead of @RequestMapping(method = RequestMethod.GET)), which
also represents the HTTP for the indicated method. The @PostMapping is also a version of @RequestMapping
(instead of @RequestMapping(method = RequestMethod.GET)), which represents the HTTP to follow a command method. 

The Strings returned in the Controllers methods are linked to the html pages associated with the methods. For instance,     
when "author-list" is returned, the author-list.html should be showing on the localhost. Because we have specified the      
variables, they can just be called into the table in the book-list.html and the author-list.html. The "th:fields=" are
evidence of the Thymeleaf Dependencies. You will notice different types of inputs. The @{} indicate the action URLs, 
${} indicate objects, *{} binds the field to it's corresponding object, and ${} represents the values of the instances.

How to run: you can just run the main CrudApplication class. Then you open the localhost as instructed in the 
information shown in the Run field; usually 8080 (http://localhost:8080/). Make sure what yours is before, though.  
The Thymeleaf templates should show with /books or /authors and from there you can manipulate the data with CRUD. 

