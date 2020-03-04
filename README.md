
# AEFRH - Asociación Española de Fiestas y Recreaciones Históricas

_Android Project in collaboration with AEFRH to show all related with Spanish Historic celebrations all over the country.
Also helps the users to keep in touch with different entities of each town and have in one place all the social networks of each association._

## Kotlin + MVVM + Clean Architecture + Coroutines + Koin 🛠️

This project was made with the objective of creating a base structure for new apps, using tools and components supported by Google and by most of the Android development community.

### Clean Architecture

Clean architecture promotes separation of concerns, making the code loosely coupled. This results in a more testable and flexible code.

-   **Presentation**: Layer with the Android Framework, the MVVM pattern and the DI module. Depends on domain to access the use cases and on di, to inject dependencies.
-   **Domain**: Layer with the business logic. Contains the use cases, in charge of calling the correct repository or data member.
-   **Data**: Layer with the responsibility of selecting the proper data source for the domain layer. It contains the implementations of the repositories declared in the domain layer. It may, for example, check if the data in a database is up to date, and retrieve it from a service if it’s not.
-   **Use cases**: Also called interactors. They enclose a single action, like getting data from a database or posting to a service. They use the repositories to resolve the action they are supposed to do. They usually override the operator “invoke”, so they can be called as a function.

### MVVM

The Model View ViewModel pattern helps with the separation of concerns, dividing the user interface with the logic behind. The decision to use this pattern is mainly based on the support Google has been giving to it. Not only they have created a ViewModel class to use as a parent to the viewmodels, there is also a huge use of the pattern in official Android presentations and samples. Moreover, MVVM is vastly used in today’s Android development, and combines very well with Android Architecture Components like LiveData and DataBindings.

#### [](https://github.com/priettt/kotlin-mvvm-clean-sample#model)Model

As we are implementing MVVM alongside with Clean Architecture, we decided not to have a model class per se. The ViewModel interacts directly with the domain, utilizing the use cases.

#### [](https://github.com/priettt/kotlin-mvvm-clean-sample#view-model)View Model

The orchestrator of the relationship between the data and the user interface of the application. The ViewModel has the logic to convert what the use cases provide into information that the view can understand and present. Furthermore, it has the logic to react to the user’s input, and call the pertinent use cases.

The most useful part of the Android’s ViewModel class is its lifecycle consciousness. It only communicates to the View with LiveData components, so it’s totally agnostic of contexts and activities: it can keep the information alive even against configuration changes like screen rotations or calls to background.

#### [](https://github.com/priettt/kotlin-mvvm-clean-sample#view)View

The view in our implementation of MVVM is actually a Fragment or an Activity. The views enclose everything needed to handle the user interface. They observe the ViewModel, using LiveData components, and react to its changes as they need to.

#### [](https://github.com/priettt/kotlin-mvvm-clean-sample#livedata-architecture-component)LiveData Architecture Component

The view uses LiveData to observe changes in the ViewModel. This has several advantages:

-   The UI matches the data state, and this keeps data up to date.
-   Not having to worry about stopped activities and memory leaks. Live data objects are subscript to a lifecycle and automatically stop observing when that lifecycle is ended.
-   Handles configuration changes properly.
-   The same data could be shared between activities.

### Dependency Injection with Koin

Dependency injection is closely related to two SOLID concepts: dependency inversion, which states that high level modules should not depend on low level modules, both should depend on abstractions; and single responsibility principle, which states that every class or module is responsible for just a single piece of functionality. DI supports these goals by decoupling the creation and the usage of an object. It allows you to replace dependencies without changing the class that uses them and also reduces the risk of modifying a class because one of its dependencies changed. This sample app uses Koin as the dependency injection library.

## Autor ✒️

 **Guillermo Marco Alija** - *Android developer* - [gmalija](https://github.com/gmalija)