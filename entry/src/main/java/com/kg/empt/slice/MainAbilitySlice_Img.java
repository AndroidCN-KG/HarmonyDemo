package com.kg.empt.slice;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.kg.empt.ResourceTable;
import com.kg.empt.utils.glide.ColorFilterTransformation;
import com.kg.empt.utils.glide.GlideRoundTransform;
import com.kg.empt.utils.glide.RoundedCornersTransformation;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Image;
import ohos.agp.components.element.Element;
import ohos.media.image.PixelMap;
import ohos.utils.net.Uri;
import org.jetbrains.annotations.Nullable;

public class MainAbilitySlice_Img extends AbilitySlice {
    Image image;
    private PhotoView pv;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main_img);
//        text.setClickedListener(listener->(present());
        image = (Image) findComponentById(ResourceTable.Id_image_test);
        Uri uri = Uri.parse("http://121.36.21.112:9527/profile/file/2021/05/25/21e8971660e860ebba23358ee4e311cb.jpg");
        Uri uri1 = Uri.parse("http://121.36.21.112:9527/profile/file/2021/05/25/91da4557519a5347c1cd83c59a6b11e0.jpg");

        RequestOptions myOptions = new RequestOptions().transform(new GlideRoundTransform(getContext(), 30));

        ColorFilterTransformation colorFilterTransformation = new ColorFilterTransformation(getColor(ResourceTable.Color_black));
//        GlideRoundTransformTBRL transformation = new GlideRoundTransformTBRL
//                (30, 0, GlideRoundTransformTBRL.CornerType.BOTTOM_RIGHT);
        //顶部右边圆角
        RoundedCornersTransformation transformation1 = new RoundedCornersTransformation
                (20, 0, RoundedCornersTransformation.CornerType.OTHER_BOTTOM_LEFT);

        //组合各种Transformation,
        MultiTransformation<PixelMap> mation = new MultiTransformation<>
                //Glide设置圆角图片后设置ImageVIew的scanType="centerCrop"无效解决办法,将new CenterCrop()添加至此
                (colorFilterTransformation, new GlideRoundTransform(getContext(), 30));
        pv = (PhotoView) findComponentById(ResourceTable.Id_pv);

        Glide.with(this).load(uri)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false)
//                .fitCenter()
//                .apply(myOptions)
//                .apply(RequestOptions.bitmapTransform(colorFilterTransformation))//单色处理 比如黑白，红色 绿色 蓝色等
                .apply(RequestOptions.bitmapTransform(transformation1))
//                .apply(RequestOptions.bitmapTransform(mation))
                .into(image);

        pv.setPixelMap(ResourceTable.Media_01);


        Glide.with(this).load(uri1)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .fitCenter()
                .placeholder(ResourceTable.Media_icon)
                .error(ResourceTable.Media_08)
                .listener(new RequestListener<Element>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Element> target, boolean b) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Element element, Object o, Target<Element> target, DataSource dataSource, boolean b) {
                        return false;
                    }
                })
//                .apply(myOptions)
//                .apply(RequestOptions.bitmapTransform(colorFilterTransformation))//单色处理 比如黑白，红色 绿色 蓝色等
//                .apply(RequestOptions.bitmapTransform(transformation1))
//                .apply(RequestOptions.bitmapTransform(mation))
                .into(pv); //TODO 2021年6月22日18:07:10 图片一直加载失败
//
//
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

