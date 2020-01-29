package intf.dream.cricket.user.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.user.form.CricketUserListForm;
import intf.dream.cricket.user.service.CricketUserListService;

/**
 * user 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketUserList" name="cricketUserListForm"
 *                input="/cricket/user/cricketUserList.jsp" scope="request"
 *                validate="false"
 */
public class CricketUserListAction extends IfOnlineAuthAction
{
    //USER 
    public static final String USER_FIND 					= "USER";
    //SAVE FILTERS
    public static final String UPDATE_FILTER 				= "UPDATE_FILTER";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketUserListForm cricketUserListForm = (CricketUserListForm) form;
        
        switch (cricketUserListForm.getServiceName())
        {
            case CricketUserListAction.USER_FIND:
            	findList(request, response, cricketUserListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case CricketUserListAction.UPDATE_FILTER:
            	updateFilter(request, response, cricketUserListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, CricketUserListForm cricketUserListForm) throws Exception
    {
    	CricketUserListService cricketUserListService = (CricketUserListService) getBean("cricketUserListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketUserListService.findList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void updateFilter(HttpServletRequest request, HttpServletResponse response, CricketUserListForm cricketUserListForm) throws Exception
    {
    	CricketUserListService cricketUserListService = (CricketUserListService) getBean("cricketUserListService");
    	List list = getListData(request);
    	int qty = cricketUserListService.updateFilter(list);
    	setMessage(response, "","OK");
    }
}
