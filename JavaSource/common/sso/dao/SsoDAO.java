package common.sso.dao;

import common.bean.User;
import common.sso.dto.SsoDTO;

/**
 * SSO DAO
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public interface SsoDAO
{
    public String checkUser(SsoDTO ssoDTO, User user);

}