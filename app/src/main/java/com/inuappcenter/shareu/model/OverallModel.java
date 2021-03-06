package com.inuappcenter.shareu.model;

import android.util.Log;
import android.widget.Toast;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.OverallNoticeActivity;
import com.inuappcenter.shareu.my_class.Notice;
import com.inuappcenter.shareu.recycler.OverallNoticeAdapter;
import com.inuappcenter.shareu.service.RetrofitHelper;
import com.inuappcenter.shareu.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverallModel {

    // MutableLiveData란 변경할 수 있는 LiveData 형이다.
    // 일반적인 LiveData형은 변경할 수 없고 오로지 데이터의 변경값만을 소비하는데 반해
    // MutableLiveData는 데이터를 UI Thread와 Background Thread에서 선택적으로 바꿀 수 있다.

    //LiveData has no public method to modify its data. setValue 나 postValue 불가능 하다.
    //setValue는 데이터를 set하는데 UI Thread에서 진행, 값을 즉시 받아옴 postValue는 데이터를 set하긴 하지만 background에서 과정이 진행되다가 UI Thread에서
    //값을 변경함. postValue하고 바로 getValue를 하면 값을 받아오지 못할 확률이 크다.
    //메인스레드에서 LiveData를 갱신하려면 반드시 setValue(T) 메서드를 호출해야 한다. 만약, 작업스레드에서 LiveData를 갱신하려면 postValue(T) 메서드를 호출해야 한다.
    // .
    private MutableLiveData<List<Notice>> _dataList = new MutableLiveData<>();
    private MutableLiveData<Boolean> flag = new MutableLiveData<>();

    public MutableLiveData<List<Notice>> getDataList() {
        return _dataList;
    }
    public void setDataList(List<Notice> dataList) {
        this._dataList.postValue(dataList);
        //콜백
    }

    public MutableLiveData<Boolean> getFlag() {
        return flag;
    }

    public void setDatas()
    {
        RetrofitService networkService = RetrofitHelper.create();
        networkService.getNotice().enqueue(new Callback<List<Notice>>(){
            @Override
            public void onResponse(Call<List<Notice> > call, Response<List<Notice>> response)
            {
                flag.postValue(false);
                if(response.isSuccessful())
                {
                    List<Notice> tmp_list = new ArrayList<>();

                    for(int i=0;i<response.body().size();i++)
                    {
                        tmp_list.add(response.body().get(i));
                        tmp_list.get(i).setNoticeKey(i+1);
                    }
                    setDataList(tmp_list);
                    //overallNoticeView.setDatas(overallModel.getDatas(response.body()));
                }

            }
            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {

                flag.postValue(true);

            }
        });
    }


}
