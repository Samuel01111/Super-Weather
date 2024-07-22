
# SuperWeather
2023 made in Jetpack Compose ❤️

<div align="left">
    <a href = "https://developer.android.com/jetpack/androidx/versions/all-channel?hl=pt-br#october_26_2022">
        <img src = "https://img.shields.io/badge/Jetpack%20Compose%20Bom-2022.10.00-brightgreen" />
    </a>
</div>
<br>

<div align="left">
    <a href = "https://github.com/airbnb/lottie-android+">
        <img src = "https://img.shields.io/badge/airbnb%20android%20lottie%20compose-5.2.0-brightgreen" />
    </a>
</div>
<br>

This a Weather app that user can get theirs weather details based on current location and search any country or city in the world.

# Requirements
1. Android Studio : Arctic Fox | 2020.3.1	3.1 or higher
2. Android Emulator or Physical android device

# Running
1. The first step to run the app is create a file called `env.properties` in the root of the project.
2. Add the following line to the file `env.properties`:
``` WEATHER_API_KEY = (YOUR_API_KEY) ```
3. Replace `YOUR_API_KEY` with your own API key from [OpenWeatherMap](https://openweathermap.org/api)
4. Create file called by `google-service.json` in the `app` module and add your Firebase project configuration.
5. Sync the project with gradle files.
6. Be happy! 🎉

# Built With 🏗

| Tools                        |                                             Link                                              |
|:-----------------------------|:---------------------------------------------------------------------------------------------:|
| 🤖   Kotlin                  |                               [Kotlin](https://kotlinlang.org/)                               |
| 📱   Jetpack Compose         |               [Jetpack Compose](https://developer.android.com/jetpack?hl=pt-br)               |
| 🏛   Architecture Components | [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) |
| 💉   Dagger Hilt             |    [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)    |
| 🌐   Retrofit                |                         [Retrofit](http://square.github.io/retrofit/)                         |
| 🚦   OkHttp                  |                           [OkHttp](http://square.github.io/okhttp/)                           |
| 🌊   Kotlin Coroutines       |             [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)              |
