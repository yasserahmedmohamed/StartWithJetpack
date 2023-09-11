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
import com.example.startwithjetpack.PLPFeature.PLPRepository
import com.example.startwithjetpack.ui.theme.StartWithJetpackTheme
import com.yasser.networklayer.restAPIs.NetworkLayer
import com.yasser.networklayer.restAPIs.response.NetworkResponseState
import com.yasser.networklayer.restAPIs.provider.retrofit.RetrofitNetworkProvider
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

        val provider = RetrofitNetworkProvider(hashMapOf(
            "authorization" to "Bearer 7i7xck3uns4eezn7isg6cngyjda5wzc2"
        ))

        val netLayer = NetworkLayer(provider)

        val repo = PLPRepository(netLayer)

        GlobalScope.launch {
          val response =   repo.callApi(50,1,20,"position")

            when(response){
                is NetworkResponseState.Success -> {
                    Log.e("retrofitResponse",response.results.toString())
                }

                is NetworkResponseState.Fail -> {
                    Log.e("retrofitResponse","${response.errorType?.name} ${response.error}")
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