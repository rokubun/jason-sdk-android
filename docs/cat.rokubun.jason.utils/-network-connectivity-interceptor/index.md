[sdk](../../index.md) / [cat.rokubun.jason.utils](../index.md) / [NetworkConnectivityInterceptor](./index.md)

# NetworkConnectivityInterceptor

`class NetworkConnectivityInterceptor : Interceptor`

Is responsible for checking if the device has an internet connection

### Exceptions

| Name | Summary |
|---|---|
| [NoConnectivityException](-no-connectivity-exception/index.md) | `class NoConnectivityException : `[`IOException`](https://docs.oracle.com/javase/6/docs/api/java/io/IOException.html) |
| [NoInternetException](-no-internet-exception/index.md) | `class NoInternetException : `[`IOException`](https://docs.oracle.com/javase/6/docs/api/java/io/IOException.html) |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Is responsible for checking if the device has an internet connection`NetworkConnectivityInterceptor(context: `[`Context`](https://d.android.com/reference/android/content/Context.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [connectivityManager](connectivity-manager.md) | `val connectivityManager: `[`ConnectivityManager`](https://d.android.com/reference/android/net/ConnectivityManager.html) |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun intercept(chain: Chain): Response` |
