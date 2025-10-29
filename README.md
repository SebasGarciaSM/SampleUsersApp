# Sample Users App

This is a sample Android application built to demonstrate modern Android development practices. The app fetches a list of users from a remote API, displays them in a list, and allows the user to view the details of each user. It also includes functionality to add a new user to the list locally.

## App Flow & Features

1.  **Users List:** The main screen fetches and displays a list of users from the `dummyjson.com` API.
2.  **User Details:** Tapping on a user in the list navigates to a details screen, showing more information about the selected user.
3.  **Add User:** A Floating Action Button allows the user to add a new, hardcoded user to the list. After adding, the app automatically navigates to the details screen for this new user.
4.  **Image Loading:** User profile pictures are loaded asynchronously from URLs using Coil.

## Architecture

The project follows the **Clean Architecture** principles, separating the codebase into three main layers:

-   `data`: This layer is responsible for all data-related operations. It includes the API service definition (Retrofit), data transfer objects (DTOs), and the repository implementation that fetches data from the remote source and manages an in-memory cache.
-   `domain`: This is the core layer of the application. It contains the business logic, defining the data models (`UserModel`) and the repository interface (`IUserRepository`) that the UI layer will interact with. This layer has no knowledge of the Android framework.
-   `ui`: This layer is responsible for the presentation. It is built entirely with **Jetpack Compose** and follows the **Model-View-ViewModel (MVVM)** design pattern. It contains the Composable screens, ViewModels, and navigation logic.

### MVVM Pattern

The MVVM pattern is used to separate the UI from the business logic:

-   **View:** The Composable functions (`UsersView`, `UserDetailsView`) that observe data from the ViewModel and render the UI. They delegate all user actions to the ViewModel.
-   **ViewModel:** (`UsersViewModel`, `UserDetailsViewModel`) It acts as a bridge between the View and the data layer (via the repository). It holds the UI state, processes user actions, and provides data streams that the UI can observe.
-   **Model:** The data entities defined in the `domain` layer (`UserModel`), which are manipulated by the repository and exposed to the UI by the ViewModel.

## Key Dependencies

-   **Jetpack Compose:** For building the UI declaratively.
-   **Hilt:** For dependency injection, which simplifies providing dependencies like the repository and API service.
-   **Retrofit:** For making network requests to the REST API.
-   **Gson:** For parsing JSON data from the API responses.
-   **Coil 3:** For loading and displaying images asynchronously.
-   **Jetpack Navigation for Compose:** For handling navigation between screens.
-   **Lifecycle-ViewModel-Compose:** For integrating ViewModels with the Composable lifecycle.

## Data Flow PlantUML Diagram

Here is a diagram illustrating the flow of data from the remote API to the UI for displaying the user list.

![Data Flow Diagram](https://www.planttext.com/api/plantuml/png/VP9FJnin4CNlyoccEHIGoFPULOYeAUrAHKKXj8U8WsCFmQt_R3sJeAZjTsUy247OqBtiodxlDy_FUyfYYgnZW3ToIv5muvV5904GBu5mQiJWMSXtEFLcLKm4C5PomIkc0b38Yaah5OwkMlngjbH6-0TG7vjZbnCbmKNLydTFTzUlxfndH-4Q_kqWfpaQdtQnFWcL6sC95srrpwZBx3NHTk2kqVyNwSdQnYxippRf_2MLZRVqiEyKkZHSfwVnEKKruqTZRwcVRaDUEnpDT5q89nVju-I5MyUu_SavDJgXrqBbu_ZaUJtu7jjwFgsOyP2QLNECDdZxY_5WubnV-i4HF7Vi01vQLCgtfIvjYF5IZ2Pvgnb3O5IkpubXuAYKbtKgPaQ_ryIQfHuR3rSalV4OZNFrIwCCN1_-DitUVfNdzQq_MH86s2iPzq5UDDi7COxV_IXUY5zndWnlJ8rd98fWz3TeAmmz8yLEjZ1KLyJkFIdWotn-WPy_pH-x03YbfAfm3m00)
