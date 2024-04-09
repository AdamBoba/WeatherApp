package com.example.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        button.setOnClickListener {
            val city = editText.text.toString()
            if (city.isNotEmpty()) {
                getWeatherByCity(city, textView, textView2)
            }
        }
    }

    private fun getWeatherByCity(city: String, textView: TextView, textView2: TextView) {
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
                    }
                } else {
                    runOnUiThread {
                        textView.text = "Nie udało się znaleźć danych dla podanego miasta"
                    }
                }
            }
        })
    }
}
