package com.sullenart.callingcard.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sullenart.callingcard.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomePresenter.View {
    @Inject lateinit var presenter: HomePresenter

    private val profileAdapter = ProfileAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (activity?.application as MainApplication).component.inject(this)
        presenter.view = this

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val source = BitmapFactory.decodeResource(resources, R.drawable.avatar)
        home_image.setImageBitmap(source.getRounded())

        profile.adapter = profileAdapter

        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.stop()
    }

    override fun showProfile(entries: List<ProfileEntry>) {
        profileAdapter.entries.setAll(entries)
        profileAdapter.notifyDataSetChanged()
    }
}
