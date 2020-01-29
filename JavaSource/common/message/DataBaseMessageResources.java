package common.message;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

import common.config.service.ResourceService;
import common.struts.BaseAction;

/**
 * Message를 DB에서 추출한다.
 */
public class DataBaseMessageResources
        extends MessageResources
{
    // ----------------------------------------------------------- Constructors
    /**
     * @param factory
     * @param config
     */
    public DataBaseMessageResources(MessageResourcesFactory factory,
            String config)
    {
        super(factory, config);
    }

    // ------------------------------------------------------------- Properties
    /**
     * The set of locale keys for which we have already loaded messages, keyed
     * by the value calculated in <code>localeKey()</code>.
     */
    protected HashMap locales = new HashMap();
    /**
     * The <code>Log</code> instance for this class.
     */
    protected static final Log log = LogFactory.getLog(DataBaseMessageResources.class);
    /**
     * The cache of messages we have accumulated over time, keyed by the value
     * calculated in <code>messageKey()</code>.
     */
    protected HashMap messages = new HashMap();

    // --------------------------------------------------------- Public Methods
    /**
     * Returns a text message for the specified key, for the default Locale. A
     * null string result will be returned by this method if no relevant message
     * resource is found for this key or Locale, if the <code>returnNull</code>
     * property is set. Otherwise, an appropriate error message will be
     * returned.
     * <p>
     * This method must be implemented by a concrete subclass.
     * 
     * @param locale
     *            The requested message Locale, or <code>null</code> for the
     *            system default Locale
     * @param key
     *            The message key to look up
     * @return text message for the specified key and locale
     */
    public String getMessage(Locale locale, String key)
    {
        if (log.isDebugEnabled())
        {
            log.debug("getMessage(" + locale + "," + key + ")");
        }
        

        // Initialize variables we will require
        String localeKey = locale.getLanguage();
        String originalKey = messageKey(localeKey, key.toUpperCase());
        String messageKey = null;
        String message = null;
        int underscore = 0;
        boolean addIt = false; // Add if not found under the original key
        // Loop from specific to general Locales looking for this message

        while (true)
        {
            // Load this Locale's messages if we have not done so yet
//            loadLocale(localeKey);
            // Check if we have this key for the current locale key
            messageKey = messageKey(localeKey, key.toUpperCase());
            synchronized (messages)
            {
                message = (String) messages.get(messageKey);
                if (message != null)
                {
                    if (addIt)
                    {
                        messages.put(originalKey, message);
                    }
                    return (message);
                }
            }
            // Strip trailing modifiers to try a more general locale key
            addIt = true;
            underscore = localeKey.lastIndexOf("_");
            if (underscore < 0)
            {
                break;
            }
            localeKey = localeKey.substring(0, underscore);
        }
        
        // Try the default locale if the current locale is different
        // 위의 Key로 메세지가 없는 경우 DB 에서 다시 조회한다.
//        message = loadMessageByKey(locale.getLanguage(), originalKey);
        if (message != null && !"".equals(message)) 
        {
//            messages.put(originalKey, message);
//            return message;
            return (message);
        }
        else return (key);
       
        /*
        // Return an appropriate error indication
        if (returnNull)
        {
            return (null);
        }
        */
      
        // There is no key IN DB
        //return ("???" + messageKey(locale, key) + "???");
        //return ("? No Data ?");
//       return (key);
    }

    /**
     * message key 에 해당 하는 message 를 DB에서 조회한다.
     * @author  javaworker
     * @version $Id: DataBaseMessageResources.java,v 1.1 2013/08/30 09:14:14 javaworker Exp $
     * @since   1.0
     * 
     * @param messageKey
     * @return
     */
    private String loadMessageByKey(String localeKey, String messageKey)
    {
        ResourceService resourceService = (ResourceService) BaseAction.ctx.getBean("resourceService");
        return  resourceService.getMessage(localeKey, messageKey);
    }

    // ------------------------------------------------------ Protected Methods
    /**
     * Load the messages associated with the specified Locale key. For this
     * implementation, the <code>config</code> property should contain a fully
     * qualified package and resource name, separated by periods, of a series of
     * property resources to be loaded from the class loader that created this
     * PropertyMessageResources instance. This is exactly the same name format
     * you would use when utilizing the
     * <code>java.util.PropertyResourceBundle</code> class.
     * 
     * @param localeKey
     *            Locale key for the messages to be retrieved
     */
    public synchronized void loadLocale(String localeKey)
    {
        if (log.isTraceEnabled())
        {
            log.trace("loadLocale(" + localeKey + ")");
        }
        if (localeKey == null || "".equals(localeKey))
        {
            localeKey = defaultLocale.getLanguage();
        }
        
        // Have we already attempted to load messages for this locale?
        if (locales.get(localeKey) != null)
        {
            return;
        }
        locales.put(localeKey, localeKey);
        // Set up to load the property resource for this locale key, if we can
        // Load the specified property resource
        if (log.isTraceEnabled())
        {
            log.trace("  Loading DataBase Resources ");
        }
        ResourceService resourceService = (ResourceService) BaseAction.ctx.getBean("resourceService");
        // locale key에 해당 하는 리소스를 검색 한다.
        Properties props = resourceService.findLocaleResource(localeKey);
        // Javascript Resource
        Properties jsprops = resourceService.findLocaleJsResource(localeKey);
        //Resources For Gaia 
        Properties gaiaProps = null;
        gaiaProps = resourceService.findLocaleGaiaResource(localeKey);
        
        //Road Resource For Enhance
        Properties enhanceProps = null;
        
        if (log.isTraceEnabled())
        {
            log.trace("  Loading resource completed");
        }
        // Copy the corresponding values into our cache
        if (props.size() < 1)
        {
            return;
        }
        synchronized (messages)
        {
            Iterator names = props.keySet().iterator();
            while (names.hasNext())
            {
                String key = (String) names.next();

                if (log.isTraceEnabled())
                {
                    log.trace("  Saving message key '" + messageKey(localeKey,
                            key));
                }
//                System.out.println("loadLocale:"+messageKey(localeKey, key)+"   "+props.getProperty(key));
                messages.put(messageKey(localeKey, key), props.getProperty(key));
            }
            
            Iterator jsnames = jsprops.keySet().iterator();
            while (jsnames.hasNext())
            {
                String key = (String) jsnames.next();
                if (log.isTraceEnabled())
                {
                    log.trace("  Saving message key '" + messageKey(localeKey,
                            key));
                }
                messages.put(messageKey(localeKey, key), jsprops.getProperty(key));
            }
            
            Iterator gaiaPNames = gaiaProps.keySet().iterator();
            while (gaiaPNames.hasNext())
            {
                String key = (String) gaiaPNames.next();
                if (log.isTraceEnabled())
                {
                    log.trace("  Saving message key '" + messageKey(localeKey,key));
                }
                messages.put(messageKey(localeKey, key), gaiaProps.getProperty(key));
            }
        }
    }

    /**
     * 공통 스크립트의 메세지 추출과
     * 메세지 변동시 셋팅을 위해서 메세지를 셋팅한 HashMap을
     * 사용가능하게 리턴한다.
     * @author  javaworker
     * @version $Id: DataBaseMessageResources.java,v 1.1 2013/08/30 09:14:14 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public HashMap getMessages()
    {
        return messages;
    }

    /**
     * Key 에 해당하는 Message를 
     * 셋팅한다.
     * @author  javaworker
     * @version $Id: DataBaseMessageResources.java,v 1.1 2013/08/30 09:14:14 javaworker Exp $
     * @since   1.0
     * 
     * @param key
     * @param message
     */
    public void setMessage(String key, String message)
    {
        // hash map messages을 셋팅한다. 
        messages.remove(key);
        messages.put(key, message);
        
        // formats remove 시킴 (struts el tab)
        formats.remove(key);
    }
    
    /**
     * Message 삭제
     * @author  javaworker
     * @version $Id: DataBaseMessageResources.java,v 1.1 2013/08/30 09:14:14 javaworker Exp $
     * @since   1.0
     * 
     * @param key
     */
    public void removeMessage(String key)
    {
        // hash map messages을 셋팅한다. 
        messages.remove(key);
        
        // formats 에 셋팅한다. (struts el tab)
        formats.remove(key);
    }
    
    /**
     * Message의 resource(T4RESOURCE)를 다시 조회한다.
     * @author  javaworker
     * @version $Id: DataBaseMessageResources.java,v 1.1 2013/08/30 09:14:14 javaworker Exp $
     * @since   1.0
     *
     */
    public void reloadMessage()
    {
        // 현재 저장된 locale key를 추출한다.
        Object [] locValues = (locales.values()).toArray();
        
        ResourceService resourceService = (ResourceService) BaseAction.ctx.getBean("resourceService");
        
        // 조회된 locale 을 모두 다시 조회하여 로딩한다.
        for (int i=0; i<locValues.length; i++)
        {
            String localeKey = (String)locValues[i];
            
            // locale key에 해당 하는 리소스를 검색 한다.
            Properties props = resourceService.findLocaleResource(localeKey);

            // Copy the corresponding values into our cache
            if (props.size() < 1)
            {
                continue;
            }
            
            synchronized (messages)
            {
                Iterator names = props.keySet().iterator();
                while (names.hasNext())
                {
                    String key = (String) names.next();
                    
                    // message 셋팅한다.
                    setMessage(messageKey(localeKey, key), props.getProperty(key));
                }
            }
        }
    }
}
