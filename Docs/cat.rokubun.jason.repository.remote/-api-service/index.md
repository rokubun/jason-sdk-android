[sdk](../../index.md) / [cat.rokubun.jason.repository.remote](../index.md) / [ApiService](./index.md)

# ApiService

`interface ApiService`

Interface to make API requests

**See Also**

[POST](https://square.github.io/retrofit/2.x/retrofit/retrofit2/http/POST.html)

[GET](https://square.github.io/retrofit/2.x/retrofit/retrofit2/http/GET.html)

[FormUrlEncoded](https://square.github.io/retrofit/2.x/retrofit/retrofit2/http/FormUrlEncoded.html)

[Multipart](https://square.github.io/retrofit/2.x/retrofit/retrofit2/http/Multipart.html)

[Response](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Response.html)

### Functions

| [getAllProcess](get-all-process.md) | `abstract fun getAllProcess(token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Call`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ProcessApiResult`](../../cat.rokubun.jason.repository.remote.dto/-process-api-result/index.md)`>>` |
| [getProcessInformation](get-process-information.md) | `abstract suspend fun getProcessInformation(processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Response`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Response.html)`<`[`ProcessStatusResult`](../../cat.rokubun.jason.repository.remote.dto/-process-status-result/index.md)`>` |
| [retry](retry.md) | `abstract fun retry(processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Call`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>` |
| [submitProcess](submit-process.md) | `abstract fun submitProcess(label: RequestBody, token: RequestBody, type: RequestBody, rover_file: Part): `[`Call`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>`<br>`abstract fun submitProcess(label: RequestBody, token: RequestBody, type: RequestBody, rover_file: Part, base_file: Part, location: RequestBody?): `[`Call`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>` |
| [userlogin](userlogin.md) | `abstract fun userlogin(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Call`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html)`<`[`UserLoginResult`](../../cat.rokubun.jason.repository.remote.dto/-user-login-result/index.md)`>` |

