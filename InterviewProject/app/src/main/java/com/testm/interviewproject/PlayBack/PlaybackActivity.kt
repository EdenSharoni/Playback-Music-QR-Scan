package com.testm.interviewproject.PlayBack

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.testm.demosdk.Audio
import com.testm.demosdk.DemoSdk
import com.testm.interviewproject.R
import kotlinx.android.synthetic.main.activity_playback.*

class PlaybackActivity : AppCompatActivity(), PlaybackAdapter.OnAudioClickListener {

    private val viewModel by lazy { ViewModelProvider(this)[PlaybackViewModel::class.java] }
    lateinit var audioArrayList: ArrayList<Audio>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)
        audioArrayList = DemoSdk.getUniqueAndValidAudios()
        recyclerView.adapter = PlaybackAdapter(audioArrayList, this)
        viewModel.setMediaPlayer()
    }

    override fun onAudioClick(position: Int, viewItem: View) {
        viewModel.onClick(audioArrayList.get(position).url!!, viewItem)
    }
}