**METROMEDS PHARAMACY MANAGEMENT SYSTEM**

**Read Me**

**Contributors:**
Khyati Johri 20066074 
Dhavan Sanjay Shah 20031696 
Yukti Dilip Patil 20097786 
Avinash Reddy Vaka 20057415 
Ajit Francis Joy 16112105

#Instructions to Build and Deploy:

->To build project into package:

mvn clean install

Deploy in Apache Tomcat Server and start the services.

#To Run the app:

-> To Access the h2 database http://localhost:9119/h2-console/ 
JDBC URL: jdbc:h2:mem:demo 
Driver: org.h2.Driver
username: sa
password: blank
-> Open REST Client 

_List of API Calls:_

**Login:**
POST - http://localhost:9119/api/login
{
"username":"admin",
"password":"password"
}
Copy token from the response and paste it in request headers ad token to validate session.


**Products:**
GET: http://localhost:9119/api/products
POST: http://localhost:9119/api/products
{
"id": 5,
"productCode": "P5",
"productName": "bla bla 250mg",
"description": null,
"standardCost": 10,
"listPrice": 10,
"targetLevel": 80,
"reorderLevel": 14,
"minimumReorderQuantity": 10,
"quantityPerUnit": "11",
"discontinued": 1,
"category": "Medicine"
}
DELETE: http://localhost:9119/api/products?id=5

**Orders:**
GET: http://localhost:9119/api/orders
POST: http://localhost:9119/api/orders
{
"id": 3,
"employeeId": 1,
"customerId": 1,
"orderDate": 1604514600000,
"shippedDate": 1607193000000,
"paidDate": 1602441000000,
"shipName": "Metromeds",
"shipAddress1": "Limerick",
"shipAddress2": null,
"shipCity": "Limerick",
"shipState": null,
"shipPostalCode": null,
"shipCountry": "Ireland",
"shippingFee": 8,
"paymentType": "Card",
"orderStatus": "Delivered",
"promoCode": "WEEKEND10",
"cartPrice": 20.22,
"deliveryCharge": 30,
"discountAmount": 10,
"amountPayable": 33.33,
"serviceType": "Express",
"orderType": "Pickup",
"isPrescribed": 1
}
DELETE: http://localhost:9119/api/products?id=5


**Payment:**
GET: http://localhost:9119/api/payments
POST: http://localhost:9119/api/payments
{
"billId": 1,
"paymentType": "wallet"
}
DELETE: http://localhost:9119/api/payments?id=1

**Customers:**
http://localhost:9119/api/customers

**Employees:**
http://localhost:9119/api/employees

## Run Tests
**To generate test report run the command: mvn surefire-report:report**
**Only to run the tests run : mvn tests**

## To Analyse code via Code MR
Install Code MR plugin and extract model by selecting from tools tab.



