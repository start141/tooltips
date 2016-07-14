
/*
Copyright 2016 Tomer Goldstein

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.tomergoldst.tooltips;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tomer on 01/07/2016.
 *
 *
 */
public class ToolTip {

    public static final long DURATION_SHORT = 3000;
    public static final long DURATION_LONG = 6000;

    @IntDef({POSITION_ABOVE, POSITION_BELOW, POSITION_LEFT_TO, POSITION_RIGHT_TO})
    public @interface Position {}
    public static final int POSITION_ABOVE = 0;
    public static final int POSITION_BELOW = 1;
    public static final int POSITION_LEFT_TO = 3;
    public static final int POSITION_RIGHT_TO = 4;

    @IntDef({ALIGN_CENTER, ALIGN_LEFT, ALIGN_RIGHT})
    public @interface Align {}
    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;

    @IntDef({GRAVITY_CENTER, GRAVITY_LEFT, GRAVITY_RIGHT})
    public @interface Gravity {}
    public static final int GRAVITY_CENTER = 0;
    public static final int GRAVITY_LEFT = 1;
    public static final int GRAVITY_RIGHT = 2;

    private Context mContext;
    private View mAnchorView;
    private ViewGroup mRootViewGroup;
    private String mMessage;
    private @Position int mPosition;
    private @Align int mAlign;
    private int mOffsetX;
    private int mOffsetY;
    private boolean mArrow;
    private boolean mTintBackground;
    private int mBackgroundColor;
    private int mTextColor;
    private int mTextSize;
    private float mElevation;
    private @Gravity int mTextGravity;
    private Drawable mDrawableAboveLeft;
    private Drawable mDrawableAboveCenter;
    private Drawable mDrawableAboveRight;
    private Drawable mDrawableBelowLeft;
    private Drawable mDrawableBelowCenter;
    private Drawable mDrawableBelowRight;
    private Drawable mDrawableLeft;
    private Drawable mDrawableRight;
    private Drawable mDrawableNoArrow;

    public ToolTip(Builder builder){
        mContext = builder.mContext;
        mAnchorView = builder.mAnchorView;
        mRootViewGroup = builder.mRootViewGroup;
        mMessage = builder.mMessage;
        mPosition = builder.mPosition;
        mAlign = builder.mAlign;
        mOffsetX = builder.mOffsetX;
        mOffsetX = builder.mOffsetX;
        mOffsetY = builder.mOffsetY;
        mArrow = builder.mArrow;
        mTintBackground = builder.mTintBackground;
        mBackgroundColor = builder.mBackgroundColor;
        mTextColor = builder.mTextColor;
        mTextSize = builder.mTextSize;
        mElevation = builder.mElevation;
        mTextGravity = builder.mTextGravity;
        mDrawableAboveLeft = builder.mDrawableAboveLeft;
        mDrawableAboveCenter = builder.mDrawableAboveCenter;
        mDrawableAboveRight = builder.mDrawableAboveRight;
        mDrawableBelowLeft = builder.mDrawableBelowLeft;
        mDrawableBelowCenter = builder.mDrawableBelowCenter;
        mDrawableBelowRight = builder.mDrawableBelowRight;
        mDrawableLeft = builder.mDrawableLeft;
        mDrawableRight = builder.mDrawableRight;
        mDrawableNoArrow = builder.mDrawableNoArrow;
    }

    public Context getContext() {
        return mContext;
    }

    public View getAnchorView() {
        return mAnchorView;
    }

    public ViewGroup getRootView() {
        return mRootViewGroup;
    }

    public String getMessage() {
        return mMessage;
    }

    public int getPosition() {
        return mPosition;
    }

    public int getAlign() {
        return mAlign;
    }

    public int getOffsetX() {
        return mOffsetX;
    }

    public int getOffsetY() {
        return mOffsetY;
    }

    public boolean hideArrow() {
        return !mArrow;
    }

