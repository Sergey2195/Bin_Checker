package com.ssv.binchecker

import android.app.Application
import com.ssv.binchecker.di.DaggerApplicationComponent

/***
 * Custom application storing component
 */

class BinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}