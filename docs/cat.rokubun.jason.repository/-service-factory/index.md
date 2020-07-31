[sdk](../../index.md) / [cat.rokubun.jason.repository](../index.md) / [ServiceFactory](./index.md)

# ServiceFactory

`class ServiceFactory`

Create an implementation of Retrofit to communicate with a service API.

**See Also**

[HttpLoggingInterceptor](#)

[Retrofit](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Retrofit.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Create an implementation of Retrofit to communicate with a service API.`ServiceFactory(context: `[`Context`](https://d.android.com/reference/android/content/Context.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [retrofit](retrofit.md) | `var retrofit: `[`Retrofit`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Retrofit.html)`?` |

### Functions

| Name | Summary |
|---|---|
| [getService](get-service.md) | Provides the Jason Services based on the url and api key passed as parameter`fun getService(baseUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, apiKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Retrofit`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Retrofit.html)`?` |
