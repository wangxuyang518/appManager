package xinyi.com.appmanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xylibrary.base.BaseActivity;
import com.xylibrary.base.RxPresenter;

import javax.inject.Inject;

import xinyi.com.appmanager.application.AppManagerApplication;
import xinyi.com.appmanager.di.component.ActivityComponent;
import xinyi.com.appmanager.di.component.DaggerActivityComponent;
import xinyi.com.appmanager.di.module.ActivityModule;


/**
 * Created by jiajun.wang on 2018/3/19.
 */

public abstract class BaseMvpActivity<T extends RxPresenter> extends BaseActivity {

    @Inject
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }
    public ActivityComponent getDaggerActivityComponent() {
        return  DaggerActivityComponent.builder()
                .applicationComponent(AppManagerApplication.mAppManagerApplication.mApplicationComponent)
                .activityModule(new ActivityModule(this))
                .build();
    }


    public abstract void inject();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
