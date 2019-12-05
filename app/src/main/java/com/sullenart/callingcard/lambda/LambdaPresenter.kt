package com.sullenart.callingcard.lambda

import android.content.Intent
import android.net.Uri
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import com.sullenart.callingcard.BasePresenter
import com.sullenart.callingcard.BaseView
import javax.inject.Inject

class LambdaPresenter @Inject constructor(
    private val lambdaCapitaliser: LambdaCapitaliser
) : BasePresenter() {
    lateinit var view: View

    fun onCapitaliseClicked() {
        with(view) {
            clearResult()
            showProgress(true)
            showResultSection(true)
        }

        add(lambdaCapitaliser.capitalise(view.getInput())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { view.showProgress(false) }
            .subscribeBy(
                onSuccess = { view.showResult(it) },
                onError = { Timber.e(it) }
            )
        )
    }

    fun onCloseClicked() {
        view.showResultSection(false)
    }

    fun onSourceClicked() {
        val uri = Uri.parse("https://github.com/geoffday67/callingcard-lambda/blob/master/capitalise.js")
        view.startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    interface View : BaseView {
        fun getInput(): String
        fun showResultSection(show: Boolean)
        fun clearResult()
        fun showResult(value: LambdaOutput)
        fun showProgress(show: Boolean)
    }
}
