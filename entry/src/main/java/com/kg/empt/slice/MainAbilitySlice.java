package com.kg.empt.slice;

import com.kg.empt.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        Text text = (Text) findComponentById(ResourceTable.Id_text_helloworld);
//        text.setClickedListener(listener->(present());
        findComponentById(ResourceTable.Id_btn_goto_img).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_goto_Tab).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_goto_list).setClickedListener(this);
        findComponentById(ResourceTable.Id_btn_goto_anim).setClickedListener(this);
//        btnImg.setClickedListener(this);
//        Uri uri = Uri.parse("http://121.36.21.112:9527/profile/file/2021/05/25/21e8971660e860ebba23358ee4e311cb.jpg");
//        Glide.with(this).load(uri)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(image);
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

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_btn_goto_img:
                presentForResult(new MainAbilitySlice_Img(), new Intent(), 0);
                break;
            case ResourceTable.Id_btn_goto_Tab:
                present(new MainAbilitySlice_Tab(), new Intent());
                break;
            case ResourceTable.Id_btn_goto_list:
                present(new MainAbilitySlice_List(), new Intent());
                break;
            case ResourceTable.Id_btn_goto_anim:
                present(new MainAbilitySlice_anim(), new Intent());
                break;
        }
    }
}

