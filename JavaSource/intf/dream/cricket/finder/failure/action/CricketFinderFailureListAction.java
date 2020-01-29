package intf.dream.cricket.finder.failure.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.failure.form.CricketFinderFailureListForm;
import intf.dream.cricket.finder.failure.service.CricketFinderFailureListService;

/**
 * finder - Failure 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderFailureList" name="cricketFinderFailureListForm"
 *                input="/cricket/finder/failure/cricketFinderFailureList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderFailureListAction extends IfOnlineAuthAction
{
    //FAILURE_FIND
    public static final String FAILURE_FIND 			= "FAILURE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderFailureListForm cricketFinderFailureListForm = (CricketFinderFailureListForm) form;
        
        switch (cricketFinderFailureListForm.getServiceName())
        {
            case CricketFinderFailureListAction.FAILURE_FIND:
            	findFailureList(request, response, cricketFinderFailureListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findFailureList(HttpServletRequest request, HttpServletResponse response, CricketFinderFailureListForm cricketFinderFailureListForm) throws Exception
    {
    	CricketFinderFailureListService cricketFinderFailureListService = (CricketFinderFailureListService) getBean("cricketFinderFailureListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = cricketFinderFailureListService.findFailureList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
}
