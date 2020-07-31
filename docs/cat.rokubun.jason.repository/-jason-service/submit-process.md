[sdk](../../index.md) / [cat.rokubun.jason.repository](../index.md) / [JasonService](index.md) / [submitProcess](./submit-process.md)

# submitProcess

`fun submitProcess(label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, roverFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`, baseFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`? = null, location: `[`Location`](../../cat.rokubun.jason/-location/index.md)`? = null): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>`

Sent file to JASON in order process it, the responses are parsed and emitted

### Parameters

`type` - of processing to be performed

`roverFile` - rover file to process

`baseFile` - base file to process

`location` - of the base station

**Return**
Single

