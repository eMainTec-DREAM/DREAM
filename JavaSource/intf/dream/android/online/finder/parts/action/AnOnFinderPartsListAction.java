package intf.dream.android.online.finder.parts.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.parts.form.AnOnFinderPartsListForm;
import intf.dream.android.online.finder.parts.service.AnOnFinderPartsListService;

/**
 * finder - Parts 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderPartsList" name="anOnFinderPartsListForm"
 *                input="/android/online/finder/parts/anOnFinderPartsList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderPartsListAction extends IfOnlineAuthAction
{
    //PARTS_FIND
    public static final String PARTS_FIND 			= "PARTS";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderPartsListForm anOnFinderPartsListForm = (AnOnFinderPartsListForm) form;
        
        switch (anOnFinderPartsListForm.getServiceName())
        {
            case AnOnFinderPartsListAction.PARTS_FIND:
            	findPartsList(request, response, anOnFinderPartsListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPartsList(HttpServletRequest request, HttpServletResponse response, AnOnFinderPartsListForm anOnFinderPartsListForm) throws Exception
    {
    	AnOnFinderPartsListService anOnFinderPartsListService = (AnOnFinderPartsListService) getBean("anOnFinderPartsListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderPartsListService.findPartsList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
