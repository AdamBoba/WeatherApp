package com.example.weatherapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val apiKey = "ec863e8d82abb802938a191603df2ae4"
    private val weatherBaseUrl = "https://api.openweathermap.org/data/2.5/weather?"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val backgroundLayout = findViewById<RelativeLayout>(R.id.backgroundLayout)

        button.setOnClickListener {
            val city = editText.text.toString()
            if (city.isNotEmpty()) {
                getWeatherByCity(city, textView, textView2, backgroundLayout)
            }
        }
    }

    private fun getWeatherByCity(city: String, textView: TextView, textView2: TextView, backgroundLayout: RelativeLayout) {
        val weatherUrl = "$weatherBaseUrl&q=$city&appid=$apiKey&units=metric"

        val request = Request.Builder().url(weatherUrl).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    textView.text = "Błąd podczas pobierania danych pogodowych"
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val json = JSONObject(body)
                if (json.has("main") && json.has("weather")) {
                    val main = json.getJSONObject("main")
                    val temperature = main.getDouble("temp")
                    val weatherArray = json.getJSONArray("weather")
                    val weatherObject = weatherArray.getJSONObject(0)
                    val description = weatherObject.getString("description")

                    val tempe = " $city: $temperature°C"
                    val zach = " $description"

                    runOnUiThread {
                        textView.text = tempe
                        textView2.text = zach
                        setWeatherBackground(description, backgroundLayout)
                    }
                } else {
                    runOnUiThread {
                        textView.text = "Nie udało się znaleźć danych dla podanego miasta"
                    }
                }
            }
        })
    }

    private fun setWeatherBackground(description: String, backgroundLayout: RelativeLayout) {
        val drawableResource = when (description.toLowerCase()) {
            "overcast clouds" -> R.drawable.cloudy
            "clear sky" -> R.drawable.day_clear
            "light rain" -> R.drawable.rainy_icon
            "mist" -> R.drawable.mist
            else -> R.drawable.cloudy
        }
        val drawable: Drawable? = ContextCompat.getDrawable(this, drawableResource)
        backgroundLayout.background = drawable
    }
}
