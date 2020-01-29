package intf.dream.bee.finder.plant.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.plant.form.BeeFinderPlantListForm;
import intf.dream.bee.finder.plant.service.BeeFinderPlantListService;

/**
 * finder - Plant 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderPlantList" name="beeFinderPlantListForm"
 *                input="/bee/finder/wh/beeFinderPlantList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderPlantListAction extends IfOnlineAuthAction
{
    //WH_FIND
    public static final String PLANT_FIND 			= "PLANT";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderPlantListForm beeFinderPlantListForm = (BeeFinderPlantListForm) form;
        
        switch (beeFinderPlantListForm.getServiceName())
        {
            case BeeFinderPlantListAction.PLANT_FIND:
            	findPlantList(request, response, beeFinderPlantListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPlantList(HttpServletRequest request, HttpServletResponse response, BeeFinderPlantListForm beeFinderPlantListForm) throws Exception
    {
    	BeeFinderPlantListService beeFinderPlantListService = (BeeFinderPlantListService) getBean("beeFinderPlantListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderPlantListService.findPlantList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
