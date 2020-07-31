[sdk](../../index.md) / [cat.rokubun.jason](../index.md) / [JasonClient](./index.md)

# JasonClient

`class JasonClient`

JasonClient is responsible for communicating with JASON related services

**See Also**

[Single](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)

[Observable](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)

[MultipartBody](#)

### Types

| [Companion](-companion/index.md) | Provides a JasonClient singleton.`companion object Companion : `[`SingletonHolder`](../../cat.rokubun.jason.utils/-singleton-holder/index.md)`<`[`JasonClient`](./index.md)`, `[`Context`](https://developer.android.com/reference/android/content/Context.html)`>` |

### Functions

| [getProcesses](get-processes.md) | Get user's processes`fun getProcesses(token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ProcessInfo`](../-process-info/index.md)`>>` |
| [getProcessStatus](get-process-status.md) | Get process status with the log and the results. The Observable will be updated every one second until the processing finishes, either successful or with an error.`fun getProcessStatus(processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, maxTimeoutMillis: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = MAX_LOG_REQUEST_TIMEOUT_MS): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`ProcessStatus`](../-process-status/index.md)`>` |
| [login](login.md) | Perform the Login process to Jason and return a Single object.`fun login(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`User`](../-user/index.md)`>?`<br>Set token instead of retrieving it with the login.`fun login(token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [logout](logout.md) | Logout from JASOn by removing the token associated to the user.`fun logout(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`?` |
| [retryProcess](retry-process.md) | Perform a PPK processing specifying a rover, base file as well as the location of the base station and return a Single  Compatible files are Argounaut, Ublox, Septentrio, Rinex 2/3 devices and Android GnssLogger, GPS_Test and GalileoPVT applications`fun retryProcess(processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>` |
| [submitProcess](submit-process.md) | Perform a PPK processing with JASON specifying a Rover file and returns a Single Compatible files are Argounaut, Ublox, Septentrio, Rinex 2/3 devices and Android GnssLogger, GPS_Test and GalileoPVT applications`fun submitProcess(label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, roverFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>`<br>Perform a PPK processing specifying a rover, base file as well as the location of the base station and return a Single  Compatible files are Argounaut, Ublox, Septentrio, Rinex 2/3 devices and Android GnssLogger, GPS_Test and GalileoPVT applications`fun submitProcess(label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, roverFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`, baseFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`, location: `[`Location`](../-location/index.md)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>` |

### Companion Object Properties

| [API_KEY](-a-p-i_-k-e-y.md) | Api key for request`val API_KEY: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [URL](-u-r-l.md) | base URL for request`val URL: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

