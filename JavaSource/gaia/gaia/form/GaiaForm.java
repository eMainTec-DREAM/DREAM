/**
 * 
 */
package gaia.gaia.form;

import java.util.Collection;
import java.util.List;

import common.bean.User;
import common.struts.BaseForm;
import gaia.gaia.dto.GaiaDTO;

/**
 * Gaia Form
 * @author  jung7126
 * @version $Id: GaiaForm.java,v 1.1 2013/08/30 09:14:58 javaworker Exp $
 * @since   1.0
 * @struts.form name="gaiaForm"
 */
public class GaiaForm extends BaseForm
{
    private GaiaDTO gaiaDTO  = new GaiaDTO();
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

    public GaiaDTO getGaiaDTO()
    {
        return gaiaDTO;
    }

    public void setGaiaDTO(GaiaDTO gaiaDTO)
    {
        this.gaiaDTO = gaiaDTO;
    }
}
