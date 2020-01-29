/**
 * 
 */
package dream.consult.login.form;

import java.util.Collection;
import java.util.List;

import common.bean.User;
import common.struts.BaseForm;
import dream.consult.login.dto.ConsultDTO;

/**
 * Consult Form
 * @author  jung7126
 * @version $Id: ConsultForm.java,v 1.1 2013/08/30 09:14:58 javaworker Exp $
 * @since   1.0
 * @struts.form name="consultForm"
 */
public class ConsultForm extends BaseForm
{
    private ConsultDTO consultDTO  = new ConsultDTO();
    private User    userDTO  = new User();
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

    public ConsultDTO getConsultDTO()
    {
        return consultDTO;
    }

    public void setConsultDTO(ConsultDTO consultDTO)
    {
        this.consultDTO = consultDTO;
    }
}
