## Bank API

This Api allows create accounts and trnasfer money betweet accounts un a concurrent safe way.


## How to run

1. Clone the project  

	`git clone https://github.com/juanux/bank-api.git`  
	`cd bank-api`
	
	
2. Create database
    
	`mvn flyway:migrate`
	
3. Compile and run   
    Specify the port to run the service, otherwise 8080
	` mvn clean compile exec:java -Dexec.args="<port>" ` 

## Api specification

### Create an Account
**Url:**  http://localhost:8080/mybank/user  
**Method:** POST   
**Request body:**



`{
	"name":"Juan",  
	"lastName":"Morales",  
	"documentType":"PASSPORT",  
	"documentId":"8106694",  
	"email":"ddddddd@gmail.com",  
	"birthDay":"1985-09-25",  
	"gender":"MALE",  
	"account": {
		"accountNumber":"22",  
		"balance": 1200.0,  
		"currency" : "EURO". 
	}
} `   

**Request response:**


`{
  "account": {
    "accountNumber": "222",
    "balance": 1200.0,
    "currency": "EURO"
  },
  "birthDay": "1985-09-25",
  "documentId": "8106694",
  "documentType": "PASSPORT",
  "email": "ddddddd@gmail.com",
  "gender": "MALE",
  "id": "64a22943-d9f0-4692-8211-032a7e1246c9",
  "lastName": "Morales",
  "name": "Juan"
}`

**Error responses:**    

  
***Creating an user with an existent account id***


`{
  "code": "400",
  "message": "The given account number already exist"
}`


***Invalid Document type:*** Valid document types are PASSPORT,DNI and SCN


`{
  "code": "400",
  "message": "Invalid document type"
}`

***Invalid Currency:*** Valid document types are DOLLAR,EURO,JPY and GBP


`{
  "code": "400",
  "message": "Currency not supported"
}`

***Invalid Gender:*** Valid document types are MALE, FEMALE


`{
  "code": "400",
  "message": "Invalid  gender"
}`

***Invalid initial balance:*** 


`{
  "code": "400",
  "message": "Balance must be higher tha 0"
}`

### Get an account by id
**Url:**   http://localhost:8089/mybank/user/account/{accountId}
**Method:** GET   
**Response body:**


`{
  "account": {
    "accountNumber": "22",
    "balance": 1100.0,
    "currency": "EURO"
  },
  "birthDay": "1985-09-25",
  "documentId": "8106694",
  "documentType": "PASSPORT",
  "email": "juanjosemoralesarias@gmail.com",
  "gender": "MALE",
  "id": "75186279-0d96-4b8a-9ffe-bc34134edf50",
  "lastName": "Morales",
  "name": "Juan"
}`


**Error responses:**  


***User not found:*** 


`{
  "code": "404",
  "message": "User not found."
}`

### Get an account user by id
**Url:**   http://localhost:8089/mybank/user/{userId}
**Method:** GET   
**Response body:**


`{
  "account": {
    "accountNumber": "22",
    "balance": 1100.0,
    "currency": "EURO"
  },
  "birthDay": "1985-09-25",
  "documentId": "8106694",
  "documentType": "PASSPORT",
  "email": "juanjosemoralesarias@gmail.com",
  "gender": "MALE",
  "id": "75186279-0d96-4b8a-9ffe-bc34134edf50",
  "lastName": "Morales",
  "name": "Juan"
}`


**Error responses:**  


***User not found:*** 


`{
  "code": "404",
  "message": "User not found."
}`

### Execute a transfer
**Url:**    http://localhost:8080/mybank/transaction     
**Method:** POST      
**Request body:**    


`{
	"originAccountId":"22",
	"targetAccountId":"11",
	"amount": 50
}`       


**Response body:**      


`{
  "code": "OK",
  "message": "Transaction sucess"
}`
 
 
**Error responses:** 


***Account not found:*** 


`{
  "code": "Error",
  "message": "Account does not exist"
}`


***Incompatible currencies:*** 


`{
  "code": "Error",
  "message": "Incompatible currencies EURO - DOLLAR"
}`


***Not founds enough to make the transfer:*** 


`{
  "code": "Error",
  "message": "Not founds enough to make the transfer."
}`



