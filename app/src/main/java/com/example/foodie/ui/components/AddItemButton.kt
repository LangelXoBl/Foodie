import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddItemButton(itemName: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF40454F),
            contentColor = Color.White,
        )
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add $itemName",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "$itemName")
        }
    }
}
