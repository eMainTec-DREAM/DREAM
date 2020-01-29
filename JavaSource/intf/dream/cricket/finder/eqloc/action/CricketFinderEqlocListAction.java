package intf.dream.cricket.finder.eqloc.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.eqloc.form.CricketFinderEqlocListForm;
import intf.dream.cricket.finder.eqloc.service.CricketFinderEqlocListService;

/**
 * finder - Eqloc 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderEqlocList" name="beeFinderEqlocListForm"
 *                input="/cricket/finder/eqloc/beeFinderEqlocList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderEqlocListAction extends IfOnlineAuthAction
{
    //EQLOC_FIND
    public static final String EQLOC_FIND 			= "EQLOC";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderEqlocListForm beeFinderEqlocListForm = (CricketFinderEqlocListForm) form;
        
        switch (beeFinderEqlocListForm.getServiceName())
        {
            case CricketFinderEqlocListAction.EQLOC_FIND:
            	findEqlocList(request, response, beeFinderEqlocListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEqlocList(HttpServletRequest request, HttpServletResponse response, CricketFinderEqlocListForm beeFinderEqlocListForm) throws Exception
    {
    	CricketFinderEqlocListService beeFinderEqlocListService = (CricketFinderEqlocListService) getBean("beeFinderEqlocListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderEqlocListService.findEqlocList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
