package com.testvagrant.tatests.workflows.mobileweb;

import static com.testvagrant.core.BaseContext.getPlatform;
import com.testvagrant.tatests.workflows.model.mobileweb.IBoardCanvasWF;
import com.testvagrant.tatests.workflows.model.mobileweb.IBoardWF;
import com.testvagrant.tatests.workflows.model.mobileweb.ILoginWF;
import com.testvagrant.model.Enums;


/**
 * Created by Aishwarya
 */
public class WorkFlows
{
    public static ILoginWF LoginWF() {
        if (getPlatform() == Enums.Platform.IOS)
            return LoginWF.getInstance();
        return LoginWF.getInstance();
    }

    public static IBoardWF BoardWF() {
        if (getPlatform() == Enums.Platform.IOS)
            return BoardWF.getInstance();
        return BoardWF.getInstance();
    }

    public static IBoardCanvasWF BoardCanvasWF() {
        if (getPlatform() == Enums.Platform.IOS)
            return BoardCanvasWF.getInstance();
        return BoardCanvasWF.getInstance();
    }
}

