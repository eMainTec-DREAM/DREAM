/**
 * 
 */
package dream.login.login.form;

import java.util.Collection;
import java.util.List;

import common.bean.User;
import common.struts.BaseForm;
import dream.login.login.dto.LoginDTO;

/**
 * Login Form
 * @author  javaworker
 * @version $Id: LoginForm.java,v 1.1 2013/08/30 09:14:58 javaworker Exp $
 * @since   1.0
 * @struts.form name="loginForm"
 */
public class LoginForm
        extends BaseForm
{
    private LoginDTO loginDTO  = new LoginDTO();
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

    public LoginDTO getLoginDTO()
    {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO)
    {
        this.loginDTO = loginDTO;
    }
}
