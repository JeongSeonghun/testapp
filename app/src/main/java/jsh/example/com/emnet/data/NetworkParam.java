package jsh.example.com.emnet.data;

/**
 * Created by EMGRAM on 2017-05-18.
 */

public class NetworkParam {
    private String strHttpMethod = CommonData.strGet;
    private Http http = Http.HTTP;
    private Object networkParam;
    private String strUrl;

    public enum HttpMethod{GET, POST}
    public enum Http{HTTP, HTTPS}

    public void setHttpMethod(HttpMethod httpMethod){
        if(httpMethod == HttpMethod.GET){
            strHttpMethod = CommonData.strGet;
        }else{
            strHttpMethod = CommonData.strPost;
        }
    }

    public String getHtttpMethod(){
        return strHttpMethod;
    }

    public void setHttp(Http http){
        this.http = http;
    }

    public Http getHttp(){
        return http;
    }

    public void setStrUrl(String strUrl){
        this.strUrl = strUrl;
    }

    public String getStrUrl(){
        return strUrl;
    }

    public void setNetworkParam(Object networkParam){
        this.networkParam = networkParam;
    }

    public Object getNetworkParam(){
        return networkParam;
    }

    public void set(HttpMethod httpMethod, Http http, Object networkParam){
        setHttpMethod(httpMethod);
        setHttp(http);
        setNetworkParam(networkParam);
    }

    public void set(HttpMethod httpMethod, Http http, String strUrl, Object networkParam){
        setHttpMethod(httpMethod);
        setHttp(http);
        setStrUrl(strUrl);
        setNetworkParam(networkParam);
    }
}
