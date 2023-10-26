# FinMyIP

**FinMyIP** is an Android sample application that demonstrates how to fetch the current device's IP details using the [ipapi.co](https://ipapi.co/json) API. The application showcases several modern Android development features and libraries to provide a robust and efficient solution. The following is an overview of the application's features, the technologies used, and how to set up the library module:

## Features

The **FinMyIP** Android application offers the following key features:

1. **IP Detail Retrieval**: The app fetches the current device's IP details via the [ipapi.co](https://ipapi.co/json) API and displays information such as IP address, location, and other relevant data.

2. **Dependency Injection**: It leverages Hilt Android for dependency injection, ensuring that components are easily testable and maintainable.

3. **Network Operations**: The application uses Retrofit, a powerful networking library, to make HTTP requests to the API and handle responses.

4. **Modern UI**: Jetpack Compose is utilized for creating a modern and dynamic user interface, allowing for flexible and efficient UI development.

5. **Navigation**: Jetpack Navigation simplifies the navigation flow within the application, making it easy to manage transitions between different screens.

6. **Unit Testing**: MockK is employed for writing JUnit tests, ensuring that the application's components and functionality are thoroughly tested.

## Set Up as a Library Module

To convert **FinMyIP** into a library module and create a new application that references it, follow these steps:

1. **Convert to Library Module**:
   - Open the project in Android Studio.
   - Right-click on the **FinMyIP** module in the Project Explorer.
   - Select "Refactor" and then "Convert to Module."
   - Choose "Library module" and follow the wizard to create the library module.

2. **Create a New Application**:
   - In the same project or a new one, create a new Android application module.
   - Open the `build.gradle` file for the application module.
   - Add a dependency to the **FinMyIP** library module by including it in the `dependencies` block like this:

   ```gradle
   implementation project(':finmyiplibrary')
   ```

   Make sure to replace `:finmyip-library` with the actual module name.

3. **Use the Library Module**:
   - In your application module, you can now use the functionality provided by the **FinMyIP** library module by importing its classes and invoking its methods.

By following these steps, you can use the **FinMyIP** library module in your new application, enabling you to quickly incorporate IP detail retrieval functionality into your project.

Feel free to explore the code, run the application, and integrate it with your projects to leverage these features and libraries for IP-related functionality in Android applications.
