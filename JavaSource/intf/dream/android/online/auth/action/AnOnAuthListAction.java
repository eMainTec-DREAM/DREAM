package intf.dream.android.online.auth.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.auth.form.AnOnAuthListForm;
import intf.dream.android.online.auth.service.AnOnAuthListService;

/**
 * 페이지,버튼,탭 등 관련 API
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnAuthList" name="anOnAuthListForm"
 *                input="/android/online/auth/anOnAuthList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnAuthListAction extends IfOnlineAuthAction
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
        AnOnAuthListForm anOnAuthListForm = (AnOnAuthListForm) form;
        
        switch (anOnAuthListForm.getServiceName())
        {
            case AnOnAuthListAction.PAGE:
            	findAuthPageList(request, response, anOnAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAuthListAction.PGBTN:
            	findAuthPgBtnList(request, response, anOnAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAuthListAction.PGFIELD:
            	findAuthPgFieldList(request, response, anOnAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAuthListAction.PGGRIDCOL:
            	findAuthPgGridColList(request, response, anOnAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAuthListAction.PGPAGE:
            	findAuthPgPageList(request, response, anOnAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAuthListAction.PGLINKED:
            	findAuthPgLinkedList(request, response, anOnAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findAuthPageList(HttpServletRequest request, HttpServletResponse response, AnOnAuthListForm anOnAuthListForm) throws Exception
    {
    	AnOnAuthListService anOnAuthListService = (AnOnAuthListService) getBean("anOnAuthListService");

    	Map map = getMapData(request);
        List resultList = anOnAuthListService.findAuthPageList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgBtnList(HttpServletRequest request, HttpServletResponse response, AnOnAuthListForm anOnAuthListForm) throws Exception
    {
    	AnOnAuthListService anOnAuthListService = (AnOnAuthListService) getBean("anOnAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnAuthListService.findAuthPgBtnList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgFieldList(HttpServletRequest request, HttpServletResponse response, AnOnAuthListForm anOnAuthListForm) throws Exception
    {
    	AnOnAuthListService anOnAuthListService = (AnOnAuthListService) getBean("anOnAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnAuthListService.findAuthPgFieldList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgGridColList(HttpServletRequest request, HttpServletResponse response, AnOnAuthListForm anOnAuthListForm) throws Exception
    {
    	AnOnAuthListService anOnAuthListService = (AnOnAuthListService) getBean("anOnAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnAuthListService.findAuthPgGridColList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgPageList(HttpServletRequest request, HttpServletResponse response, AnOnAuthListForm anOnAuthListForm) throws Exception
    {
    	AnOnAuthListService anOnAuthListService = (AnOnAuthListService) getBean("anOnAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnAuthListService.findAuthPgPageList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgLinkedList(HttpServletRequest request, HttpServletResponse response, AnOnAuthListForm anOnAuthListForm) throws Exception
    {
    	AnOnAuthListService anOnAuthListService = (AnOnAuthListService) getBean("anOnAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnAuthListService.findAuthPgLinkedList(map);
    	super.makeJsonResult(resultList, request, response);
    }
}
