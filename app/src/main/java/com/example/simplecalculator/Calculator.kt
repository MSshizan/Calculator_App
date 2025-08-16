package com.example.simplecalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplecalculator.ui.theme.SimpleCalculatorTheme

@Composable
fun Calculator(viewModel: CalculatprViewModel) {
    val buttons = CalculatorButtons.buttonList
    val calculationText = viewModel.calculation.observeAsState()
    val resultText = viewModel.result.observeAsState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = calculationText.value ?: "",
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.End
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5,

                    )

                Text(
                    text = resultText.value ?: "",
                    style = TextStyle(
                        fontSize = 40.sp
                    ),
                    maxLines = 2,

                    )


            }

        }

        Spacer(modifier = Modifier.padding(20.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            LazyVerticalGrid(
                verticalArrangement = Arrangement.Center,
                columns = GridCells.Fixed(4)
            ) {
                items(buttons) {
                    CalButtons(button = it, onclick = { viewModel.onclick(it.label) })
                }
            }

        }

    }


}


@Composable
fun CalButtons(button: CalculatorButton, onclick: () -> Unit) {
    val isOperator = button.type == ButtonType.OPERATOR
    Box(
        modifier = Modifier.padding(10.dp),
    ) {
        FloatingActionButton(
            modifier = Modifier.size(80.dp),
            onClick = onclick,
            contentColor = if (isOperator) Color.White else Color.Black,
            containerColor = if (isOperator) Color(0xFFFF9800) else Color.LightGray,
            shape = if (!isOperator) CircleShape else FloatingActionButtonDefaults.shape,
        ) {
            Text(
                text = button.label,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp
                )
            )

        }


    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCalculatorTheme {
        Calculator(viewModel = CalculatprViewModel())
    }
}

