package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun CalculatorInstructionRoute(
    padding: PaddingValues,
    navigateToCalculator: () -> Unit
) {
    CalculatorInstructionScreen(
        padding = padding,
        navigateToCalculator = navigateToCalculator
    )
}

@Composable
private fun CalculatorInstructionScreen(
    padding: PaddingValues,
    navigateToCalculator: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(all = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "탄소발자국은 일상 속 다양한 활동에서 얼마나 많은 탄소가 배출되고 있는지 나타내는 지표입니다." +
                    "\n\n탄소발자국 계산을 통해 우리의 일상이 기후변화에 어느 정도로 영향을 미치고 있는지 간접적으로 확인할 수 있습니다.\n"
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "사용 방법\n" +
                    "1. 아래 항목 중 계산기에 입력할 항목을 선택합니다.\n" +
                    "2. 다음 화면에서 각 항목에 대한 사용량을 입력합니다.\n" +
                    "3. 결과 화면에서 총 CO₂ 발생량을 확인합니다.\n"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { }
                )
                Text("전기")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { }
                )
                Text("가스")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { }
                )
                Text("수도")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { }
                )
                Text("생활 폐기물")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = navigateToCalculator) {
            Text(modifier = Modifier.padding(8.dp), text = "계산기 시작")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorInstructionScreenPreview() {
    CalculatorInstructionScreen(
        padding = PaddingValues(),
        navigateToCalculator = {}
    )
}