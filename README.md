# E-commerceApp

E-commerceApp is a Spring Boot web application designed to manage users, shopping carts, products, and authentication using refresh tokens.

## Database Configuration

To run this application, you need to configure the database connection in the `src/main/resources/application.properties` file. Follow the steps below:

1. Open the `src/main/resources/application.properties` file in your project.

2. Update the following properties with your database information:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name?serverTimezone=UTC
# Specify the JDBC URL for the MySQL database.
# Make sure to replace `your_db_name` with the name of your database.

spring.datasource.username=your_db_username
# Replace `your_db_username` with the username to connect to the database.

spring.datasource.password=your_db_password
# Replace `your_db_password` with the password for the database user.
```

3. Save the `application.properties` file after making these changes.

## Security and Authentication

The application uses Spring Security for authentication and authorization management. It also includes refresh token support for secure authentication.

## Main Features

The application provides the following main features:

- User management, including registration, updating, and deleting users.
- Assignment of roles to users, such as regular user or administrator.
- Shopping cart management, including creating new shopping carts and adding products to carts.
- Product management, including adding, editing, and deleting products.
- Refresh token support for secure authentication.

## CRUD Operations

- **User Management**: You can perform CRUD operations on users, including creating, reading, updating, and deleting user accounts.

- **Shopping Cart Management**: You can create and manage shopping carts, add products to carts, and update cart contents.

- **Product Management**: CRUD operations for products, including adding new products, updating existing ones, and deleting products.

## Running the Application

To run the application, follow these steps:

1. Make sure you have configured the database connection as described above.

2. Start the application using your preferred development environment or by running the Spring Boot application class.

3. The application will be accessible at [http://localhost:8080](http://localhost:8080).

If you encounter any issues or need further assistance, feel free to contact the project owner:

- Name: Francesca Mazzeo
- Email: dev.francesca.mazzeo@gmail.com
