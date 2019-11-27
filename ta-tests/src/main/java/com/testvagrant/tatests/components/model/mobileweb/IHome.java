package com.testvagrant.tatests.components.model.mobileweb;

import com.testvagrant.tatests.components.mobileweb.Footer;
import com.testvagrant.tatests.components.mobileweb.Header;
import com.testvagrant.tatests.components.mobileweb.TopSection;

/**
 * Created by Aishwarya
 */
public interface IHome
{
    void loadHomePage();
    void validateIsDisplayed(String item);
    Header getHeader();
    TopSection getTopSection();
    Footer getFooter();
}
