package common.db;

import org.springframework.jndi.JndiObjectFactoryBean;
import com.opensymphony.module.sitemesh.util.Container;

/**
 * Set Jndi Name
 * @author  javaworker
 * @version $Id: JndiObjectFactoryBeanUtil.java,v 1.1 2013/08/30 09:13:07 javaworker Exp $
 * @since   1.0
 */
public class JndiObjectFactoryBeanUtil
        extends JndiObjectFactoryBean
{
    /**
     * @see org.springframework.jndi.JndiObjectLocator#setJndiName(
     *      java.lang.String)
     */
    public void setJndiName(String jndiName)
    {
        super.setJndiName(jndiName);
        if (Container.get() == Container.TOMCAT)
        {
            super.setResourceRef(true);
        }
        else
        {
            super.setResourceRef(false);
        }
    }
}