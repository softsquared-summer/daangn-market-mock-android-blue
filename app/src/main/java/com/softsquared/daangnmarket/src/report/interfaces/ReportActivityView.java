package com.softsquared.daangnmarket.src.report.interfaces;

public interface ReportActivityView {
    void validateReportSuccess(boolean isSuccess, int code, String message);

    void validateReportFailure();
}
