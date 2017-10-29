package com.baseproject.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.baseproject.rest.ApiClient;
import com.baseproject.rest.ApiInterface;
import com.baseproject.utils.PrefStore;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by anshul on 12/2/17.
 */

public class BaseActivity extends AppCompatActivity {

    private Toast toast;
    public PrefStore store;
    public ApiInterface apiService;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        store = new PrefStore(this);
        initProgressDialog();

        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("waiting...");
    }

    public void startProgressDialog() {
        try {
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopProgressDialog() {
        try {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //dateCheck();
    }

    private void dateCheck() {
        Calendar validUntillCal = Calendar.getInstance();
        validUntillCal.set(Calendar.YEAR, 2017);
        validUntillCal.set(Calendar.MONTH, Calendar.MAY);
        validUntillCal.set(Calendar.DAY_OF_MONTH, 01);

        Calendar currentCal = Calendar.getInstance();

        if (currentCal.after(validUntillCal)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Message");
            builder.setMessage("Expired, Ask for renew");
            builder.setCancelable(false);
            builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finishAffinity();
                }
            });
            builder.show();
        }
     }

    public void hideSoftKeyboard() {
        try {
            if (getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideSoftKeyboard(EditText editText) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToast(String message) {
        toast.setText(message);
        toast.show();
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void exitFromApp(){
        finishAffinity();
    }

    public void loge(String key, String log) {
        if (key == null || log == null)
            return;
        Log.e("Logger: " + key + ": ", log);
    }

    public void setLanguage(String lang, Class activityClass) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, activityClass);
        startActivity(refresh);
        finish();
    }

    public void setLanguage(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}
