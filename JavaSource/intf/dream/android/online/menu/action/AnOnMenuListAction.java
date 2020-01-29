package intf.dream.android.online.menu.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.menu.form.AnOnMenuListForm;
import intf.dream.android.online.menu.service.AnOnMenuListService;

/**
 * �¶��ι��� ����� 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnMenuList" name="anOnMenuListForm"
 *                input="/android/online/stock/anOnMenuList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnMenuListAction extends IfOnlineAuthAction
{
    //MENU_LIST
    public static final String MENU_LIST		= "MENU_LIST";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnMenuListForm anOnMenuListForm = (AnOnMenuListForm) form;
        
        switch (anOnMenuListForm.getServiceName())
        {
            case AnOnMenuListAction.MENU_LIST:
            	findMenuList(request, response, anOnMenuListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findMenuList(HttpServletRequest request, HttpServletResponse response, AnOnMenuListForm anOnMenuListForm) throws Exception
    {
    	AnOnMenuListService anOnMenuListService = (AnOnMenuListService) getBean("anOnMenuListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnMenuListService.findMenuList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
}
