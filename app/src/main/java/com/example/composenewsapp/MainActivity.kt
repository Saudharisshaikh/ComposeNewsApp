package com.example.composenewsapp

import android.app.Application
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
import androidx.room.Room
import com.example.composenewsapp.data.local.ArticlesDao
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.data.local.NewArticleDatabase
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.model.Source
import com.example.composenewsapp.manager.usecases.AppEntryUserCases
import com.example.composenewsapp.onboarding.OnboardingViewModel
import com.example.composenewsapp.presentation.navgraph.NavGraph
import com.example.composenewsapp.ui.theme.NewsAppTheme
import com.example.e_pharmacycompose.presentation.onboarding.OnBoardingScreen
import com.example.e_pharmacycompose.utils.Constants
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<MainViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        try {

            lifecycleScope.launch {
                upsertNow()
            }
//          mainExecutor.execute(kotlinx.coroutines.Runnable {
//
//              val db = Room.databaseBuilder(
//                  context = ComposeNewApp.getAppContext(),
//                  klass = NewArticleDatabase::class.java,
//                  name = Constants.DATABASE_NAME
//
//              )
//                  .fallbackToDestructiveMigration()
//                  .build()
//
//              val articleDao = db.articlesDao
//
//
//
//              articleDao.upsert(
//                  articlesEntity = ArticlesEntity(
//                      author = "",
//                      title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
//                      description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
//                      content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
//                      publishedAt = "2023-06-16T22:24:33Z",
//                      url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1" ,
//                      id = "",
//                      name = "bbc",
//                      urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
//                  ))
//          })




        }
         catch (e:Exception){
             Log.d("--er", "onCreate: "+e.message+" ")
             e.printStackTrace()

         }



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


    suspend fun upsertNow() = withContext(Dispatchers.IO) {
        try {
            val db = Room.databaseBuilder(
                context = ComposeNewApp.getAppContext(),
                klass = NewArticleDatabase::class.java,
                name = Constants.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

            val articleDao = db.articlesDao
            val source = Source(id = "", name = "bbc")
            val article = Article(
                author = "",
                title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                content = "We use cookies and data to Deliver and maintain Google services...",
                publishedAt = "2023-06-16T22:24:33Z",
                url = "https://consent.google.com/ml?...",
                urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg",
                source = source
            )
            val convertedArticleEntity = Constants.convertToArticleEntity(article)
            articleDao.upsert(
                convertedArticleEntity
            )
            Log.d("--database", "upsertNow:created: ")
        } catch (e: Exception) {
            Log.d("--er", "onCreate: ${e.message} ")
            e.printStackTrace()
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