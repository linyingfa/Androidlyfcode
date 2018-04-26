package com.example.lyf.androidlyfcode.scroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.lyf.androidlyfcode.R;
import com.example.lyf.androidlyfcode.utils.WindowUtils;

public class SlideSwitch extends View {
    private boolean isOpen;
    private Paint paint;
    //    private Rect backRect;//
    private RectF backRect;//
    private int max_left;//
    private int frontRect_left;//
    private int frontRect_left_begin = 0;//
    private int eventStartX;//
    private int eventLastX;//
    private int diffX = 0;//
    private SlideListener listener;
    //
    private Bitmap bitmapTemp;
    private Bitmap closebitmap;
    private Context mContext;
    private boolean drawbitmapstate;

    public SlideSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        listener = null;
        this.mContext = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.slideswitch);
        isOpen = a.getBoolean(R.styleable.slideswitch_isOpen, false);
        /**
         * 电脑上图片尺寸，单位是像素。Android手机的屏幕分ldpi、mdpi、hdpi，甚至还有xhdpi，
         * 对于mdpi（density=160）设备，1dp=1px，对于hdpi（density=240）的设备，1dp=1.5px。
         * 所以，把图片放在了res/drawable-mdpi目录下，而运行的Android设备屏幕属于hdpi，导致图片尺寸会扩大1.5倍。
         * 解决办法
         （1）现在的Android设备一般都在hdpi及以上，所以建议把图片资源主要放在hdpi中。
         （2）修改上述代码，设置缩放为false
         */
        BitmapFactory.Options bfoOptions = new BitmapFactory.Options();
        bfoOptions.inScaled = false;
//		bitmapTemp = BitmapFactory.decodeResource(getResources(), R.drawable.t2, bfoOptions);
        bitmapTemp = BitmapFactory.decodeResource(getResources(), R.mipmap.open);
        closebitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.close);
        a.recycle();
    }

    public SlideSwitch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideSwitch(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int bitmapHeight = bitmapTemp.getHeight();
        int bitmapWidth = bitmapTemp.getWidth();
//		setMeasuredDimension(bitmapWidth * 2, bitmapHeight);
        setMeasuredDimension((int) (bitmapWidth * 2.5), (int) (bitmapHeight * 1.2));
        initDrawingVal();
    }

    public void initDrawingVal() {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
//        backRect = new Rect(0, 0, width + 10, height + 10);
        backRect = new RectF(0, 0, width, height);
        max_left = width - bitmapTemp.getWidth();//
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //一个矩形，灰色背景
        paint.setColor(Color.parseColor("#1a000000"));
//        canvas.drawRect(backRect, paint);
        canvas.drawRoundRect(backRect, 10, 10, paint);
        //frontRect_left  矩形的X值
        paint.setColor(Color.parseColor("#767676"));
        if (drawbitmapstate) {
            canvas.drawBitmap(closebitmap, frontRect_left, backRect.centerY() - WindowUtils.dp2px(mContext, 12), paint);
        } else {
            canvas.drawBitmap(bitmapTemp, frontRect_left, backRect.centerY() - WindowUtils.dp2px(mContext, 12), paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                eventStartX = (int) event.getRawX();//获取手指开始按下的绝对坐标X
                break;
            case MotionEvent.ACTION_MOVE:
                //获取手指绝对左边X
                eventLastX = (int) event.getRawX();
                //偏移量，，diffX=end-start
                diffX = eventLastX - eventStartX;
                //frontRect_left_begin 只是一个保存开始的值，【左往右->0，右往左-->新值】
                int tempX = diffX + frontRect_left_begin;
                //tempX=偏移量+开始值（左右2边）
                //下面2个判断是，是防止手指滑动超出固定的范围，如下
                // diffX==271^^^^^^frontRect_left_begin==298^^^^^^tempX==569^^^^^^max_left===298
                tempX = (tempX > max_left ? max_left : tempX);
                tempX = (tempX < 0 ? 0 : tempX);
                Log.i("LYF", "diffX==" + diffX + "^^^^^^"
                        + "frontRect_left_begin==" + frontRect_left_begin
                        + "^^^^^^" + "tempX==" + tempX + "^^^^^^" + "max_left===" + max_left);
                //判断是在0-max之间才会进行不断绘制
                if (tempX >= 0 && tempX <= max_left) {
                    frontRect_left = tempX;
                    invalidateView();
                }
                break;
            case MotionEvent.ACTION_UP:
                int RawX = (int) event.getRawX();
                int wholeX = RawX - eventStartX;
                //tempX=偏移量+开始值（左右2边），手指滑动最终的值
                frontRect_left_begin = frontRect_left;
                boolean toRight;
                // 这里判断向右边滑动还是向左边滑动，
                toRight = (frontRect_left_begin > max_left / 2);
//				toRight = (frontRect_left_begin > max_left / 2 ? true : false);
                if (Math.abs(wholeX) < 3) {//这个只是一个偏移量
                    toRight = !toRight;
                }
                if (toRight) {
                    drawbitmapstate = true;
                } else {
                    drawbitmapstate = false;
                }
                moveToDest(toRight);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * draw again
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    public void setSlideListener(SlideListener listener) {
        this.listener = listener;
    }

    public void moveToDest(final boolean toRight) {
        //0-149   149-0
        ValueAnimator toDestAnim = ValueAnimator.ofInt(frontRect_left, toRight ? max_left : 0);
        toDestAnim.setDuration(500);
        toDestAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        toDestAnim.start();
        toDestAnim.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                frontRect_left = (Integer) animation.getAnimatedValue();
                invalidateView();
            }
        });
        toDestAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (toRight) {
                    isOpen = true;
                    if (listener != null)
                        listener.open();
                    frontRect_left_begin = max_left;
                } else {
                    isOpen = false;
                    if (listener != null)
                        listener.close();
                    frontRect_left_begin = 0;
                }
            }
        });
    }

    public void setState(boolean isOpen) {
        this.isOpen = isOpen;
        initDrawingVal();
        invalidateView();
        if (listener != null)
            if (isOpen == true) {
                listener.open();
            } else {
                listener.close();
            }
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.isOpen = bundle.getBoolean("isOpen");
            state = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putBoolean("isOpen", this.isOpen);
        return bundle;
    }

    public interface SlideListener {
        void open();

        void close();
    }
}
