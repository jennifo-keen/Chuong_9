package com.example.chuong_9.ui.mon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class monViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public monViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is kn fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
