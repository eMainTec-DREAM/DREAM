package intf.dream.cricket.count.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.count.form.CricketCountListForm;
import intf.dream.cricket.count.service.CricketCountListService;

/**
 * count 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketCountList" name="cricketCountListForm"
 *                input="/cricket/count/cricketCountList.jsp" scope="request"
 *                validate="false"
 */
public class CricketCountListAction extends IfOnlineAuthAction
{
    //Main Count List
    public static final String MAIN_FIND 				= "MAIN";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketCountListForm cricketCountListForm = (CricketCountListForm) form;
        
        switch (cricketCountListForm.getServiceName())
        {
            case CricketCountListAction.MAIN_FIND:
            	findMainList(request, response, cricketCountListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findMainList(HttpServletRequest request, HttpServletResponse response, CricketCountListForm cricketCountListForm) throws Exception
    {
    	CricketCountListService cricketCountListService = (CricketCountListService) getBean("cricketCountListService",request);

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketCountListService.findMainList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
}
