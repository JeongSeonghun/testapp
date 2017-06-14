package jsh.example.com.emnet.model;

/**
 * Created by EMGRAM on 2017-05-18.
 */

public interface NetworkListener {
    // Main
    void onComplete(int resCode, Object response, String eMsg);

    // Network
    void onNetComplete(int resCode, Object response, String eMsg);
}
