package com.ssv.binchecker

import android.app.Application
import com.ssv.binchecker.di.DaggerApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class BinApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this, appScope)
    }

    private val appScope by lazy {
        CoroutineScope(Dispatchers.IO)
    }
}