package intf.dream.android.online.finder.emp.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.emp.form.AnOnFinderEmpListForm;
import intf.dream.android.online.finder.emp.service.AnOnFinderEmpListService;

/**
 * finder - emp 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderEmpList" name="anOnFinderEmpListForm"
 *                input="/android/online/finder/emp/anOnFinderEmpList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderEmpListAction extends IfOnlineAuthAction
{
    //EMP_FIND
    public static final String EMP_FIND 			= "EMP";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderEmpListForm anOnFinderEmpListForm = (AnOnFinderEmpListForm) form;
        
        switch (anOnFinderEmpListForm.getServiceName())
        {
            case AnOnFinderEmpListAction.EMP_FIND:
            	findEmpList(request, response, anOnFinderEmpListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEmpList(HttpServletRequest request, HttpServletResponse response, AnOnFinderEmpListForm anOnFinderEmpListForm) throws Exception
    {
    	AnOnFinderEmpListService anOnFinderEmpListService = (AnOnFinderEmpListService) getBean("anOnFinderEmpListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderEmpListService.findEmpList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
