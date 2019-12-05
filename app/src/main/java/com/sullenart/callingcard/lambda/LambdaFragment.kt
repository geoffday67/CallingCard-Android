package com.sullenart.callingcard.lambda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_lambda.*
import com.sullenart.callingcard.BaseFragment
import com.sullenart.callingcard.MainApplication
import com.sullenart.callingcard.R
import javax.inject.Inject

class LambdaFragment : BaseFragment(), LambdaPresenter.View {
    @Inject lateinit var presenter: LambdaPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity?.application as MainApplication).component.inject(this)
        presenter.view = this

        return inflater.inflate(R.layout.fragment_lambda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        capitalise.setOnClickListener { presenter.onCapitaliseClicked() }
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

    override fun showResult(value: LambdaOutput) {
        result.text = value.uppercase
        result.visibility = View.VISIBLE
        device.text = value.device
        device.visibility = View.VISIBLE
        close.visibility = View.VISIBLE
        progress.isVisible = false
    }

    override fun clearResult() {
        result.text = ""
        device.text = ""
    }

    override fun showResultSection(show: Boolean) {
        result_group.isVisible = show
    }

    override fun showProgress(show: Boolean) {
        progress.isVisible = show
    }
}