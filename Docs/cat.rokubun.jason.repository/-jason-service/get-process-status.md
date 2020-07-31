[sdk](../../index.md) / [cat.rokubun.jason.repository](../index.md) / [JasonService](index.md) / [getProcessStatus](./get-process-status.md)

# getProcessStatus

`fun getProcessStatus(processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, maxTimeoutMillis: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`ProcessStatus`](../../cat.rokubun.jason/-process-status/index.md)`>`

Requests periodically the status of the process id passed as parameter. If the state is "RUNNING" ,
requests are made every second. When the status is FINISHED or and ERROR is returned, the requests
are stopped

### Parameters

`processId` - process ID

`maxTimeoutMillis` - time to wait for a server respond

**Return**
[Observable](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)&lt;[ProcessStatus](../../cat.rokubun.jason/-process-status/index.md)&gt;

