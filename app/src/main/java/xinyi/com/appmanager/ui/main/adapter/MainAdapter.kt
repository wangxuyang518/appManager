package xinyi.com.appmanager.ui.main.adapter

import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xinyi.com.appmanager.R
import xinyi.com.appmanager.ui.main.model.MainItem
import xinyi.com.appmanager.util.GlideImageLoader


public class MainAdapter : BaseQuickAdapter<MainItem, BaseViewHolder> {

    constructor(layoutResId: Int, data: List<MainItem>?) : super(layoutResId, data) {}

    override fun convert(helper: BaseViewHolder?, item: MainItem?) {
        var mTextView = helper!!.getView<TextView>(R.id.mTextView);
        var mImageView = helper!!.getView<ImageView>(R.id.mImageView);
        mTextView.text=item!!.name;
        GlideImageLoader.showRoundImage(mContext,"",mImageView);
    }

}