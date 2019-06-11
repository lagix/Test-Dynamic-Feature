package br.com.lagix.dynamic_feature1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.play.core.splitcompat.SplitCompat

class Feat1Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SplitCompat.install(this)

        setContentView(R.layout.activity_feat1_main)
    }
}
