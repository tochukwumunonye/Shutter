# Shutter

![Android Build](https://github.com/Ezike/StarWarsSearch/workflows/Android%20Build/badge.svg)

Hello Engineer, firstly thank you so much for your time in reviewing this project. I look forward to discussing more technical decisions in detail  👋🏼👋🏼👋🏼


## Table of content

- [Prerequisite](#prerequisite)
- [Preview](#preview)
- [Requirement](#feature)
- [Design](#design)
- [Architecture](#architecture)
- [Testing](#testing)
- [Improvement](#improvement)


## Prerequisite
- Android Studio Arctic Fox | 2020.3.1
- Gradle version 7.1.0
- MinSdk 21
- TargetSdk 32


## Preview
<img src="https://user-images.githubusercontent.com/61085272/196795375-fc9a71bf-a1a1-4cdd-ba39-4e721dce0cbb.jpg" width="33%" /> <img src="https://user-images.githubusercontent.com/61085272/196795428-5f7e5b40-4624-4a7c-92db-ed71962e3e7b.jpg" width="33%" /> 

## Requirement
```
Please implement an app that can search for pictures from the Shutterstock API (https://apireference.shutterstock.com/ ) and displays them in an infinite scrollable view.
 Main focus should be on a highly performant app (smooth scrolling & lag-free UI) and 
on testing(unit/UI) feasible parts.
```

## Design
- Constraint layouts were also used to prevent nested views and ensure layouts rendered in less than 60 frames per second which prevents UI jank
- Made consideration for An architecture(MVVM) which will ensure separation of concerns. Preventing memory leaks, threading issues while also testable and scalable
- For seamless scrolling I chose to use the paging library for pagination 

## Architecture

The application follows clean architecture because of the benefits it brings to software which includes scalability, maintainability and testability.
It enforces separation of concerns and dependency inversion, where higher and lower level layers all depend on abstractions. In the project, the layers are separated into different layers namely:

- Data Layer
- Domain Layer
- Presentation Layer


### Data Layer
The data layer contains application data and business logic. The business logic is what gives value to your app—it's made of real-world business rules that determine how application data must be created, stored, and changed.


#### Remote layer
The remote later relies on Retrofit library to fetch data from the API.  The remote layer contains its own data class called ArticleResult. 

#### Paging Source
This helps load data from a data source into a snapshot of pages.

#### Repository
My repository was used to expose data to the rest of the application and also reolving conflicts. Helped in Abstracting sources of data from the rest of the app.


### Presentation
The UI/Presentation layer is the pipeline that converts application data-changes to a form that the UI can present and then displays it. I used a  pattern where state of the application flows down and events flow up called `Unidirectional data flow`. Here the view model holds and exposes the state in an observable data holder called `LiveData`. This ensures quick retoration of state after configuration changes. Also the UI can react to any changes made in the state without having to manually pull data directly from the ViewModel.

The UI notifies the ViewModel of user events and data requests.
The ViewModel handles the actions and updates the state.
The updated state is fed back to the UI to render.
The above is repeated for any event that causes a mutation of state.


## Testing
Testing is done with Junit4 testing framework for assertions and Mockito for mocking classes. Each  layer has its own test. 
Viewmodel tests verify that each call to repository produces the correct view state.
Repository Test verify each interaction with server returns the expected result.


## Improvement
- App should be made to operate offline first because network availability is not always guaranteed
-  I also will be grateful to hear your feedback/criticism so I can improve and make better decisions next time.


## - Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [StateFlows](https://developer.android.com/kotlin/flow) -  Flow APIs that enable flows to optimally emit state updates and emit values to multiple consumers.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [LiveData] -Lifecycle aware data holder 
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [OkHttp](http://square.github.io/okhttp/) - HTTP client that's efficient by default: HTTP/2 support allows all requests to the same host to share a socket
- [Glide](https://github.com/bumptech/glide) - image loading framework for Android
- [Gson](https://github.com/google/gson) - used to convert Java Objects into their JSON representation and vice versa.
- [Mockito](http://site.mockito.org/) - Most popular mocking framework for Java/kotlin.
