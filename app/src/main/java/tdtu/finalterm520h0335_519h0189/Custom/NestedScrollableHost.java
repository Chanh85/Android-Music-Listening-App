package tdtu.finalterm520h0335_519h0189.Custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.viewpager2.widget.ViewPager2;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;

public final class NestedScrollableHost extends FrameLayout {
    private int touchSlop;
    private float initialX;
    private float initialY;

    private final ViewPager2 getParentViewPager() {
        ViewParent var10000 = this.getParent();
        if (!(var10000 instanceof View)) {
            var10000 = null;
        }

        View v;
        for(v = (View)var10000; v != null && !(v instanceof ViewPager2); v = (View)var10000) {
            var10000 = v.getParent();
            if (!(var10000 instanceof View)) {
                var10000 = null;
            }
        }

        View var2 = v;
        if (!(v instanceof ViewPager2)) {
            var2 = null;
        }

        return (ViewPager2)var2;
    }

    private final View getChild() {
        return this.getChildCount() > 0 ? this.getChildAt(0) : null;
    }

    private final boolean canChildScroll(int orientation, float delta) throws Throwable {
        int direction = -((int)Math.signum(delta));
        View var10000;
        boolean var5;
        switch(orientation) {
            case 0:
                var10000 = this.getChild();
                var5 = var10000 != null ? var10000.canScrollHorizontally(direction) : false;
                break;
            case 1:
                var10000 = this.getChild();
                var5 = var10000 != null ? var10000.canScrollVertically(direction) : false;
                break;
            default:
                throw (Throwable)(new IllegalArgumentException());
        }

        return var5;
    }

    public boolean onInterceptTouchEvent(@NotNull MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        try {
            this.handleInterceptTouchEvent(e);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return super.onInterceptTouchEvent(e);
    }

    private final void handleInterceptTouchEvent(MotionEvent e) throws Throwable {
        ViewPager2 var10000 = this.getParentViewPager();
        if (var10000 != null) {
            int orientation = var10000.getOrientation();
            if (this.canChildScroll(orientation, -1.0F) || this.canChildScroll(orientation, 1.0F)) {
                if (e.getAction() == 0) {
                    this.initialX = e.getX();
                    this.initialY = e.getY();
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                } else if (e.getAction() == 2) {
                    float dx = e.getX() - this.initialX;
                    float dy = e.getY() - this.initialY;
                    boolean isVpHorizontal = orientation == 0;
                    float scaledDx = Math.abs(dx) * (isVpHorizontal ? 0.5F : 1.0F);
                    float scaledDy = Math.abs(dy) * (isVpHorizontal ? 1.0F : 0.5F);
                    if (scaledDx > (float)this.touchSlop || scaledDy > (float)this.touchSlop) {
                        if (isVpHorizontal == scaledDy > scaledDx) {
                            this.getParent().requestDisallowInterceptTouchEvent(false);
                        } else if (this.canChildScroll(orientation, isVpHorizontal ? dx : dy)) {
                            this.getParent().requestDisallowInterceptTouchEvent(true);
                        } else {
                            this.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }
                }

            }
        }
    }

    public NestedScrollableHost(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewConfiguration var10001 = ViewConfiguration.get(this.getContext());
        Intrinsics.checkNotNullExpressionValue(var10001, "ViewConfiguration.get(context)");
        this.touchSlop = var10001.getScaledTouchSlop();
    }

    public NestedScrollableHost(@NotNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewConfiguration var10001 = ViewConfiguration.get(this.getContext());
        Intrinsics.checkNotNullExpressionValue(var10001, "ViewConfiguration.get(context)");
        this.touchSlop = var10001.getScaledTouchSlop();
    }

}
