package intf.dream.bee.count.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.count.form.BeeCountListForm;
import intf.dream.bee.count.service.BeeCountListService;

/**
 * count 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeCountList" name="beeCountListForm"
 *                input="/bee/count/beeCountList.jsp" scope="request"
 *                validate="false"
 */
public class BeeCountListAction extends IfOnlineAuthAction
{
    //Main Count List
    public static final String MAIN_FIND 				= "MAIN";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeCountListForm beeCountListForm = (BeeCountListForm) form;
        
        switch (beeCountListForm.getServiceName())
        {
            case BeeCountListAction.MAIN_FIND:
            	findMainList(request, response, beeCountListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findMainList(HttpServletRequest request, HttpServletResponse response, BeeCountListForm beeCountListForm) throws Exception
    {
    	BeeCountListService beeCountListService = (BeeCountListService) getBean("beeCountListService",request);

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeCountListService.findMainList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
}
