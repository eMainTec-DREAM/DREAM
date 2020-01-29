package intf.dream.cricket.finder.wkctr.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.wkctr.form.CricketFinderWkctrListForm;
import intf.dream.cricket.finder.wkctr.service.CricketFinderWkctrListService;

/**
 * finder - Wkctr 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderWkctrList" name="cricketFinderWkctrListForm"
 *                input="/cricket/finder/wkctr/cricketFinderWkctrList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderWkctrListAction extends IfOnlineAuthAction
{
    //WORKGROUP_FIND
    public static final String WORKGROUP_FIND 			= "WKCTR";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderWkctrListForm cricketFinderWkctrListForm = (CricketFinderWkctrListForm) form;
        
        switch (cricketFinderWkctrListForm.getServiceName())
        {
            case CricketFinderWkctrListAction.WORKGROUP_FIND:
            	findWkctrList(request, response, cricketFinderWkctrListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findWkctrList(HttpServletRequest request, HttpServletResponse response, CricketFinderWkctrListForm cricketFinderWkctrListForm) throws Exception
    {
    	CricketFinderWkctrListService cricketFinderWkctrListService = (CricketFinderWkctrListService) getBean("cricketFinderWkctrListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderWkctrListService.findWkctrList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
