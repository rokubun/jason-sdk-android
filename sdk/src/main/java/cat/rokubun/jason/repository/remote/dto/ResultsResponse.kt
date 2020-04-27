package cat.rokubun.jason.repository.remote.dto


/*
{   "process":{},
   "log":[],
   "results":[      {
         "id":17598,
         "process_id":3600,
         "url":"https:\/\/argonaut-files.s3.eu-central-1.amazonaws.com\/results\/4335223784822e540ef194b0338054a0\/jason_gnss_test_file_rover.txt.rnx",
         "created":"2020-03-30 15:53:57",
         "name":"jason_gnss_test_file_rover.txt.rnx",
         "static_pos":null,
         "extension":"rnx"
}]
 */

data class ResultsResponse (

    val id : Int,
    val processId : Int,
    val url : String,
    val created : String,
    val name : String,
    val staticPos : String,
    val extension : String
)