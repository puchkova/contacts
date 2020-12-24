# Contacts
The project is a simple "Contact List" web application with functions of listing people, searching by name and paging. 
The back-end is the SpringBoot RESTful API application built using Maven. The front-end was developed with Angular 10. Automated tests was created using Junit+Mockito.

## Prerequisites
- NodeJS - You can follow the instructions https://nodejs.org/en/ to install Node on your machine.
- IntelliJ IDEA - You can follow the instructions https://www.jetbrains.com/idea/ to install IDE on your machine.
- JDK - You can follow the instructions https://www.oracle.com/java/technologies/javase-downloads.html to install Java Development Kit on your machine.

## Application Structure
The `src\` directory contains the server project. It contains controller, service and model packages inside the `src\main\java\ee\kuehnenagel\contacts\` package.
- `ContactController` contains the GET request with name of contact as optional request parameter. It returns full or filtered contact list.
- `ContactService` contains method that calls repository and returns contact list. The service contains csv file field with its path as @Value parameter.
- `Contact` is a model that contains contact name and photo url fields.
- `ContactRepository` implements `ContactRepository` interface. It contains operations with data. 
	 - `getContactsFromCsvFile` is a public method for getting contacts from CSV file with contact name for searching and file as a parameters. It contains a while loop that fill the contact list.
	 - `createNewContact` is a private method for creating a new contact and avoiding cases of extra whitespaces and commas the input file.
   - `filterContacts` is the private method that contains searching logic and returns a new contact.
- `people.csv` is located in the `src\main\resources` folder. It is the input file for upload contacts to contact list.

The `src\test\java\ee\kuehnenagel\contacts\` package contains unit tests for controller, repository and service. There is also `people-test.csv` file for testing in `src\test` folder.

The `frontend\` directory contains the client project. It contains component and service packages inside the `frontend\src\app` folder.
- `app.component.css` contains all the slyles using in the project.
- `app.component.html` contains markup tags to display the web page. It contains toolbar with welcome message, search field, search and refresh buttons, table as contact list and pagination component.
- `app.component.ts` contains 3 fuctions: 
	 - `ngOnInit()` to upload the contact list on the page. It uses `getContacts()` function.
	 - `getContacts()` fuction that calls service and returns contact list. 
	 - `clearSearchField()` removes the text from search field after clicking on the refresh button
- `contact.service.ts` contains method with contact name for searching as a parameter. It returns GET responses for getting full or filtered contact list depending on parameter.

## Installing & Running 
1. Clone this repo https://github.com/puchkova/contacts.git

2. Use your IDE `Run` menu to run the server

3. Go `frontend` folder using `cd` in your terminal and run the `npm install` command to install node modules 

4. Run the `nmp start` command to run the client application

5. Now the application is available on http://localhost:4200/

6. Right click on the `contact` module in your IDE and choose `Run 'All Tests'` to run automated tests 

## This is how it looks like
![Screenshot 2020-12-24 051131](https://user-images.githubusercontent.com/54691147/103057364-f2c84c00-45a7-11eb-9397-101e83878960.jpg)

