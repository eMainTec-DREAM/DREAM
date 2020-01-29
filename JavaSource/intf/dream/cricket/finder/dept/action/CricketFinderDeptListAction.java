package intf.dream.cricket.finder.dept.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.dept.form.CricketFinderDeptListForm;
import intf.dream.cricket.finder.dept.service.CricketFinderDeptListService;

/**
 * finder - dept 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderDeptList" name="cricketFinderDeptListForm"
 *                input="/cricket/finder/dept/cricketFinderDeptList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderDeptListAction extends IfOnlineAuthAction
{
    //DEPT_FIND
    public static final String DEPT_FIND 			= "DEPT";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderDeptListForm cricketFinderDeptListForm = (CricketFinderDeptListForm) form;
        
        switch (cricketFinderDeptListForm.getServiceName())
        {
            case CricketFinderDeptListAction.DEPT_FIND:
            	findDeptList(request, response, cricketFinderDeptListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findDeptList(HttpServletRequest request, HttpServletResponse response, CricketFinderDeptListForm cricketFinderDeptListForm) throws Exception
    {
    	CricketFinderDeptListService cricketFinderDeptListService = (CricketFinderDeptListService) getBean("cricketFinderDeptListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderDeptListService.findDeptList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
