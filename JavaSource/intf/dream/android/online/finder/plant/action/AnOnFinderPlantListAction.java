package intf.dream.android.online.finder.plant.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.plant.form.AnOnFinderPlantListForm;
import intf.dream.android.online.finder.plant.service.AnOnFinderPlantListService;

/**
 * finder - Plant 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderPlantList" name="anOnFinderPlantListForm"
 *                input="/android/online/finder/wh/anOnFinderPlantList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderPlantListAction extends IfOnlineAuthAction
{
    //WH_FIND
    public static final String PLANT_FIND 			= "PLANT";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderPlantListForm anOnFinderPlantListForm = (AnOnFinderPlantListForm) form;
        
        switch (anOnFinderPlantListForm.getServiceName())
        {
            case AnOnFinderPlantListAction.PLANT_FIND:
            	findPlantList(request, response, anOnFinderPlantListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPlantList(HttpServletRequest request, HttpServletResponse response, AnOnFinderPlantListForm anOnFinderPlantListForm) throws Exception
    {
    	AnOnFinderPlantListService anOnFinderPlantListService = (AnOnFinderPlantListService) getBean("anOnFinderPlantListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderPlantListService.findPlantList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
