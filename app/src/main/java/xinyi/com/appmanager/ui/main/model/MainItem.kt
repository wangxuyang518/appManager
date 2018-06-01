package xinyi.com.appmanager.ui.main.model

import com.chad.library.adapter.base.entity.MultiItemEntity
import xinyi.com.appmanager.R

public  class MainItem constructor(): MultiItemEntity {

    public var position:Int = 0
    public var iconUrl:Int= R.mipmap.ic_launcher
    public var name:String="测试数据"
    public  var downLoadUrl:String=""
    public var isShowTag:Boolean=false;
    public var hasSelect:Boolean=false;
    public var uiType:Int = 1   //0表示上面的title  1表示图片+文字的布局

    override fun getItemType(): Int {
        return uiType
    }
}