package cat.rokubun.jason.domain

import cat.rokubun.jason.ProcessLog
import cat.rokubun.jason.ProcessResult

/**
 *  Contains information about the process
 *  @property processId process ID
 *  @property type type of process per example GNSS
 *  @property status status of prcess per example FINISH - RUNNING
 *  @property processLogList list of [Processlogs]
 *  @property processResultList list of [ProcessResult]
 *
 */

data class ProcessInformation (var processId: Int,
                               var type: String,
                               var status: String,
                               var processLogList: List<ProcessLog>,
                               var processResultList: List<ProcessResult>)