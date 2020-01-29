package intf.dream.bee.finder.dept.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.dept.form.BeeFinderDeptListForm;
import intf.dream.bee.finder.dept.service.BeeFinderDeptListService;

/**
 * finder - dept 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderDeptList" name="beeFinderDeptListForm"
 *                input="/bee/finder/dept/beeFinderDeptList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderDeptListAction extends IfOnlineAuthAction
{
    //DEPT_FIND
    public static final String DEPT_FIND 			= "DEPT";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderDeptListForm beeFinderDeptListForm = (BeeFinderDeptListForm) form;
        
        switch (beeFinderDeptListForm.getServiceName())
        {
            case BeeFinderDeptListAction.DEPT_FIND:
            	findDeptList(request, response, beeFinderDeptListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findDeptList(HttpServletRequest request, HttpServletResponse response, BeeFinderDeptListForm beeFinderDeptListForm) throws Exception
    {
    	BeeFinderDeptListService beeFinderDeptListService = (BeeFinderDeptListService) getBean("beeFinderDeptListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderDeptListService.findDeptList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
