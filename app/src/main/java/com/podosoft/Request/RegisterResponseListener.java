package com.podosoft.Request;

public interface RegisterResponseListener {
    void didRegister(RegisterResponse response, String message);
    void didError(String message);
}
