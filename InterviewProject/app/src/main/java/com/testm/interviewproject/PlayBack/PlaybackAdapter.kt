package com.testm.interviewproject.PlayBack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testm.demosdk.Audio
import com.testm.interviewproject.R
import java.util.*

class PlaybackAdapter(private val dataSet: ArrayList<Audio>, val clickListener: OnAudioClickListener) : RecyclerView.Adapter<PlaybackAdapter.ViewHolder>() {

    class ViewHolder(view: View, onAudioClickListener: OnAudioClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val textView: TextView = view.findViewById(R.id.titleText)
        private val onClick: OnAudioClickListener = onAudioClickListener

        init { view.setOnClickListener(this) }

        override fun onClick(p0: View?) {
            onClick.onAudioClick(adapterPosition, p0!!)
        }
    }

    interface OnAudioClickListener { fun onAudioClick(position: Int, viewItem: View) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.audio, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.textView.text = dataSet[position].name }

    override fun getItemCount(): Int { return dataSet.size }
}