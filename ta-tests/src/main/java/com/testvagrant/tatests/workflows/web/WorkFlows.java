package com.testvagrant.tatests.workflows.web;

import com.testvagrant.core.BaseContext;
import com.testvagrant.tatests.workflows.model.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Aishwarya
 */
public class WorkFlows
{

    public static IBoardWF BoardWF()
    {
        if (BaseContext.isDesktopMobileEmulation()) {
            throw new NotImplementedException();
        }
        else {
            return BoardWF.getInstance();
        }
    }

    public static ILoginWF LoginWF()
    {
        if (BaseContext.isDesktopMobileEmulation()) {
            throw new NotImplementedException();
        }
        else{
            return LoginWF.getInstance();
        }
    }

    public static IBoardCanvasWF BoardCanvasWF()
    {
        if (BaseContext.isDesktopMobileEmulation()) {
            throw new NotImplementedException();
        }
        else{
            return BoardCanvasWF.getInstance();
        }
    }
}

