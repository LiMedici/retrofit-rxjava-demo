package com.medici.demo.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.medici.demo.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    /**
     * 内容
     */
    private TextView mTxtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtContent = findViewById(R.id.txt_content);
        MainPresenter mPresenter = new MainPresenter(this);
        mPresenter.start();
    }


    @Override
    public void show(String content) {
        mTxtContent.setText(content);
    }
}
