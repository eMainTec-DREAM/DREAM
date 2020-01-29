package common.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import common.config.service.ResourceService;

/**
 * Message Util
 * @author  javaworker
 * @version $Id: MessageUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
 * @since   1.0
 *
 */
public class MessageUtil
{
    /**
     * ���õ� ������ commonInclude.jsp ���Ͽ��� 
     * alert�� ������ ����ϰ� �ȴ�.
     * @author  javaworker
     * @version $Id: MessageUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param message
     * @throws Exception
     */
    static public void setMessage(HttpServletRequest request, String message) throws Exception
    {
        request.setAttribute("MESSAGE", message);        
    }
    
    /**
     * TALANG���� key_name ��ȯ
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param locale
     * @param keyType
     * @param keyNo
     * @return
     */
    public static String getMessage(Locale locale, String keyType, String keyNo)
    {
        String msg = "";
        if(locale!=null && !"".equals(keyType) && !"".equals(keyNo)) {
            
            if(RequestContextHolder.getRequestAttributes() == null)
            {
                ResourceService resourceService = (ResourceService)CommonUtil.getBean("resourceService");
                msg = resourceService.getMessage(locale.getLanguage(), locale.getLanguage()+"."+keyType+"."+keyNo);
            }
            else
            {                
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
                MessageResources messages = (MessageResources) request.getServletContext().getAttribute(org.apache.struts.Globals.MESSAGES_KEY);
                msg = messages.getMessage(locale, keyType+"."+keyNo);
            }
        }
        return msg;
    }
}
