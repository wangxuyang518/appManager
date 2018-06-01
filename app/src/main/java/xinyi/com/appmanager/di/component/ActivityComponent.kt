package xinyi.com.appmanager.di.component;


import dagger.Component
import xinyi.com.appmanager.base.BaseMvpActivity
import xinyi.com.appmanager.di.module.ActivityModule
import xinyi.com.appmanager.di.scope.ActivityScope
import xinyi.com.appmanager.ui.main.MainActivity
import xinyi.com.appmanager.ui.main.Splashactivity


/**
 * Created by wxy on 2017/9/4.
 */

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    val activity: BaseMvpActivity<*>

    fun inject(mSplashActivity: Splashactivity)
    fun inject(mSplashActivity: MainActivity)
}

