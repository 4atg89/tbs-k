import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.atg.tbs_k.EnterPoint
import com.atg.tbs_k.di.appModule
import org.koin.compose.KoinApplication

fun main() = application {
    Window(title = "TBS", onCloseRequest = ::exitApplication) {
        KoinApplication(application = { modules(appModule()) }) {
            EnterPoint()
        }
    }
}

