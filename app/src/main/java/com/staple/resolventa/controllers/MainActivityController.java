package com.staple.resolventa.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.core.content.FileProvider;

import com.google.android.material.snackbar.Snackbar;
import com.staple.resolventa.R;
import com.staple.resolventa.activities.MainActivity;
import com.staple.resolventa.execruns.PdfToBitmap;
import com.staple.resolventa.prosol.Problem;
import com.staple.resolventa.webs.PostProblem;
import com.staple.resolventa.webs.ResponseHandler;

import java.io.File;
import java.util.Objects;

public class MainActivityController implements Controller {

    private final String cur_type;
    private String pdf_path;
    private final MainActivity activity;
    private final PostProblem postman;
    private final Animation fade_in;

    public MainActivityController(MainActivity activity){
        this.activity = activity;
        postman = new PostProblem(activity.getString(R.string.base_url));
        cur_type = activity.getString(R.string.nst);
        fade_in = AnimationUtils.loadAnimation(activity, R.anim.crossfade);
    }

    public void setPdf_path(String pdf_path) {
        this.pdf_path = pdf_path;
    }

    public String getCur_type() {
        return cur_type;
    }

    public MainActivity getActivity() {
        return activity;
    }

    public void display_exception(Exception e){
        Snackbar snackbar = Snackbar.make(activity.main_layout, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG);
        snackbar.setTextColor(0xFFFFFFFF);
        snackbar.setBackgroundTint(0xFFB71C1C);
        snackbar.show();
    }

    public void on_click_submit(){
        postman.post_and_handle(new Problem(cur_type, activity.edit_text.getText().toString()), new ResponseHandler(this));
    }
}
