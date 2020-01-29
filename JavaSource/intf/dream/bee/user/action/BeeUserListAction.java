package intf.dream.bee.user.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.user.form.BeeUserListForm;
import intf.dream.bee.user.service.BeeUserListService;

/**
 * user 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeUserList" name="beeUserListForm"
 *                input="/bee/user/beeUserList.jsp" scope="request"
 *                validate="false"
 */
public class BeeUserListAction extends IfOnlineAuthAction
{
    //USER 
    public static final String USER_FIND 					= "USER";
    //SAVE FILTERS
    public static final String UPDATE_FILTER 				= "UPDATE_FILTER";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeUserListForm beeUserListForm = (BeeUserListForm) form;
        
        switch (beeUserListForm.getServiceName())
        {
            case BeeUserListAction.USER_FIND:
            	findList(request, response, beeUserListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeUserListAction.UPDATE_FILTER:
            	updateFilter(request, response, beeUserListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, BeeUserListForm beeUserListForm) throws Exception
    {
    	BeeUserListService beeUserListService = (BeeUserListService) getBean("beeUserListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeUserListService.findList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void updateFilter(HttpServletRequest request, HttpServletResponse response, BeeUserListForm beeUserListForm) throws Exception
    {
    	BeeUserListService beeUserListService = (BeeUserListService) getBean("beeUserListService");
    	List list = getListData(request);
    	int qty = beeUserListService.updateFilter(list);
    	setMessage(response, "","OK");
    }
}
