package com.codezync.codezync_boilerplate.Listeners;

public interface OnResponseListener<X> {
    void  onSuccessResponse(X response);
    void  onErrorResponse(String error);
    void  onNetworkFailureResponse(String error);

}
