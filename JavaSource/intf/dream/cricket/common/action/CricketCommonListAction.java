package intf.dream.cricket.common.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.common.form.CricketCommonListForm;
import intf.dream.cricket.common.service.CricketCommonListService;

/**
 * common action 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketCommonList" name="cricketCommonListForm"
 *                input="/cricket/common/cricketCommonList.jsp" scope="request"
 *                validate="false"
 */
public class CricketCommonListAction extends IfOnlineAuthAction
{
    //next sequence
    public static final String NEXT_VAL 			= "NEXT_VAL";
    //config value
    public static final String CONFIG_VAL 			= "CONFIG_VAL";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketCommonListForm cricketCommonListForm = (CricketCommonListForm) form;
        
        switch (cricketCommonListForm.getServiceName())
        {
            case CricketCommonListAction.NEXT_VAL:
            	findNextVal(request, response, cricketCommonListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case CricketCommonListAction.CONFIG_VAL:
            	findConfigVal(request, response, cricketCommonListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findNextVal(HttpServletRequest request, HttpServletResponse response, CricketCommonListForm cricketCommonListForm) throws Exception
    {
    	CricketCommonListService cricketCommonListService = (CricketCommonListService) getBean("cricketCommonListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketCommonListService.findNextVal(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findConfigVal(HttpServletRequest request, HttpServletResponse response, CricketCommonListForm cricketCommonListForm) throws Exception
    {
    	CricketCommonListService cricketCommonListService = (CricketCommonListService) getBean("cricketCommonListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketCommonListService.findConfigVal(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
