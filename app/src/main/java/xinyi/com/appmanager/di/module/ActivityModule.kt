package xinyi.com.appmanager.di.module;

import dagger.Module
import dagger.Provides
import xinyi.com.appmanager.base.BaseMvpActivity
import xinyi.com.appmanager.di.scope.ActivityScope


/**
 * Created by wxy on 2017/9/4.
 */

@Module
class ActivityModule(public val mActivity: BaseMvpActivity<*>) {
    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @ActivityScope
    internal fun provideBaseMvpActivity(): BaseMvpActivity<*> {
        return this.mActivity
    }



}
