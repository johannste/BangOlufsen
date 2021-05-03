package com.bangolufsen.soundcontroller

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bangolufsen.soundcontroller.utils.PropertiesUtil

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var wvMain: WebView
    private lateinit var btnSoundLevels: Button
    private lateinit var btnSoundControls: Button
    private lateinit var soundLevelsUrl: String
    private lateinit var soundControlsUrl: String
    private var isShow: Boolean = false

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundLevelsUrl = PropertiesUtil.getProperty(applicationContext, "soundLevelsUrl")
        if (soundLevelsUrl.isBlank()) {
            return
        }
        isShow = true
        soundControlsUrl = PropertiesUtil.getProperty(applicationContext, "soundControlsUrl")

        wvMain = findViewById(R.id.webview)
        wvMain.setBackgroundColor(0)
        wvMain.loadUrl(soundLevelsUrl)
        wvMain.settings.javaScriptEnabled = true

        btnSoundLevels = findViewById(R.id.btnSoundLevels)
        btnSoundLevels.setOnClickListener(this)
        btnSoundControls = findViewById(R.id.btnSoundControls)
        btnSoundControls.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        if (isShow) {
            btnSoundLevels.visibility = View.VISIBLE
            btnSoundControls.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSoundLevels -> {
                if (wvMain.url == soundLevelsUrl) {
                    return
                }
                wvMain.loadUrl(soundLevelsUrl)
            }
            R.id.btnSoundControls -> {
                if (wvMain.url == soundControlsUrl) {
                    return
                }
                wvMain.loadUrl(soundControlsUrl)
            }
        }
    }

}