package intf.dream.bee.common.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.common.form.BeeCommonListForm;
import intf.dream.bee.common.service.BeeCommonListService;

/**
 * common action 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeCommonList" name="beeCommonListForm"
 *                input="/bee/common/beeCommonList.jsp" scope="request"
 *                validate="false"
 */
public class BeeCommonListAction extends IfOnlineAuthAction
{
    //next sequence
    public static final String NEXT_VAL 			= "NEXT_VAL";
    //config value
    public static final String CONFIG_VAL 			= "CONFIG_VAL";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeCommonListForm beeCommonListForm = (BeeCommonListForm) form;
        
        switch (beeCommonListForm.getServiceName())
        {
            case BeeCommonListAction.NEXT_VAL:
            	findNextVal(request, response, beeCommonListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeCommonListAction.CONFIG_VAL:
            	findConfigVal(request, response, beeCommonListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findNextVal(HttpServletRequest request, HttpServletResponse response, BeeCommonListForm beeCommonListForm) throws Exception
    {
    	BeeCommonListService beeCommonListService = (BeeCommonListService) getBean("beeCommonListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeCommonListService.findNextVal(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findConfigVal(HttpServletRequest request, HttpServletResponse response, BeeCommonListForm beeCommonListForm) throws Exception
    {
    	BeeCommonListService beeCommonListService = (BeeCommonListService) getBean("beeCommonListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeCommonListService.findConfigVal(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
