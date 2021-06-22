package com.kg.empt.utils.glide;


import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import ohos.agp.render.Canvas;
import ohos.agp.render.PixelMapHolder;
import ohos.agp.render.Texture;
import ohos.agp.utils.RectFloat;
import ohos.app.Context;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;

public class GlideRoundTransform extends BitmapTransformation {

    private static float radius = 0f;
    Context context;

    public GlideRoundTransform(Context context) {
        this(context, 0);
    }

    public GlideRoundTransform(Context context, int dp) {
        super();
        this.context = context;
        radius = dp;
    }


    @Override
    protected PixelMap transform(@NonNls BitmapPool pool, @NonNls PixelMap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getImageInfo().size.width;
        int height = toTransform.getImageInfo().size.height;

        PixelFormat config = toTransform.getImageInfo() != null ? toTransform.getImageInfo().pixelFormat : PixelFormat.ARGB_8888;
        PixelMap bitmap = pool.get(width, height, config);

        setCanvasBitmapDensity(toTransform, bitmap);

        Canvas canvas = new Canvas(new Texture(bitmap));
        canvas.drawPixelMapHolderRoundRectShape(new PixelMapHolder(toTransform), new RectFloat(0, 0, width, height), new RectFloat(0, 0, width, height), radius, radius);
        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(@NotNull MessageDigest messageDigest) {

    }
}
