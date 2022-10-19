# Shutter

![Android Build](https://github.com/Ezike/StarWarsSearch/workflows/Android%20Build/badge.svg)

# Shutter

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
- -For seamless scrolling I chose to use the paging library for pagination 










