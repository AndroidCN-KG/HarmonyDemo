package com.kg.empt.utils.glide;

/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.render.PixelMapHolder;
import ohos.agp.render.Texture;
import ohos.agp.utils.RectFloat;
import ohos.media.image.PixelMap;
import ohos.media.image.common.AlphaType;
import ohos.media.image.common.PixelFormat;
import org.jetbrains.annotations.NonNls;

import java.security.MessageDigest;

public class RoundedCornersTransformation extends BitmapTransformation {

  private static final int VERSION = 1;
  private static final String ID = "jp.wasabeef.glide.transformations.RoundedCornersTransformation." + VERSION;

  public enum CornerType {
    ALL,
    TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
    TOP, BOTTOM, LEFT, RIGHT,
    OTHER_TOP_LEFT, OTHER_TOP_RIGHT, OTHER_BOTTOM_LEFT, OTHER_BOTTOM_RIGHT,
    DIAGONAL_FROM_TOP_LEFT, DIAGONAL_FROM_TOP_RIGHT
  }

  private final int radius;
  private final int diameter;
  private final int margin;
  private final CornerType cornerType;

  public RoundedCornersTransformation(int radius, int margin) {
    this(radius, margin, CornerType.ALL);
  }

  public RoundedCornersTransformation(int radius, int margin, CornerType cornerType) {
    this.radius = radius;
    this.diameter = this.radius * 2;
    this.margin = margin;
    this.cornerType = cornerType;
  }

  @Override
  protected PixelMap transform(@NonNls BitmapPool pool, @NonNls PixelMap toTransform, int outWidth, int outHeight) {
    int width = toTransform.getImageInfo().size.width;
    int height = toTransform.getImageInfo().size.height;

    PixelFormat config =
            toTransform.getImageInfo() != null ? toTransform.getImageInfo().pixelFormat : PixelFormat.ARGB_8888;
    PixelMap bitmap = pool.get(width, height, config);
    bitmap.setAlphaType(AlphaType.OPAQUE); // instead of true

    setCanvasBitmapDensity(toTransform, bitmap);

    Canvas canvas = new Canvas(new Texture(bitmap));
    Paint paint = new Paint();
//    paint.setColor(Color.TRANSPARENT);
    paint.setAntiAlias(true);
    drawRoundRect(canvas, paint, width, height);
//    paint.setColor(Color.BLACK);
    canvas.drawPixelMapHolder(new PixelMapHolder(toTransform), 0, 0, paint);


    return bitmap;
  }

