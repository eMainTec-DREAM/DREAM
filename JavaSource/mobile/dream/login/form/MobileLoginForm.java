/**
 * 
 */
package mobile.dream.login.form;

import java.util.Collection;
import java.util.List;

import common.bean.User;
import common.struts.BaseForm;
import dream.login.login.dto.LoginDTO;
import mobile.dream.login.dto.MobileLoginDTO;

/**
 * Login Form
 * @author  javaworker
 * @version $Id: LoginForm.java,v 1.1 2013/08/30 09:14:58 javaworker Exp $
 * @since   1.0
 * @struts.form name="mobileLoginForm"
 */
public class MobileLoginForm
        extends BaseForm
{
    private MobileLoginDTO mobileLoginDTO  = new MobileLoginDTO();
    private User 	userDTO  = new User();
    private List    userList = null;
    
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

	public MobileLoginDTO getMobileLoginDTO() {
		return mobileLoginDTO;
	}

	public void setMobileLoginDTO(MobileLoginDTO mobileLoginDTO) {
		this.mobileLoginDTO = mobileLoginDTO;
	}

    
}
