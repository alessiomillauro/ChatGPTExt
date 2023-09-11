package com.android.chatgptext

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.android.chatgptext.ui.common.AppBar
import com.android.chatgptext.ui.common.AppScaffold
import com.android.chatgptext.ui.theme.ChatGPTExtTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(ComposeView(this).apply {
            consumeWindowInsets = false
            setContent {

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                val scope = rememberCoroutineScope()
                val focusManager = LocalFocusManager.current
                BackHandler {
                    if (drawerState.isOpen) {
                        scope.launch {
                            drawerState.close()
                        }
                    } else {
                        focusManager.clearFocus()
                    }
                }
                val darkTheme = remember(key1 = "darkTheme") {
                    mutableStateOf(true)
                }
                ChatGPTExtTheme(darkTheme = darkTheme.value) {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        AppScaffold(
                            drawerState = drawerState,
                            onChatClicked = {
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            onNewChatClicked = {
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            onIconClicked = {
                                scope.launch {
                                    darkTheme.value = !darkTheme.value
                                }
                            }
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                AppBar(onClickMenu = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                })
                                Divider()
                                //Conversation()
                            }
                        }
                    }
                }
            }
        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatGPTExtTheme {
        Greeting("Android")
    }
}