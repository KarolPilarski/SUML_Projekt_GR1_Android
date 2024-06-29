package pl.karolpilarski.mathscorepredictor.ui.form

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.karolpilarski.mathscorepredictor.R
import pl.karolpilarski.mathscorepredictor.data.PredictionDataAPI
import pl.karolpilarski.mathscorepredictor.repository.DataRepository
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    val dataRepository: DataRepository
) : ViewModel() {

    val genderRadioOptions = listOf(R.string.female, R.string.male)
    val genderSelectedOption = mutableIntStateOf(0)

    val ethnicityOptions = listOf(
        R.string.group_a,
        R.string.group_b,
        R.string.group_c,
        R.string.group_d,
        R.string.group_e
    )
    val ethnicitySelected = mutableIntStateOf(0)

    val parentEducationOptions = listOf(
        R.string.associates_degree,
        R.string.bachelors_degree,
        R.string.high_school,
        R.string.masters_degree,
        R.string.some_collage,
        R.string.some_high_school
    )
    val parentEducationSelected = mutableIntStateOf(0)

    val lunchRadioOptions = listOf(R.string.free_reduced, R.string.standard)
    val lunchSelectedOption = mutableIntStateOf(0)

    val preparationRadioOptions = listOf(R.string.completed, R.string.none)
    val preparationSelectedOption = mutableIntStateOf(0)

    val parentMartialStatusOptions = listOf(
        R.string.divorced,
        R.string.married,
        R.string.single,
        R.string.widowed,
    )
    val parentMartialStatusSelected = mutableIntStateOf(0)

    val sportOptions = listOf(
        R.string.never,
        R.string.regularly,
        R.string.sometimes,
    )
    val sportSelected = mutableIntStateOf(0)

    val firstChildOptions = listOf(
        R.string.no,
        R.string.yes,
    )
    val firstChildSelected = mutableIntStateOf(0)

    val numberOfSiblingsSelected = mutableIntStateOf(0)

    val transportOptions = listOf(
        R.string.private_transport,
        R.string.school_bus,
    )
    val transportSelected = mutableIntStateOf(0)

    val weeklyStudyHoursOptions = listOf(
        R.string.between_5_and_10,
        R.string.less_than_5,
        R.string.more_than_10
    )
    val weeklyStudyHoursSelected = mutableIntStateOf(0)


    fun start(){
        val predictionData = resolvePredictionData()
        dataRepository.start(predictionData)
    }

    private fun resolvePredictionData(): Map<String, Float>{
        val resolved = mapOf(
            "Gender" to genderSelectedOption.value.toFloat(),
            "EthnicGroup" to ethnicitySelected.value.toFloat(),
            "ParentEduc" to parentEducationSelected.value.toFloat(),
            "LunchType" to lunchSelectedOption.value.toFloat(),
            "TestPrep" to preparationSelectedOption.value.toFloat(),
            "ParentMaritalStatus" to parentMartialStatusSelected.value.toFloat(),
            "PracticeSport" to sportSelected.value.toFloat(),
            "IsFirstChild" to firstChildSelected.value.toFloat(),
            "NrSiblings" to numberOfSiblingsSelected.value.toFloat(),
            "TransportMeans" to transportSelected.value.toFloat(),
            "WklyStudyHours" to weeklyStudyHoursSelected.value.toFloat(),
        )

        println(resolved)

        return resolved
    }
}