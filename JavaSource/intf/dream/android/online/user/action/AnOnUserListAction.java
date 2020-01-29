package intf.dream.android.online.user.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.user.form.AnOnUserListForm;
import intf.dream.android.online.user.service.AnOnUserListService;

/**
 * user 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnUserList" name="anOnUserListForm"
 *                input="/android/online/user/anOnUserList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnUserListAction extends IfOnlineAuthAction
{
    //USER 
    public static final String USER_FIND 					= "USER";
    //SAVE FILTERS
    public static final String UPDATE_FILTER 				= "UPDATE_FILTER";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnUserListForm anOnUserListForm = (AnOnUserListForm) form;
        
        switch (anOnUserListForm.getServiceName())
        {
            case AnOnUserListAction.USER_FIND:
            	findList(request, response, anOnUserListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnUserListAction.UPDATE_FILTER:
            	updateFilter(request, response, anOnUserListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AnOnUserListForm anOnUserListForm) throws Exception
    {
    	AnOnUserListService anOnUserListService = (AnOnUserListService) getBean("anOnUserListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnUserListService.findList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void updateFilter(HttpServletRequest request, HttpServletResponse response, AnOnUserListForm anOnUserListForm) throws Exception
    {
    	AnOnUserListService anOnUserListService = (AnOnUserListService) getBean("anOnUserListService");
    	List list = getListData(request);
    	int qty = anOnUserListService.updateFilter(list);
    	setMessage(response, "","OK");
    }
}
