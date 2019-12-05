package com.sullenart.callingcard.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sullenart.callingcard.ProfileEntry
import com.sullenart.callingcard.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.profile_item.view.*

class ProfileAdapter() : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    val entries = mutableListOf<ProfileEntry>()

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(entry: ProfileEntry) {
            with(containerView) {
                title.text = entry.title
                subtitle.text = entry.subtitle
                subtitle.isVisible = entry.subtitle.isNotEmpty()
                content.text = entry.content.replace("\\n", "\n")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.profile_item, parent, false))

    override fun getItemCount() = entries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entries[position])
    }
}