  private void drawRoundRect(Canvas canvas, Paint paint, float width, float height) {
    float right = width - margin;
    float bottom = height - margin;

    switch (cornerType) {
      case ALL:
        canvas.drawRoundRect(new RectFloat(margin, margin, right, bottom), radius, radius, paint);
        break;
      case TOP_LEFT:
        drawTopLeftRoundRect(canvas, paint, right, bottom);
        break;
      case TOP_RIGHT:
        drawTopRightRoundRect(canvas, paint, right, bottom);
        break;
      case BOTTOM_LEFT:
        drawBottomLeftRoundRect(canvas, paint, right, bottom);
        break;
      case BOTTOM_RIGHT:
        drawBottomRightRoundRect(canvas, paint, right, bottom);
        break;
      case TOP:
        drawTopRoundRect(canvas, paint, right, bottom);
        break;
      case BOTTOM:
        drawBottomRoundRect(canvas, paint, right, bottom);
        break;
      case LEFT:
        drawLeftRoundRect(canvas, paint, right, bottom);
        break;
      case RIGHT:
        drawRightRoundRect(canvas, paint, right, bottom);
        break;
      case OTHER_TOP_LEFT:
        drawOtherTopLeftRoundRect(canvas, paint, right, bottom);
        break;
      case OTHER_TOP_RIGHT:
        drawOtherTopRightRoundRect(canvas, paint, right, bottom);
        break;
      case OTHER_BOTTOM_LEFT:
        drawOtherBottomLeftRoundRect(canvas, paint, right, bottom);
        break;
      case OTHER_BOTTOM_RIGHT:
        drawOtherBottomRightRoundRect(canvas, paint, right, bottom);
        break;
      case DIAGONAL_FROM_TOP_LEFT:
        drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom);
        break;
      case DIAGONAL_FROM_TOP_RIGHT:
        drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom);
        break;
      default:
        canvas.drawRoundRect(new RectFloat(margin, margin, right, bottom), radius, radius, paint);
        break;
    }
  }

  private void drawTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, margin, margin + diameter, margin + diameter), radius,
      radius, paint);
    canvas.drawRect(new RectFloat(margin, margin + radius, margin + radius, bottom), paint);
    canvas.drawRect(new RectFloat(margin + radius, margin, right, bottom), paint);
  }

  private void drawTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(right - diameter, margin, right, margin + diameter), radius,
      radius, paint);
    canvas.drawRect(new RectFloat(margin, margin, right - radius, bottom), paint);
    canvas.drawRect(new RectFloat(right - radius, margin + radius, right, bottom), paint);
  }

  private void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, bottom - diameter, margin + diameter, bottom), radius,
      radius, paint);
    canvas.drawRect(new RectFloat(margin, margin, margin + diameter, bottom - radius), paint);
    canvas.drawRect(new RectFloat(margin + radius, margin, right, bottom), paint);
  }

  private void drawBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(right - diameter, bottom - diameter, right, bottom), radius,
      radius, paint);
    canvas.drawRect(new RectFloat(margin, margin, right - radius, bottom), paint);
    canvas.drawRect(new RectFloat(right - radius, margin, right, bottom - radius), paint);
  }

  private void drawTopRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, margin, right, margin + diameter), radius, radius,
      paint);
    canvas.drawRect(new RectFloat(margin, margin + radius, right, bottom), paint);
  }

  private void drawBottomRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, bottom - diameter, right, bottom), radius, radius,
      paint);
    canvas.drawRect(new RectFloat(margin, margin, right, bottom - radius), paint);
  }

  private void drawLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, margin, margin + diameter, bottom), radius, radius,
      paint);
    canvas.drawRect(new RectFloat(margin + radius, margin, right, bottom), paint);
  }

  private void drawRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(right - diameter, margin, right, bottom), radius, radius, paint);
    canvas.drawRect(new RectFloat(margin, margin, right - radius, bottom), paint);
  }

  private void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, bottom - diameter, right, bottom), radius, radius,
      paint);
    canvas.drawRoundRect(new RectFloat(right - diameter, margin, right, bottom), radius, radius, paint);
    canvas.drawRect(new RectFloat(margin, margin, right - radius, bottom - radius), paint);
  }

  private void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, margin, margin + diameter, bottom), radius, radius,
      paint);
    canvas.drawRoundRect(new RectFloat(margin, bottom - diameter, right, bottom), radius, radius,
      paint);
    canvas.drawRect(new RectFloat(margin + radius, margin, right, bottom - radius), paint);
  }

  private void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, margin, right, margin + diameter), radius, radius,
      paint);
    canvas.drawRoundRect(new RectFloat(right - diameter, margin, right, bottom), radius, radius, paint);
    canvas.drawRect(new RectFloat(margin, margin + radius, right - radius, bottom), paint);
  }

  private void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float right,
                                             float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, margin, right, margin + diameter), radius, radius,
      paint);
    canvas.drawRoundRect(new RectFloat(margin, margin, margin + diameter, bottom), radius, radius,
      paint);
    canvas.drawRect(new RectFloat(margin + radius, margin + radius, right, bottom), paint);
  }

  private void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float right,
                                                float bottom) {
    canvas.drawRoundRect(new RectFloat(margin, margin, margin + diameter, margin + diameter), radius,
      radius, paint);
    canvas.drawRoundRect(new RectFloat(right - diameter, bottom - diameter, right, bottom), radius,
      radius, paint);
    canvas.drawRect(new RectFloat(margin, margin + radius, right - radius, bottom), paint);
    canvas.drawRect(new RectFloat(margin + radius, margin, right, bottom - radius), paint);
  }

  private void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float right,
                                                 float bottom) {
    canvas.drawRoundRect(new RectFloat(right - diameter, margin, right, margin + diameter), radius,
      radius, paint);
    canvas.drawRoundRect(new RectFloat(margin, bottom - diameter, margin + diameter, bottom), radius,
      radius, paint);
    canvas.drawRect(new RectFloat(margin, margin, right - radius, bottom - radius), paint);
    canvas.drawRect(new RectFloat(margin + radius, margin + radius, right, bottom), paint);
  }

  @Override
  public String toString() {
    return "RoundedTransformation(radius=" + radius + ", margin=" + margin + ", diameter="
      + diameter + ", cornerType=" + cornerType.name() + ")";
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof RoundedCornersTransformation &&
      ((RoundedCornersTransformation) o).radius == radius &&
      ((RoundedCornersTransformation) o).diameter == diameter &&
      ((RoundedCornersTransformation) o).margin == margin &&
      ((RoundedCornersTransformation) o).cornerType == cornerType;
  }

  @Override
  public int hashCode() {
    return ID.hashCode() + radius * 10000 + diameter * 1000 + margin * 100 + cornerType.ordinal() * 10;
  }

  @Override
  public void updateDiskCacheKey(@NonNls MessageDigest messageDigest) {
    messageDigest.update((ID + radius + diameter + margin + cornerType).getBytes(CHARSET));
  }
}
