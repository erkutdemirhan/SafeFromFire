package com.sparky.safefromfire.screens.home.fragments;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class InfoFragment extends BaseFragment {

    public static final String TAG = "InfoFragment";

    @BindView(R.id.info_webview)
    WebView webView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_info;
    }

    @Override
    protected void setupViews(View view) {
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.youtube.com/embed/7EaT5A5hf7I");
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
