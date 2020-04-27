package cat.rokubun.jason.repository.remote.dto

/**
 * Process status response.
 */

data class ProcessStatusResult(val process : ProcessDetailResult,
                               val log : List<Log>,
                               val results : List<ResultsResponse>
)