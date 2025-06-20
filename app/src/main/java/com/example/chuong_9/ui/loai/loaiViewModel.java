package com.example.chuong_9.ui.loai;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class loaiViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public loaiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is kn fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
