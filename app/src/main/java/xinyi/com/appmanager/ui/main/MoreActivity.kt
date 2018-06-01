package xinyi.com.appmanager.ui.main

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.TextView
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.Utils
import com.xylibrary.base.BaseActivity
import com.xylibrary.recycleview.RecyclerViewHelper
import com.xylibrary.recycleview.divider.HorizontalDividerItemDecoration
import com.xylibrary.recycleview.divider.VerticalDividerItemDecoration
import kotlinx.android.synthetic.main.activity_more.*
import xinyi.com.appmanager.R
import xinyi.com.appmanager.helper.RecycleItemTouchHelper
import xinyi.com.appmanager.ui.main.adapter.MoreSelectAdapter
import xinyi.com.appmanager.ui.main.model.MainItem

/**
 * 应用管理界面
 */
class MoreActivity : BaseActivity() {

    //已选的应用adapter
    private lateinit var selectAdapter: MoreSelectAdapter
    //所有的应用adapter
    private lateinit var allSelectAdapter: MoreSelectAdapter
    //已经选择好的应用
    private var dataAdapterList: ArrayList<MainItem> = ArrayList<MainItem>();
    //所有的应用
    private var allItemDataList: ArrayList<MainItem> = ArrayList<MainItem>();
    private var titlePosition: ArrayList<Int> = ArrayList<Int>();//标题所在的位置

    private final var ITEM: String = "item";//保存所有的item的key
    private final var ORDER: String = "order";//保存所选item的顺序
    override fun getLayoutResource(): Int {
        return R.layout.activity_more
    }

    override fun initView() {
        var s = SPUtils.getInstance().getString(ITEM);
        //内存获取数据
        if (!StringUtils.isEmpty(s)) {
            getDataFromSp(s)
        } else {
            //自己生成模拟数据
            getSimulationData()
        }

        selectAdapter = MoreSelectAdapter(dataAdapterList);
        allSelectAdapter = MoreSelectAdapter(allItemDataList)
        val horizontalDividerItemDecoration = HorizontalDividerItemDecoration
                .Builder(Utils.getApp()).drawable(R.drawable.divider_decoration).build()
        val verticalDividerItemDecoration = VerticalDividerItemDecoration
                .Builder(Utils.getApp()).drawable(R.drawable.divider_decoration).build()
        RecyclerViewHelper.initRecyclerViewG(selectRecyclerView, true, selectAdapter,
                4, horizontalDividerItemDecoration, verticalDividerItemDecoration)
        var layoutManager = RecyclerViewHelper.initRecyclerViewG(unSelectRecyclerView, true, allSelectAdapter,
                4, horizontalDividerItemDecoration, verticalDividerItemDecoration)
        //设置Span,标题栏单独占据一行
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position in titlePosition) {
                    return layoutManager.spanCount
                } else
                    return 1
            }
        }
        //已选的RecycleView的滑动设置
        var itemTouch = RecycleItemTouchHelper(selectAdapter);
        var helper = ItemTouchHelper(itemTouch);
        helper.attachToRecyclerView(selectRecyclerView);

        mTextView.setOnClickListener {
            editTextClick(it, itemTouch)
        }
        selectAdapter.setOnItemLongClickListener { adapter, view, position ->
            return@setOnItemLongClickListener if (mTextView.text.equals("编辑")) mTextView.performClick() else false
        }
        mBack.setOnClickListener { finish() }
    }

    //编辑/完成按钮监听事件
    private fun editTextClick(it: View?, itemTouch: RecycleItemTouchHelper) {
        var tv = it as TextView
        var bl: Boolean
        bl = if (tv.text.equals("完成")) {
            tv.setText("编辑")
            itemTouch.isLongPress = false
            for (item in allItemDataList) {
                item.isShowTag = false;
            }
            //保存所有应用的数据
            var s = JSONObject.toJSONString(allItemDataList);
            SPUtils.getInstance().put(ITEM, s)

            //保存当前选中的应用的顺序
            var order = ArrayList<String>()
            for (i in dataAdapterList) {
                order.add("" + i.position)
            }
            SPUtils.getInstance().put(ORDER, JSONObject.toJSONString(order))
            false;
        } else {
            itemTouch.isLongPress = true
            tv.setText("完成")
            true;
        }
        for (item in allItemDataList) {
            item.isShowTag = bl;
        }
        selectAdapter.notifyDataSetChanged()
        allSelectAdapter.notifyDataSetChanged()
    }


    //获取虚拟数据
    private fun getSimulationData() {
        for (i in 0..22) {
            var m = MainItem()
            m.name = "测试数据" + i
            m.hasSelect = false
            m.position = i;
            if (i % 5 == 0) {
                m.uiType = 0;
                titlePosition.add(i)
            }
            if (i % 7 == 0 && i != 0) {
                m.hasSelect = true
                m.uiType = 1
                dataAdapterList.add(m)
            }
            allItemDataList.add(m)
        }
    }


    //从内存中获取数据，填充给allItemDataList,然后根据顺序得到allItemDataList
    private fun getDataFromSp(s: String?) {
        allItemDataList.clear()
        allItemDataList.addAll(JSONArray.parseArray(s, MainItem::class.java))
        dataAdapterList.clear()
        var order = JSONArray.parseArray(SPUtils.getInstance().getString(ORDER), String::class.java);
        for (i in order) {
            var p = i.toInt()
            dataAdapterList.add(allItemDataList.get(p))
        }
        for (n in allItemDataList.indices) {
            if (n % 5 == 0) {
                titlePosition.add(n)
            }
        }
    }


    //点击小图标，更新ui
    public fun updateSelectApp(item: MainItem) {
        if (!item.hasSelect) {
            dataAdapterList.add(item)
        } else {
            dataAdapterList.remove(item);
        }
        item.hasSelect = !item.hasSelect
        allSelectAdapter.notifyDataSetChanged()
        selectAdapter.notifyDataSetChanged()
    }
}
