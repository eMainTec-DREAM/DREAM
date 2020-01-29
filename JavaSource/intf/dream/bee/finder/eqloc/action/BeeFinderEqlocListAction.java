package intf.dream.bee.finder.eqloc.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.eqloc.form.BeeFinderEqlocListForm;
import intf.dream.bee.finder.eqloc.service.BeeFinderEqlocListService;

/**
 * finder - Eqloc 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderEqlocList" name="beeFinderEqlocListForm"
 *                input="/bee/finder/eqloc/beeFinderEqlocList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderEqlocListAction extends IfOnlineAuthAction
{
    //EQLOC_FIND
    public static final String EQLOC_FIND 			= "EQLOC";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderEqlocListForm beeFinderEqlocListForm = (BeeFinderEqlocListForm) form;
        
        switch (beeFinderEqlocListForm.getServiceName())
        {
            case BeeFinderEqlocListAction.EQLOC_FIND:
            	findEqlocList(request, response, beeFinderEqlocListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEqlocList(HttpServletRequest request, HttpServletResponse response, BeeFinderEqlocListForm beeFinderEqlocListForm) throws Exception
    {
    	BeeFinderEqlocListService beeFinderEqlocListService = (BeeFinderEqlocListService) getBean("beeFinderEqlocListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderEqlocListService.findEqlocList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
