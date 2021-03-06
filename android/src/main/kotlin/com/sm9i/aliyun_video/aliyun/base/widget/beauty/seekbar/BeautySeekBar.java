package com.sm9i.aliyun_video.aliyun.base.widget.beauty.seekbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.sm9i.aliyun_video.R;
import com.sm9i.aliyun_video.aliyun.base.widget.beauty.listener.OnProgresschangeListener;


/**
 * Created by Akira on 2018/5/30.
 */

public class BeautySeekBar extends FrameLayout {
    private Context mContext;
    /**
     * 拖拽seek
     */
    private IndicatorSeekBar mFrontSeekBar;
    /**
     * 菱形标记
     */
    private SeekBar mBackSeekBar;

    private boolean hasHistory;

    public BeautySeekBar(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public BeautySeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public BeautySeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public void setProgress(int progress) {
        mFrontSeekBar.setProgress(progress);
        //mBackSeekBar.setProgress(progress);
        mBackSeekBar.setVisibility(View.VISIBLE);
    }

    public void setLastProgress(float progress) {
        hasHistory = true;
        //mBackSeekBar.setProgress(progress);
        mFrontSeekBar.setProgress(progress);
        mBackSeekBar.setVisibility(View.VISIBLE);
    }

    public void setSeekIndicator(float progress) {
        mBackSeekBar.setProgress((int)progress);
    }

    public int getSeekIndicator() {
        return mBackSeekBar.getProgress();
    }

    public void resetProgress() {
        mFrontSeekBar.setProgress(mBackSeekBar.getProgress());
    }

    private OnProgresschangeListener mListener;

    public void setProgressChangeListener(OnProgresschangeListener listener) {
        mListener = listener;
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.alivc_beauty_seekbar, this);
        mFrontSeekBar = findViewById(R.id.front_seekbar);
        mFrontSeekBar.setIndicatorGap(10);

        mBackSeekBar = findViewById(R.id.back_seekbar);

        mBackSeekBar.setEnabled(false);
        mBackSeekBar.setActivated(false);

        mFrontSeekBar.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                if (mListener != null) {
                    mListener.onProgressChange(progress);
                }
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
    }
}
