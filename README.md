# Internet-shop
* [Short Description](#description)
* [Project Structure](#structure)
* [Setup Guide](#setup)

<hr>

# <a name="description"></a>Short description
In this project I attempted to implement basics functions of an online store.There are two type of roles 
in that store:USER and ADMIN. Each of them has the following are access rights:
 - for **Admin** 
    * get list of users/items;
    * add/remove user or item;
 - for **User**  
     * get list of all items;
     * add/remove item to bucket;
     * complete order;
     * get your orders history;   
    
For security purposes regardless of role there is obligatory procedure of login/registration to have
access to main resources of the web-site. It is enhanced as well by using hashing 
of passwords with "salt". This way even breached, users data won't be exposed. 

<hr>

<a name="structure"><h2>Project structure</h2></a>

  * Java 11

  * Maven 4.0.0

  * hibernate 5.4.5.Final

  * javax.servlet 3.1.0

  * jstl 1.2

  * log4j 2.13.0

  * maven-checkstyle-plugin

<a name="developer"><h2>Setup Guide</h2></a>

Open the project in your IDE.

Add it as maven project.

Configure Tomcat:

add artifact;

add sdk 11.0.3;

Add sdk 11.0.3 in project struсture.

Create a schema "internet_shop" in any SQL database.

Use file internetshop.src.main.resources.init_db.sql to create all the tables required by this app.

At internetshop.factory.Factory class use username, password and name of DB to create a Connection.

Change a path in internetshop.src.main.resources.log4j.properties. It has to reach your logFile.

Create your first user using registration form.

There is no option to create user with ADMIN access rights so you have to do that manually in database.

Run the project.

#Author
[Dmytro_Hryhoruk](https://github.com/Dmytro2121)