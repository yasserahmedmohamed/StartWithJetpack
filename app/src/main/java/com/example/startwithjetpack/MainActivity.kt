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
import com.example.startwithjetpack.XFeature.XRepository
import com.yasser.networklayer.NetworkLayerImplementation
import com.yasser.networklayer.base.response.NetworkResponseState
import com.yasser.networklayer.provider.retrofit.RetrofitNetworkProvider
import com.example.startwithjetpack.ui.theme.StartWithJetpackTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val provider = RetrofitNetworkProvider()
        provider.startService()

        val netLayer = NetworkLayerImplementation(provider)

        val repo = XRepository(netLayer)

        GlobalScope.launch {
          val response =   repo.callApi()

            when(response){
                is NetworkResponseState.AuthorizationError -> TODO()
                is NetworkResponseState.GenericError -> TODO()
                is NetworkResponseState.NetworkConnectionError -> TODO()
                is NetworkResponseState.ServerError -> TODO()
                is NetworkResponseState.Success -> {
                    Log.e("CallResponse",response.results.products.toString())
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