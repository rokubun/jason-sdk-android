package cat.rokubun.jason.repository.remote.dto

/*
"process":{
      "id":3600,
      "user_id":237,
      "type":"GNSS",
      "status":"FINISHED",
      "source_file":"https:\/\/argonaut-files.s3.eu-central-1.amazonaws.com\/source_files\/5cd5ac29f5a1dc900bef2a9c752208b3\/jason_gnss_test_file_rover.txt",
      "source_base_file":"https:\/\/argonaut-files.s3.eu-central-1.amazonaws.com\/source_files\/68811980cf61006d2a220b211b83392a\/jason_gnss_test_file_base.txt",
      "camera_metadata_file":null,
      "config_file":null,
      "created":"2020-03-30 17:53:36",
      "finished":"2020-03-30 17:54:00"
}
 */
data class ProcessDetailResult (

    val id : Int,
    val user_id : Int,
    val type : String,
    val status : String,
    val source_file : String,
    val source_base_file : String,
    val camera_metadata_file : String,
    val config_file : String,
    val created : String,
    val finished : String
)