# Login App

 Overview
Login Application is a Spring boot REST API application. The user can test run the application by running the Swagger UI in a browser and then entering values in the fields for the REST API end-point.

Usage
1. Internally the application compares the username and password with the username and password from the database.
2. The communication with the backend MYSQL database was simplified using the JPA Repository.
3. Also, in addition to the user credentials, the application utilizes an external API called IP-API.com to confirm the geo-location based on the entered IP address.

Backend Tables used: User
1. The fields and its type and constraints. The ipaddress field is optional. The initial idea was to store the IP addresses but then added functionality to take an IP address from the user on the fly.
![Test Image 1](https://github.com/Maninderjeet31/Springboot_SwaggerUI_Login_Demo/blob/main/screenshots/User_Table_Descr.png)

2. The dummy values in the table.
![Test Image 1](https://github.com/Maninderjeet31/Springboot_SwaggerUI_Login_Demo/blob/main/screenshots/User_Table_Values.png)
