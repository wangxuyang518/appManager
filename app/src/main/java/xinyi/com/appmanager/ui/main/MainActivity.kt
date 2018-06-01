package xinyi.com.appmanager.ui.main

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import com.alibaba.fastjson.JSONArray
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.Utils
import com.xylibrary.base.BaseActivity
import com.xylibrary.recycleview.RecyclerViewHelper
import com.xylibrary.recycleview.divider.HorizontalDividerItemDecoration
import com.xylibrary.recycleview.divider.VerticalDividerItemDecoration
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.activity_main.*
import xinyi.com.appmanager.R
import xinyi.com.appmanager.ui.main.adapter.MainAdapter
import xinyi.com.appmanager.ui.main.model.MainItem
import xinyi.com.appmanager.util.GlideImageLoader


/**
 * 主界面
 */
class MainActivity : BaseActivity() {
    private final var ITEM: String = "item";//保存所有的item的key
    private final var ORDER: String = "order";//保存所选item的顺序
    private var imageUrlList: ArrayList<String> = ArrayList<String>();
    private var bannerTitlesList: ArrayList<String> = ArrayList<String>();
    private var dataAdapterList: ArrayList<MainItem> = ArrayList<MainItem>();
    private lateinit var adapter: MainAdapter
    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        centerTitle = findViewById(R.id.centerTitle);
        centerTitle?.text = resources.getText(R.string.main_title)
        imageUrlList.add("http://img05.tooopen.com/images/20140604/sy_62331342149.jpg");
        imageUrlList.add("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");
        imageUrlList.add("http://img.taopic.com/uploads/allimg/121017/234940-12101FR22825.jpg");
        bannerTitlesList.add("item1")
        bannerTitlesList.add("item2")
        bannerTitlesList.add("item3")
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        mBanner.setIndicatorGravity(BannerConfig.CENTER)
        mBanner.setImageLoader(GlideImageLoader())
        mBanner.setImages(imageUrlList)
        mBanner.setBannerTitles(bannerTitlesList);
        mBanner.setDelayTime(3000)
        mBanner.start()

        mRecyclerView.layoutManager = GridLayoutManager(this, 3);
        dataAdapterList.add(MainItem())
        dataAdapterList.add(MainItem())
        dataAdapterList.add(MainItem())
        dataAdapterList.add(MainItem())
        dataAdapterList.add(MainItem())
        dataAdapterList.add(MainItem())
        adapter = MainAdapter(R.layout.item_main, dataAdapterList)
        val horizontalDividerItemDecoration = HorizontalDividerItemDecoration.Builder(Utils.getApp())
                .colorResId(xinyi.com.xylibrary.R.color.comm_grey300).build()
        val verticalDividerItemDecoration = VerticalDividerItemDecoration.Builder(Utils.getApp())
                .colorResId(xinyi.com.xylibrary.R.color.comm_grey300).build()
        RecyclerViewHelper.initRecyclerViewG(mRecyclerView, true, adapter, 3, horizontalDividerItemDecoration, verticalDividerItemDecoration)
        adapter.setOnItemClickListener { adapter, view, position ->
            if (position == dataAdapterList.size - 1) {
                startActivity(Intent(this, MoreActivity::class.java))
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        dataAdapterList.clear()
        var s = SPUtils.getInstance().getString(ITEM);
        if (!StringUtils.isEmpty(s)) {
            var allItem = JSONArray.parseArray(s, MainItem::class.java);
            var order = JSONArray.parseArray(SPUtils.getInstance().getString(ORDER), String::class.java);
            for (i in order) {
                var p = i.toInt()
                dataAdapterList.add(allItem.get(p))
            }
        }
        if (dataAdapterList.size > 0) {
            var item = MainItem()
            item.name = "更多"
            dataAdapterList.add(item)
        }
        adapter.notifyDataSetChanged();
    }

}
