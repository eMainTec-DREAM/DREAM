package intf.dream.bee.finder.ctctr.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.ctctr.form.BeeFinderCtctrListForm;
import intf.dream.bee.finder.ctctr.service.BeeFinderCtctrListService;

/**
 * finder - Ctctr 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderCtctrList" name="beeFinderCtctrListForm"
 *                input="/bee/finder/ctctr/beeFinderCtctrList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderCtctrListAction extends IfOnlineAuthAction
{
    //WORKGROUP_FIND
    public static final String CTCTR_FIND 			= "CTCTR";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderCtctrListForm beeFinderCtctrListForm = (BeeFinderCtctrListForm) form;
        
        switch (beeFinderCtctrListForm.getServiceName())
        {
            case BeeFinderCtctrListAction.CTCTR_FIND:
            	findCtctrList(request, response, beeFinderCtctrListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findCtctrList(HttpServletRequest request, HttpServletResponse response, BeeFinderCtctrListForm beeFinderCtctrListForm) throws Exception
    {
    	BeeFinderCtctrListService beeFinderCtctrListService = (BeeFinderCtctrListService) getBean("beeFinderCtctrListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderCtctrListService.findCtctrList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
