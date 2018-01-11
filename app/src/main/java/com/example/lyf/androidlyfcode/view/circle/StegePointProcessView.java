package com.example.lyf.androidlyfcode.view.circle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.example.lyf.androidlyfcode.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by huangweizhu on 2018/1/10.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 * <p>
 * 需求：
 * <p>
 * 1.进度条（多高啊。多宽啊），2.圆（半径是多少啊，坐标啊）。3.文本（大小啊，x,y啊）。 4.bitmap（坐标啊，图片啊，，，），（选中，正常）
 * 一般宽啊，高啊，半径啊，这些由我们固定值咯，设定一个数咯
 */

public class StegePointProcessView extends View {

    private static final String TAG = "StegePointProcessView";

    private Paint processNorPaint;//正常的进度条
    private Paint processRunPaint;//连续签到的进度
    private Paint circlePaint;//圆
    private Paint txtPaint;//日期

    private RectF processRect;//norm
    private RectF processRunRect;//run

    private Rect tempCalu;//

    private int colorProcessBgDef = 0xff2b2c30;    // 默认背景颜色

    private int processHeight = dp2px(10);   // 进度条高度
    private int radiusCicle = dp2px(20);   // 圆半径
    private int width;

    private Bitmap bitmapTemp;

    private int maxProcess = 100;
    private float curProcess = 0;

    private List<StageBean> datas;

    private Context mContext;


    public StegePointProcessView(Context context) {
        super(context);
        initViewConfig(context);
    }

