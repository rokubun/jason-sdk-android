[sdk](../../index.md) / [cat.rokubun.jason](../index.md) / [JasonClient](index.md) / [submitProcess](./submit-process.md)

# submitProcess

`fun submitProcess(label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, roverFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>`

Perform a PPK processing with JASON specifying a Rover file and returns a
Single
Compatible files are Argounaut, Ublox, Septentrio, Rinex 2/3 devices and
Android GnssLogger, GPS_Test and GalileoPVT applications

### Parameters

`type` - of process to be performed {@sample GNSS, CONVERTER}

`roverFile` - Input file to be send to Jason

**Return**
Single&lt;[SubmitProcessResult](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)&gt;

`fun submitProcess(label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, roverFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`, baseFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`, location: `[`Location`](../-location/index.md)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>`

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

