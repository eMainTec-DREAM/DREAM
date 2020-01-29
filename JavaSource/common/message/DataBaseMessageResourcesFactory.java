package common.message;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

/**
 * STURTS 의 Resource Loading
 * strus_config.xml 에서 정의 
 * @author  javaworker
 * @version $Id: DataBaseMessageResourcesFactory.java,v 1.1 2013/08/30 09:14:14 javaworker Exp $
 * @since   1.0
 *
 */
public class DataBaseMessageResourcesFactory
        extends MessageResourcesFactory
{
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.util.MessageResourcesFactory#createResources(java.lang.String)
     */
    public MessageResources createResources(String config)
    {
        return new DataBaseMessageResources(this, config);
    }
}
