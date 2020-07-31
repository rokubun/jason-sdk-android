[sdk](../../index.md) / [cat.rokubun.jason.repository.remote](../index.md) / [ApiService](index.md) / [submitProcess](./submit-process.md)

# submitProcess

`@Multipart @POST("processes/") abstract fun submitProcess(@Part("label") label: RequestBody, @Part("token") token: RequestBody, @Part("type") type: RequestBody, @Part rover_file: Part): `[`Call`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>`
`@Multipart @POST("processes/") abstract fun submitProcess(@Part("label") label: RequestBody, @Part("token") token: RequestBody, @Part("type") type: RequestBody, @Part rover_file: Part, @Part base_file: Part, @Part("location") location: RequestBody?): `[`Call`](https://square.github.io/retrofit/2.x/retrofit/retrofit2/Call.html)`<`[`SubmitProcessResult`](../../cat.rokubun.jason.repository.remote.dto/-submit-process-result/index.md)`>`