package xinyi.com.appmanager.ui.main

import android.content.Intent
import android.view.Window
import android.view.WindowManager
import com.blankj.utilcode.util.ActivityUtils
import com.xylibrary.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import xinyi.com.appmanager.R
import java.util.concurrent.TimeUnit

public class Splashactivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_splash;
    }

    override fun initView() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<Long> {
                    override fun accept(t: Long) {
                        ActivityUtils.startActivity(Intent(this@Splashactivity, MainActivity::class.java))
                        finish()
                    }
                })
    }

}