package com.example.font

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import android.util.Log
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.font.ui.theme.FontTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 打印日志，确认进入了 onCreate 方法
        Log.d("MainActivity", "onCreate called")

        // 创建自定义视图并设置为内容视图
        val customTextView = CustomTextView(this)

        // 设置宽高参数
        val layoutParams = ViewGroup.LayoutParams(
            300, // 可以使用WRAP_CONTENT, MATCH_PARENT 或者具体的尺寸(例如，200)
            300  // 可以使用WRAP_CONTENT, MATCH_PARENT 或者具体的尺寸(例如，100)
        )
        customTextView.layoutParams = layoutParams


        setContentView(customTextView)

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
    FontTheme {
        Greeting("Android")
    }
}