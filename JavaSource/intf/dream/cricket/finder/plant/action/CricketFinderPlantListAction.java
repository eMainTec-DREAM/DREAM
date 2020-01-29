package intf.dream.cricket.finder.plant.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.plant.form.CricketFinderPlantListForm;
import intf.dream.cricket.finder.plant.service.CricketFinderPlantListService;

/**
 * finder - Plant 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderPlantList" name="cricketFinderPlantListForm"
 *                input="/cricket/finder/wh/cricketFinderPlantList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderPlantListAction extends IfOnlineAuthAction
{
    //WH_FIND
    public static final String PLANT_FIND 			= "PLANT";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderPlantListForm cricketFinderPlantListForm = (CricketFinderPlantListForm) form;
        
        switch (cricketFinderPlantListForm.getServiceName())
        {
            case CricketFinderPlantListAction.PLANT_FIND:
            	findPlantList(request, response, cricketFinderPlantListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPlantList(HttpServletRequest request, HttpServletResponse response, CricketFinderPlantListForm cricketFinderPlantListForm) throws Exception
    {
    	CricketFinderPlantListService cricketFinderPlantListService = (CricketFinderPlantListService) getBean("cricketFinderPlantListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderPlantListService.findPlantList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
