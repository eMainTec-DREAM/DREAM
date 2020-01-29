package intf.dream.bee.finder.syscode.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.syscode.form.BeeFinderSyscodeListForm;
import intf.dream.bee.finder.syscode.service.BeeFinderSyscodeListService;

/**
 * finder - syscode 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderSyscodeList" name="beeFinderSyscodeListForm"
 *                input="/bee/finder/syscode/beeFinderSyscodeList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderSyscodeListAction extends IfOnlineAuthAction
{
    //Syscode
    public static final String SYSCODE_FIND 			= "SYSCODE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderSyscodeListForm beeFinderSyscodeListForm = (BeeFinderSyscodeListForm) form;
        
        switch (beeFinderSyscodeListForm.getServiceName())
        {
            case BeeFinderSyscodeListAction.SYSCODE_FIND:
            	findSyscodeList(request, response, beeFinderSyscodeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSyscodeList(HttpServletRequest request, HttpServletResponse response, BeeFinderSyscodeListForm beeFinderSyscodeListForm) throws Exception
    {
    	BeeFinderSyscodeListService beeFinderSyscodeListService = (BeeFinderSyscodeListService) getBean("beeFinderSyscodeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderSyscodeListService.findSyscodeList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
