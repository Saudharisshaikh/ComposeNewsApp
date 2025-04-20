package com.example.composenewsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.composenewsapp.manager.usecases.AppEntryUserCases
import com.example.composenewsapp.onboarding.OnboardingViewModel
import com.example.composenewsapp.presentation.navgraph.NavGraph
import com.example.composenewsapp.ui.theme.NewsAppTheme
import com.example.e_pharmacycompose.presentation.onboarding.OnBoardingScreen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {

            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }




        setContent {

            NewsAppTheme {

//                val isSystemInDarkMode = isSystemInDarkTheme()
//                val systemController = rememberSystemUiController()
//
//                SideEffect {
//
//                    systemController.setSystemBarsColor(
//                        color = Color.Transparent,
//                        darkIcons = !isSystemInDarkMode
//
//                    )
//                }

                // SideEffect is here becuase when we want to execute uncomposable code in compose function we use SideEffect.

                val startDestination = viewModel.startDestination
                NavGraph(startDestination = startDestination)
            }


        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}







//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeNewsAppTheme {
//        Greeting("Android")
//    }
//}