package com.example.addressproject;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class VillageAdapter extends BaseQuickAdapter<AddressBean.CityBean.AreaBean.VillageBean, BaseViewHolder> {
    public VillageAdapter(int layoutResId, @Nullable List<AddressBean.CityBean.AreaBean.VillageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean.CityBean.AreaBean.VillageBean item) {
        helper.setText(R.id.textview, item.getLabel());
        helper.setTextColor(R.id.textview, item.isStatus() ? Color.parseColor("#65C15C") : Color.parseColor("#444444"));
    }
}
