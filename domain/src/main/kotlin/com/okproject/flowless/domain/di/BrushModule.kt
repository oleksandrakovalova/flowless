package com.okproject.flowless.domain.di

import com.okproject.flowless.domain.brush.GetLatestBrushUseCase
import com.okproject.flowless.domain.brush.GetLatestBrushUseCaseImpl
import com.okproject.flowless.domain.brush.UpdateBrushUseCase
import com.okproject.flowless.domain.brush.UpdateBrushUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val brushModule = module {
    singleOf(::GetLatestBrushUseCaseImpl) {
        bind<GetLatestBrushUseCase>()
    }
    singleOf(::UpdateBrushUseCaseImpl) {
        bind<UpdateBrushUseCase>()
    }
}