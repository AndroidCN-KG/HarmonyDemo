package com.kg.empt.slice;

import com.kg.empt.ResourceTable;
import com.kg.empt.slice.adapter.PageAdapter;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.PageSlider;
import ohos.agp.components.TabList;

public class MainAbilitySlice_Tab extends AbilitySlice {
    private PageSlider pageSlider;
    private TabList tabList;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main_tab);
        tabList = (TabList) findComponentById(ResourceTable.Id_tab_list);
        pageSlider = (PageSlider) findComponentById(ResourceTable.Id_page_slider);

        if(tabList.getChildCount()>0){
            return;
        }
        tabList.setFixedMode(true);//
        TabList.Tab tab1 = tabList.new Tab(getContext());
        TabList.Tab tab2 = tabList.new Tab(getContext());
        TabList.Tab tab3 = tabList.new Tab(getContext());
        tab1.setName("tab1");
        tab1.setText("tab1");

        tabList.addTab(tab1);
        tab2.setName("tab2");
        tab2.setText("tab2");
        tabList.addTab(tab2);
        tab3.setName("tab3");
        tab3.setText("tab3");
        tabList.addTab(tab3);

//        tabList.setTabMargin(10);
//        tabList.setPadding(10,0,10,0);
        tabList.selectTabAt(0);
        tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                pageSlider.setCurrentPage(tab.getPosition());
            }

            @Override
            public void onUnselected(TabList.Tab tab) {

            }

            @Override
            public void onReselected(TabList.Tab tab) {

            }
        });
        pageSlider.setProvider(new PageAdapter(getContext(), new String[]{"tab1", "tab2", "tab3"}));
        pageSlider.setCurrentPage(0);
        pageSlider.setReboundEffect(true);
        pageSlider.setCentralScrollMode(true);

        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int i, float v, int i1) {

            }

            @Override
            public void onPageSlideStateChanged(int i) {

            }

            @Override
            public void onPageChosen(int i) {
                tabList.selectTabAt(i);
            }
        });

    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    protected void onResult(int requestCode, Intent resultIntent) {
        super.onResult(requestCode, resultIntent);
    }


}

