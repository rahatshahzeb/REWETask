package com.example.rewetask

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Sets the main coroutine dispatcher to a test dispatcher for unit testing.
 * A test dispatcher provides control over the execution of coroutines.
 */
internal class MainCoroutineExtension : BeforeEachCallback, AfterEachCallback {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun beforeEach(context: ExtensionContext) {
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun afterEach(context: ExtensionContext) {
        Dispatchers.resetMain()
    }

    companion object {
        @OptIn(ExperimentalCoroutinesApi::class)
        val dispatcher = UnconfinedTestDispatcher()
    }
}