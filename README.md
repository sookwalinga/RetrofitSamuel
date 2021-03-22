# RetrofitSamuel
Android Application Test using JSONPlaceholder API

This project is using the Retrofit Library which is a type-safe HTTP Library for Android and Java.  I am using Retrofit in order to turn the HTTP API into a Java interface. The volley library can also be used but Retrofit is the newer more recommended library. Retrofit has five built in methods: GET, POST, PUT, PATCH, DELETE which are sufficient for interacting with the JSONPlaceholder REST API we are using in this project. 
STEPS TO FOLLOW (RETROFIT WORKFLOW)
	Add dependencies to our build.gradle file containing the required dependencies.
-	Retrofit
-	GSON and GSON –Converter: By default, Retrofit can only deserialize HTTP bodies into OkHttp's ResponseBody type and it can only accept its RequestBody type for @Body. GSON is Java library which can be used to convert Java Objects into their JSON Representation.
-	Recyclerview
-	Lifecycle

OTHER NOTES
- The project is based on the MVVM architecture.
- Make sure to add INTERNET permision in the androidmanifest file.
- 
