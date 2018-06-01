package xinyi.com.appmanager.di.module;

import dagger.Module
import dagger.Provides
import xinyi.com.appmanager.application.AppManagerApplication

import javax.inject.Singleton


/**
 * Created by wxy on 2017/9/4.
 */
@Module
class ApplicationModule(private val application: AppManagerApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): AppManagerApplication {
        return application
    }

}

