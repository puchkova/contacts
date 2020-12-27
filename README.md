# Contacts
The project is a simple contact list web application with functions of listing people, searching by name and paging. 
The back-end is the **Java Spring Boot** application built using **Maven** and connected to the in-memory **H2 database**. The front-end was developed with **Angular 10**. Automated tests was created using **Junit** and **Mockito**. 

## Prerequisites
- **NodeJS** - https://nodejs.org/en/
- **IntelliJ IDEA** - https://www.jetbrains.com/idea/
- **JDK** - https://www.oracle.com/java/technologies/javase-downloads.html

## Application Structure
### Back-end
The server project is stored in the `src\` directory. It contains *controller*, *service*, *repository* and *entity* packages inside the `src\main\java\ee\kuehnenagel\contacts\` package.
- `ContactController` contains the *REST API* GET request with the contact's name as an optional request parameter. It returns a full or a filtered contact list.
- `ContactService` contains all main logic. It contains 2 public and 3 private methods. The *service* also contains a CSV file field with its path as *@Value* parameter. 
	 - `getContacts` is a public method  that calls one of two private methods for getting a full or a filtered list of contacts depending on if there is a parameter or not.
	 - `saveContactsFromFileToRepository` is a public method for getting contacts from CSV file and saving it to the *repository*. It contains a *while* loop that fills the contact list.
	 - `getAllContacts` is a private method with the contact's name as a parameter. The method calls `findAll` method from the *repository* and returns the full contact list.
	 - `getContactsByName` is a private method that calls `findByNameContainsIgnoreCase` method from the *repository* and returns the filtered contact list.
	 - `createNewContact` is a private method for creating a new contact and avoiding cases of extra whitespaces and commas in the input file.
- `ContactRepository` extends `JpaRepository` interface. That allows to create easily query methods using keywords. It contins `findByNameContainsIgnoreCase` method that implement a LIKE query and ignore case.
- `Contact` is an *entity* that contains contact id, name and photo url fields. These fields are linked with database columns.
- `ApplicationStartup` class implements `ApplicationListener` interface. It contains the `onApplicationEvent` method that is invoked with application starting. This method calls `saveContactFromFileToRepository` from the *service*.  	 
- `people.csv` file is located in the `src\main\resources` directory. It is the input file for uploading contacts to the contact list.
- `application.properties` provides a list of database settings and a CSV file path.

### Automated Tests
The `src\test\java\ee\kuehnenagel\contacts\` package contains automated tests for *controller*, *service* and *repository*. 
Unit tests was created using Mockito testing framework and integration tests was created using SpringRunner.  
There is also `people-test.csv` file for testing in `src\test\resources` directory and its path is stored in `application-test.properties`.

### Front-end
The client project is stored in the `frontend\` directory It contains *component* and *service* packages inside the `frontend\src\app` directory.
- `app.component.css` contains all the slyles using in the project.
- `app.component.html` contains markup tags to display the web page. It contains the toolbar with a welcome message, search field, search and refresh buttons, table as the contact list and the pagination component.
- `app.component.ts` contains 3 fuctions: 
	 - `ngOnInit()` to upload the contact list on the page. It calls `getContacts()` function.
	 - `getContacts()` calls *service* and returns the contact list. 
	 - `clearSearchField()` removes the text from search field after clicking on the refresh button
- `contact.service.ts` contains method with the contact's name for searching as a parameter. It returns GET responses for getting a full or a filtered contact list depending on a parameter.

## Installing & Running 
1. Clone this repo https://github.com/puchkova/contacts.git

2. Use your IDE `Run` menu to run the server application

3. Go to the `frontend` directory using `cd` in your terminal and run the `npm install` command to install node modules 

4. Run the `nmp start` command to run the client application

5. Now the application is available on http://localhost:4200/

6. Right click on the `contact` module in your IDE and choose `Run 'All Tests'` to run automated tests 

## User Interface
![Screenshot 2020-12-24 070727](https://user-images.githubusercontent.com/54691147/103062477-f1eae680-45b6-11eb-97e3-6deb5f8fa165.jpg)

![Screenshot 2020-12-24 070836](https://user-images.githubusercontent.com/54691147/103062609-69207a80-45b7-11eb-90fd-766e347a00b9.jpg)