    public boolean tintBackground() {
        return mTintBackground;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public boolean positionedLeftTo(){
        return POSITION_LEFT_TO == mPosition;
    }

    public boolean positionedRightTo(){
        return POSITION_RIGHT_TO == mPosition;
    }

    public boolean positionedAbove(){
        return POSITION_ABOVE == mPosition;
    }

    public boolean positionedBelow(){
        return POSITION_BELOW == mPosition;
    }

    public boolean alignedCenter(){
        return ALIGN_CENTER == mAlign;
    }

    public boolean alignedLeft(){
        return ALIGN_LEFT == mAlign;
    }

    public boolean alignedRight(){
        return ALIGN_RIGHT == mAlign;
    }

    public void setPosition(@Position int position){
        mPosition = position;
    }

    public float getElevation() {
        return mElevation;
    }

    public int getTextGravity(){
        int gravity;
        switch (mTextGravity){
            case GRAVITY_CENTER:
                gravity = android.view.Gravity.CENTER;
                break;
            case GRAVITY_LEFT:
                gravity = android.view.Gravity.LEFT;
                break;
            case GRAVITY_RIGHT:
                gravity = android.view.Gravity.RIGHT;
                break;
            default:
                gravity = android.view.Gravity.CENTER;
        }
        return gravity;
    }

    public Drawable getDrawableAboveLeft() {
        return mDrawableAboveLeft;
    }

    public Drawable getDrawableAboveCenter() {
        return mDrawableAboveCenter;
    }

    public Drawable getDrawableAboveRight() {
        return mDrawableAboveRight;
    }

    public Drawable getDrawableBelowLeft() {
        return mDrawableBelowLeft;
    }

    public Drawable getDrawableBelowCenter() {
        return mDrawableBelowCenter;
    }

    public Drawable getDrawableBelowRight() {
        return mDrawableBelowRight;
    }

    public Drawable getDrawableLeft() {
        return mDrawableLeft;
    }

    public Drawable getDrawableRight() {
        return mDrawableRight;
    }

    public Drawable getDrawableNoArrow() {
        return mDrawableNoArrow;
    }

    public static class Builder {
        private Context mContext;
        private View mAnchorView;
        private ViewGroup mRootViewGroup;
        private String mMessage;
        private @Position int mPosition;
        private @Align int mAlign;
        private int mOffsetX;
        private int mOffsetY;
        private boolean mArrow;
        private boolean mTintBackground;
        private int mBackgroundColor;
        private int mTextColor;
        private int mTextSize;
        private float mElevation;
        private @Gravity int mTextGravity;
        private Drawable mDrawableAboveLeft;
        private Drawable mDrawableAboveCenter;
        private Drawable mDrawableAboveRight;
        private Drawable mDrawableBelowLeft;
        private Drawable mDrawableBelowCenter;
        private Drawable mDrawableBelowRight;
        private Drawable mDrawableLeft;
        private Drawable mDrawableRight;
        private Drawable mDrawableNoArrow;

        /**
         *
         * @param context context
         * @param anchorView the view which near it we want to put the tip
         * @param root a class extends ViewGroup which the created tip view will be added to
         * @param message message to show
         * @param position  put the tip above / below / left to / right to
         */
        public Builder(Context context, View anchorView, ViewGroup root, String message, @Position int position){
            mContext = context;
            mAnchorView = anchorView;
            mRootViewGroup = root;
            mMessage = message;
            mPosition = position;
            mAlign = ALIGN_CENTER;
            mOffsetX = 0;
            mOffsetY = 0;
            mArrow = true;
            mTextColor = context.getResources().getColor(R.color.colorText);
            mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.textSize);
            mTextGravity = GRAVITY_LEFT;
        }

        public Builder setPosition(@Position int position){
            mPosition = position;
            return this;
        }

        public Builder setAlign(@Align int align){
            mAlign = align;
            return this;
        }

        /**
         * @param offset offset to move the tip on x axis after tip was positioned
         * @return
         */
        public Builder setOffsetX(int offset){
            mOffsetX = offset;
            return this;
        }

        /**
         * @param offset offset to move the tip on y axis after tip was positioned
         * @return
         */
        public Builder setOffsetY(int offset){
            mOffsetY = offset;
            return this;
        }

        public Builder withArrow(boolean value){
            mArrow = value;
            return this;
        }

        public Builder withTintBackground(boolean value){
            mTintBackground = value;
            return this;
        }

        public Builder setBackgroundColor(int color){
            mTintBackground = true;
            mBackgroundColor = color;
            return this;
        }

        public Builder setTextColor(int color){
            mTextColor = color;
            return this;
        }

        public Builder setTextSize(int textSize){
            mTextSize = textSize;
            return this;
        }

        public Builder setElevation(float elevation){
            mElevation = elevation;
            return this;
        }

        public Builder setGravity(@Gravity int gravity){
            mTextGravity = gravity;
            return this;
        }

        public Builder setDrawableAboveLeft(Drawable drawable) {
            mDrawableAboveLeft = drawable;
            return this;
        }

        public Builder setDrawableAboveCenter(Drawable drawable) {
            mDrawableAboveCenter = drawable;
            return this;
        }

        public Builder setDrawableAboveRight(Drawable drawable) {
            mDrawableAboveRight = drawable;
            return this;
        }

        public Builder setDrawableBelowLeft(Drawable drawable) {
            mDrawableBelowLeft = drawable;
            return this;
        }

        public Builder setDrawableBelowCenter(Drawable drawable) {
            mDrawableBelowCenter = drawable;
            return this;
        }

        public Builder setDrawableBelowRight(Drawable drawable) {
            mDrawableBelowRight = drawable;
            return this;
        }

        public Builder setDrawableLeft(Drawable drawable) {
            mDrawableLeft = drawable;
            return this;
        }

        public Builder setDrawableRight(Drawable drawable) {
            mDrawableRight = drawable;
            return this;
        }

        public Builder setDrawableNoArrow(Drawable drawable) {
            mDrawableNoArrow = drawable;
            return this;
        }

        public ToolTip build(){
            return new ToolTip(this);
        }

    }
}
