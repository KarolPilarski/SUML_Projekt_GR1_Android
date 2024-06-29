package pl.karolpilarski.mathscorepredictor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import pl.karolpilarski.mathscorepredictor.ui.form.FormComposable
import pl.karolpilarski.mathscorepredictor.ui.main.MainComposable
import pl.karolpilarski.mathscorepredictor.ui.theme.MathScorePredictorTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathScorePredictorTheme {
                MainComposable()
            }
        }
    }
}
