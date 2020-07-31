[sdk](../../index.md) / [cat.rokubun.jason](../index.md) / [JasonClient](index.md) / [login](./login.md)

# login

`fun login(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`User`](../-user/index.md)`>?`

Perform the Login process to Jason and return a Single object.

### Parameters

`email` - user's email

`password` - user's password

**Return**
Single&lt;[User](../-user/index.md)&gt;

`fun login(token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Set token instead of retrieving it with the login.

### Parameters

`token` - user's token