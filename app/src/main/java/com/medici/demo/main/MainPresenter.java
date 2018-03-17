package com.medici.demo.main;

import com.medici.demo.helper.MediciHelper;
import com.medici.demo.model.api.CodeModel;
import com.medici.retrofit.data.DataSource;

/**
 * ***************************************
 *
 * @desc:
 * @author：李宗好
 * @time: 2018/3/14 0014 20:21
 * @email：lzh@cnbisoft.com
 * @version：
 * @history:
 *
 * ***************************************
 */
public class MainPresenter implements MainContract.Presenter,DataSource.Callback<CodeModel>{

    private MainContract.View mView;

    public MainPresenter(MainContract.View view){
        this.mView = view;
    }

    @Override
    public void start() {
        MediciHelper.sendCode("18156998386",this);
    }

    @Override
    public void onDataLoaded(CodeModel codeModel) {
        mView.show(codeModel.toString());
    }

    @Override
    public void onDataNotAvailable(int strRes) {

    }
}
