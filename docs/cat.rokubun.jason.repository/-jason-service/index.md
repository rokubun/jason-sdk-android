[sdk](../../index.md) / [cat.rokubun.jason.repository](../index.md) / [JasonService](./index.md)

# JasonService

`class JasonService`

This class is responsible for managing the requests made to the JASON API

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | instantiate ServiceFactory and create an ApiService`JasonService(context: `[`Context`](https://d.android.com/reference/android/content/Context.html)`)` |

### Functions

| Name | Summary |
|---|---|
| [getProcesses](get-processes.md) | Get user's processes`fun getProcesses(token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ProcessInfo`](../../cat.rokubun.jason/-process-info/index.md)`>>` |
| [getProcessStatus](get-process-status.md) | Requests periodically the status of the process id passed as parameter. If the state is "RUNNING" , requests are made every second. When the status is FINISHED or and ERROR is returned, the requests are stopped`fun getProcessStatus(processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, maxTimeoutMillis: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`ProcessStatus`](../../cat.rokubun.jason/-process-status/index.md)`>` |
| [login](login.md) | Perform login in request and emit a Single. Errors are provided on the OnError method`fun login(user: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`User`](../../cat.rokubun.jason/-user/index.md)`>` |
| [logout](logout.md) | Logout user from Jason by removing token`fun logout(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [retryProcess](retry-process.md) | `fun retryProcess(processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>` |
| [setToken](set-token.md) | Insert user token for every request`fun setToken(token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [submitProcess](submit-process.md) | Sent file to JASON in order process it, the responses are parsed and emitted`fun submitProcess(label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, roverFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`, baseFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`? = null, location: `[`Location`](../../cat.rokubun.jason/-location/index.md)`? = null): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>` |
