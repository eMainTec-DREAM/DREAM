package intf.dream.bee.finder.eqctg.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.eqctg.form.BeeFinderEqctgListForm;
import intf.dream.bee.finder.eqctg.service.BeeFinderEqctgListService;

/**
 * finder - Eqctg 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderEqctgList" name="beeFinderEqctgListForm"
 *                input="/bee/finder/eqctg/beeFinderEqctgList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderEqctgListAction extends IfOnlineAuthAction
{
    //EQCTG_FIND
    public static final String EQCTG_FIND 			= "EQCTG";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderEqctgListForm beeFinderEqctgListForm = (BeeFinderEqctgListForm) form;
        
        switch (beeFinderEqctgListForm.getServiceName())
        {
            case BeeFinderEqctgListAction.EQCTG_FIND:
            	findEqctgList(request, response, beeFinderEqctgListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEqctgList(HttpServletRequest request, HttpServletResponse response, BeeFinderEqctgListForm beeFinderEqctgListForm) throws Exception
    {
    	BeeFinderEqctgListService beeFinderEqctgListService = (BeeFinderEqctgListService) getBean("beeFinderEqctgListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderEqctgListService.findEqctgList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
