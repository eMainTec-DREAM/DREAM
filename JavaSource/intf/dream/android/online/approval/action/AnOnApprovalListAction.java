package intf.dream.android.online.approval.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.approval.form.AnOnApprovalListForm;
import intf.dream.android.online.approval.service.AnOnApprovalListService;

/**
 * approval 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnApprovalList" name="anOnApprovalListForm"
 *                input="/android/online/approval/anOnApprovalList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnApprovalListAction extends IfOnlineAuthAction
{
    //APPROVAL
    public static final String APPROVAL_FIND 			= "APPROVAL";
    //APPROVAL_INSERT
    public static final String APPROVAL_INSERT 			= "APPROVAL_INSERT";
    //APPROVAL_UPDATE
    public static final String APPROVAL_UPDATE			= "APPROVAL_UPDATE";
    //APPROVAL_REQ
    public static final String APPROVAL_REQ				= "APPROVAL_REQ";
    
    //APPROVAL_USER
    public static final String APPROVAL_USER_FIND 		= "APPROVAL_USER";
    //APPROVAL_USER_NEXT_SEQ
    public static final String APPROVAL_USER_NEXT_NO 	= "APPROVAL_USER_NEXT_NO";
    //APPROVAL_USER_INSERT
    public static final String APPROVAL_USER_INSERT 	= "APPROVAL_USER_INSERT";
    //APPROVAL_USER_UPDATE
    public static final String APPROVAL_USER_UPDATE		= "APPROVAL_USER_UPDATE";
    //APPROVAL_USER_DELETE
    public static final String APPROVAL_USER_DELETE		= "APPROVAL_USER_DELETE";
    
    //APPROVAL_LINE
    public static final String APPROVAL_LINE_FIND 		= "APPROVAL_LINE";
    //APPROVAL_LINE_USER
    public static final String APPROVAL_LINE_USER_FIND 	= "APPROVAL_LINE_USER";
    //ADD_APPROVAL_LINE
    public static final String APPROVAL_LINE_ADD 		= "APPROVAL_LINE_ADD";

    //APPROVAL_READY
    public static final String APPROVAL_READY_FIND 		= "APPROVAL_READY";
    //APPROVAL_ACTION
    public static final String APPROVAL_ACTION 			= "APPROVAL_ACTION";
    //APPROVAL_REFERENCE
    public static final String APPROVAL_REFERENCE 		= "APPROVAL_REFERENCE";
    //APPROVAL_REJECT
    public static final String APPROVAL_REJECT 			= "APPROVAL_REJECT";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnApprovalListForm anOnApprovalListForm = (AnOnApprovalListForm) form;
        
        switch (anOnApprovalListForm.getServiceName())
        {
            case AnOnApprovalListAction.APPROVAL_FIND:
            	findApprovalList(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_INSERT:
            	insertApproval(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_UPDATE:
            	updateApproval(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_REQ:
            	reqApproval(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_USER_FIND:
            	findApprovalUserList(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_USER_NEXT_NO:
            	findApprovalUserNextNo(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_USER_INSERT:
            	insertApprovalUser(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_USER_UPDATE:
            	updateApprovalUser(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_USER_DELETE:
            	deleteApprovalUser(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_LINE_FIND:
            	findApprovalLineList(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_LINE_USER_FIND:
            	findApprovalLineUserList(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_LINE_ADD:
            	addApprovalLine(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_READY_FIND:
            	findApprovalReadyList(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_ACTION:
            	actionApproval(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_REFERENCE:
            	referenceApproval(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnApprovalListAction.APPROVAL_REJECT:
            	rejectApproval(request, response, anOnApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }
        return returnActionForward;
    }
    
    private void findApprovalList(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");

    	Map map = getMapData(request);
        List resultList = anOnApprovalListService.findApprovalList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void insertApproval(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.insertApproval(list);
    	setMessage(response, "","OK"); 
    }
    private void updateApproval(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.updateApproval(list);
    	setMessage(response, "","OK"); 
    }
    private void reqApproval(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.reqApproval(list);
    	setMessage(response, "","OK"); 
    }
    
    private void findApprovalUserList(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnApprovalListService.findApprovalUserList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findApprovalUserNextNo(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnApprovalListService.findApprovalUserNextNo(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void insertApprovalUser(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.insertApprovalUser(list);
    	setMessage(response, "","OK"); 
    }
    private void updateApprovalUser(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.updateApprovalUser(list);
    	setMessage(response, "","OK"); 
    }
    private void deleteApprovalUser(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.deleteApprovalUser(list);
    	setMessage(response, "","OK"); 
    }
    private void findApprovalLineList(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnApprovalListService.findApprovalLineList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findApprovalLineUserList(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnApprovalListService.findApprovalLineUserList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void addApprovalLine(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.addApprovalLine(list);
    	setMessage(response, "","OK"); 
    }
    private void findApprovalReadyList(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = anOnApprovalListService.findApprovalReadyList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void actionApproval(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.actionApproval(list);
    	setMessage(response, "","OK"); 
    }
    private void referenceApproval(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.referenceApproval(list);
    	setMessage(response, "","OK"); 
    }
    private void rejectApproval(HttpServletRequest request, HttpServletResponse response, AnOnApprovalListForm anOnApprovalListForm) throws Exception
    {
    	AnOnApprovalListService anOnApprovalListService = (AnOnApprovalListService) getBean("anOnApprovalListService");
    	List list = getListData(request);
    	int qty = anOnApprovalListService.rejectApproval(list);
    	setMessage(response, "","OK"); 
    }
}
