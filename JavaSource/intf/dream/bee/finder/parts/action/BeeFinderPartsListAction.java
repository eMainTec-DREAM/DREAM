package intf.dream.bee.finder.parts.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.parts.form.BeeFinderPartsListForm;
import intf.dream.bee.finder.parts.service.BeeFinderPartsListService;

/**
 * finder - Parts 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderPartsList" name="beeFinderPartsListForm"
 *                input="/bee/finder/parts/beeFinderPartsList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderPartsListAction extends IfOnlineAuthAction
{
    //PARTS_FIND
    public static final String PARTS_FIND 			= "PARTS";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderPartsListForm beeFinderPartsListForm = (BeeFinderPartsListForm) form;
        
        switch (beeFinderPartsListForm.getServiceName())
        {
            case BeeFinderPartsListAction.PARTS_FIND:
            	findPartsList(request, response, beeFinderPartsListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPartsList(HttpServletRequest request, HttpServletResponse response, BeeFinderPartsListForm beeFinderPartsListForm) throws Exception
    {
    	BeeFinderPartsListService beeFinderPartsListService = (BeeFinderPartsListService) getBean("beeFinderPartsListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderPartsListService.findPartsList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
