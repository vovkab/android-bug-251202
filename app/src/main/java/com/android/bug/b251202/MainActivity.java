package com.android.bug.b251202;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private View mAndroidPopupMenuButton;
    private View mAppCompatPopupMenuButton;

    private android.widget.PopupMenu mAndroidPopupMenu;
    private android.support.v7.widget.PopupMenu mAppCompatPopupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAndroidPopupMenuButton = findViewById(R.id.android_popupmenu);
        mAndroidPopupMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndroidPopupMenu();
            }
        });

        mAppCompatPopupMenuButton = findViewById(R.id.appcompat_popupmenu);
        mAppCompatPopupMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppCompatPopupMenu();
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        mAndroidPopupMenuButton.post(new Runnable() {
            @Override
            public void run() {
                mAndroidPopupMenuButton.performClick();
                mAppCompatPopupMenuButton.performClick();
            }
        });
    }

    private void showAndroidPopupMenu() {
        dismiss(mAndroidPopupMenu);

        mAndroidPopupMenu = new android.widget.PopupMenu(this, mAndroidPopupMenuButton);
        mAndroidPopupMenu.getMenuInflater().inflate(R.menu.popup, mAndroidPopupMenu.getMenu());
        mAndroidPopupMenu.show();
    }

    private void showAppCompatPopupMenu() {
        dismiss(mAppCompatPopupMenu);

        mAppCompatPopupMenu = new PopupMenu(this, mAppCompatPopupMenuButton);
        mAppCompatPopupMenu.getMenuInflater().inflate(R.menu.popup, mAppCompatPopupMenu.getMenu());
        mAppCompatPopupMenu.show();
    }

    private void dismiss(android.widget.PopupMenu popupMenu) {
        if (popupMenu != null) popupMenu.dismiss();
    }

    private void dismiss(android.support.v7.widget.PopupMenu popupMenu) {
        if (popupMenu != null) popupMenu.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dismiss(mAndroidPopupMenu);
        dismiss(mAppCompatPopupMenu);
    }
}
