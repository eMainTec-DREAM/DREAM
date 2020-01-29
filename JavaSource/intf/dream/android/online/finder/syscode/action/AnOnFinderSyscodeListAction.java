package intf.dream.android.online.finder.syscode.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.syscode.form.AnOnFinderSyscodeListForm;
import intf.dream.android.online.finder.syscode.service.AnOnFinderSyscodeListService;

/**
 * finder - syscode 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderSyscodeList" name="anOnFinderSyscodeListForm"
 *                input="/android/online/finder/syscode/anOnFinderSyscodeList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderSyscodeListAction extends IfOnlineAuthAction
{
    //Syscode
    public static final String SYSCODE_FIND 			= "SYSCODE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderSyscodeListForm anOnFinderSyscodeListForm = (AnOnFinderSyscodeListForm) form;
        
        switch (anOnFinderSyscodeListForm.getServiceName())
        {
            case AnOnFinderSyscodeListAction.SYSCODE_FIND:
            	findSyscodeList(request, response, anOnFinderSyscodeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSyscodeList(HttpServletRequest request, HttpServletResponse response, AnOnFinderSyscodeListForm anOnFinderSyscodeListForm) throws Exception
    {
    	AnOnFinderSyscodeListService anOnFinderSyscodeListService = (AnOnFinderSyscodeListService) getBean("anOnFinderSyscodeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderSyscodeListService.findSyscodeList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
