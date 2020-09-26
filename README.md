# Android SDK for Jason GNSS Positioning-as-a-Service
![Bintray](https://img.shields.io/bintray/v/rokubun/jason-sdk/cat.rokubun.jason:android-sdk)


[Jason GNSS Positioning-as-a-Service](https://jason.rokubun.cat) is a cloud-based
positioning engine that uses GNSS data. One of the main features of this service
is that offers an API so that users can automatize the GNSS data processing
without the need to access the front-end.

The online documentation of the service can be found [here](https://jason.docs.rokubun.cat).

## How to Use the SDK module

The SDK can be integrated into your projects like other libraries.

Permission approval:

```xml
 <manifest xmlns:android="http://schemas.android.com/apk/res/android" ...>

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_INTERNAL_STORAGE" />
    <application ...>
        ...
    </application>
</manifest>

```
Gradle dependencies for SDK: 

``` java
dependencies { 
    
    // Jason SDK
    implementation 'cat.rokubun.jason:sdk:1.12.0'
    
    // https://github.com/square/retrofit
    implementation "com.squareup.retrofit2:converter-gson:2.7.1"
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.7.1"
    implementation "com.squareup.retrofit2:retrofit:2.7.2"
    // https://github.com/square/okhttp
    implementation "com.squareup.okhttp3:okhttp:4.4.0"
    implementation "com.squareup.okhttp3:logging-interceptor:4.4.0"
     // https://github.com/ReactiveX/RxAndroid
    implementation "io.reactivex.rxjava2:rxandroid:2.0.2"
    implementation "io.reactivex.rxjava3:rxjava:3.0.1"
    //https://github.com/Kotlin/
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.10"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0"

}
```

Gradle dependencies for demo App:

``` java
dependencies {
    // JetPack Navigator
    classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
    //https://jakewharton.github.io/butterknife/
    implementation "com.jakewharton:butterknife:$butterknife_version"
    //Recyclerview
    implementation "com.android.support:recyclerview-v7:+"
    //Material 
    implementation "com.google.android.material:material:1.1.0"

}
```

## Authentication & Functionally

It is important that you have the `JASON_API_KEY` and `JASON_SECRET_TOKEN` 
environment variables. These can be fetched by accessing your area in the
[Jason PaaS](https://jason.rokubun.cat/#!/account) and then going to `My Account` -> `API Credentials`.

```java
  Context context;

  String processId=3145;
  Long maxTimeoutMillis = 60000L;

  //Instantiate a JasanClient
  JasonClient jasonClient = JasonClient.Companion.getInstance(context);
  ProcessInformation processInformation;
  ProcessStatus processStatus;
  
  // You should perform login before submiting a 
  // process with username and password
  jasonClient.loging(username, password);
  // Or you can login by settign the token 
  jasonClient.login(token)
  
  // Submit a process
  Single<ProcessInformation> submitProcess = jasonClient.submitProcess(type, roverFile);
  submitProcess.map(process -> processInformation = new ProcessInformation(process.getMessage(), process.getId()))
  
  // Get the status of your process
  Observable<ProcessStatus> processStatus =  jasonClient.getProcessStatus(processId, maxTimeoutMillis) 
  processStatus.map(process -> processStatus = new ProcessStatus(process.processLog, process.processResult))

  // When process is finished you can get a specific result
  processStatus.processResult.getSppKmlUrl()
  processStatus.processResult.getPreciseCsvUrl()
  ...
  // Or can download results file
  processStatus.processResult.getZipUrl()

```

