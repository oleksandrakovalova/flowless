package com.okproject.flowless.data.di

import com.okproject.flowless.data.datasource.RoleDataSource
import com.okproject.flowless.data.datasource.RoleDataSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataSourceModule = module {
    singleOf(::RoleDataSourceImpl) {
        bind<RoleDataSource>()
    }
}