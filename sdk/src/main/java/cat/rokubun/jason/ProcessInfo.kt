package cat.rokubun.jason

/**
 * Process Information
 *
 * @param id process ID
 * @param userId user ID
 * @param type type of process
 * @param status process Status( FINISHED, PENDING, RUNNING, ERROR)
 * @param sourceFile process source file
 * @param basePosition process base Position
 * @param created date the process was created
 * @param label  process label
 * @param processResult [ProcessResult]
 */
data class ProcessInfo(val id: Int,
                       val userId: String,
                       val type: String,
                       val status: String,
                       val sourceFile: String,
                       val basePosition: String,
                       val created: String,
                       val label: String,
                       val processResult: ProcessResult)