package common.config.dao;

import java.util.List;

/**
 * @author pksup
 * @version $Id: ResourceDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
 * @since 1.0
 */
public interface ResourceDAO
{
    /**
     * Resource ��ȸ
     * @author  javaworker
     * @version $Id: ResourceDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @param localeKey
     * @return
     */
    public List findResources(String localeKey);

    /**
     * �ش� LocalKey�� key�� Resouce �� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: ResourceDAO.java,v 1.1 2013/08/30 09:14:38 javaworker Exp $
     * @since   1.0
     * 
     * @param localeKey
     * @param key
     * @return
     */
    public String getMessage(String localeKey, String key);

    public String getGaiaMessage(String localeKey, String key);

	public List findJsResources(String localeKey); 

    public List findGaiaResources(String localeKey);
}