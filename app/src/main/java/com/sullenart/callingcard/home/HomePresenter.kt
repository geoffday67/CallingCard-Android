package com.sullenart.callingcard.home

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import com.sullenart.callingcard.BasePresenter
import com.sullenart.callingcard.BaseView
import com.sullenart.callingcard.ProfileEntry
import com.sullenart.callingcard.fetch
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val gson: Gson
) : BasePresenter() {
    lateinit var view: View

    fun start() {
        add(
            Firebase.firestore
                .collection("profile")
                .fetch<ProfileEntry>()
                .toSortedList { o1, o2 -> o1.order.compareTo(o2.order) }
                .subscribeBy(
                    onSuccess = {
                        view.showProfile(it)
                    },
                    onError = {
                        Timber.e(it)
                    }
                )
        )
    }

    interface View: BaseView {
        fun showProfile(entries: List<ProfileEntry>)
    }
}

