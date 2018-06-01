package xinyi.com.appmanager.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.loader.ImageLoader


class GlideImageLoader : ImageLoader() {

    companion object {
        public fun  showRoundImage(context: Context,url:String,view:ImageView){
            val requestOptions = RequestOptions.circleCropTransform()
            Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528341723&di=f9cafc72c81bf2aad52f7ce91c17cc2b&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.duoziwang.com%2F2018%2F04%2F2410452717836.jpg").apply(requestOptions).into(view)
        }
    }


    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context).load(path).into(imageView)
    }

}