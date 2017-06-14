package jsh.example.com.emnet.model;

import jsh.example.com.emnet.AppConfig;

/**
 * Created by EMGRAM on 2017-05-18.
 */

public class URLManager {

    private static URLManager urlManager;

    private String act1Url;
    private String act2Url;

    private URLManager(){}

    public URLManager getInstance(){
        if(urlManager == null){
            urlManager = new URLManager();
            initUrl();
        }
        return urlManager;
    }

    private void initUrl(){
        if(AppConfig.DEBUG_MODE){
            setAct1Url("");
            setAct2Url("");
        }else{
            setAct1Url("");
            setAct2Url("");
        }
    }

    public String getUrl(int actCode){
        switch(actCode){
            case NetworkAction.ACT1:
                return getAct1Url();
            case NetworkAction.ACT2:
                return getAct2Url();
            default:
                return "";
        }
    }


    private void setAct1Url(String act1Url) {
        this.act1Url = act1Url;
    }

    private String getAct1Url(){
        return act1Url;
    }

    private void setAct2Url(String act2Url){
        this.act2Url = act2Url;
    }

    private String getAct2Url(){
        return act2Url;
    }
}
