package com.kg.empt.slice;

import com.kg.empt.ResourceTable;
import com.kg.empt.slice.adapter.ListAdapter;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

public class MainAbilitySlice_List extends AbilitySlice {
    ListContainer lv;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main_list);
        lv = (ListContainer) findComponentById(ResourceTable.Id_lv);
        lv.setItemProvider(new ListAdapter(this));
        lv.setItemClickedListener(new ListContainer.ItemClickedListener() {
            @Override
            public void onItemClicked(ListContainer listContainer, Component component, int i, long l) {
                new ToastDialog(MainAbilitySlice_List.this).setText("点击第" + i + "个Item")
                        .setAlignment(LayoutAlignment.CENTER)
                        .setAutoClosable(false)
                        .setDuration(1)//没效果
                        .show();
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

