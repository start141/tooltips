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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Tomer on 01/07/2016.
 */
class ToolTipBackgroundConstructor {

    /**
     * Select which background will be assign to the tip view
     */
    static void setBackground(View tipView, ToolTip toolTip) {

        // show tool tip without arrow. no need to continue
        if (toolTip.hideArrow()) {
            setToolTipNoArrowBackground(tipView, toolTip);
            return;
        }

        // show tool tip according to requested position
        switch (toolTip.getPosition()) {
            case ToolTip.POSITION_ABOVE:
                setToolTipAboveBackground(tipView, toolTip);
                break;
            case ToolTip.POSITION_BELOW:
                setToolTipBelowBackground(tipView, toolTip);
                break;
            case ToolTip.POSITION_LEFT_TO:
                setToolTipLeftToBackground(tipView, toolTip);
                break;
            case ToolTip.POSITION_RIGHT_TO:
                setToolTipRightToBackground(tipView, toolTip);
                break;
        }

    }

    private static void setToolTipAboveBackground(View tipView, ToolTip toolTip) {
        switch (toolTip.getAlign()) {
            case ToolTip.ALIGN_CENTER: {
                Drawable drawable = toolTip.getDrawableAboveCenter();
                if (drawable == null) {
                    drawable = getDrawable(tipView.getContext(), R.drawable.tooltip_arrow_down);
                }
                setTipBackground(tipView, drawable, toolTip);
                break;
            }
            case ToolTip.ALIGN_LEFT: {
                boolean isRtl = UiUtils.isRtl();
                Drawable drawable;
                if (!isRtl) {
                    drawable = toolTip.getDrawableAboveLeft();
                } else {
                    drawable = toolTip.getDrawableAboveRight();
                }
                if (drawable == null) {
                    drawable = getDrawable(tipView.getContext(), !isRtl ?
                            R.drawable.tooltip_arrow_down_left :
                            R.drawable.tooltip_arrow_down_right);
                }
                setTipBackground(tipView, drawable, toolTip);
                break;
            }
            case ToolTip.ALIGN_RIGHT: {
                boolean isRtl = UiUtils.isRtl();
                Drawable drawable;
                if (!isRtl) {
                    drawable = toolTip.getDrawableAboveRight();
                } else {
                    drawable = toolTip.getDrawableAboveLeft();
                }
                if (drawable == null) {
                    drawable = getDrawable(tipView.getContext(), !isRtl ?
                            R.drawable.tooltip_arrow_down_right :
                            R.drawable.tooltip_arrow_down_left);
                }
                setTipBackground(tipView, drawable, toolTip);
                break;
            }
        }
    }

    private static void setToolTipBelowBackground(View tipView, ToolTip toolTip) {

        switch (toolTip.getAlign()) {
            case ToolTip.ALIGN_CENTER: {
                Drawable drawable = toolTip.getDrawableBelowCenter();
                if (drawable == null) {
                    drawable = getDrawable(tipView.getContext(), R.drawable.tooltip_arrow_up);
                }
                setTipBackground(tipView, drawable, toolTip);
                break;
            }
            case ToolTip.ALIGN_LEFT: {
                boolean isRtl = UiUtils.isRtl();
                Drawable drawable;
                if (!isRtl) {
                    drawable = toolTip.getDrawableBelowLeft();
                } else {
                    drawable = toolTip.getDrawableBelowRight();
                }
                if (drawable == null) {
                    drawable = getDrawable(tipView.getContext(), !isRtl ?
                            R.drawable.tooltip_arrow_up_left :
                            R.drawable.tooltip_arrow_up_right);
                }
                setTipBackground(tipView, drawable, toolTip);
                break;
            }
            case ToolTip.ALIGN_RIGHT: {
                boolean isRtl = UiUtils.isRtl();
                Drawable drawable;
                if (!isRtl) {
                    drawable = toolTip.getDrawableBelowRight();
                } else {
                    drawable = toolTip.getDrawableBelowLeft();
                }
                if (drawable == null) {
                    drawable = getDrawable(tipView.getContext(), !isRtl ?
                            R.drawable.tooltip_arrow_up_right :
                            R.drawable.tooltip_arrow_up_left);
                }
                setTipBackground(tipView, drawable, toolTip);
                break;
            }
        }

    }

    private static void setToolTipLeftToBackground(View tipView, ToolTip toolTip) {
        boolean isRtl = UiUtils.isRtl();
        Drawable drawable;
        if (!isRtl) {
            drawable = toolTip.getDrawableLeft();
        } else {
            drawable = toolTip.getDrawableRight();
        }
        if (drawable == null) {
            drawable = getDrawable(tipView.getContext(), !isRtl ?
                    R.drawable.tooltip_arrow_right :
                    R.drawable.tooltip_arrow_left);
        }
        setTipBackground(tipView, drawable, toolTip);
    }

    private static void setToolTipRightToBackground(View tipView, ToolTip toolTip) {
        boolean isRtl = UiUtils.isRtl();
        Drawable drawable;
        if (!isRtl) {
            drawable = toolTip.getDrawableRight();
        } else {
            drawable = toolTip.getDrawableLeft();
        }
        if (drawable == null) {
            drawable = getDrawable(tipView.getContext(), !isRtl ?
                    R.drawable.tooltip_arrow_left :
                    R.drawable.tooltip_arrow_right);
        }
        setTipBackground(tipView, drawable, toolTip);
    }

    private static void setToolTipNoArrowBackground(View tipView, ToolTip toolTip) {
        Drawable drawable = toolTip.getDrawableNoArrow();
        if (drawable == null) {
            drawable = getDrawable(tipView.getContext(), R.drawable.tooltip_no_arrow);
        }
        setTipBackground(tipView, drawable, toolTip);
    }

    private static void setTipBackground(View tipView, Drawable drawable, ToolTip toolTip) {
        Drawable paintedDrawable = getTintedDrawable(drawable, toolTip);
        setViewBackground(tipView, paintedDrawable);
    }

    private static void setViewBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    private static Drawable getTintedDrawable(Drawable drawable, ToolTip toolTip) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (drawable != null && toolTip.tintBackground()) {
                drawable.setTint(toolTip.getBackgroundColor());
            }
        } else {
            if (drawable != null && toolTip.tintBackground()) {
                drawable.setColorFilter(toolTip.getBackgroundColor(), PorterDuff.Mode.SRC_ATOP);
            }
        }

        return drawable;
    }

    private static final Object sLock = new Object();
    private static TypedValue sTempValue;

    @SuppressLint("NewApi")
    public static final Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 21) {
            return context.getDrawable(id);
        } else if (version >= 16) {
            return context.getResources().getDrawable(id);
        } else {
            // Prior to JELLY_BEAN, Resources.getDrawable() would not correctly
            // retrieve the final configuration density when the resource ID
            // is a reference another Drawable resource. As a workaround, try
            // to resolve the drawable reference manually.
            final int resolvedId;
            synchronized (sLock) {
                if (sTempValue == null) {
                    sTempValue = new TypedValue();
                }
                context.getResources().getValue(id, sTempValue, true);
                resolvedId = sTempValue.resourceId;
            }
            return context.getResources().getDrawable(resolvedId);
        }
    }

}
