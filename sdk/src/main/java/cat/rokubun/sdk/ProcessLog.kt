package cat.rokubun.sdk


/**
 * Process log information
 * @property logId Log ID
 * @property processId Process ID
 * @property level Process level {@sample INFO, WARNING, ERROR, FATAL, etc}
 * @property message log message
 * @property created date the process was created
 */
data class ProcessLog(var logId: Int,
                      var processId: Int,
                      var level: String,
                      var message: String,
                      var created: String)