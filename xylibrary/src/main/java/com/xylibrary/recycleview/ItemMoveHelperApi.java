package com.xylibrary.recycleview;

public interface ItemMoveHelperApi {

    /**
     * Item 切换位置
     *
     * @param fromPosition 开始位置
     * @param toPosition   结束位置
     */
    void onItemMoved(int fromPosition, int toPosition);


}
