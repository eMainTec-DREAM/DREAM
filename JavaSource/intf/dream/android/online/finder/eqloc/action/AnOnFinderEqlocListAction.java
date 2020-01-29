package intf.dream.android.online.finder.eqloc.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.eqloc.form.AnOnFinderEqlocListForm;
import intf.dream.android.online.finder.eqloc.service.AnOnFinderEqlocListService;

/**
 * finder - Eqloc 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderEqlocList" name="anOnFinderEqlocListForm"
 *                input="/android/online/finder/eqloc/anOnFinderEqlocList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderEqlocListAction extends IfOnlineAuthAction
{
    //EQLOC_FIND
    public static final String EQLOC_FIND 			= "EQLOC";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderEqlocListForm anOnFinderEqlocListForm = (AnOnFinderEqlocListForm) form;
        
        switch (anOnFinderEqlocListForm.getServiceName())
        {
            case AnOnFinderEqlocListAction.EQLOC_FIND:
            	findEqlocList(request, response, anOnFinderEqlocListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEqlocList(HttpServletRequest request, HttpServletResponse response, AnOnFinderEqlocListForm anOnFinderEqlocListForm) throws Exception
    {
    	AnOnFinderEqlocListService anOnFinderEqlocListService = (AnOnFinderEqlocListService) getBean("anOnFinderEqlocListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderEqlocListService.findEqlocList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
