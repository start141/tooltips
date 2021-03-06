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

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Tomer on 18/06/2016.
 */
class AnimationUtils {

    static ObjectAnimator popup(final View view, final long duration, long startDelay) {
        view.setAlpha(0);
        view.setVisibility(View.VISIBLE);

        ObjectAnimator popup = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f),
                PropertyValuesHolder.ofFloat("scaleX", 0f, 1f),
                PropertyValuesHolder.ofFloat("scaleY", 0f, 1f));
        popup.setDuration(duration);
        popup.setInterpolator(new OvershootInterpolator());
        popup.setStartDelay(startDelay);

        return popup;
    }

    static ObjectAnimator popout(final View view, final long duration, long startDelay, final AnimatorListenerAdapter animatorListenerAdapter) {
        ObjectAnimator popout = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("alpha", 1f, 0f),
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0f));
        popout.setDuration(duration);
        popout.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                if (view.getTag() != null && view.getTag().equals("isCanceled")) {
                    return;
                }

                ViewGroup root = (ViewGroup) view.getParent();
                if (root != null) {
                    root.removeView(view);
                }
                if (animatorListenerAdapter != null) {
                    animatorListenerAdapter.onAnimationEnd(animation);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if (view.getVisibility() == View.VISIBLE && !view.getTag().equals("noDismissAnimate")) {
                    view.setTag("isCanceled");
                    popout(view, duration, 0L, null).start();
                }
            }
        });

        popout.setInterpolator(new AnticipateOvershootInterpolator());
        popout.setStartDelay(startDelay);

        return popout;
    }
}
