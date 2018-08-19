# Welcome to Weather Talkback (Accessibility) app project page!

Rev. 19/08/2018 (1)

![X|X](http://i68.tinypic.com/dxnvoj.png)

## Vision for the final product
Recently learning about the large number of people using accessibility features on their mobile devices paired with my curiosity of exploring the Accessibility API have set my mind on building an app that would use these features and that would benefit people with low vision or loss of hearing as a final product.

This will be a weather forecast app build following the best accessibility practices. It's UI will use large and high-contrasting views and selecting a weather forecast for a specific day will have the device announce out loud the conditions and temperature of the weather.

At the moment the product is in a prototype stage.


# Current state of the product (v0.1.0)

### Minimal requirements for v0.1.0
The requirements for a simple starter app on which the extra functionality will be built were:
- See the weather for user current location.
- Screen shows current conditions, temperature, wind speed and direction, location and time since last update.
- The weather information should be cached for future offline use (cache expire in 24 hours).
- A button for refreshing the data.
- A screen to indicate there is no previous data available.
- A message should be displayed to indicate that I need to connect to the - Internet in order to get updated data.
- A loading indicator if the app is fetching data.

All the above requirements were built except for getting user current location and caching it (as of 19th August, 2018). It will be implemented in the upcoming days. Now the app queries the weather data for a hardcoded location - London, UK.
https://developer.android.com/training/location/retrieve-current

Tests cover presenter, activity, local database code and utility classes.

### App screenshots

![X|X](http://i64.tinypic.com/34edg7t.png)

### Architecture & project file structure

I've chosen to build this project in MVP (Model-View-Presenter) architecture as its simples design pattern that allows easy testing of the code base.
![X|X](https://github.com/googlesamples/android-architecture/wiki/images/mvp.png)

To keep the business logic separate from the views the project files were structured in the following way
![X|X](http://i65.tinypic.com/kbzsyv.png)
### Libraries used (and why)
I'm only using **one third-party library** for the project 
https://github.com/kevinzetterstrom/forecast-android
It allowed me to build the app quicker as it takes care of the Dark Skies API call, allows a fast configuration and has all the data models in it.

I've trusted caching of data to the **Android Room Persistence Library**. Of course it would have been quicker to store the weather data from the API as a **Parcelable** object in the Shared Preferences, but I've been excited to work with Room and believe it will allow me for easier app data management and feature extendability as in future version of the app I will be loading whole weeks worth of data.

For testing I'm using the standard libs:
**JUnit**, **Mockito**, **Hamcrest**, **Espresso**.

### Classes and their functions
(TODO: List the classes)

### Manual test cases
(TODO: Write test cases)

# The future of this project
I will continue to regularly update this repo as it's my "pet project" to try out new features or technologies that Google might release in the future.

### Improvements to code base
- Keep on improving codebase and exploring other Android Architectural Components that might benefit the app (for learning purposes mostly).
- Rewrite the app in **Kotlin** (for learning purposes).
- Use a **dependency injection** library to decouple some of the classes and improve the tests.
- Add **RxJava2** to implement concurrency, and to further abstract the data layer.
- Architecture improvements - update project to use Google recommended App Architecture (**MVVM**) https://developer.android.com/jetpack/docs/guide
- Use **Retrofit** for the API calls instead of 3rd party library for more freedom of customisation.
- To improve the data fetching flow by implementing a NetworkBoundResource class https://developer.android.com/jetpack/docs/guide#addendum

### Improvements to make this in to a final product
- Redesign UI to support rotation and larger screen devices like **tablets**.
- Display a week of weather information.
- Add UI tests for the screens.
- Make sure the data displayed persists on device rotation.
- Allow user to select multiple locations.
- Update UI design following **best accessibility practices**.
- Add a **text-to-speech API** so that selecting a weather entry in the list would read out the summary.
- Add other accessibility features, descriptions to UI elements.
- Add app **icon**.
- Add **Firebase** Analytics to track user behaviour, app crashes, index the app for easier discoverability in the store.
- Release it to the **Play Store** as a v1.0.0.

The sky is the limit...

### License
GNU AGPL-v3 (2018)
