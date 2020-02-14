package com.inuappcenter.shareu.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.fragment.GyoyangFragment;
import com.inuappcenter.shareu.fragment.MajorFragment;
import com.inuappcenter.shareu.my_class.Major;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;

public class MajorActivity extends AppCompatActivity {
    IndexFastScrollRecyclerView recyclerView;
    IndexFastScrollRecyclerView recyclerView2;
    LinearLayoutManager manager;
    private ArrayList<Major> dataList;
    private FragmentManager fragmentManager;
    private MajorFragment fragmentMajor;
    private GyoyangFragment fragmentGyoyang;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_major);
        //this.initializeData();

        TextView tv_major = (TextView) findViewById(R.id.tv_major) ;
        TextView tv_gyoyang = (TextView) findViewById(R.id.tv_gyoyang) ;
        Button btn_left_bar_major = (Button)findViewById(R.id.btn_left_bar_major);

        tv_gyoyang.setTextColor(Color.parseColor("#000000"));
        tv_major.setTextColor(Color.parseColor("#574FBA"));
        tv_major.setTypeface(Typeface.DEFAULT_BOLD);

        fragmentManager = getSupportFragmentManager();
        fragmentMajor = new MajorFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_frame_category,fragmentMajor);
        transaction.commit();
        TextView.OnClickListener onClickListener = new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_major :
                        if(tv_major.getCurrentTextColor()!=ContextCompat.getColor(getApplicationContext(), R.color.Iris)){
                            tv_major.setTextColor(Color.parseColor("#574FBA"));
                            tv_gyoyang.setTextColor(Color.parseColor("#000000"));
                            tv_gyoyang.setTypeface(Typeface.DEFAULT_BOLD);
                            fragmentManager = getSupportFragmentManager();
                            fragmentMajor = new MajorFragment();
                            transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.layout_frame_category,fragmentMajor);
                            transaction.commit();
                        }
                        break;
                    case R.id.tv_gyoyang:
                        if(tv_gyoyang.getCurrentTextColor()!=ContextCompat.getColor(getApplicationContext(), R.color.Iris))
                        {
                            tv_major.setTextColor(Color.parseColor("#000000"));
                            tv_gyoyang.setTextColor(Color.parseColor("#574FBA"));
                            tv_gyoyang.setTypeface(Typeface.DEFAULT_BOLD);
                            fragmentManager = getSupportFragmentManager();
                            fragmentGyoyang = new GyoyangFragment();
                            transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.layout_frame_category,fragmentGyoyang).commitAllowingStateLoss();
                        }
                        break;
                }
            }
        } ;
        tv_major.setOnClickListener(onClickListener) ;
        tv_gyoyang.setOnClickListener(onClickListener);


        Button.OnClickListener onClickListener2 = new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch(view.getId())
                {
                    case R.id.btn_left_bar_major:
                    {
                        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            getSupportFragmentManager().popBackStack();
                        } else {
                            finish();
                        }
                    }
                }
            }
        };
        btn_left_bar_major.setOnClickListener(onClickListener2);

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

}
