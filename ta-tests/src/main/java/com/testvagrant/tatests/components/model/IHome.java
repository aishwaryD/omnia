package com.testvagrant.tatests.components.model;

import com.testvagrant.tatests.components.web.Footer;
import com.testvagrant.tatests.components.web.Header;
import com.testvagrant.tatests.components.web.TopSection;

/**
 * Created by Aishwarya
 */
public interface IHome
{
    void loadHomePage();
    void validate_isDisplayed(String item);
    Header get_Header();
    TopSection get_Top_Section();
    Footer get_Footer();
}
