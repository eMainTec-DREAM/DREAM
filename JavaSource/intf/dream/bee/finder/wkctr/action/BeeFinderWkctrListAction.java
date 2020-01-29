package intf.dream.bee.finder.wkctr.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.wkctr.form.BeeFinderWkctrListForm;
import intf.dream.bee.finder.wkctr.service.BeeFinderWkctrListService;

/**
 * finder - Wkctr 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderWkctrList" name="beeFinderWkctrListForm"
 *                input="/bee/finder/wkctr/beeFinderWkctrList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderWkctrListAction extends IfOnlineAuthAction
{
    //WORKGROUP_FIND
    public static final String WORKGROUP_FIND 			= "WKCTR";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderWkctrListForm beeFinderWkctrListForm = (BeeFinderWkctrListForm) form;
        
        switch (beeFinderWkctrListForm.getServiceName())
        {
            case BeeFinderWkctrListAction.WORKGROUP_FIND:
            	findWkctrList(request, response, beeFinderWkctrListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findWkctrList(HttpServletRequest request, HttpServletResponse response, BeeFinderWkctrListForm beeFinderWkctrListForm) throws Exception
    {
    	BeeFinderWkctrListService beeFinderWkctrListService = (BeeFinderWkctrListService) getBean("beeFinderWkctrListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderWkctrListService.findWkctrList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
