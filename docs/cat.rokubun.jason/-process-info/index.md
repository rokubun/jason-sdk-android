[sdk](../../index.md) / [cat.rokubun.jason](../index.md) / [ProcessInfo](./index.md)

# ProcessInfo

`data class ProcessInfo`

Process Information

### Parameters

`id` - process ID

`userId` - user ID

`type` - type of process

`status` - process Status( FINISHED, PENDING, RUNNING, ERROR)

`sourceFile` - process source file

`basePosition` - process base Position

`created` - date the process was created

`label` - process label

`processResult` - [ProcessResult](../-process-result/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Process Information`ProcessInfo(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, userId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, status: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, sourceFile: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, basePosition: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, created: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, processResult: `[`ProcessResult`](../-process-result/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [basePosition](base-position.md) | process base Position`val basePosition: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [created](created.md) | date the process was created`val created: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [id](id.md) | process ID`val id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [label](label.md) | process label`val label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [processResult](process-result.md) | [ProcessResult](../-process-result/index.md)`val processResult: `[`ProcessResult`](../-process-result/index.md) |
| [sourceFile](source-file.md) | process source file`val sourceFile: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [status](status.md) | process Status( FINISHED, PENDING, RUNNING, ERROR)`val status: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [type](type.md) | type of process`val type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [userId](user-id.md) | user ID`val userId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
