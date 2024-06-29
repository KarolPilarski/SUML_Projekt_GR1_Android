package pl.karolpilarski.mathscorepredictor.ui.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.karolpilarski.mathscorepredictor.R

@Composable
fun ResultComposable(viewModel: ResultViewModel = hiltViewModel()) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        val resultSummary = viewModel.summary.collectAsState(initial = ResultSummary.NEUTRAL)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(
                id = when (resultSummary.value) {
                    ResultSummary.NEUTRAL -> R.drawable.baner1
                    ResultSummary.PASSED -> R.drawable.banner_positive
                    ResultSummary.NOT_PASSED -> R.drawable.banner_negative
                }
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = when (resultSummary.value) {
                ResultSummary.NEUTRAL -> R.string.neutral_info
                ResultSummary.PASSED -> R.string.positive_info
                ResultSummary.NOT_PASSED -> R.string.negative_info
            }),
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider()

        ReadingResult(viewModel)
        WritingResult(viewModel)
        MathResult(viewModel)

        Button(
            onClick = {
                viewModel.back()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                stringResource(R.string.new_prediction),
                fontSize = 16.sp
            )
        }
    }

}

@Composable
fun ReadingResult(viewModel: ResultViewModel) {
    val readingResult = viewModel.readingResult.collectAsState()

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            ResultLabel(
                icon = painterResource(id = R.drawable.reading),
                labelRes = R.string.reading_score
            )

            Spacer(modifier = Modifier.weight(1f))

            readingResult.value?.let {
                Text(text = it, fontSize = 24.sp)
            } ?: run {
                CircularProgressIndicator(Modifier.size(20.dp))
            }
        }

        HorizontalDivider()
    }
}

@Composable
fun WritingResult(viewModel: ResultViewModel) {
    val writingResult = viewModel.writingResult.collectAsState()

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            ResultLabel(
                icon = painterResource(id = R.drawable.writing),
                labelRes = R.string.writing_score
            )

            Spacer(modifier = Modifier.weight(1f))

            writingResult.value?.let {
                Text(text = it, fontSize = 24.sp)
            } ?: run {
                CircularProgressIndicator(Modifier.size(20.dp))
            }
        }

        HorizontalDivider()
    }
}

@Composable
fun MathResult(viewModel: ResultViewModel) {
    val mathResult = viewModel.mathResult.collectAsState()

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            ResultLabel(
                icon = painterResource(id = R.drawable.math),
                labelRes = R.string.math_score
            )

            Spacer(modifier = Modifier.weight(1f))

            mathResult.value?.let {
                Text(text = it, fontSize = 24.sp)
            } ?: run {
                CircularProgressIndicator(Modifier.size(20.dp))
            }
        }

        HorizontalDivider()
    }
}

@Composable
private fun ResultLabel(icon: Painter, labelRes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
        )

        Text(
            text = stringResource(id = labelRes),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}