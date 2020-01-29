package intf.dream.cricket.finder.parts.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.parts.form.CricketFinderPartsListForm;
import intf.dream.cricket.finder.parts.service.CricketFinderPartsListService;

/**
 * finder - Parts 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderPartsList" name="cricketFinderPartsListForm"
 *                input="/cricket/finder/parts/cricketFinderPartsList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderPartsListAction extends IfOnlineAuthAction
{
    //PARTS_FIND
    public static final String PARTS_FIND 			= "PARTS";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderPartsListForm cricketFinderPartsListForm = (CricketFinderPartsListForm) form;
        
        switch (cricketFinderPartsListForm.getServiceName())
        {
            case CricketFinderPartsListAction.PARTS_FIND:
            	findPartsList(request, response, cricketFinderPartsListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPartsList(HttpServletRequest request, HttpServletResponse response, CricketFinderPartsListForm cricketFinderPartsListForm) throws Exception
    {
    	CricketFinderPartsListService cricketFinderPartsListService = (CricketFinderPartsListService) getBean("cricketFinderPartsListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderPartsListService.findPartsList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
