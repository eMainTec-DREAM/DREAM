/**  */
package common.config.service.spring;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

import common.config.dao.ResourceDAO;
import common.config.service.ResourceService;

/**
 * message를 로딩한다.
 * @author javaworker
 * @version $Id: ResourceServiceImpl.java,v 1.1 2013/08/30 09:13:28 javaworker Exp $
 * @since 1.0
 * @spring.bean id="resourceServiceTarget"
 * @spring.txbn id="resourceService"
 * @spring.property name="resourceDAO" ref="resourceDAO"
 */
public class ResourceServiceImpl
        implements ResourceService
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ResourceServiceImpl.class);
    private ResourceDAO resourceDAO = null;

    /*
     * (non-Javadoc)
     * 
     * @see common.message.ResourceService#findResource(java.lang.String)
     */
    public Properties findLocaleResource(String localeKey)
    {
        Properties props = new Properties();
        if (logger.isDebugEnabled())
        {
            logger.debug(localeKey);
        }
        List list = resourceDAO.findResources(localeKey);
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            try
            {
                Map map = (Map) it.next();
                props.put(String.valueOf(map.get("MESSAGE_KEY")).toUpperCase(), map.get("MESSAGE"));
            }
            catch (Exception ignored)
            {}
        }
        return props;
    }

    public ResourceDAO getResourceDAO()
    {
        return resourceDAO;
    }

    public void setResourceDAO(ResourceDAO resourceDAO)
    {
        this.resourceDAO = resourceDAO;
    }

    public String getMessage(String localeKey, String key)
    {
        logger.debug(">>> load key : [" + localeKey + "]" + key);
        String rtnMsg = resourceDAO.getMessage(localeKey, key);
        
        if(rtnMsg == "") rtnMsg = resourceDAO.getGaiaMessage(localeKey, key);
        
        return rtnMsg;
    }


	public Properties findLocaleJsResource(String localeKey) 
	{
		Properties props = new Properties();
        if (logger.isDebugEnabled())
        {
            logger.debug(localeKey);
        }
        List list = resourceDAO.findJsResources(localeKey);
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            try
            {
                Map map = (Map) it.next();
                props.put(String.valueOf(map.get("MESSAGE_KEY")).toUpperCase(), map.get("MESSAGE"));
            }
            catch (Exception ignored)
            {}
        }
        return props;
	}

    public Properties findLocaleGaiaResource(String localeKey)
    {
        Properties props = new Properties();
        if (logger.isDebugEnabled())
        {
            logger.debug(localeKey);
        }
        List list = resourceDAO.findGaiaResources(localeKey);
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            try
            {
                Map map = (Map) it.next();
                props.put(String.valueOf(map.get("MESSAGE_KEY")).toUpperCase(), map.get("MESSAGE"));
            }
            catch (Exception ignored)
            {}
        }
        return props;
    }
}
