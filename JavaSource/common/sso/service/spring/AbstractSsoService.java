package common.sso.service.spring;

import common.sso.service.SsoService;

/**
 * Abstract SSO service
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractSsoService implements SsoService
{    
	protected SsoService ssoService;
	
    public AbstractSsoService(SsoService ssoService)
    {
        this.ssoService = ssoService;
    }
	
	
}
