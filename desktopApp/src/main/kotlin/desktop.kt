import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.atg.tbs_k.Entry

fun main() = application {
    Window(title = "TBS", onCloseRequest = ::exitApplication) {
        Entry()
    }
}

