@file:OptIn(ExperimentalMaterial3Api::class)

package pl.karolpilarski.mathscorepredictor.ui.form

import android.media.Image
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.karolpilarski.mathscorepredictor.R


@Composable
fun FormComposable(viewModel: FormViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.baner1),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            stringResource(R.string.form_desc),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        DoubleRadioSelector(
            iconDrawableRes = R.drawable.gender,
            labelRes = R.string.gender,
            values = viewModel.genderRadioOptions,
            selectedTextState = viewModel.genderSelectedOption
        )

        DoubleRadioSelector(
            iconDrawableRes = R.drawable.child_care,
            labelRes = R.string.first_child,
            values = viewModel.firstChildOptions,
            selectedTextState = viewModel.firstChildSelected
        )


        DoubleRadioSelector(
            iconDrawableRes = R.drawable.bus,
            labelRes = R.string.transport,
            values = viewModel.transportOptions,
            selectedTextState = viewModel.transportSelected
        )

        DoubleRadioSelector(
            iconDrawableRes = R.drawable.lunch,
            labelRes = R.string.lunch,
            values = viewModel.lunchRadioOptions,
            selectedTextState = viewModel.lunchSelectedOption
        )

        DoubleRadioSelector(
            iconDrawableRes = R.drawable.library,
            labelRes = R.string.preparation,
            values = viewModel.preparationRadioOptions,
            selectedTextState = viewModel.preparationSelectedOption
        )


        DropdownSelector(
            iconDrawableRes = R.drawable.diversity,
            labelRes = R.string.ethnicity,
            values = viewModel.ethnicityOptions,
            selectedTextState = viewModel.ethnicitySelected
        )

        DropdownSelector(
            iconDrawableRes = R.drawable.school,
            labelRes = R.string.parent_education,
            values = viewModel.parentEducationOptions,
            selectedTextState = viewModel.parentEducationSelected
        )

        DropdownSelector(
            iconDrawableRes = R.drawable.family,
            labelRes = R.string.parent_martial_status,
            values = viewModel.parentMartialStatusOptions,
            selectedTextState = viewModel.parentMartialStatusSelected
        )

        DropdownSelector(
            iconDrawableRes = R.drawable.soccer,
            labelRes = R.string.sport,
            values = viewModel.sportOptions,
            selectedTextState = viewModel.sportSelected
        )

        IntRangeDropdownSelector(
            iconDrawableRes = R.drawable.group,
            labelRes = R.string.number_of_siblings,
            intRange = 0..7,
            selectedTextState = viewModel.numberOfSiblingsSelected
        )

        DropdownSelector(
            iconDrawableRes = R.drawable.schedule,
            labelRes = R.string.weekly_study_hours,
            values = viewModel.weeklyStudyHoursOptions,
            selectedTextState = viewModel.weeklyStudyHoursSelected
        )

        Button(
            onClick = {
                viewModel.start()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                stringResource(R.string.submit),
                fontSize = 16.sp)
        }
    }
}

@Composable
private fun InputLabel(icon: Painter, labelRes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(0.4f)
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

@Composable
private fun DoubleRadioSelector(
    iconDrawableRes: Int,
    labelRes: Int,
    values: List<Int>,
    selectedTextState: MutableState<Int>

) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            InputLabel(icon = painterResource(id = iconDrawableRes), labelRes = labelRes)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                DoubleRadioSelectorItem(0, values, selectedTextState)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                DoubleRadioSelectorItem(1, values, selectedTextState)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider()
    }

}

@Composable
private fun DoubleRadioSelectorItem(
    itemInt: Int,
    values: List<Int>,
    selectedTextState: MutableState<Int>
) {
    RadioButton(
        selected = selectedTextState.value == itemInt,
        onClick = {
            selectedTextState.value = itemInt
        }
    )

    Text(
        stringResource(id = values[itemInt]),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
private fun DropdownSelector(
    iconDrawableRes: Int,
    labelRes: Int,
    values: List<Int>,
    selectedTextState: MutableState<Int>
) {
    var expanded by remember { mutableStateOf(false) }

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputLabel(icon = painterResource(id = iconDrawableRes), labelRes = labelRes)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = stringResource(id = values[selectedTextState.value]),
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        values.forEachIndexed { inx, _ ->
                            DropdownMenuItem(
                                text = { Text(text = stringResource(id = values[inx])) },
                                onClick = {
                                    selectedTextState.value = inx
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider()
    }
}

@Composable
private fun IntRangeDropdownSelector(
    iconDrawableRes: Int,
    labelRes: Int,
    intRange: IntRange,
    selectedTextState: MutableState<Int>
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        ) {
            InputLabel(icon = painterResource(id = iconDrawableRes), labelRes = labelRes)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = selectedTextState.value.toString(),
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        intRange.forEach {
                            DropdownMenuItem(
                                text = { Text(text = it.toString()) },
                                onClick = {
                                    selectedTextState.value = it
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider()
    }
}