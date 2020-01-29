package intf.dream.cricket.menu.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.menu.form.CricketMenuListForm;
import intf.dream.cricket.menu.service.CricketMenuListService;

/**
 * �¶��ι��� ����� 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketMenuList" name="cricketMenuListForm"
 *                input="/cricket/stock/cricketMenuList.jsp" scope="request"
 *                validate="false"
 */
public class CricketMenuListAction extends IfOnlineAuthAction
{
    //MENU_LIST
    public static final String MENU_LIST		= "MENU_LIST";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketMenuListForm cricketMenuListForm = (CricketMenuListForm) form;
        
        switch (cricketMenuListForm.getServiceName())
        {
            case CricketMenuListAction.MENU_LIST:
            	findMenuList(request, response, cricketMenuListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findMenuList(HttpServletRequest request, HttpServletResponse response, CricketMenuListForm cricketMenuListForm) throws Exception
    {
    	CricketMenuListService cricketMenuListService = (CricketMenuListService) getBean("cricketMenuListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = cricketMenuListService.findMenuList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
}
