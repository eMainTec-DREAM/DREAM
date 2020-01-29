package intf.dream.android.online.common.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.common.form.AnOnCommonListForm;
import intf.dream.android.online.common.service.AnOnCommonListService;

/**
 * common action 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnCommonList" name="anOnCommonListForm"
 *                input="/android/online/common/anOnCommonList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnCommonListAction extends IfOnlineAuthAction
{
    //next sequence
    public static final String NEXT_VAL 			= "NEXT_VAL";
    //config value
    public static final String CONFIG_VAL 			= "CONFIG_VAL";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnCommonListForm anOnCommonListForm = (AnOnCommonListForm) form;
        
        switch (anOnCommonListForm.getServiceName())
        {
            case AnOnCommonListAction.NEXT_VAL:
            	findNextVal(request, response, anOnCommonListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnCommonListAction.CONFIG_VAL:
            	findConfigVal(request, response, anOnCommonListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findNextVal(HttpServletRequest request, HttpServletResponse response, AnOnCommonListForm anOnCommonListForm) throws Exception
    {
    	AnOnCommonListService anOnCommonListService = (AnOnCommonListService) getBean("anOnCommonListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnCommonListService.findNextVal(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findConfigVal(HttpServletRequest request, HttpServletResponse response, AnOnCommonListForm anOnCommonListForm) throws Exception
    {
    	AnOnCommonListService anOnCommonListService = (AnOnCommonListService) getBean("anOnCommonListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnCommonListService.findConfigVal(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
