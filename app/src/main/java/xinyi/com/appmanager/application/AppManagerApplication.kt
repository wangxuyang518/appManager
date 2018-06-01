package xinyi.com.appmanager.application;

import com.xylibrary.application.XinYiApplication
import xinyi.com.appmanager.di.component.ApplicationComponent
import xinyi.com.appmanager.di.component.DaggerApplicationComponent
import xinyi.com.appmanager.di.module.ApplicationModule

/**
 * Created by jiajun.wang on 2018/4/18.
 */

class AppManagerApplication : XinYiApplication() {

    public lateinit var mApplicationComponent: ApplicationComponent;

    companion object {
        public lateinit var mAppManagerApplication: AppManagerApplication;
    }

    override fun onCreate() {
        super.onCreate()
        mAppManagerApplication = this
        initDagger2()
    }

    fun initDagger2() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}
