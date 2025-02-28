package com.okproject.flowless.di

import com.okproject.flowless.editor.NoteEditorViewModel
import com.okproject.flowless.role.RoleViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::RoleViewModel)
    viewModelOf(::NoteEditorViewModel)
}