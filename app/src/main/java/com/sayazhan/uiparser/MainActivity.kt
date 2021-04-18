package com.sayazhan.uiparser

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sayazhan.uiparser.models.Element

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val root = findViewById<ViewGroup>(R.id.root)
        val name = getElementTagName(root)
        val children = getChildren(root)

        val element = Element(name, children)

        Log.i(TAG, element.toString())
    }

    private fun getElementTagName(view: View): String {
        return when (view) {
            is LinearLayout -> "LinearLayout"
            is Button -> "Button"
            is TextView -> "TextView"
            else -> "Container"
        }
    }

    private fun getChildren(view: View): List<Element> {
        val elements = mutableListOf<Element>()

        if (view is ViewGroup) {
            val count: Int = view.childCount
            for (i in 0 until count) {
                val element = view.getChildAt(i)
                val name = getElementTagName(element)
                val children = getChildren(element)
                elements.add(Element(name, children))
            }
        }

        return elements
    }
}