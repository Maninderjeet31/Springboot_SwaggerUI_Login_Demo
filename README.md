# Login App

 Overview

Login Application is a Spring boot REST API application. The user can test run the application by running the Swagger UI in a browser and then entering values in the fields for the REST API end-point.

Usage

1. Internally the application compares the username and password with the username and password from the database.
2. The communication with the backend MYSQL database was simplified using the JPA Repository.
3. Also, in addition to the user credentials, the application utilizes an external API called IP-API.com to confirm the geo-location based on the entered IP address.
4. This API passes only if the credentials are OK and the geo-location returned is anywhere within Canada.
5. Response message has an ID (generated on the fly using UUID) and greeting message with username and city name based on their IP address.

To run the Swager UI, use the following url in your browser:
http://localhost:8080/swagger-ui/index.html#/default/get_users_login_username__username__password__password__ipaddress__ipaddress_

Backend Tables used: User
1. The fields and their type and constraints. The ipaddress field is optional. The initial idea was to store the IP addresses but then added functionality to take an IP address from the user on the fly.
![Test Image 1](https://github.com/Maninderjeet31/Springboot_SwaggerUI_Login_Demo/blob/main/screenshots/User_Table_Descr.png)

2. The dummy values in the table.
![Test Image 2](https://github.com/Maninderjeet31/Springboot_SwaggerUI_Login_Demo/blob/main/screenshots/User_Table_Values.png)

Swagger UI Screenshots

1. Swagger UI Initial Page 1
![Test Image 3](https://github.com/Maninderjeet31/Springboot_SwaggerUI_Login_Demo/blob/main/screenshots/Screenshot_1.png)

2. Swagger UI Initial Page 2
![Test Image 4](https://github.com/Maninderjeet31/Springboot_SwaggerUI_Login_Demo/blob/main/screenshots/Screenshot_2.png)

3. Swagger UI Initial Page 2
![Test Image 5](https://github.com/Maninderjeet31/Springboot_SwaggerUI_Login_Demo/blob/main/screenshots/Screenshot_3.png)

4. Swagger UI Result page with a successful message as a response from API
![Test Image 6](https://github.com/Maninderjeet31/Springboot_SwaggerUI_Login_Demo/blob/main/screenshots/op_Screenshot_4.png)

5. Swagger UI Result page with a fail message as a response from API
![Test Image 7](https://github.com/Maninderjeet31/Springboot_SwaggerUI_Login_Demo/blob/main/screenshots/fail_op_Screenshot_5.png)
