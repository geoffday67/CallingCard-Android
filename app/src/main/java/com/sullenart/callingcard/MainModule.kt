package com.sullenart.callingcard

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import com.sullenart.callingcard.heroku.HerokuReverser
import com.sullenart.callingcard.lambda.LambdaCapitaliser
import javax.inject.Singleton

@Module
class MainModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideHerokuReverser() = HerokuReverser()

    @Singleton
    @Provides
    fun provideLambdaCapitaliser() = LambdaCapitaliser(context)
}
