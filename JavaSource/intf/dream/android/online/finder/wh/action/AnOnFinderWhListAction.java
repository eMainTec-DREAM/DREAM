package intf.dream.android.online.finder.wh.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.wh.form.AnOnFinderWhListForm;
import intf.dream.android.online.finder.wh.service.AnOnFinderWhListService;

/**
 * finder - Wh 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderWhList" name="anOnFinderWhListForm"
 *                input="/android/online/finder/wh/anOnFinderWhList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderWhListAction extends IfOnlineAuthAction
{
    //WH_FIND
    public static final String WH_FIND 			= "WAREHOUSE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderWhListForm anOnFinderWhListForm = (AnOnFinderWhListForm) form;
        
        switch (anOnFinderWhListForm.getServiceName())
        {
            case AnOnFinderWhListAction.WH_FIND:
            	findWhList(request, response, anOnFinderWhListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findWhList(HttpServletRequest request, HttpServletResponse response, AnOnFinderWhListForm anOnFinderWhListForm) throws Exception
    {
    	AnOnFinderWhListService anOnFinderWhListService = (AnOnFinderWhListService) getBean("anOnFinderWhListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderWhListService.findWhList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
