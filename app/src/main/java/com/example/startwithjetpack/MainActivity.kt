package com.example.startwithjetpack

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.startwithjetpack.PLPFeature.PLPRepository
import com.example.startwithjetpack.ui.theme.StartWithJetpackTheme
import com.yasser.networklayer.restAPIs.interfaces.BaseNetworkLayer
import com.yasser.networklayer.restAPIs.response.NetworkResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    @Inject
    lateinit var baseNetworkLayer: BaseNetworkLayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      installSplashScreen()

        setContent {
            StartWithJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }


        val repo = PLPRepository(baseNetworkLayer)

        lifecycleScope.launch {
            when (val response = repo.callApi(50, 1, 20, "position")) {
                is NetworkResponseState.Success -> {
                    Log.e("retrofitResponse", response.results.toString())
                }

                is NetworkResponseState.Fail -> {
                    Log.e("retrofitResponse", "${response.errorType?.name} ${response.error}")
                }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StartWithJetpackTheme {
        Greeting("Android")
    }
}