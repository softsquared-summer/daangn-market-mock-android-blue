package com.softsquared.daangnmarket.src.anotherAll.interfaces;



import com.softsquared.daangnmarket.src.anotherAll.models.ResponseProductAnother;

import java.util.ArrayList;

public interface AnotherAllActivityView {
    void validateProductAnotherSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseProductAnother.Result> resultArrayList);

    void validateProductAnotherFailure();
}
