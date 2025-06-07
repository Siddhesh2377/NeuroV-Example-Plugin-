package com.test.plugin_example

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dark.plugin_api.info.Plugin
import org.json.JSONArray
import org.json.JSONObject

class PluginExample(context: Context) : Plugin(context) {

    override fun getName(): String = ""

    override fun onStart() {
        Log.d(getName(), "onStart called()")
    }


    override fun onStop() {
        Log.d(getName(), "onStop called()")
    }

    override fun submitAiRequest(prompt: String): JSONObject {
        return JSONObject().apply {
            put("messages", JSONArray().apply {
                put(JSONObject().apply {
                    put("role", "system")
                    put("content", "You are a ai agent plugin")
                })
                put(JSONObject().apply {
                    put("role", "user")
                    put("content", prompt)
                })
            })
        }
    }

    override fun onAiResponse(response: JSONObject): ViewGroup {
        val root = LinearLayout(context).apply {
            layoutParams = fillMaxSize()
            gravity = Gravity.CENTER
            addView(TextView(context).apply {
                text = response.toString(2)
            })
        }
        return root
    }
}


