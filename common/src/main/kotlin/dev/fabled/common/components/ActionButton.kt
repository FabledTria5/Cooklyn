package dev.fabled.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fabled.common.ui.Active
import dev.fabled.common.ui.SegoeUi

@Composable
fun ActionButton(
    text: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Active),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        text()
    }
}

@Preview(showBackground = true)
@Composable
fun ActionButtonPreview() {
    ActionButton(
        text = {
            Text(
                text = "Get Started!",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                color = Color.Black,
                fontFamily = SegoeUi,
                fontWeight = FontWeight.SemiBold
            )
        },
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(10.dp)
    )
}