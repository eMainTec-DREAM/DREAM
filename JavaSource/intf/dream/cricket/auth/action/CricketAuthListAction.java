package intf.dream.cricket.auth.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.auth.form.CricketAuthListForm;
import intf.dream.cricket.auth.service.CricketAuthListService;

/**
 * 페이지,버튼,탭 등 관련 API
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketAuthList" name="cricketAuthListForm"
 *                input="/cricket/auth/cricketAuthList.jsp" scope="request"
 *                validate="false"
 */
public class CricketAuthListAction extends IfOnlineAuthAction
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
        CricketAuthListForm cricketAuthListForm = (CricketAuthListForm) form;
        
        switch (cricketAuthListForm.getServiceName())
        {
            case CricketAuthListAction.PAGE:
            	findAuthPageList(request, response, cricketAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case CricketAuthListAction.PGBTN:
            	findAuthPgBtnList(request, response, cricketAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case CricketAuthListAction.PGFIELD:
            	findAuthPgFieldList(request, response, cricketAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case CricketAuthListAction.PGGRIDCOL:
            	findAuthPgGridColList(request, response, cricketAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case CricketAuthListAction.PGPAGE:
            	findAuthPgPageList(request, response, cricketAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case CricketAuthListAction.PGLINKED:
            	findAuthPgLinkedList(request, response, cricketAuthListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findAuthPageList(HttpServletRequest request, HttpServletResponse response, CricketAuthListForm cricketAuthListForm) throws Exception
    {
    	CricketAuthListService cricketAuthListService = (CricketAuthListService) getBean("cricketAuthListService");

    	Map map = getMapData(request);
        List resultList = cricketAuthListService.findAuthPageList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgBtnList(HttpServletRequest request, HttpServletResponse response, CricketAuthListForm cricketAuthListForm) throws Exception
    {
    	CricketAuthListService cricketAuthListService = (CricketAuthListService) getBean("cricketAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = cricketAuthListService.findAuthPgBtnList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgFieldList(HttpServletRequest request, HttpServletResponse response, CricketAuthListForm cricketAuthListForm) throws Exception
    {
    	CricketAuthListService cricketAuthListService = (CricketAuthListService) getBean("cricketAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = cricketAuthListService.findAuthPgFieldList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgGridColList(HttpServletRequest request, HttpServletResponse response, CricketAuthListForm cricketAuthListForm) throws Exception
    {
    	CricketAuthListService cricketAuthListService = (CricketAuthListService) getBean("cricketAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = cricketAuthListService.findAuthPgGridColList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgPageList(HttpServletRequest request, HttpServletResponse response, CricketAuthListForm cricketAuthListForm) throws Exception
    {
    	CricketAuthListService cricketAuthListService = (CricketAuthListService) getBean("cricketAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = cricketAuthListService.findAuthPgPageList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findAuthPgLinkedList(HttpServletRequest request, HttpServletResponse response, CricketAuthListForm cricketAuthListForm) throws Exception
    {
    	CricketAuthListService cricketAuthListService = (CricketAuthListService) getBean("cricketAuthListService");
    	
    	Map map = getMapData(request);
    	List resultList = cricketAuthListService.findAuthPgLinkedList(map);
    	super.makeJsonResult(resultList, request, response);
    }
}
