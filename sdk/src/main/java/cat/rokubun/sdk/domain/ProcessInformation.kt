package cat.rokubun.sdk.domain

import cat.rokubun.sdk.ProcessLog
import cat.rokubun.sdk.ProcessResult

data class ProcessInformation (var processId: Int,
                               var type: String,
                               var status: String,
                               var processLogList: List<ProcessLog>,
                               var processResultList: List<ProcessResult>)