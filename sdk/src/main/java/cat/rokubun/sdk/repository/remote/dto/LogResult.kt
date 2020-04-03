package cat.rokubun.sdk.repository.remote.dto


/*
{   "process":{},
   "log":[      {
         "id":24496,
         "process_id":3600,
         "level":"INFO",
         "message":"Converting file jason_gnss_test_file_rover.txt to RINEX 3.03\n",
         "created":"2020-03-30 15:53:37"
}]
 */
data class Log (
    val id : Int,
    val process_id : Int,
    val level : String,
    val message : String,
    val created : String
)