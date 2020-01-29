package common.config.service;

import java.util.Properties;

/**
 * Message를 로딩한다.
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
     * 해당 LocalKey와 key로 Resouce 를 조회한다.
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
	 * JS LocalKey와 key로 Resource를 조회한다.
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
