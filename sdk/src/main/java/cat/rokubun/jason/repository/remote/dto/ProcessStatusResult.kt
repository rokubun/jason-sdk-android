package cat.rokubun.jason.repository.remote.dto


/*
{   "process":{},
   "log":[],
   "results":[{}]
}
 */
data class ProcessStatusResult(val process : ProcessDetailResult,
                               val log : List<Log>,
                               val results : List<ResultsResponse>
)