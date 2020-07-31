[sdk](../../index.md) / [cat.rokubun.jason](../index.md) / [ProcessResult](./index.md)

# ProcessResult

`class ProcessResult`

This class returns information from the processed file

### Parameters

`listResultsResponse` - list of [ResultsResponse](../../cat.rokubun.jason.repository.remote.dto/-results-response/index.md)

### Constructors

| [&lt;init&gt;](-init-.md) | This class returns information from the processed file`ProcessResult(listResultsResponse: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ResultsResponse`](../../cat.rokubun.jason.repository.remote.dto/-results-response/index.md)`>)` |

### Properties

| [listResultsResponse](list-results-response.md) | list of [ResultsResponse](../../cat.rokubun.jason.repository.remote.dto/-results-response/index.md)`val listResultsResponse: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ResultsResponse`](../../cat.rokubun.jason.repository.remote.dto/-results-response/index.md)`>` |

### Functions

| [getNumEpochs](get-num-epochs.md) | Returns number of epochs`fun getNumEpochs(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getNumSatellitesUrlPng](get-num-satellites-url-png.md) | Returns number of satellites url.`fun getNumSatellitesUrlPng(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getPreciseCsvUrl](get-precise-csv-url.md) | Returns precise CSV url.`fun getPreciseCsvUrl(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getpreciseKmlUrl](getprecise-kml-url.md) | Returns precise KML url`fun getpreciseKmlUrl(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getProcessingType](get-processing-type.md) | Returns processing type (SPP, PPP, RTK, PPK)`fun getProcessingType(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getSkyPlotUrl](get-sky-plot-url.md) | Returns sky plot url`fun getSkyPlotUrl(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getSppCsvUrl](get-spp-csv-url.md) | Returns spp CSV url.`fun getSppCsvUrl(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getSppKmlUrl](get-spp-kml-url.md) | Returns spp KMl url.`fun getSppKmlUrl(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStaticPosition](get-static-position.md) | Returns static position`fun getStaticPosition(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getZipUrl](get-zip-url.md) | Returns zip url`fun getZipUrl(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isStatic](is-static.md) | Returns if a process is static`fun isStatic(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

