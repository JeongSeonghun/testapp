package jsh.example.com.emnet.wear;

/**
 * Created by EMGRAM on 2017-05-23.
 */

public class WController {
    private static WController wController;

    private WController(){}

    public static WController getInstance(){
        if(wController == null){
            wController = new WController();
        }

        return wController;
    }


}
