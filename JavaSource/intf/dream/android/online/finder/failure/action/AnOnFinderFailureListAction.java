package intf.dream.android.online.finder.failure.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.failure.form.AnOnFinderFailureListForm;
import intf.dream.android.online.finder.failure.service.AnOnFinderFailureListService;

/**
 * finder - Failure 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderFailureList" name="anOnFinderFailureListForm"
 *                input="/android/online/finder/failure/anOnFinderFailureList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderFailureListAction extends IfOnlineAuthAction
{
    //FAILURE_FIND
    public static final String FAILURE_FIND 			= "FAILURE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderFailureListForm anOnFinderFailureListForm = (AnOnFinderFailureListForm) form;
        
        switch (anOnFinderFailureListForm.getServiceName())
        {
            case AnOnFinderFailureListAction.FAILURE_FIND:
            	findFailureList(request, response, anOnFinderFailureListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findFailureList(HttpServletRequest request, HttpServletResponse response, AnOnFinderFailureListForm anOnFinderFailureListForm) throws Exception
    {
    	AnOnFinderFailureListService anOnFinderFailureListService = (AnOnFinderFailureListService) getBean("anOnFinderFailureListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderFailureListService.findFailureList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
