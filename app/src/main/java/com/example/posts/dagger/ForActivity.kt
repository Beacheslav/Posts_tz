package com.example.posts.dagger

import javax.inject.Qualifier
import javax.inject.Scope

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
@Scope
annotation class ForActivity