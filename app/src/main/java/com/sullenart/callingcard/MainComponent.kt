package com.sullenart.callingcard

import dagger.Component
import com.sullenart.callingcard.heroku.HerokuFragment
import com.sullenart.callingcard.home.HomeFragment
import com.sullenart.callingcard.lambda.LambdaFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: HerokuFragment)
    fun inject(fragment: LambdaFragment)
}
