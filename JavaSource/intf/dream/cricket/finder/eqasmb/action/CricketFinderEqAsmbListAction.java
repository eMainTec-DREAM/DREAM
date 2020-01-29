package intf.dream.cricket.finder.eqasmb.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.eqasmb.form.CricketFinderEqAsmbListForm;
import intf.dream.cricket.finder.eqasmb.service.CricketFinderEqAsmbListService;

/**
 * finder - EQ asmb 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderEqAsmbList" name="cricketFinderEqAsmbListForm"
 *                input="/cricket/finder/eqasmb/cricketFinderEqAsmbList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderEqAsmbListAction extends IfOnlineAuthAction
{
    //DEPT_FIND
    public static final String EQASMB_FIND 			= "EQASMB";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderEqAsmbListForm cricketFinderEqAsmbListForm = (CricketFinderEqAsmbListForm) form;
        
        switch (cricketFinderEqAsmbListForm.getServiceName())
        {
            case CricketFinderEqAsmbListAction.EQASMB_FIND:
            	findEqAsmbList(request, response, cricketFinderEqAsmbListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEqAsmbList(HttpServletRequest request, HttpServletResponse response, CricketFinderEqAsmbListForm cricketFinderEqAsmbListForm) throws Exception
    {
    	CricketFinderEqAsmbListService cricketFinderEqAsmbListService = (CricketFinderEqAsmbListService) getBean("cricketFinderEqAsmbListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderEqAsmbListService.findEqAsmbList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
