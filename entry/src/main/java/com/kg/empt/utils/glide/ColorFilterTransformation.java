package com.kg.empt.utils.glide;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import ohos.agp.render.*;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;
import org.jetbrains.annotations.NonNls;

import java.security.MessageDigest;


public class ColorFilterTransformation extends BitmapTransformation {
    private static final int VERSION = 1;
    private static final String ID =
        "jp.wasabeef.glide.transformations.ColorFilterTransformation." + VERSION;

    private final int color;

    public ColorFilterTransformation(int color) {
        this.color = color;
    }

    @Override
    protected PixelMap transform(@NonNls BitmapPool pool, @NonNls PixelMap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getImageInfo().size.width;
        int height = toTransform.getImageInfo().size.height;

        PixelFormat config =
            toTransform.getImageInfo() != null ? toTransform.getImageInfo().pixelFormat : PixelFormat.ARGB_8888;
        PixelMap bitmap = pool.get(width, height, config);

        setCanvasBitmapDensity(toTransform, bitmap);

        Canvas canvas = new Canvas(new Texture(bitmap));
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColorFilter(new ColorFilter(color, BlendMode.COLOR));
        canvas.drawPixelMapHolder(new PixelMapHolder(toTransform), 0, 0, paint);

        return bitmap;
    }

    @Override
    public String toString() {
        return "GrayscaleTransformation()";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ColorFilterTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNls MessageDigest messageDigest) {
        messageDigest.update((ID).getBytes(CHARSET));
    }
}
