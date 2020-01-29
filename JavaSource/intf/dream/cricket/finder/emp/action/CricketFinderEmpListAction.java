package intf.dream.cricket.finder.emp.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.emp.form.CricketFinderEmpListForm;
import intf.dream.cricket.finder.emp.service.CricketFinderEmpListService;

/**
 * finder - emp 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderEmpList" name="cricketFinderEmpListForm"
 *                input="/cricket/finder/emp/cricketFinderEmpList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderEmpListAction extends IfOnlineAuthAction
{
    //EMP_FIND
    public static final String EMP_FIND 			= "EMP";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderEmpListForm cricketFinderEmpListForm = (CricketFinderEmpListForm) form;
        
        switch (cricketFinderEmpListForm.getServiceName())
        {
            case CricketFinderEmpListAction.EMP_FIND:
            	findEmpList(request, response, cricketFinderEmpListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEmpList(HttpServletRequest request, HttpServletResponse response, CricketFinderEmpListForm cricketFinderEmpListForm) throws Exception
    {
    	CricketFinderEmpListService cricketFinderEmpListService = (CricketFinderEmpListService) getBean("cricketFinderEmpListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderEmpListService.findEmpList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
