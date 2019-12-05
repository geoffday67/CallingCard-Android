package com.sullenart.callingcard.jni

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_jni.*
import com.sullenart.callingcard.BaseFragment
import com.sullenart.callingcard.R

class JniFragment : BaseFragment() {
    private var jniView: GL2JNIView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_jni, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jniView = GL2JNIView(safeContext)
        jni_display.addView(jniView)
    }


    override fun onPause() {
        super.onPause()
        jniView?.onPause()
    }

    override fun onResume() {
        super.onResume()
        jniView?.onResume()
    }
}