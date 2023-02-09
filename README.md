# PayBackAndroidChallenge


## Language, libraries and tools
- [Kotlin](https://kotlinlang.org/docs/reference/) the project is 100% written in Kotlin
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html) a Kotlin
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) a Kotlin light-weighted dependency injection framework.
- [Retrofit](https://square.github.io/retrofit/) A type-safe HTTP client for Android
- [Room](https://developer.android.com/training/data-storage/room/index.html) The Room persistence
library provides an abstraction layer over SQLite to allow for more robust database access while
harnessing the full power of SQLite
- [AndroidX libraries](https://developer.android.com/jetpack/androidx/)
- [Architecture components](https://developer.android.com/topic/libraries/architecture/) provides
the life-cycle aware components and observable data
- [Glide](https://github.com/bumptech/glide) An image loading and caching library for Android
focused on smooth scrolling
- [navigation and safe args]
- [Gson]



## Architecture
MVVM-Clean Architure with a single actitvity concept.

### Modularisation
Each of the following layers will be implemented as a its own module in order to keep them 
separated in alignment with clean architecture and enable parallel builds in gradle.  

### App or View
Android UI components belong here. View communicates with ViewModel, subscribing to ViewState 
using StateFlow component.
This module provides the logic of the View, by providing subscribing components to the view to 
react to. The ViewModel changes the ViewState based on the use case responses.

### Domain
Contain use cases of the application. They provide the application specific business rules and are 
are responsible of accessing data from different repositories, combine and transform them, to 
provide single use case business rule.

### Data
The Data layer is our access point to external data layers and is used to fetch data from multiple 
sources (network, cache). It contains implementations of Repositories, which request data from 
necessary RemoteDataSources and CacheDataSources to feed the use case and make communication 
between the 2 types of data sources.

### Cache
The Cache layer handles all communication with the local databasea.
