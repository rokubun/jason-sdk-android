[sdk](../../index.md) / [cat.rokubun.jason.repository](../index.md) / [JasonService](index.md) / [login](./login.md)

# login

`fun login(user: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`User`](../../cat.rokubun.jason/-user/index.md)`>`

Perform login in request and emit a Single. Errors are provided on the OnError method

### Parameters

`user` - user's email

`password` - user's password

### Exceptions

`ResponseCodeEum` - ERROR if something went wrong, FORBIDDEN if login is unsuccessful

**Return**
if login is correct Single

