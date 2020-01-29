package intf.dream.android.online.login.form;

import java.util.Collection;
import java.util.List;

import common.bean.User;
import common.struts.BaseForm;
import intf.dream.android.online.login.dto.AnOnLoginDTO;

/**
 * Android Login Form
 * @author  javaworker
 * @version $Id: AnOnLoginForm.java,v 1.1 2013/08/30 09:14:58 javaworker Exp $
 * @since   1.0
 * @struts.form name="anOnLoginForm"
 */
public class AnOnLoginForm
        extends BaseForm
{
    private AnOnLoginDTO anOnLoginDTO  = new AnOnLoginDTO();
    private User userDTO  = new User();
    private List     userList = null;
    
    private Collection languageOptions = null;
    
    public User getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(User userDTO) {
		this.userDTO = userDTO;
	}

	public List getUserList()
    {
        return userList;
    }

    public Collection getLanguageOptions()
    {
        return languageOptions;
    }

    public void setLanguageOptions(Collection languageOptions)
    {
        this.languageOptions = languageOptions;
    }

	public AnOnLoginDTO getAnOnLoginDTO() {
		return anOnLoginDTO;
	}

	public void setAnOnLoginDTO(AnOnLoginDTO anOnLoginDTO) {
		this.anOnLoginDTO = anOnLoginDTO;
	}
}
