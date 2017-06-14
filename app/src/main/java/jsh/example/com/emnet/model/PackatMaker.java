package jsh.example.com.emnet.model;

import jsh.example.com.emnet.data.NetworkParam;

/**
 * Created by EMGRAM on 2017-05-23.
 */

public class PackatMaker {
    public NetworkParam getAct1Packat(){
        NetworkParam networkParam = new NetworkParam();
        networkParam.set(NetworkParam.HttpMethod.POST, NetworkParam.Http.HTTP, "", null);
        return networkParam;
    }

    public NetworkParam getAct2Packat(){
        NetworkParam networkParam = new NetworkParam();
        networkParam.set(NetworkParam.HttpMethod.POST, NetworkParam.Http.HTTP, "", null);
        return networkParam;
    }

    public NetworkParam setAct1Packat(){
        NetworkParam networkParam = new NetworkParam();
        networkParam.set(NetworkParam.HttpMethod.POST, NetworkParam.Http.HTTP, "", null);
        return networkParam;
    }
}
