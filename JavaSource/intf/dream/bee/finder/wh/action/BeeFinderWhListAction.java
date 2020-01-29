package intf.dream.bee.finder.wh.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.wh.form.BeeFinderWhListForm;
import intf.dream.bee.finder.wh.service.BeeFinderWhListService;

/**
 * finder - Wh 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderWhList" name="beeFinderWhListForm"
 *                input="/bee/finder/wh/beeFinderWhList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderWhListAction extends IfOnlineAuthAction
{
    //WH_FIND
    public static final String WH_FIND 			= "WAREHOUSE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderWhListForm beeFinderWhListForm = (BeeFinderWhListForm) form;
        
        switch (beeFinderWhListForm.getServiceName())
        {
            case BeeFinderWhListAction.WH_FIND:
            	findWhList(request, response, beeFinderWhListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findWhList(HttpServletRequest request, HttpServletResponse response, BeeFinderWhListForm beeFinderWhListForm) throws Exception
    {
    	BeeFinderWhListService beeFinderWhListService = (BeeFinderWhListService) getBean("beeFinderWhListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderWhListService.findWhList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
