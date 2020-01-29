package intf.dream.bee.login.form;

import java.util.Collection;
import java.util.List;

import common.bean.User;
import common.struts.BaseForm;
import intf.dream.bee.login.dto.BeeLoginDTO;

/**
 * Android Login Form
 * @author  javaworker
 * @version $Id: BeeLoginForm.java,v 1.1 2013/08/30 09:14:58 javaworker Exp $
 * @since   1.0
 * @struts.form name="beeLoginForm"
 */
public class BeeLoginForm
        extends BaseForm
{
    private BeeLoginDTO beeLoginDTO  = new BeeLoginDTO();
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

	public BeeLoginDTO getBeeLoginDTO() {
		return beeLoginDTO;
	}

	public void setBeeLoginDTO(BeeLoginDTO beeLoginDTO) {
		this.beeLoginDTO = beeLoginDTO;
	}
}
