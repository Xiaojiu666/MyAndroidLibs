package com.xiaojiu.studylibs.customview;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.socks.library.KLog;
import com.test.myselfview.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class CustomMyView extends LinearLayout implements View.OnTouchListener {


    private int touchSlop;
    /**
     * 下拉头的高度
     */
    private int hideHeaderHeight;
    /**
     * 是否已加载过一次layout，这里onLayout中的初始化只需加载一次
     */
    private boolean loadOnce;
    /**
     * 下拉头部回滚的速度
     */
    public static final int SCROLL_SPEED = -20;
    /**
     * 下拉状态
     */
    public static final int STATUS_PULL_TO_REFRESH = 0;

    /**
     * 释放立即刷新状态
     */
    public static final int STATUS_RELEASE_TO_REFRESH = 1;

    /**
     * 正在刷新状态
     */
    public static final int STATUS_REFRESHING = 2;

    /**
     * 刷新完成或未刷新状态
     */
    public static final int STATUS_REFRESH_FINISHED = 3;
    /**
     * 正在回滚
     */
    public boolean STATUS_REFRESH_BACKING = false;
    /**
     * 当前处理什么状态，可选值有STATUS_PULL_TO_REFRESH, STATUS_RELEASE_TO_REFRESH,
     * STATUS_REFRESHING 和 STATUS_REFRESH_FINISHED
     */
    private int currentStatus = STATUS_REFRESH_FINISHED;

    /**
     * 一分钟的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MINUTE = 60 * 1000;

    /**
     * 一小时的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    /**
     * 一天的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * 一月的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MONTH = 30 * ONE_DAY;

    /**
     * 一年的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    /**
     * 为了防止不同界面的下拉刷新在上次更新时间上互相有冲突，使用id来做区分
     */
    private int mId = -1;
    /**
     * 上次更新时间的字符串常量，用于作为SharedPreferences的键值
     */
    private static final String UPDATED_AT = "updated_at";
    /**
     * 用于存储上次更新时间
     */
    private SharedPreferences preferences;

    /**
     * 上次更新时间的毫秒值
     */
    private long lastUpdateTime;

    private ImageView arrow;
    private TextView description;
    private TextView updateAt;
    private ProgressBar progressBar;
    private View header;
    private MarginLayoutParams headerLayoutParams;
    private RecyclerView listView;
    private float yDown;
    private int topMargin;
    private Subscription subscribe;
    private Subscription hideHeaderSubscribe;
    private int lastStatus;
    private PullToRefreshListener mListener;


    public CustomMyView(Context context) {
        this(context, null);
    }

    public CustomMyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //1.加载头布局，添加到当前viewgroup中
        header = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh, null, true);
        progressBar = (ProgressBar) header.findViewById(R.id.progress_bar);
        arrow = (ImageView) header.findViewById(R.id.arrow);
        description = (TextView) header.findViewById(R.id.description);
        updateAt = (TextView) header.findViewById(R.id.updated_at);
        //定义手指最小滑动距离，如果超过这个高度，才会进行下拉刷新
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        //touchSlop = 600;
        setOrientation(VERTICAL);
        addView(header);
    }

    public CustomMyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //changed作用还未知,经过尝试只有第一次家再说true 用于第一次初始化用
        if (changed && !loadOnce) {
            //2.隐藏头布局
            hideHeaderHeight = -header.getHeight();
            //2.1 获取头布局的 参数
            headerLayoutParams = (MarginLayoutParams) header.getLayoutParams();
            headerLayoutParams.topMargin = hideHeaderHeight;

            //3.把Linerlayout中的 的儿个 子布局设置为 RecyclerView类型的，通过RecyclerView的触摸事件进行整体的下拉刷新
            listView = (RecyclerView) getChildAt(1);
            listView.setOnTouchListener(this);
            loadOnce = true;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        KLog.e("STATUS_REFRESH_BACKING" + STATUS_REFRESH_BACKING);
        //9. 当布局处于回弹的情况下，不能让用户去再去重复下拉
        if (STATUS_REFRESH_BACKING) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                yDown = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float yMove = event.getRawY();
                int distance = (int) (yMove - yDown);
                if (distance < touchSlop) {
                    return false;
                }

                // 如果手指是下滑状态，并且下拉头是完全隐藏的，就屏蔽下拉事件,否则 RecyclerView和下拉就会冲突
                if (distance <= 0 && headerLayoutParams.topMargin <= hideHeaderHeight) {
                    return false;
                }

                if (currentStatus != STATUS_REFRESHING) {
                    //6.移动时，如果头部距离顶部的距离大于0，说明松手的时候，动作变为释放刷新
                    //否则还是下拉状态
                    if (headerLayoutParams.topMargin > 0) {
                        currentStatus = STATUS_RELEASE_TO_REFRESH;
                    } else {
                        currentStatus = STATUS_PULL_TO_REFRESH;
                    }
                    //4.获取移动距离 让头部View的高等与滑动的距离
                    headerLayoutParams.topMargin = (distance / 2) + hideHeaderHeight;
                    header.setLayoutParams(headerLayoutParams);
                }
                break;
            case MotionEvent.ACTION_UP:
            default:
                if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
                    // 7.松手时如果是释放立即刷新状态，就去调用正在刷新的任务
                    refreshing();
                } else if (currentStatus == STATUS_PULL_TO_REFRESH) {
                    // 7.松手时如果是下拉状态，就去调用隐藏下拉头的任务
                    hideHeader();
                }
                break;
        }
        // 时刻记得更新下拉头中的信息
        if (currentStatus == STATUS_PULL_TO_REFRESH
                || currentStatus == STATUS_RELEASE_TO_REFRESH) {
            // 当前正处于下拉或释放状态，要让ListView失去焦点，否则被点击的那一项会一直处于选中状态
            listView.setPressed(false);
            listView.setFocusable(false);
            listView.setFocusableInTouchMode(false);
            lastStatus = currentStatus;
            // 当前正处于下拉或释放状态，通过返回true屏蔽掉ListView的滚动事件
            return true;
        }

        return false;
    }


    private void refreshing() {
        topMargin = headerLayoutParams.topMargin;
        subscribe = Observable.interval(10, TimeUnit.MILLISECONDS).map(new Func1<Long, Integer>() {
            @Override
            public Integer call(Long aLong) {
                int i = new Long(aLong).intValue();
                return topMargin - i * 8;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (integer > 0) {
                            headerLayoutParams.topMargin = integer;
                            header.setLayoutParams(headerLayoutParams);
                            //8. 下拉刷新的时候用户如果重复下拉的话 会造成 手势混乱
                            STATUS_REFRESH_BACKING = true;
                        }
                        if (integer <= 0) {
                            subscribe.unsubscribe();
                            mListener.onRefresh();
                            hideHeader();
                        }
                    }
                });

    }

    /**
     * 使当前线程睡眠指定的毫秒数。
     *
     * @param time 指定当前线程睡眠多久，以毫秒为单位
     */
    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void hideHeader() {
        //5.隐藏头部,
        //需要判断好手势状态，不然一直刷新 会造成重复调用，数值混乱
        topMargin = headerLayoutParams.topMargin;
        hideHeaderSubscribe = Observable.interval(10, TimeUnit.MILLISECONDS).map(new Func1<Long, Integer>() {
            @Override
            public Integer call(Long aLong) {
                int i = new Long(aLong).intValue();
                return topMargin - i * 3;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (integer >= hideHeaderHeight) {
                            headerLayoutParams.topMargin = integer;
                            header.setLayoutParams(headerLayoutParams);
                        } else {
                            STATUS_REFRESH_BACKING = false;
                            //需要停止发射 否则将会无限递增
                            hideHeaderSubscribe.unsubscribe();
                        }
                    }
                });
    }

    /**
     * 更新下拉头中的信息。
     */
    private void updateHeaderView() {
        if (lastStatus != currentStatus) {
            if (currentStatus == STATUS_PULL_TO_REFRESH) {
                description.setText(getResources().getString(R.string.pull_to_refresh));
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            } else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
                description.setText(getResources().getString(R.string.release_to_refresh));
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            } else if (currentStatus == STATUS_REFRESHING) {
                description.setText(getResources().getString(R.string.refreshing));
                progressBar.setVisibility(View.VISIBLE);
                arrow.clearAnimation();
                arrow.setVisibility(View.GONE);
            }
            refreshUpdatedAtValue();
        }
    }

    /**
     * 根据当前的状态来旋转箭头。
     */
    private void rotateArrow() {
        float pivotX = arrow.getWidth() / 2f;
        float pivotY = arrow.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        if (currentStatus == STATUS_PULL_TO_REFRESH) {
            fromDegrees = 180f;
            toDegrees = 360f;
        } else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
            fromDegrees = 0f;
            toDegrees = 180f;
        }
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        animation.setDuration(100);
        animation.setFillAfter(true);
        arrow.startAnimation(animation);
    }

    /**
     * 刷新下拉头中上次更新时间的文字描述。
     */
    private void refreshUpdatedAtValue() {
        lastUpdateTime = preferences.getLong(UPDATED_AT + mId, -1);
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastUpdateTime;
        long timeIntoFormat;
        String updateAtValue;
        if (lastUpdateTime == -1) {
            updateAtValue = getResources().getString(R.string.not_updated_yet);
        } else if (timePassed < 0) {
            updateAtValue = getResources().getString(R.string.time_error);
        } else if (timePassed < ONE_MINUTE) {
            updateAtValue = getResources().getString(R.string.updated_just_now);
        } else if (timePassed < ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_MINUTE;
            String value = timeIntoFormat + "分钟";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else if (timePassed < ONE_DAY) {
            timeIntoFormat = timePassed / ONE_HOUR;
            String value = timeIntoFormat + "小时";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else if (timePassed < ONE_MONTH) {
            timeIntoFormat = timePassed / ONE_DAY;
            String value = timeIntoFormat + "天";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else if (timePassed < ONE_YEAR) {
            timeIntoFormat = timePassed / ONE_MONTH;
            String value = timeIntoFormat + "个月";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        } else {
            timeIntoFormat = timePassed / ONE_YEAR;
            String value = timeIntoFormat + "年";
            updateAtValue = String.format(getResources().getString(R.string.updated_at), value);
        }
        updateAt.setText(updateAtValue);
    }

    public void setOnRefreshListener(PullToRefreshListener listener) {
        mListener = listener;
    }

    /**
     * 下拉刷新的监听器，使用下拉刷新的地方应该注册此监听器来获取刷新回调。
     *
     * @author guolin
     */
    public interface PullToRefreshListener {

        /**
         * 刷新时会去回调此方法，在方法内编写具体的刷新逻辑。注意此方法是在子线程中调用的， 你可以不必另开线程来进行耗时操作。
         */
        void onRefresh();

    }
}
