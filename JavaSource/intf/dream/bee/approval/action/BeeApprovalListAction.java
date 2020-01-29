package intf.dream.bee.approval.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.approval.form.BeeApprovalListForm;
import intf.dream.bee.approval.service.BeeApprovalListService;

/**
 * approval 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeApprovalList" name="beeApprovalListForm"
 *                input="/bee/approval/beeApprovalList.jsp" scope="request"
 *                validate="false"
 */
public class BeeApprovalListAction extends IfOnlineAuthAction
{
    //APPROVAL Å×½ºÆ®
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
        BeeApprovalListForm beeApprovalListForm = (BeeApprovalListForm) form;
        
        switch (beeApprovalListForm.getServiceName())
        {
            case BeeApprovalListAction.APPROVAL_FIND:
            	findApprovalList(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_INSERT:
            	insertApproval(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_UPDATE:
            	updateApproval(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_REQ:
            	reqApproval(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_USER_FIND:
            	findApprovalUserList(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_USER_NEXT_NO:
            	findApprovalUserNextNo(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_USER_INSERT:
            	insertApprovalUser(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_USER_UPDATE:
            	updateApprovalUser(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_USER_DELETE:
            	deleteApprovalUser(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_LINE_FIND:
            	findApprovalLineList(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_LINE_USER_FIND:
            	findApprovalLineUserList(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_LINE_ADD:
            	addApprovalLine(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_READY_FIND:
            	findApprovalReadyList(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_ACTION:
            	actionApproval(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_REFERENCE:
            	referenceApproval(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeApprovalListAction.APPROVAL_REJECT:
            	rejectApproval(request, response, beeApprovalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }
        return returnActionForward;
    }
    
    private void findApprovalList(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");

    	Map map = getMapData(request);
        List resultList = beeApprovalListService.findApprovalList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void insertApproval(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.insertApproval(list);
    	setMessage(response, "","OK"); 
    }
    private void updateApproval(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.updateApproval(list);
    	setMessage(response, "","OK"); 
    }
    private void reqApproval(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.reqApproval(list);
    	setMessage(response, "","OK"); 
    }
    
    private void findApprovalUserList(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeApprovalListService.findApprovalUserList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findApprovalUserNextNo(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeApprovalListService.findApprovalUserNextNo(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void insertApprovalUser(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.insertApprovalUser(list);
    	setMessage(response, "","OK"); 
    }
    private void updateApprovalUser(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.updateApprovalUser(list);
    	setMessage(response, "","OK"); 
    }
    private void deleteApprovalUser(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.deleteApprovalUser(list);
    	setMessage(response, "","OK"); 
    }
    private void findApprovalLineList(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeApprovalListService.findApprovalLineList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void findApprovalLineUserList(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeApprovalListService.findApprovalLineUserList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void addApprovalLine(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.addApprovalLine(list);
    	setMessage(response, "","OK"); 
    }
    private void findApprovalReadyList(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	
    	Map map = getMapData(request);
    	List resultList = beeApprovalListService.findApprovalReadyList(map);
    	super.makeJsonResult(resultList, request, response);
    }
    private void actionApproval(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.actionApproval(list);
    	setMessage(response, "","OK"); 
    }
    private void referenceApproval(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.referenceApproval(list);
    	setMessage(response, "","OK"); 
    }
    private void rejectApproval(HttpServletRequest request, HttpServletResponse response, BeeApprovalListForm beeApprovalListForm) throws Exception
    {
    	BeeApprovalListService beeApprovalListService = (BeeApprovalListService) getBean("beeApprovalListService");
    	List list = getListData(request);
    	int qty = beeApprovalListService.rejectApproval(list);
    	setMessage(response, "","OK"); 
    }
}
