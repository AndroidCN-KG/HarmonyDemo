package com.kg.empt.slice.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kg.empt.ResourceTable;
import com.kg.empt.utils.glide.GlideRoundTransform;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;
import ohos.utils.net.Uri;

/**
 * 说明： <br>
 * 作者：陈晓凯<br>
 * Copyright (c) 2020 上海车娱<br>
 * Mail: 1683380911@qq.com
 * 时间：2021-6-18 16:54<br>
 * 修改记录： <br>
 */
public class ListAdapter extends BaseItemProvider {

    AbilitySlice slice;

    String[] imgs = new String[]{
            "http://121.36.21.112:9527/profile/file/2021/05/25/21e8971660e860ebba23358ee4e311cb.jpg",
            "http://121.36.21.112:9527/profile/file/2021/05/26/da16a277d4168310d21efc263e5d2e99.jpg",
            "http://121.36.21.112:9527/profile/file/2021/05/24/6ab074fbe156acfb27a62e08ee67566a.jpg",
            "http://121.36.21.112:9527/profile/file/2021/05/24/1399b5cbceb6e530e4d72c69319bc009.jpg",
            "http://121.36.21.112:9527/profile/file/2021/05/25/ced3b1ee6c46c58289f1c3274fcfaebf.jpg",
            "http://121.36.21.112:9527/profile/file/2021/05/25/91da4557519a5347c1cd83c59a6b11e0.jpg",
    };

    int[] imgIds = new int[]{
            ResourceTable.Media_ic_home_fun_oil_new_year,
            ResourceTable.Media_ic_home_fun_honeycomb,
            ResourceTable.Media_ic_home_fun_honeycomb_new_year,
            ResourceTable.Media_ic_home_fun_travel,

    };

    public ListAdapter(AbilitySlice slice) {
        this.slice = slice;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getComponentTypeCount() {
        return 2;
    }

    @Override
    public int getItemComponentType(int position) {
        //type 必须从0开始
        if (position % 3 == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {

        int type = getItemComponentType(i);
        final ViewHolder viewHolder;

        if (component == null) {
            if (type == 0) {
                component = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_item_lv, null, false);
            } else {
                component = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_item_lv_1, null, false);
            }
            viewHolder = new ViewHolder(component, type);
        } else {
            viewHolder = (ViewHolder) component.getTag();
        }
        RequestOptions myOptions = new RequestOptions().transform(new GlideRoundTransform(slice, 40));
        if (type == 1) {
            Uri uri1 = Uri.parse(imgs[(i + 1) % imgs.length]);
            Glide.with(slice).load(uri1)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .apply(myOptions)
                    .placeholder(ResourceTable.Media_icon)
                    .error(ResourceTable.Media_vxshare)
                    .fitCenter()
                    .into(viewHolder.image2);
        }
        viewHolder.text.setText(i + "");
//        viewHolder.image.setPixelMap(imgIds[i%imgIds.length]);
//        RequestOptions myOptions = new RequestOptions().transform(new GlideRoundTransform(slice, 40));
        Uri uri = Uri.parse(imgs[i % imgs.length]);
        Glide.with(slice).load(uri)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)//TODO 设置true 跳过缓存 每次家在网络图片会混乱
                .apply(myOptions)
                .placeholder(ResourceTable.Media_icon)
                .error(ResourceTable.Media_vxshare)
                .fitCenter()
                .into(viewHolder.image);
        component.setTag(viewHolder);
        return component;
    }

    static class ViewHolder {
        public Image image;
        public Image image2;
        public Text text;

        public ViewHolder(Component component, int type) {
            if (type == 0) {
                text = (Text) component.findComponentById(ResourceTable.Id_item_index);
                image = (Image) component.findComponentById(ResourceTable.Id_iv_logo);
            } else if (type == 1) {
                text = (Text) component.findComponentById(ResourceTable.Id_item_index);
                image = (Image) component.findComponentById(ResourceTable.Id_iv_logo);
                image2 = (Image) component.findComponentById(ResourceTable.Id_iv_logo1);
            }
        }
    }
}
