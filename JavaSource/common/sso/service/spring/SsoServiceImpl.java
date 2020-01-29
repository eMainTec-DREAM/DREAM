package common.sso.service.spring;

import javax.servlet.http.HttpServletRequest;

import common.bean.User;
import common.sso.dao.SsoDAO;
import common.sso.dto.SsoDTO;
import common.sso.service.SsoService;

/**
 * SSO Service Impl
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 * @spring.bean id="ssoServiceTarget"
 * @spring.txbn id="ssoService"
 * @spring.property name="ssoDAO" ref="ssoDAO"
 */
public class SsoServiceImpl implements SsoService
{
	private SsoDAO ssoDAO = null;
	
	public SsoDAO getSsoDAO() {
		return ssoDAO;
	}

	public void setSsoDAO(SsoDAO ssoDAO) {
		this.ssoDAO = ssoDAO;
	}

	@Override
	public boolean checkSso(HttpServletRequest request, SsoDTO ssoDTO, User user) {
		String chkUsr = ssoDAO.checkUser(ssoDTO, user);
		if (!"".equals(chkUsr)&&chkUsr != null) {
			return true;
		}
		return false;
	}

    	
}
