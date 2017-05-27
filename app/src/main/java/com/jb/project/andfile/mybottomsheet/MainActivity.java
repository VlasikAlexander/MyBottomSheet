package com.jb.project.andfile.mybottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    NestedScrollView bottomSheet;
    BottomSheetBehavior mBottomSheetBehavior;
    FragmentManager fm;
    Fragment mFragment;
    Fragment mFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment = new MainFragment();
        mFragment2 = new SecondFragment();

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, mFragment ).commit();
        fm.beginTransaction().add(R.id.container2, mFragment2).commit();


        mButton = (Button)findViewById(R.id.button);
        bottomSheet = (NestedScrollView)findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            // mButton.setText("Collapse Bottom Sheet");

        } else {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            // mButton.setText("Expand Bottom Sheet");
        }
    }
});
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mButton.setText("Expand Bottom Sheet");
                } else mButton.setText("Collapse Bottom Sheet");
                Toast.makeText(MainActivity.this, "StateChanged", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Toast.makeText(MainActivity.this, "Slide", Toast.LENGTH_SHORT).show();
            }
        });
        
        mBottomSheetBehavior.setPeekHeight(100);
        mBottomSheetBehavior.setSkipCollapsed(false);


    }
}
