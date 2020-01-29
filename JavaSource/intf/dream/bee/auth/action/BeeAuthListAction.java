package intf.dream.bee.auth.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.auth.form.BeeAuthListForm;
import intf.dream.bee.auth.service.BeeAuthListService;

/**
 * 페이지,버튼,탭 등 관련 API
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeAuthList" name="beeAuthListForm"
 *                input="/bee/auth/beeAuthList.jsp" scope="request"
 *                validate="false"
 */
public class BeeAuthListAction extends IfOnlineAuthAction
{
    public static final String PAGE 					= "PAGE";
    public static final String PGBTN 					= "PGBTN";
    public static final String PGFIELD 					= "PGFIELD";
    public static final String PGGRIDCOL 				= "PGGRIDCOL";
    public static final String PGPAGE 					= "PGPAGE";
    public static final String PGLINKED 				= "PGLINKED";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeAuthListForm beeAuthListForm = (BeeAuthListForm) form;
        
        switch (beeAuthListForm.getServiceName())
        {
            case BeeAuthListAction.PAGE:
            	findAuthPageList(request, response, beeAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAuthListAction.PGBTN:
            	findAuthPgBtnList(request, response, beeAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAuthListAction.PGFIELD:
            	findAuthPgFieldList(request, response, beeAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAuthListAction.PGGRIDCOL:
            	findAuthPgGridColList(request, response, beeAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAuthListAction.PGPAGE:
            	findAuthPgPageList(request, response, beeAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAuthListAction.PGLINKED:
            	findAuthPgLinkedList(request, response, beeAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findAuthPageList(HttpServletRequest request, HttpServletResponse response, BeeAuthListForm beeAuthListForm) throws Exception
    {
    	BeeAuthListService beeAuthListService = (BeeAuthListService) getBean("beeAuthListService");

    	Map map = getMapData(request);
        List resultList = beeAuthListService.findAuthPageList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgBtnList(HttpServletRequest request, HttpServletResponse response, BeeAuthListForm beeAuthListForm) throws Exception
    {
    	BeeAuthListService beeAuthListService = (BeeAuthListService) getBean("beeAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeAuthListService.findAuthPgBtnList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgFieldList(HttpServletRequest request, HttpServletResponse response, BeeAuthListForm beeAuthListForm) throws Exception
    {
    	BeeAuthListService beeAuthListService = (BeeAuthListService) getBean("beeAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeAuthListService.findAuthPgFieldList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgGridColList(HttpServletRequest request, HttpServletResponse response, BeeAuthListForm beeAuthListForm) throws Exception
    {
    	BeeAuthListService beeAuthListService = (BeeAuthListService) getBean("beeAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeAuthListService.findAuthPgGridColList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgPageList(HttpServletRequest request, HttpServletResponse response, BeeAuthListForm beeAuthListForm) throws Exception
    {
    	BeeAuthListService beeAuthListService = (BeeAuthListService) getBean("beeAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeAuthListService.findAuthPgPageList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgLinkedList(HttpServletRequest request, HttpServletResponse response, BeeAuthListForm beeAuthListForm) throws Exception
    {
    	BeeAuthListService beeAuthListService = (BeeAuthListService) getBean("beeAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeAuthListService.findAuthPgLinkedList(map);
    	super.makeJsonResult(resultList, request, response);
    }
}
