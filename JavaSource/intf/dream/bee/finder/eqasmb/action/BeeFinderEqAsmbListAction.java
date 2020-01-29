package intf.dream.bee.finder.eqasmb.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.eqasmb.form.BeeFinderEqAsmbListForm;
import intf.dream.bee.finder.eqasmb.service.BeeFinderEqAsmbListService;

/**
 * finder - EQ asmb 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderEqAsmbList" name="beeFinderEqAsmbListForm"
 *                input="/bee/finder/eqasmb/beeFinderEqAsmbList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderEqAsmbListAction extends IfOnlineAuthAction
{
    //DEPT_FIND
    public static final String EQASMB_FIND 			= "EQASMB";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderEqAsmbListForm beeFinderEqAsmbListForm = (BeeFinderEqAsmbListForm) form;
        
        switch (beeFinderEqAsmbListForm.getServiceName())
        {
            case BeeFinderEqAsmbListAction.EQASMB_FIND:
            	findEqAsmbList(request, response, beeFinderEqAsmbListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEqAsmbList(HttpServletRequest request, HttpServletResponse response, BeeFinderEqAsmbListForm beeFinderEqAsmbListForm) throws Exception
    {
    	BeeFinderEqAsmbListService beeFinderEqAsmbListService = (BeeFinderEqAsmbListService) getBean("beeFinderEqAsmbListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderEqAsmbListService.findEqAsmbList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
