package intf.dream.android.online.finder.eqasmb.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.eqasmb.form.AnOnFinderEqAsmbListForm;
import intf.dream.android.online.finder.eqasmb.service.AnOnFinderEqAsmbListService;

/**
 * finder - EQ asmb 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderEqAsmbList" name="anOnFinderEqAsmbListForm"
 *                input="/android/online/finder/eqasmb/anOnFinderEqAsmbList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderEqAsmbListAction extends IfOnlineAuthAction
{
    //DEPT_FIND
    public static final String EQASMB_FIND 			= "EQASMB";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderEqAsmbListForm anOnFinderEqAsmbListForm = (AnOnFinderEqAsmbListForm) form;
        
        switch (anOnFinderEqAsmbListForm.getServiceName())
        {
            case AnOnFinderEqAsmbListAction.EQASMB_FIND:
            	findEqAsmbList(request, response, anOnFinderEqAsmbListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEqAsmbList(HttpServletRequest request, HttpServletResponse response, AnOnFinderEqAsmbListForm anOnFinderEqAsmbListForm) throws Exception
    {
    	AnOnFinderEqAsmbListService anOnFinderEqAsmbListService = (AnOnFinderEqAsmbListService) getBean("anOnFinderEqAsmbListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderEqAsmbListService.findEqAsmbList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
