[sdk](../../index.md) / [cat.rokubun.jason](../index.md) / [JasonClient](index.md) / [getProcessStatus](./get-process-status.md)

# getProcessStatus

`fun getProcessStatus(processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, maxTimeoutMillis: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = MAX_LOG_REQUEST_TIMEOUT_MS): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`ProcessStatus`](../-process-status/index.md)`>`

Get process status with the log and the results. The Observable will be updated every one
second until the processing finishes, either successful or with an error.

### Parameters

`processId` - process ID

`maxTimeoutMillis` - time to wait for a server respond

**Return**
Observable &lt;[ProcessStatus](../-process-status/index.md)&gt;

