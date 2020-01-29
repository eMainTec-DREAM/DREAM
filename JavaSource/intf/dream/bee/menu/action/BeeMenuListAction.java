package intf.dream.bee.menu.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.menu.form.BeeMenuListForm;
import intf.dream.bee.menu.service.BeeMenuListService;

/**
 * �¶��ι��� ����� 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeMenuList" name="beeMenuListForm"
 *                input="/bee/stock/beeMenuList.jsp" scope="request"
 *                validate="false"
 */
public class BeeMenuListAction extends IfOnlineAuthAction
{
    //MENU_LIST
    public static final String MENU_LIST		= "MENU_LIST";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeMenuListForm beeMenuListForm = (BeeMenuListForm) form;
        
        switch (beeMenuListForm.getServiceName())
        {
            case BeeMenuListAction.MENU_LIST:
            	findMenuList(request, response, beeMenuListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findMenuList(HttpServletRequest request, HttpServletResponse response, BeeMenuListForm beeMenuListForm) throws Exception
    {
    	BeeMenuListService beeMenuListService = (BeeMenuListService) getBean("beeMenuListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = beeMenuListService.findMenuList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
}
