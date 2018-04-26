package com.example.lyf.androidlyfcode.scroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.lyf.androidlyfcode.R;
import com.example.lyf.androidlyfcode.utils.WindowUtils;

/**
 * Created by Administrator on 2018/4/26.
 */

public class NewSlideSwitch extends View {

    private Paint paint;
    private Bitmap bitmap;
    private Context context;
    private RectF bgRect;
    private int sliding_max_distance;//
    private int defuBitmap_X;//默认的图片X坐标
    private int frontRect_left_begin = 0;//


    public NewSlideSwitch(Context context) {
        super(context);
        initView(context);
    }

    public NewSlideSwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NewSlideSwitch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    void initView(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        bgRect = new RectF();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.open);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //TODO 图片的宽高
        int bitmapHeight = bitmap.getHeight();
        int bitmapWidth = bitmap.getWidth();
        //告诉系统要多少，宽，高，是加点偏移量
        setMeasuredDimension((int) (bitmapWidth * 2.5), (int) (bitmapHeight * 1.2));
        //getMeasuredWidth(): 对View上的内容进行测量后得到的View内容佔据的宽度，
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        bgRect.left = 0;
        bgRect.top = 0;
        bgRect.right = width;
        bgRect.bottom = height;
        //控件的宽度-图片的宽度=剩下滑动的距离
        sliding_max_distance = width - bitmap.getWidth();//
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(Color.parseColor("#1a000000"));
        paint.setColor(Color.RED);
        canvas.drawRoundRect(bgRect, 10, 10, paint);
        paint.setColor(Color.parseColor("#767676"));
        //@NonNull Bitmap bitmap, float left, float t+op, @Nullable Paint paint
        canvas.drawBitmap(bitmap, defuBitmap_X, bgRect.centerY() - WindowUtils.dp2px(context, 12), paint);
    }


    private int eventStartX;//
    private int eventLastX;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        /**
         * 04-26 17:22:37.291 16241-16241/lyfproject.com.myapplication I/eventStartX: ----------------->475
         * //TODO
         04-26 17:22:37.501 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->476
         04-26 17:22:37.520 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->479
         04-26 17:22:37.529 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->481
         04-26 17:22:37.537 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->482
         04-26 17:22:37.546 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->483
         04-26 17:22:37.570 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->488
         04-26 17:22:37.586 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->491
         04-26 17:22:37.603 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->494
         04-26 17:22:37.619 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->496
         04-26 17:22:37.636 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->500
         04-26 17:22:37.653 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->504
         04-26 17:22:37.670 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->507
         04-26 17:22:37.687 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->509
         04-26 17:22:37.703 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->511
         04-26 17:22:37.720 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->513
         04-26 17:22:37.737 16241-16241/lyfproject.com.myapplication I/eventLastX: ----------------->514
         //TODO
         04-26 17:22:37.797 16241-16241/lyfproject.com.myapplication I/RawX: ----------------->514
         */
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //getRawX()是表示相对于屏幕左上角的x坐标值
                // (注意:这个屏幕左上角是手机屏幕左上角,不管activity是否有titleBar或是否全屏幕)
                eventStartX = (int) event.getRawX();
                Log.i("eventStartX", "----------------->" + eventStartX);
                break;
            case MotionEvent.ACTION_MOVE:
                eventLastX = (int) event.getRawX();
                Log.i("eventLastX", "----------------->" + eventLastX);
                int offset = eventLastX - eventStartX;
                //defuBitmap_X,,不能用图片的坐标加这个偏移量，因为不断绘制，这个坐标会不断变化的
                int tempX = offset + frontRect_left_begin;
                Log.i("ACTION_MOVE", "----------offset------->" + offset + "----------tempX------->" + tempX);
                tempX = (tempX > sliding_max_distance ? sliding_max_distance : tempX);//越界判断
                tempX = (tempX < 0 ? 0 : tempX);
                if (tempX >= 0 && tempX <= sliding_max_distance) {
                    defuBitmap_X = tempX;
                    invalidateView();
                }
                break;
            case MotionEvent.ACTION_UP:
                int RawX = (int) event.getRawX();
                Log.i("RawX", "----------------->" + RawX);
                int distance = RawX - eventStartX;//end-start=distance
                frontRect_left_begin = defuBitmap_X;//如果不用滑动的，拖拽的就不用这个临时的值了，主要是记录偏移量
                boolean toRight;
                //图片开始的位置>max滑动距离的一半，表示在右边，反之
//                toRight = (defuBitmap_X > sliding_max_distance / 2);
                //TODO frontRect_left_begin 这个值其实就是记录滑动的偏移量
                toRight = (frontRect_left_begin > sliding_max_distance / 2);
                if (Math.abs(distance) < 3) {
                    //偏移值，3个像素点，end-start
                    toRight = !toRight;//如果是true，在右边，false在左边
                }
                moveToDest(toRight);
                break;
        }
        return true;//消费

    }

    private void moveToDest(final boolean toRight) {
        ValueAnimator toDestAnim = ValueAnimator.ofInt(defuBitmap_X, toRight ? sliding_max_distance : 0);
        toDestAnim.setDuration(500);
        toDestAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        toDestAnim.start();
        toDestAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //更新图片X坐标，进行重新绘制
                defuBitmap_X = (Integer) animation.getAnimatedValue();
                invalidateView();
            }
        });
        toDestAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (toRight) {//右边，
//                    defuBitmap_X = sliding_max_distance;
                    frontRect_left_begin = sliding_max_distance;
                } else {//左边
//                    defuBitmap_X = 0;
                    frontRect_left_begin = 0;
                }
            }
        });
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

}
