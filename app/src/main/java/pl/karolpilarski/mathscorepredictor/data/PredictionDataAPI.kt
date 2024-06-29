package pl.karolpilarski.mathscorepredictor.data

data class PredictionDataAPI(
    val gender: Float,
    val ethnicGroup: Float,
    val parentEduc: Float,
    val lunchType: Float,
    val testPrep: Float,
    val parentMaritalStatus: Float,
    val practiceSport: Float,
    val isFirstChild: Float,
    val nrSiblings: Float,
    val transportMeans: Float,
    val wklyStudyHours: Float,
)