package jsh.example.com.emnet.model;

import jsh.example.com.emnet.data.NetworkParam;

/**
 * Created by EMGRAM on 2017-05-18.
 */

public class ProtocolManager {
    private static ProtocolManager protocolManager;
    private NetworkManager networkManager;
    private PackatMaker packatMaker;
    private NetworkListener mNetworkListener;

    private ProtocolManager(){
        networkManager = NetworkManager.getInstance();
        packatMaker = new PackatMaker();
    }

    public ProtocolManager getIntance(){
        if(protocolManager == null){
            protocolManager = new ProtocolManager();
        }

        return protocolManager;
    }

    public void cancel(){
        if(networkManager!=null){
            networkManager.cancelNetwork();
        }
    }

    public void getMethod1(NetworkListener mNetworkListener){
        this.mNetworkListener = mNetworkListener;

        // 내용 1

        NetworkParam networkParam = packatMaker.getAct1Packat();

        networkManager.request(networkParam, method1Listener);
    }

    public void getMethod2(NetworkListener mNetworkListener){
        this.mNetworkListener = mNetworkListener;

        // 내용 2

        NetworkParam networkParam = packatMaker.getAct2Packat();

        networkManager.request(networkParam, method2Listener);
    }


    private NetworkListener method1Listener = new NetworkListener() {
        @Override
        public void onComplete(int resCode, Object response, String eMsg) {

        }

        @Override
        public void onNetComplete(int resCode, Object response, String eMsg) {
            mNetworkListener.onComplete(resCode, response, eMsg);
        }
    };

    private NetworkListener method2Listener = new NetworkListener() {
        @Override
        public void onComplete(int resCode, Object response, String eMsg) {

        }

        @Override
        public void onNetComplete(int resCode, Object response, String eMsg) {
            mNetworkListener.onComplete(resCode, response, eMsg);
        }
    };

}
