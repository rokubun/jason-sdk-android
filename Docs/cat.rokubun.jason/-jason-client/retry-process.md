[sdk](../../index.md) / [cat.rokubun.jason](../index.md) / [JasonClient](index.md) / [retryProcess](./retry-process.md)

# retryProcess

`fun retryProcess(processId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>`

Perform a PPK processing specifying a rover, base file as well as the location of the base
station and return a Single 
Compatible files are Argounaut, Ublox, Septentrio, Rinex 2/3 devices and
Android GnssLogger, GPS_Test and GalileoPVT applications

### Parameters

`type` - of process to be performed {@sample GNSS, CONVERTER}

`roverFile` -

`baseFile` -

`location` -

**Return**
Single&lt;[SubmitProcessResult](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)&gt; which is the API response

