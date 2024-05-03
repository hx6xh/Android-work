/*第二次作业*/
package com.example.hx
import android.os.Bundle
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView

    private val imageList = listOf(
        R.mipmap.g1,
        R.mipmap.g2,
        R.mipmap.g3,
        R.mipmap.g4
    )

    private val titleList = listOf(
        "Title 1",
        "Title 2",
        "Title 3",
        "Title 4"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)

        imageView = findViewById(R.id.imageview)
        titleTextView = findViewById(R.id.textview)

        button1.setOnClickListener {
            displayImageAndTitle(0)
            highlightButton(button1)

        }

        button2.setOnClickListener {
            displayImageAndTitle(1)
            highlightButton(button2)
        }

        button3.setOnClickListener {
            displayImageAndTitle(2)
            highlightButton(button3)
        }

        button4.setOnClickListener {
            displayImageAndTitle(3)
            highlightButton(button4)
        }
    }
    var ant = Animation.REVERSE
        //Animation

    private fun displayImageAndTitle(index: Int) {
        imageView.setImageResource(imageList[index])
        titleTextView.text = titleList[index]
    }

    private fun highlightButton(button: Button) {
        val buttons = listOf<Button>(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4)
        )

        buttons.forEach {
            it.setBackgroundResource(if (it == button) R.color.red else R.color.gray)
        }
    }
}

/*
package com.example.hx

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val imageView = findViewById<ImageView>(R.id.imageview)
        val textView = findViewById<TextView>(R.id.textview)
        val redColor = ContextCompat.getColor(this,R.color.red)
        val defaultColor = ContextCompat.getColor(this,R.color.gray)

        fun resetButtons() {
            button1.setBackgroundColor(defaultColor)
            button2.setBackgroundColor(defaultColor)
            button3.setBackgroundColor(defaultColor)
            button4.setBackgroundColor(defaultColor)
        }

        button1.setOnClickListener {
            resetButtons()
            button1.setBackgroundColor(redColor)
            imageView.setImageResource(R.mipmap.g1)
            textView.text = "标题1"
        }
        button2.setOnClickListener {
            resetButtons()
            button2.setBackgroundColor(redColor)
            imageView.setImageResource(R.mipmap.g2)
            textView.text = "标题2"
        }
        button3.setOnClickListener {
            resetButtons()
            button3.setBackgroundColor(redColor)
            imageView.setImageResource(R.mipmap.g3)
            textView.text = "标题3"
        }
        button4.setOnClickListener {
            resetButtons()
            button4.setBackgroundColor(redColor)
            imageView.setImageResource(R.mipmap.g4)
            textView.text = "标题4"
        }
    }

}
 */
