package xinyi.com.appmanager.di.component;

import dagger.Component
import xinyi.com.appmanager.di.module.ApplicationModule
import javax.inject.Singleton


/**
 * Created by wxy on 2017/9/4.
 */
@Singleton
@Component(modules =[(ApplicationModule::class)])
interface ApplicationComponent {



}
