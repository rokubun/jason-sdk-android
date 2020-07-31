[sdk](../../index.md) / [cat.rokubun.jason.repository.remote](../index.md) / [ApiService](index.md) / [getProcessInformation](./get-process-information.md)

# getProcessInformation

`@GET("processes/{id}") abstract suspend fun getProcessInformation(@Path("id") processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, @Query("token") token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Response`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Response.html)`<`[`ProcessStatusResult`](../../cat.rokubun.jason.repository.remote.dto/-process-status-result/index.md)`>`