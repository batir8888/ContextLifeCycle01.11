package ru.batir8888.contextlifecycle0111

import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources

class MainActivity : AppCompatActivity() {
    private var lastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.ColorButton)
        var LLColor1 = findViewById<LinearLayout>(R.id.LLColor1)
        var editText = findViewById<EditText>(R.id.EditText)
        var textView = findViewById<TextView>(R.id.TextView)
        var LLColor2 = findViewById<LinearLayout>(R.id.LLColor2)
        var imageView = findViewById<ImageView>(R.id.ImageView)

        var colorState1 = "blue"
        var colorState2 = "red"

        button.setOnClickListener{
            when (colorState1){
                "blue" -> {
                    colorState1 = "red"
                    LLColor1.background = AppCompatResources.getDrawable(this@MainActivity, R.color.red)
                }
                "red" -> {
                    colorState1 = "orange"
                    LLColor1.background = AppCompatResources.getDrawable(this@MainActivity, R.color.orange)
                }
                "orange" -> {
                    colorState1 = "blue"
                    LLColor1.background = AppCompatResources.getDrawable(this@MainActivity, R.color.blue)
                }
            }
        }

        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textView.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                imageView.visibility = View.VISIBLE
            }

        })

        textView.setOnLongClickListener {
            when (colorState2) {
                "red" -> {
                    colorState2 = "green"
                    LLColor2.background = AppCompatResources.getDrawable(this@MainActivity, R.color.green)
                }
                "green" -> {
                    colorState2 = "yellow"
                    LLColor2.background = AppCompatResources.getDrawable(this@MainActivity, R.color.yellow)
                }
                "yellow" -> {
                    colorState2 = "red"
                    LLColor2.background = AppCompatResources.getDrawable(this@MainActivity, R.color.red)
                }
            }
            true
        }

        imageView.setOnClickListener(object : DoubleClickListener(){
            override fun onDoubleClick(v: View?) {
                imageView.visibility = if (imageView.visibility == View.VISIBLE) {
                    View.INVISIBLE
                } else {
                    View.VISIBLE
                }
            }
        })
    }
    abstract class DoubleClickListener : View.OnClickListener {

        companion object{
            private const val DOUBLE_CLICK_TIME_DELTA: Long = 300
        }
        var lastClickTime: Long = 0

        override fun onClick(v: View?) {
            val clickTime = System.currentTimeMillis()
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                onDoubleClick(v)
            }
            lastClickTime = clickTime
        }

        abstract fun onDoubleClick(v: View?)
    }
}