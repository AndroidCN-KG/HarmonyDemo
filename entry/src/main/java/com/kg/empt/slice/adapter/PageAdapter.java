package com.kg.empt.slice.adapter;

import com.kg.empt.ResourceTable;
import ohos.agp.components.*;
import ohos.app.Context;

import java.util.ArrayList;

/**
 * 说明： <br>
 * 作者：陈晓凯<br>
 * Copyright (c) 2020 上海车娱<br>
 * Mail: 1683380911@qq.com
 * 时间：2021-6-17 11:08<br>
 * 修改记录： <br>
 */
public class PageAdapter extends  PageSliderProvider {
    private String[] title = new String[]{};
    private ArrayList<Component> list = new ArrayList<>();

    public PageAdapter(Context context, String[] title) {
        this.title = title;
        for (String str : title) {
            list.add(createView(context, str));
        }
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public String getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public Object createPageInContainer(ComponentContainer componentContainer, int i) {
        componentContainer.addComponent(list.get(i));
        return componentContainer;
    }

    @Override
    public void destroyPageFromContainer(ComponentContainer componentContainer, int i, Object o) {
        componentContainer.removeComponent(list.get(i));
    }

    @Override
    public boolean isPageMatchToObject(Component component, Object o) {
        return component == o;
    }

    public Component createView(Context context, String title) {

        Component mainComponent =
                LayoutScatter.getInstance(context).parse(ResourceTable.Layout_tab_page, null, false);
        if (!(mainComponent instanceof ComponentContainer)) {
            return null;
        }
//        ComponentContainer rootLayout = (ComponentContainer) mainComponent;
        Text text = (Text) mainComponent.findComponentById(ResourceTable.Id_tv_name);
        text.setText(title);
        return mainComponent;
    }

}
