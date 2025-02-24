package com.okproject.flowless.logging

import org.koin.core.logger.KOIN_TAG
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import timber.log.Timber

class TimberLogger: Logger(level = Level.DEBUG) {
    override fun display(level: Level, msg: MESSAGE) {
        when (level) {
            Level.DEBUG -> Timber.tag(KOIN_TAG).d(msg)
            Level.INFO -> Timber.tag(KOIN_TAG).i(msg)
            Level.WARNING -> Timber.tag(KOIN_TAG).w(msg)
            Level.ERROR -> Timber.tag(KOIN_TAG).e(msg)
            Level.NONE -> { /* no-op */ }
        }
    }
}