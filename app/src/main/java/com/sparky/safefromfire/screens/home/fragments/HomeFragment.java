package com.sparky.safefromfire.screens.home.fragments;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.base.BaseActivity;
import com.sparky.safefromfire.base.BaseFragment;
import com.sparky.safefromfire.screens.WeatherForecastActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class HomeFragment extends BaseFragment {

    public static final String TAG = "HomeFragment";

    @BindView(R.id.homefragment_probability_fire)
    TextView fireProbabilityTw;

    @BindView(R.id.homefragment_probability_reason)
    TextView fireProbabilityReasonTw;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setupViews(View view) {
        fireProbabilityReasonTw.setPaintFlags(fireProbabilityReasonTw.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @OnClick(R.id.homefragment_probability_reason)
    public void onProbabilityReasonPressed() {
        ((BaseActivity) getActivity()).gotoActivity(WeatherForecastActivity.class, false);
    }
}
