package intf.dream.bee.finder.emp.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.emp.form.BeeFinderEmpListForm;
import intf.dream.bee.finder.emp.service.BeeFinderEmpListService;

/**
 * finder - emp 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderEmpList" name="beeFinderEmpListForm"
 *                input="/bee/finder/emp/beeFinderEmpList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderEmpListAction extends IfOnlineAuthAction
{
    //EMP_FIND
    public static final String EMP_FIND 			= "EMP";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderEmpListForm beeFinderEmpListForm = (BeeFinderEmpListForm) form;
        
        switch (beeFinderEmpListForm.getServiceName())
        {
            case BeeFinderEmpListAction.EMP_FIND:
            	findEmpList(request, response, beeFinderEmpListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEmpList(HttpServletRequest request, HttpServletResponse response, BeeFinderEmpListForm beeFinderEmpListForm) throws Exception
    {
    	BeeFinderEmpListService beeFinderEmpListService = (BeeFinderEmpListService) getBean("beeFinderEmpListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderEmpListService.findEmpList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
