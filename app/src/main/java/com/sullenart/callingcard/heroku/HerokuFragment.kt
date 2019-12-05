package com.sullenart.callingcard.heroku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_heroku.*
import com.sullenart.callingcard.BaseFragment
import com.sullenart.callingcard.MainApplication
import com.sullenart.callingcard.R
import javax.inject.Inject

class HerokuFragment : BaseFragment(), HerokuPresenter.View {
    @Inject lateinit var presenter: HerokuPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity?.application as MainApplication).component.inject(this)
        presenter.view = this

        return inflater.inflate(R.layout.fragment_heroku, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reverse.setOnClickListener { presenter.onReverseClicked() }
        close.setOnClickListener { presenter.onCloseClicked() }
        source.setOnClickListener { presenter.onSourceClicked() }
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.stop()
    }

    override fun getInput(): String {
        return input.text.toString()
    }

    override fun showResult(value: String) {
        result.text = value
        result.visibility = View.VISIBLE
        close.visibility = View.VISIBLE
        progress.isVisible = false
    }

    override fun clearResult() {
        result.text = ""
    }

    override fun showResultSection(show: Boolean) {
        result_group.isVisible = show
    }

    override fun showProgress(show: Boolean) {
        progress.isVisible = show
    }
}