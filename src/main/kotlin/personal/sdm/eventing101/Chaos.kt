package personal.sdm.eventing101

data class Chaos(val someString: String)

data class ChaosCompletedEvent(
        val someString: String,
        val status: String,
        val details: List<ChaosResult>,
        val errors: List<ChaosResult>)


data class LalaCompletedEvent(
        val someString: String,
        val status: String,
        val details: List<ChaosResult>,
        val errors: List<ChaosResult>)


data class EiFCompletedEvent(
        val someString: String,
        val status: String,
        val details: List<ChaosResult>,
        val errors: List<ChaosResult>)


data class DiLCompletedEvent(
        val someString: String,
        val status: String,
        val details: List<ChaosResult>,
        val errors: List<ChaosResult>)


data class ChaosResult(
        val abc: String,
        val xyz: String,
        val def: String
)