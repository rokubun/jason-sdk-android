package cat.rokubun.sdk

//TODO CHECK
data class ProcessLog(var logId: Int,
                      var processId: Int,
                      var level: String,
                      var message: String,
                      var created: String)