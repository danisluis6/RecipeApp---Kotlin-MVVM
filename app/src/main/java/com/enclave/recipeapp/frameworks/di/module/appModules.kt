package com.vogo.geographyintellect.frameworks.di.module

import com.enclave.recipeapp.lib.XmlParserUtils
import org.koin.dsl.module

val appModule = module {
    single { return@single XmlParserUtils(get()) }
}