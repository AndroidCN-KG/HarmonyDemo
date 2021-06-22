package com.kg.empt;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kg.empt.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Image;
import ohos.bundle.IBundleManager;
import ohos.utils.net.Uri;

public class MainAbility extends Ability {
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 110;
    private Image image;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());

    }

//    private void showImg() {
//        Uri uri = Uri.parse("http://121.36.21.112:9527/profile/file/2021/05/25/21e8971660e860ebba23358ee4e311cb.jpg");
//        Glide.with(this).load(uri)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(image);
//    }
//
//    @Override
//    public void onRequestPermissionsFromUserResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_INTERNET: {
//                // 匹配requestPermissions的requestCode
//                if (grantResults.length > 0
//                        && grantResults[0] == IBundleManager.PERMISSION_GRANTED) {
//                    showImg();
//                    // 权限被授予
//                    // 注意：因时间差导致接口权限检查时有无权限，所以对那些因无权限而抛异常的接口进行异常捕获处理
//                } else {
//                    // 权限被拒绝
//                }
//                return;
//            }
//        }
//    }
}
