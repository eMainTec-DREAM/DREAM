package intf.dream.cricket.finder.syscode.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.syscode.form.CricketFinderSyscodeListForm;
import intf.dream.cricket.finder.syscode.service.CricketFinderSyscodeListService;

/**
 * finder - syscode 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderSyscodeList" name="cricketFinderSyscodeListForm"
 *                input="/cricket/finder/syscode/cricketFinderSyscodeList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderSyscodeListAction extends IfOnlineAuthAction
{
    //Syscode
    public static final String SYSCODE_FIND 			= "SYSCODE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderSyscodeListForm cricketFinderSyscodeListForm = (CricketFinderSyscodeListForm) form;
        
        switch (cricketFinderSyscodeListForm.getServiceName())
        {
            case CricketFinderSyscodeListAction.SYSCODE_FIND:
            	findSyscodeList(request, response, cricketFinderSyscodeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSyscodeList(HttpServletRequest request, HttpServletResponse response, CricketFinderSyscodeListForm cricketFinderSyscodeListForm) throws Exception
    {
    	CricketFinderSyscodeListService cricketFinderSyscodeListService = (CricketFinderSyscodeListService) getBean("cricketFinderSyscodeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderSyscodeListService.findSyscodeList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
