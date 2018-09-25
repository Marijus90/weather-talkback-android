# Welcome to Weather Talkback (Accessibility) app project page!

Rev. 25/09/2018 (3)

![X|X](http://i63.tinypic.com/1patuc.png)

## Vision for the final product
Recently learning about the large number of people using accessibility features on their mobile devices paired with my curiosity of exploring the Accessibility API have set my mind on building an app that would use these features and that would benefit people with low vision or loss of hearing as a final product.

This will be a weather forecast app build following the best accessibility practices. It's UI will use large and high-contrasting views and selecting a weather forecast for a specific day will have the device announce out loud the conditions and temperature of the weather.

At the moment the product is in a prototype stage.


# Current state of the product (v0.1.2)

### Requirements for MVP
- See the weather for user current location.
- Screen shows current conditions, temperature, wind speed and direction, location and time since last update.
- The weather information should be cached for future offline use (with cache expiring after certain time).
- A button or pull-to-refresh gesture for refreshing the data.
- A screen/message to indicate there is no previous data available or that user is offline.
- A loading indicator if the app is fetching data.
- Accessibility features
- Tests should cover view, presenter, local database code and utility classes.

### App screenshots

![X|X](http://i63.tinypic.com/2lvnlj.png)

### Architecture
I've chosen to build this project in MVP (**Model-View-Presenter**) architecture as it is a simple design pattern that allows easy testing of the code base.
![X|X](https://github.com/googlesamples/android-architecture/wiki/images/mvp.png)

<!--
To keep the business logic separate from the views the project files were structured in the following way
![X|X](http://i65.tinypic.com/kbzsyv.png)
-->
### Libraries used
- **Retrofit 2** library to handles the Dark Skies API call.
- **Android Room** library is trusted of handing of data persistence.
- **RxJava 2** used for communication between the data model and presenter layers. It helps to further abstract the data layer.
- Google's **Dagger 2** manages dependencies between the classes in the app.
- **Butterknife** is lightweight library used for field and method binding for Android views.
- **Constraint Layout**,**Card View** and **RecyclerView** are used in UI.

For testing I'm using the following:
**JUnit**, **Mockito**, **Hamcrest**, (TODO: ADD **Espresso** tests).

### Classes and their functions
(TODO: List the classes)

### Manual test cases
(TODO: Write test cases)

# The future of this project
I will continue to regularly update this repo as it's my "pet project" to try out new features or technologies that Google might release in the future.

### Improvements to code base
- Keep on improving codebase and continue exploring other Android Architectural Components that might benefit the app (for learning purposes mostly).
- Rewrite the app in **Kotlin** (for learning purposes).
- Architecture improvements - update project to use Google recommended App Architecture (**MVVM** pattern) https://developer.android.com/jetpack/docs/guide
- To improve the data fetching flow by implementing a NetworkBoundResource class https://developer.android.com/jetpack/docs/guide#addendum

### Improvements to make this in to a final product
- Redesign UI to support rotation and larger screen devices like **tablets**.
- Create a weather item details screen.
- Add UI tests for the screens.
- Allow user to select multiple locations to fetch the weather data for.
- Update UI design following **best accessibility practices**.
- Add a **text-to-speech API** so that selecting a weather entry in the list would read out the summary.
- Add other accessibility features, descriptions to UI elements.
- Add app **icon**.
- Add **Firebase** Analytics to track user behaviour, app crashes, index the app for easier discoverability in the store.
- Release it to the **Play Store** as a v1.0.0.

The sky is the limit...

### License
GNU AGPL-v3 (2018)
