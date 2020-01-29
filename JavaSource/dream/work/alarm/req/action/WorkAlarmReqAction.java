package dream.work.alarm.req.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.alarm.req.dto.WorkAlarmReqDTO;
import dream.work.alarm.req.form.WorkAlarmReqForm;
import dream.work.alarm.req.service.WorkAlarmReqService;

/**
 * ������û ���� - ��� action 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workAlarmReqList" name="workAlarmReqForm"
 *                input="/dream/work/alarm/req/workAlarmReqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workAlarmReqList" path="/dream/work/alarm/req/workAlarmReqList.jsp"
 *                        redirect="false"
 */
public class WorkAlarmReqAction extends AuthAction
{
    /** ��ȸ */
    public static final int LIST_FIND         	 	= 8001;
    /** �����ϱ� */
    public static final int LIST_DELETE     	 	= 7002;
    /** ����ȸ */
    public static final int DETAIL_INIT          	= 8002;
    /** ���� */
    public static final int LIST_ALARM_REQ_INPUT	= 5001;
    /** ���� �۾���û ���� */
    public static final int LIST_WOREQ_LINK			= 5002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkAlarmReqForm workAlarmReqForm = (WorkAlarmReqForm) form;
        
        super.updateAudit(workAlarmReqForm.getWorkAlarmReqDTO().getAuditKey()==""?workAlarmReqForm.getWorkAlarmReqDTO().getAuditKey():workAlarmReqForm.getWorkAlarmReqDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workAlarmReqForm.getStrutsAction())
        {
            case WorkAlarmReqAction.LIST_FIND:
            	findList(request, workAlarmReqForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkAlarmReqAction.LIST_DELETE:
            	deleteList(request, workAlarmReqForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkAlarmReqAction.DETAIL_INIT:
                findDetail(request, response, workAlarmReqForm);
                returnActionForward = mapping.findForward("workAlarmReqDetail");
                break;
            case WorkAlarmReqAction.LIST_ALARM_REQ_INPUT:
                insertDetail(request, response, workAlarmReqForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkAlarmReqAction.LIST_WOREQ_LINK:
            	woReqLinkList(request, response, workAlarmReqForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkAlarmReqAction.BASE_SET_HEADER:
                setHeader(request, response, workAlarmReqForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkAlarmReqAction.BASE_GRID_EXPORT:
            	findList(request, workAlarmReqForm,response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkAlarmReqForm workAlarmReqForm) throws IOException
    {
        super.setHeader(request, response, workAlarmReqForm.getListId(), workAlarmReqForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workAlarmReqForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, WorkAlarmReqForm workAlarmReqForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	WorkAlarmReqService workAlarmReqService = (WorkAlarmReqService) getBean("workAlarmReqService");        

    	WorkAlarmReqDTO workAlarmReqDTO = workAlarmReqForm.getWorkAlarmReqDTO();
    	
    	//Paging
        workAlarmReqDTO.setIsLoadMaxCount("Y".equals(workAlarmReqForm.getIsLoadMaxCount())?true:false);
        workAlarmReqDTO.setFirstRow(workAlarmReqForm.getFirstRow());
        workAlarmReqDTO.setOrderBy(workAlarmReqForm.getOrderBy());
        workAlarmReqDTO.setDirection(workAlarmReqForm.getDirection());
        
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = workAlarmReqService.findList(workAlarmReqDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workAlarmReqForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workAlarmReqService.findTotalCount(workAlarmReqDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workAlarmReqForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, WorkAlarmReqForm workAlarmReqForm) throws Exception
    {
    	WorkAlarmReqService workAlarmReqService = (WorkAlarmReqService) getBean("workAlarmReqService");        
    	
        String[] deleteRows = workAlarmReqForm.getDeleteRows();
        User user = getUser(request);
        
        workAlarmReqService.deleteList(deleteRows, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * �� ��ȸ
     * @author 	nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param 	request
     * @param 	workAlarmReqForm
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkAlarmReqForm workAlarmReqForm) throws Exception 
    {   
        // Service ��ü ����
    	WorkAlarmReqService workAlarmReqService = (WorkAlarmReqService)getBean("workAlarmReqService");
    	
    	WorkAlarmReqDTO workAlarmReqDTO = workAlarmReqForm.getWorkAlarmReqDTO();
    	
    	// ����
    	User user = getUser(request);
    	
        // ��ȸ�� �� �κ�
    	workAlarmReqDTO = workAlarmReqService.findDetail(workAlarmReqDTO, user);
        
        // ��ȸ�� ��  �����Ѵ�.
        workAlarmReqForm.setWorkAlarmReqDTO(workAlarmReqDTO);
    }
    
    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workAlarmReqForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkAlarmReqForm workAlarmReqForm) throws Exception
    {
    	// Service ��ü ����
    	WorkAlarmReqService workAlarmReqService = (WorkAlarmReqService)getBean("workAlarmReqService");
    	
    	WorkAlarmReqDTO workAlarmReqDTO = workAlarmReqForm.getWorkAlarmReqDTO();
        
    	// User ��ü ����
        User user = getUser(request);
        
        workAlarmReqService.insertDetail(workAlarmReqDTO, user);
        
        setAjaxStatus(request);
    }
    
    
    /**
     * ���� ��û ����
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param 	request
     * @param 	workAlarmReqForm
     * @throws 	Exception
     */
    private void woReqLinkList(HttpServletRequest request, HttpServletResponse response, WorkAlarmReqForm workAlarmReqForm) throws Exception {
    	// Service ��ü ����
    	WorkAlarmReqService workAlarmReqService = (WorkAlarmReqService) getBean("workAlarmReqService");
    	WorkAlarmReqDTO WorkAlarmReqDTO = workAlarmReqForm.getWorkAlarmReqDTO();
    	
    	workAlarmReqService.linkWoReq(WorkAlarmReqDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
}
