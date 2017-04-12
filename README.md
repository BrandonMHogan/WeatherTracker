# WeatherTracker

Weather Tracker is a demo application that gets the location of the device, then sends to an API to retrieve local weather information. It requires a location and network to work.

### Libraries and API's
Dark Sky API,
Retrofit 2,
RXJava 2,
RXAndroid 2
Dagger 2,
GSON,
Constraint Layout 1.0.2,
and Butterknife


### Emulator Testing
Please note that if you test this application with an emulator, you need to go into settings and have it send its location. Otherwise the GPS Manager will not find a location and will not be able to continue. On native emulators, click more, go to the Location section, enter in a valid longitude and latitude, then send. The application will catch it, and immediately continue.
