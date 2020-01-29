package common.config.service;

import java.util.Properties;

/**
 * Message�� �ε��Ѵ�.
 * @author javaworker
 * @version $Id: ResourceService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
 * @since 1.0
 */
public interface ResourceService
{
    /**
     * Loading All message In Locale
     * @author  javaworker
     * @version $Id: ResourceService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @param localeKey
     * @return
     */
    public Properties findLocaleResource(String localeKey);

    /**
     * �ش� LocalKey�� key�� Resouce �� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: ResourceService.java,v 1.1 2013/08/30 09:12:13 javaworker Exp $
     * @since   1.0
     * 
     * @param string
     * @param key
     * @return
     */
    public String getMessage(String localeKey, String key);

	/**
	 * JS LocalKey�� key�� Resource�� ��ȸ�Ѵ�.
	 * @param localeKey
	 * @return
	 */
	public Properties findLocaleJsResource(String localeKey);

    /**
     * Gaia Resource
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param localeKey
     * @return
     */
    public Properties findLocaleGaiaResource(String localeKey);
}
