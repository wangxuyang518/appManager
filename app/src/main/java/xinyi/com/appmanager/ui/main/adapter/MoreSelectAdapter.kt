package xinyi.com.appmanager.ui.main.adapter

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xinyi.com.appmanager.R
import xinyi.com.appmanager.ui.main.MoreActivity
import xinyi.com.appmanager.ui.main.model.MainItem
import java.util.*

public class MoreSelectAdapter constructor(data: ArrayList<MainItem>?) : BaseMultiItemQuickAdapter<MainItem, BaseViewHolder>(data) {
    init {
        super.addItemType(1, R.layout.item_more)
        super.addItemType(0, R.layout.item_more1)
    }

    override fun convert(helper: BaseViewHolder?, item: MainItem?) {
        when (item!!.uiType) {
            1 -> {
                var image = helper!!.getView<ImageView>(R.id.mImageView)
                var textName = helper!!.getView<TextView>(R.id.mTextView)
                var tagImage = helper!!.getView<ImageView>(R.id.tagImage)
                var p = image.parent as View;
                tagImage.visibility = if (item!!.isShowTag) View.VISIBLE else View.INVISIBLE
                p.setBackgroundColor(if (item!!.isShowTag) Color.parseColor("#F5F6F5") else Color.parseColor("#ffffff"))
                if (item!!.isShowTag) {
                    tagImage.setImageResource(if (item!!.hasSelect) R.mipmap.item_del else R.mipmap.item_add)
                }
                textName.text = item.name
                tagImage.setOnClickListener {
                    (mContext as MoreActivity).updateSelectApp(item)
                }
            }
            0 -> {
                var name = helper!!.getView<TextView>(R.id.allItemTitle)
                name.text = item.name
            }
        }
    }

}