    public StegePointProcessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewConfig(context);
    }

    public StegePointProcessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewConfig(context);
    }

    void initViewConfig(Context mContext) {
        this.mContext = mContext;

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(colorProcessBgDef);

        processNorPaint = new Paint();
        processNorPaint.setAntiAlias(true);
        processNorPaint.setColor(colorProcessBgDef);

        processRunPaint = new Paint();
        processRunPaint.setAntiAlias(true);

        txtPaint = new Paint();
        txtPaint.setAntiAlias(true);

        processRect = new RectF();
        processRunRect = new RectF();
        tempCalu = new Rect();

        // 计算高度用
        bitmapTemp = BitmapFactory.decodeResource(getResources(), R.mipmap.sign_icon_gift_selected);
        datas = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            datas.add(new StageBean(new RectF(), R.mipmap.sign_icon_gift_normal, R.mipmap.sign_icon_gift_selected));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //如果在布局文件中WARP_CONTENT，这里没有做处理，就是无限大，无法绘制宽度了
        //EXACTLY ：固定或者填充父的宽度
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            this.width = width;
        }

        //直径+10个分隔+图片的高度，就是整一个控件的高度了（20*2+10+bitmapheight）
        //一个圆的高度，进度条的高度，图片的高度就是整一个控件的高度
        //其中+10是为了协调，画进度条从15开始，顶部留出空点
        int height = radiusCicle * 2 + radiusCicle / 2 + bitmapTemp.getHeight();

        setMeasuredDimension(this.width, height);

        //对于整个控件居中   是为了协调   也就是进度条始终居于圆中心    图标始终水平居中于圆底部
        // (20,15),(1086,25),就是
        processRect.set(radiusCicle, radiusCicle - processHeight / 2, this.width - radiusCicle, radiusCicle + processHeight / 2);

        processRunRect.set(processRect.left, processRect.top, processRect.left, processRect.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制进度
        canvas.drawRoundRect(processRect, 5, 5, processNorPaint);

        if (curProcess > 0) {
            //TODO
            processRunRect.right = this.processRect.width() * curProcess / maxProcess + processRect.left;
            if (processRunRect.right > processRect.right) {
                processRunRect.right = processRect.right;
            }
            processRunPaint.setShader(new LinearGradient(processRunRect.left, radiusCicle, processRunRect.right,
                    radiusCicle, 0xff12cab2, 0xff1283ca, Shader.TileMode.MIRROR));
            canvas.drawRoundRect(processRunRect, 5, 5, processRunPaint);
        }

        //
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        //绘制三个圆
        float stageWidth = processRect.width() / 3;
        txtPaint.setTextSize(sp2px(15));
        txtPaint.setColor(0xff676768);

        for (int i = 0; i < datas.size(); i++) {
            StageBean stageBean = datas.get(i);
            float stage = stageWidth * (i + 1) + radiusCicle / 2;
            stageBean.isLight = processRunRect.right >= stage; // 圆是否点亮
            // 圆的颜色
            circlePaint.setColor(stageBean.isLight ? (i == 0 ? 0xff13B0BB : (i == 1 ? 0xff1298C4 : 0xff1283ca)) : colorProcessBgDef);
            canvas.drawCircle(stage, radiusCicle, radiusCicle, circlePaint);
//            canvas.drawPoint(stage, radiusCicle, paint);


            // 圆中心文字
            if (stageBean.tip != 0) {
                String tip = String.valueOf(stageBean.tip);
                txtPaint.setColor(stageBean.isLight ? 0xfff1f1f1 : 0xff676767);
                txtPaint.getTextBounds(tip, 0, tip.length(), tempCalu);
                canvas.drawText(tip, stage - txtPaint.measureText(tip) / 2, radiusCicle + tempCalu.height() / 2, txtPaint);
            }

            canvas.drawBitmap(stageBean.isLight ? stageBean.selected : stageBean.nor, stage - stageBean.selected.getWidth() / 2,
                    radiusCicle * 2 + radiusCicle / 2,
                    txtPaint);

            // 点击用rect 要扩展点击范围在这里设置
            stageBean.rect.set(((int) (stageWidth * i - stageBean.selected.getWidth() / 2)),
                    radiusCicle * 2 + radiusCicle / 2,
                    ((int) (stage + stageBean.selected.getWidth())),
                    radiusCicle * 2 + radiusCicle * 2);

        }


        //
        //TODO 分3段，测试画点
//        Paint paint1 = new Paint();
//        paint1.setColor(Color.GREEN);
//        paint1.setStrokeWidth(10);
//        float tempX1 = processRect.width() / 3;
//        canvas.drawPoint(tempX1, radiusCicle, paint1);
//        canvas.drawPoint(tempX1 * 2, radiusCicle, paint1);
//        canvas.drawPoint(tempX1 * 3, radiusCicle, paint1);
    }

    int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    class StageBean {
        RectF rect;
        Bitmap nor;
        Bitmap selected;
        boolean isSelect = false;
        boolean isLight = false;
        int tip = 0;

        StageBean(RectF rect, int norResId, int selectedResId) {
            this.rect = rect;
            this.nor = BitmapFactory.decodeResource(getResources(), norResId);
            this.selected = BitmapFactory.decodeResource(getResources(), selectedResId);
        }
    }

    public void setTips(int one, int two, int three) {
        datas.get(0).tip = one;
        datas.get(1).tip = two;
        datas.get(2).tip = three;
    }

    private double oldTotal = 0;
    private double oldCur = 0;

    public void setMaxProcess(int max) {
        this.maxProcess = max;
    }

    public int getMaxProcess() {
        return this.maxProcess;
    }

    public void setCurProcess(double cur) {

        int limit1st = datas.get(0).tip;
        int limit2nd = datas.get(1).tip;
        int limit3rd = datas.get(2).tip;

        if (limit1st == 0 || limit2nd == 0 || limit3rd == 0) {
            throw new NullPointerException("must be called StagePointProcessView#setTips(...) !!!");
        }

        if (limit2nd < limit1st) {
            throw new IllegalArgumentException("[limit2nd] must be more than [limit1st]");
        }

        if (limit3rd < limit1st || limit3rd < limit2nd) {
            throw new IllegalArgumentException("[limit3rd] must be more than [limit1st] & [limit2nd]");
        }

        double total = oldCur + cur;

        Log.e(TAG, "addX: 处理之前\ntotal=" + total + " oldTotal=" + oldTotal + " cur=" + cur + " oldCur=" + oldCur +
                "\n1st=" + limit1st + "  2nd=" + limit2nd + " 3rd=" + limit3rd + "  " + (4 / limit1st * 0.33));

        oldCur = total;

//                (x - limit2nd) / (limit3rd -limit2nd) 阶段数值的比例

//                (x - limit2nd) / (limit3rd -limit2nd)     该除数表示目标[差值]相对于该阶段数值的比例(数值的比例)
//                * 0.33    该阶段的比例 * (1 / 阶段数(7,15,30))   得出该阶段相对于一个进度条的比例
//                + 0.66    该阶段相对于整个进度条的比例   + ((阶段数 - 1) * 0.33)
//                * max    比例具体数值
//                = current
        //------------------7-----[X点，就是传进来的进度，求这点对应在7-15之间的位置]------15----------30
        //设;7-15的距离是y，当x=10,10-7:15-7=x:y,
        if (total >= limit2nd) {
            //current=((x - 15) / (30 -15) * 0.33 + 0.66) *max
            total = ((total - limit2nd) / (maxProcess - limit2nd) * 0.33 + 0.66) * maxProcess;
        } else if (total >= limit1st && total < limit2nd) {
            //current = ((x - 7) / (15 -7) * 0.33 + 0.33) *max
            total = ((total - limit1st) / (limit2nd - limit1st) * 0.33 + 0.33) * maxProcess;
        } else {
            //current  = (x / 7 * 0.33 + 0) * max
            total = (total / limit1st * 0.33 + 0) * maxProcess;
        }

        Log.e(TAG, "addX: 处理 之后\n" + total + "   oldCur=" + oldCur);

        ValueAnimator animator = ValueAnimator.ofFloat(((float) oldTotal), ((float) (total)));

        oldTotal = total;

        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(700);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                curProcess = value;
                postInvalidate();
            }
        });

        animator.start();
    }

    float x = 0;
    float y = 0;


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP) {
            onTouchEvent(event);
            return true;
        } else {
            return super.dispatchTouchEvent(event);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (x == event.getX() && y == event.getY()) {  // 点击事件
                    for (int i = 0; i < datas.size(); i++) {
                        StageBean bean = datas.get(i);
                        if (bean.rect.contains(x, y)) {
                            if (bean.isLight) {
                                bean.isSelect = true;
                                invalidate();
                                Toast.makeText(mContext, bean.tip + "", Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    }
                }
                break;
        }
        return false;
    }
}
