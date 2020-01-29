package common.sso.service;

import javax.servlet.http.HttpServletRequest;

import common.bean.User;
import common.sso.dto.SsoDTO;

/**
 * SSO Service
 * 
 * @author js.lee
 * @version $Id: Exp $
 * @since 1.0
 */
public interface SsoService
{
    boolean checkSso(HttpServletRequest request, SsoDTO ssoDTO, User user);
}
