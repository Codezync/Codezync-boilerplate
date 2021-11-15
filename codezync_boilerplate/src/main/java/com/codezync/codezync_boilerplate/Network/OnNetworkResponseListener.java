package com.codezync.codezync_boilerplate.Network;

// Network class to handle api request
public interface OnNetworkResponseListener<T,X> {
    void  onSuccessResponse(T response);
    void  onErrorResponse(X error);
    void  onNetworkErrorResponse(String error);
}

