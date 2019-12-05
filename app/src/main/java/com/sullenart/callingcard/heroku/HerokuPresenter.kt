package com.sullenart.callingcard.heroku

import android.content.Intent
import android.net.Uri
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import com.sullenart.callingcard.BasePresenter
import com.sullenart.callingcard.BaseView
import javax.inject.Inject

class HerokuPresenter @Inject constructor(
    private val herokuReverser: HerokuReverser
) : BasePresenter() {
    lateinit var view: View

    fun onReverseClicked() {
        with (view) {
            clearResult()
            showProgress(true)
            showResultSection(true)
        }

        add(herokuReverser.reverse(view.getInput())
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
        val uri = Uri.parse("https://github.com/geoffday67/callingcard-heroku/blob/master/index.js")
        view.startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    interface View: BaseView {
        fun getInput(): String
        fun showResultSection(show: Boolean)
        fun clearResult()
        fun showResult(value: String)
        fun showProgress(show: Boolean)
    }
}