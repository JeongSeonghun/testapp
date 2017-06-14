package jsh.example.com.emnet.model;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import jsh.example.com.emnet.data.CommonData;
import jsh.example.com.emnet.data.NetworkParam;

/**
 * Created by EMGRAM on 2017-05-18.
 */

public class NetworkManager {

    private static NetworkManager networkManager;
    private HttpURLConnection conn;
    private NetworkListener networkListener;

    private boolean isNetwork = false;
    private int ConTimeout = 1000;

    public static String REQUEST_METHOD_POST ="POST";
    public static String REQUEST_METHOD_GET = "GET";

    private NetworkManager(){}

    public static NetworkManager getInstance(){
        if(networkManager == null){
            networkManager = new NetworkManager();
        }

        return networkManager;
    }

    public void request(Object param, NetworkListener networkListener){
        this.networkListener = networkListener;

        if(isNetwork){
            return;
        }

        new NetRequest(param, networkListener).start();
    }

    private void openURL(Object param){
        NetworkParam networkParam;
        URL url;
        StringBuilder html = new StringBuilder();

        if(param instanceof NetworkParam){
            networkParam = (NetworkParam)param;
        }else{
            return;
        }

        try {
            url = new URL(networkParam.getStrUrl());
            //url.getProtocol();
            if(url.getProtocol().toLowerCase().equals("https")){
                conn = (HttpsURLConnection)url.openConnection();
            }else{
                conn = (HttpURLConnection)url.openConnection();
            }
//            if(networkParam.getHttp() == NetworkParam.Http.HTTPS){
//                httpURLConnection = (HttpsURLConnection)url.openConnection();
//            }else{
//                httpURLConnection = (HttpURLConnection)url.openConnection();
//            }

//            if(urlManager.isNMapURL(mAction) == false){
//                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            }

            if(conn != null){
                // POST GET 설정
                conn.setRequestMethod(networkParam.getHtttpMethod());
                // 출력
                conn.setDoOutput(true);

                //POST param 입력 및 site 접근
                if(networkParam.getHtttpMethod().equals(CommonData.strPost)){
                    JSONObject jsonObject = new JSONObject((String)((NetworkParam) param).getNetworkParam());
                    String jsonStr = jsonObject.toString();

                    conn.setRequestProperty("Content-Length", Integer.toString(jsonStr.length()));
                    conn.getOutputStream().write(jsonStr.getBytes());
                }else{
                    return;
                }

                // timeout 시간 설정
                conn.setConnectTimeout(ConTimeout);

                // site 접근 후 응답
                int nResCode = conn.getResponseCode();

                if(nResCode == HttpURLConnection.HTTP_OK){
                    //String sContentType = httpURLConnection.getContentType();

                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    //BufferedInputStream input = new BufferedInputStream(in);

                    for(;;) {
                        String line = br.readLine();
                        if(line == null) break;
                        html.append(line).append('\n');
                    }

                    br.close();
                    //input.close();
                    String resStr = html.toString();

                    networkListener.onNetComplete(NetworkErrorCode.NET_E_CODE_SUCCESS, resStr, null);

                }else if(nResCode == HttpURLConnection.HTTP_NO_CONTENT){
                    networkListener.onNetComplete(NetworkErrorCode.NET_E_CODE_NO_CONTENTS, null, null);
                }else{
                    networkListener.onNetComplete(nResCode, null, null);
                }
            }//if(conn != null)
            else{
                networkListener.onNetComplete(NetworkErrorCode.NET_E_CODE_CONNECT_FAIL, null, null);
            }



//        } catch (MalformedURLException e) { http https urlConnection
//            e.printStackTrace();
//        } catch (IOException e) { // url openConnection
//            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            networkListener.onNetComplete(0, null, "");
        } finally {
            if(conn != null){
                conn.disconnect();
            }

        }

    }

    private void openNMapURL(){

    }

    private void webRead(){

    }

    public void cancelNetwork(){
        new NetCancel().start();
    }

    private String toStringParam(NetworkParam networkParam){
        // 내용
        return "";
    }

    private class NetRequest extends Thread{
        Object param;
        NetworkListener networkListener;

        public NetRequest(Object param, NetworkListener networkListener){
            this.param = param;
            this.networkListener = networkListener;
        }

        public void run(){
            openURL(param);
        }
    }

    private class NetCancel extends Thread{
        public void run(){
            conn.disconnect();
        }
    }
}
