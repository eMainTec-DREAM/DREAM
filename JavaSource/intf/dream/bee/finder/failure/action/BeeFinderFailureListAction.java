package intf.dream.bee.finder.failure.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.failure.form.BeeFinderFailureListForm;
import intf.dream.bee.finder.failure.service.BeeFinderFailureListService;

/**
 * finder - Failure 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderFailureList" name="beeFinderFailureListForm"
 *                input="/bee/finder/failure/beeFinderFailureList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderFailureListAction extends IfOnlineAuthAction
{
    //FAILURE_FIND
    public static final String FAILURE_FIND 			= "FAILURE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderFailureListForm beeFinderFailureListForm = (BeeFinderFailureListForm) form;
        
        switch (beeFinderFailureListForm.getServiceName())
        {
            case BeeFinderFailureListAction.FAILURE_FIND:
            	findFailureList(request, response, beeFinderFailureListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findFailureList(HttpServletRequest request, HttpServletResponse response, BeeFinderFailureListForm beeFinderFailureListForm) throws Exception
    {
    	BeeFinderFailureListService beeFinderFailureListService = (BeeFinderFailureListService) getBean("beeFinderFailureListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderFailureListService.findFailureList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
