# Contacts
The project is a simple Contact List web application with functions of listing people, searching by name and paging. 
The back-end is the **Java Spring Boot** application built using **Maven**. The front-end was developed with **Angular 10**. Unit tests was created using **Junit** and **Mockito**.

## Prerequisites
- **NodeJS** - You can follow the instructions https://nodejs.org/en/ to install Node on your machine.
- **IntelliJ IDEA** - You can follow the instructions https://www.jetbrains.com/idea/ to install IDE on your machine.
- **JDK** - You can follow the instructions https://www.oracle.com/java/technologies/javase-downloads.html to install Java Development Kit on your machine.

## Application Structure
### Back-end
The `src\` directory contains the server project. It contains *controller*, *service*, *repository* and *model* packages inside the `src\main\java\ee\kuehnenagel\contacts\` package.
- `ContactController` contains the *REST API* GET request  request with name of contact as optional request parameter. It returns full or filtered contact list.
- `ContactService` contains method that calls *repository* and returns the contact list. The service contains the CSV file field with its path as *@Value* parameter.
- `Contact` is a model that contains contact name and photo url fields. 
- `ContactRepository` implements `ContactRepository` interface. It contains operations with data. 
	 - `getContactsFromCsvFile` is a public method for getting contacts from CSV file with contact name for searching and file as a parameters. It contains a *while* loop that fill the contact list.
	 - `createNewContact` is a private method for creating a new contact and avoiding cases of extra whitespaces and commas in the input file.
	 - `filterContacts` is a private method that contains searching logic.
- `people.csv` file is located in the `src\main\resources` folder. It is the input file for uploading contacts to the contact list.

### Unit Tests
The `src\test\java\ee\kuehnenagel\contacts\` package contains unit tests for *controller*, *service* and *repository*. 
There is also `people-test.csv` file for testing in `src\test` folder.

### Front-end
The `frontend\` directory contains the client project. It contains *component* and *service* packages inside the `frontend\src\app` folder.
- `app.component.css` contains all the slyles using in the project.
- `app.component.html` contains markup tags to display the web page. It contains the toolbar with a welcome message, search field, search and refresh buttons, table as the contact list and the pagination component.
- `app.component.ts` contains 3 fuctions: 
	 - `ngOnInit()` to upload the contact list on the page. It calls `getContacts()` function.
	 - `getContacts()` calls *service* and returns the contact list. 
	 - `clearSearchField()` removes the text from search field after clicking on the refresh button
- `contact.service.ts` contains method with the contact name for searching as a parameter. It returns GET responses for getting full or filtered contact list depending on a parameter.

## Installing & Running 
1. Clone this repo https://github.com/puchkova/contacts.git

2. Use your IDE `Run` menu to run the server application

3. Go to the `frontend` folder using `cd` in your terminal and run the `npm install` command to install node modules 

4. Run the `nmp start` command to run the client application

5. Now the application is available on http://localhost:4200/

6. Right click on the `contact` module in your IDE and choose `Run 'All Tests'` to run automated tests 

## User Interface
![Screenshot 2020-12-24 070727](https://user-images.githubusercontent.com/54691147/103062477-f1eae680-45b6-11eb-97e3-6deb5f8fa165.jpg)

![Screenshot 2020-12-24 070836](https://user-images.githubusercontent.com/54691147/103062609-69207a80-45b7-11eb-90fd-766e347a00b9.jpg)

