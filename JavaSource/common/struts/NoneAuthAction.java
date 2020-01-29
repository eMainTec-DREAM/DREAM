/**  */
package common.struts;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import common.config.service.ConfigService;
import common.util.CommonUtil;


/**
 * @author  pksup
 * @version $Id: NoneAuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
 * @since   1.0
 * 
 */
public abstract class NoneAuthAction
        extends BaseAction
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(AuthAction.class);

    public NoneAuthAction()
    {
        super();
    }

    /**
     * @see org.apache.struts.action.Action#execute(
     *      org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnMapping = null;
        beforeExecute(form, request);
        try
        {
            returnMapping = run(mapping, form, request, response);
        }
        catch (Exception e)
        {
            logger.error(this, e);
            throw e;
        }
        
        //set Page Title with fileName
        super.setPageTitle(request, CommonUtil.getPageIdFromPath(returnMapping.getPath()));
        
        afterExecute();
        
        return returnMapping;
    }

    /**
     * ������ : ������ ��� Ŭ������ �� �޼ҵ带 �������̵� �Ͽ� �װ��� ������ �����Ѵ�. <br>
     * ���� : <br>
     * ���� ���� : <br>
     * �ۼ� ��¥ : (05-03-31 ���� 11:13:24)
     * 
     * @see kbl.ea.monitor.common.struts.BaseAction#run(
     *      org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    protected abstract ActionForward run(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception; 
    
    
    protected String getMessage(HttpServletRequest request, String key)
    {
        if (key == null || "".equals(key))
        {
            return "";
        }
        
        /*
         * key ���� ��ȿ���� üũ�Ѵ�. ex)MENU_ID.keyId
         * . �� ���ų� . �� �յڿ� key Id �� ���ٸ� "" �� �����Ѵ�.
         */
        int decimalIndex = key.indexOf(".");
        if (decimalIndex == -1 || 
                key.substring(0, decimalIndex).equals("") || 
                key.substring(decimalIndex+1).equals(""))
        {
            return "";
        }

        java.util.Locale locale = getLocale(request);
        if (getUser(request) != null)
        {
//            locale = getUser(request).getLocale();
            ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
            locale = new Locale(configService.findLanguage());
        }

        org.apache.struts.util.MessageResources messages = getResources(request);
        return messages.getMessage(locale, key);
    }
    public Map getMapData(HttpServletRequest request) throws ParseException
    {
        Map map = null;
        Map<String, String[]> paramMap = request.getParameterMap();
        JSONObject jsonObj = null;
        
        if(paramMap.containsKey("data"))
        {
            String data = paramMap.get("data")[0];
            JSONParser jsonParser = new JSONParser();
            jsonObj = (JSONObject) jsonParser.parse(data);
        }
        
        return jsonObj;
    }
}
