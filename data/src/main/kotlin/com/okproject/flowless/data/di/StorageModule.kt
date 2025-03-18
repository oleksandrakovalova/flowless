package com.okproject.flowless.data.di

import com.okproject.flowless.data.storage.BrushStorage
import com.okproject.flowless.data.storage.BrushStorageImpl
import com.okproject.flowless.data.storage.RoleStorage
import com.okproject.flowless.data.storage.RoleStorageImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storageModule = module {
    singleOf(::RoleStorageImpl) {
        bind<RoleStorage>()
    }
    singleOf(::BrushStorageImpl) {
        bind<BrushStorage>()
    }
}