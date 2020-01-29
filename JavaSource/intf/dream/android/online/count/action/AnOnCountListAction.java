package intf.dream.android.online.count.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.count.form.AnOnCountListForm;
import intf.dream.android.online.count.service.AnOnCountListService;

/**
 * count 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnCountList" name="anOnCountListForm"
 *                input="/android/online/count/anOnCountList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnCountListAction extends IfOnlineAuthAction
{
    //Main Count List
    public static final String MAIN_FIND 				= "MAIN";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnCountListForm anOnCountListForm = (AnOnCountListForm) form;
        
        switch (anOnCountListForm.getServiceName())
        {
            case AnOnCountListAction.MAIN_FIND:
            	findMainList(request, response, anOnCountListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findMainList(HttpServletRequest request, HttpServletResponse response, AnOnCountListForm anOnCountListForm) throws Exception
    {
    	AnOnCountListService anOnCountListService = (AnOnCountListService) getBean("anOnCountListService",request);

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnCountListService.findMainList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
}
