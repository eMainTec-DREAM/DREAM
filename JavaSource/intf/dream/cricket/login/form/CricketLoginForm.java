package intf.dream.cricket.login.form;

import java.util.Collection;
import java.util.List;

import common.bean.User;
import common.struts.BaseForm;
import intf.dream.cricket.login.dto.CricketLoginDTO;

/**
 * Android Login Form
 * @author  javaworker
 * @version $Id: CricketLoginForm.java,v 1.1 2013/08/30 09:14:58 javaworker Exp $
 * @since   1.0
 * @struts.form name="cricketLoginForm"
 */
public class CricketLoginForm
        extends BaseForm
{
    private CricketLoginDTO cricketLoginDTO  = new CricketLoginDTO();
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

	public CricketLoginDTO getCricketLoginDTO() {
		return cricketLoginDTO;
	}

	public void setCricketLoginDTO(CricketLoginDTO cricketLoginDTO) {
		this.cricketLoginDTO = cricketLoginDTO;
	}
}
