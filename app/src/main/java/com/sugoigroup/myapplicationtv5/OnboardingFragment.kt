package com.sugoigroup.myapplicationtv5

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.OnboardingSupportFragment

class OnboardingFragment : OnboardingSupportFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view?.setBackgroundColor(Color.BLACK)

        return view
    }

    override fun onFinishFragment() {
        super.onFinishFragment()
        // Our onboarding is done
        requireActivity().finish()
        startActivity(Intent(context , MainActivity::class.java))
    }

    override fun getPageCount(): Int {
        return 2
    }

    override fun getPageTitle(pageIndex: Int): CharSequence = when (pageIndex) {
        0 -> "처음 페이지"
        1 -> "마지막 페이지"
        else -> null.toString()
    }

    override fun getPageDescription(pageIndex: Int): CharSequence {
        return null.toString()
    }

    override fun onCreateBackgroundView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        return null
    }

    override fun onCreateContentView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        return null
    }

    override fun onCreateForegroundView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        return null
    }
}