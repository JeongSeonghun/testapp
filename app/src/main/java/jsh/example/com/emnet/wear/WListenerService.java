package jsh.example.com.emnet.wear;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by EMGRAM on 2017-05-23.
 */

public class WListenerService extends WearableListenerService{

    @Override
    public void onPeerConnected(Node node) {
        super.onPeerConnected(node);
    }

    @Override
    public void onPeerDisconnected(Node node) {
        super.onPeerDisconnected(node);
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        String message;
        message = new String(messageEvent.getData());

        String msgPath;
        msgPath = messageEvent.getPath();

        WMessageReceiver wMessageReceiver = new WMessageReceiver();

        if(msgPath.equals(WCommonData.PATH)){
            wMessageReceiver.callReceivedData(message);

        }else{
            super.onMessageReceived(messageEvent);
        }

    }

}
