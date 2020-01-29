package intf.dream.android.online.finder.dept.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.dept.form.AnOnFinderDeptListForm;
import intf.dream.android.online.finder.dept.service.AnOnFinderDeptListService;

/**
 * finder - dept 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderDeptList" name="anOnFinderDeptListForm"
 *                input="/android/online/finder/dept/anOnFinderDeptList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderDeptListAction extends IfOnlineAuthAction
{
    //DEPT_FIND
    public static final String DEPT_FIND 			= "DEPT";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderDeptListForm anOnFinderDeptListForm = (AnOnFinderDeptListForm) form;
        
        switch (anOnFinderDeptListForm.getServiceName())
        {
            case AnOnFinderDeptListAction.DEPT_FIND:
            	findDeptList(request, response, anOnFinderDeptListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findDeptList(HttpServletRequest request, HttpServletResponse response, AnOnFinderDeptListForm anOnFinderDeptListForm) throws Exception
    {
    	AnOnFinderDeptListService anOnFinderDeptListService = (AnOnFinderDeptListService) getBean("anOnFinderDeptListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderDeptListService.findDeptList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
