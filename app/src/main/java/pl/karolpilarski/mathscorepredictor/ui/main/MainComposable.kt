package pl.karolpilarski.mathscorepredictor.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import pl.karolpilarski.mathscorepredictor.ui.form.FormComposable
import pl.karolpilarski.mathscorepredictor.ui.loading.LoadingComposable
import pl.karolpilarski.mathscorepredictor.ui.result.ResultComposable

@Composable
fun MainComposable(viewModel: MainViewModel = hiltViewModel()) {

    val currentState = viewModel.currentState.collectAsState(initial = MainState.READY)

    when(currentState.value){
        MainState.READY -> FormComposable()
        MainState.LOADING -> LoadingComposable()
        MainState.RESULT -> ResultComposable()
    }
}