package dream.work.cal.pminsappr.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;
import dream.work.cal.pminsappr.form.WorkCalPmInsApprDetailForm;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprDetailService;

/**
 * �������˰�ȹ���� - �� action
 * 
 * @author kim2107
 * @version $Id: WorkCalPmInsApprDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workCalPmInsApprDetail" name="workCalPmInsApprDetailForm"
 *                input="/dream/work/cal/pminsappr/workCalPmInsApprDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workCalPmInsApprNOTDetail" name="workCalPmInsApprDetailForm"
 *                input="/dream/work/cal/pminsappr/workCalPmInsApprNOTDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workCalPmInsApprCompDetail" name="workCalPmInsApprDetailForm"
 *                input="/dream/work/cal/pminsappr/workCalPmInsApprCompDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmInsApprDetail" path=/dream/work/cal/pminsappr/workCalPmInsApprDetail.jsp"
 *                        redirect="false"
 */
public class WorkCalPmInsApprDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 			= 8001;
    /** ���� */
    public static final int DETAIL_INPUT 			= 5002;
    /** ���� */
    public static final int DETAIL_UPDATE 			= 6003;
    /** �ߺ��˻� */
    public static final int DETAIL_DUPLICATE_CHECK	= 8004;
    /** Ȯ�� */
    public static final int DETAIL_STATUS_UPDATE	= 6005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmInsApprDetailForm workCalPmInsApprDetailForm = (WorkCalPmInsApprDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workCalPmInsApprDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(workCalPmInsApprDetailForm.getWorkCalPmInsApprDetailDTO().getAuditKey()==""?workCalPmInsApprDetailForm.getWorkCalPmInsApprCommonDTO().getAuditKey():workCalPmInsApprDetailForm.getWorkCalPmInsApprDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workCalPmInsApprDetailForm.getStrutsAction())
        {
            case WorkCalPmInsApprDetailAction.DETAIL_INIT:
                this.findDetail(request, workCalPmInsApprDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkCalPmInsApprDetailAction.DETAIL_INPUT:
            	this.insertDetail(workCalPmInsApprDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkCalPmInsApprDetailAction.DETAIL_UPDATE:
            	this.updateDetail(workCalPmInsApprDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkCalPmInsApprDetailAction.DETAIL_STATUS_UPDATE:
            	this.updateStatus(workCalPmInsApprDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkCalPmInsApprDetailAction.DETAIL_DUPLICATE_CHECK:
            	this.checkAppr(workCalPmInsApprDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void findDetail(HttpServletRequest request, WorkCalPmInsApprDetailForm workCalPmInsApprDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkCalPmInsApprDetailService workCalPmInsApprDetailService = (WorkCalPmInsApprDetailService)getBean("workCalPmInsApprDetailService", request);

    	WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = workCalPmInsApprDetailForm.getWorkCalPmInsApprCommonDTO();
        // ��ȸ�� �� �κ�
        WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = workCalPmInsApprDetailService.findDetail(workCalPmInsApprCommonDTO, getUser(request));
        // ��ȸ�� ��  �����Ѵ�.
        workCalPmInsApprDetailForm.setWorkCalPmInsApprDetailDTO(workCalPmInsApprDetailDTO);
    }
    private void insertDetail(WorkCalPmInsApprDetailForm workCalPmInsApprDetailForm, HttpServletRequest request) throws Exception
    {
        WorkCalPmInsApprDetailService workCalPmInsApprDetailService = (WorkCalPmInsApprDetailService) getBean("workCalPmInsApprDetailService", request);
        
        WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = workCalPmInsApprDetailForm.getWorkCalPmInsApprDetailDTO();
        WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = workCalPmInsApprDetailForm.getWorkCalPmInsApprCommonDTO();
      
        workCalPmInsApprDetailService.insertDetail(workCalPmInsApprDetailDTO, workCalPmInsApprCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    private void updateDetail(WorkCalPmInsApprDetailForm workCalPmInsApprDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkCalPmInsApprDetailService workCalPmInsApprDetailService = (WorkCalPmInsApprDetailService) getBean("workCalPmInsApprDetailService", request);
        
        WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = workCalPmInsApprDetailForm.getWorkCalPmInsApprDetailDTO();
        WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = workCalPmInsApprDetailForm.getWorkCalPmInsApprCommonDTO();
        
        workCalPmInsApprDetailService.updateDetail(workCalPmInsApprDetailDTO, workCalPmInsApprCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    private void updateStatus(WorkCalPmInsApprDetailForm workCalPmInsApprDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkCalPmInsApprDetailService workCalPmInsApprDetailService = (WorkCalPmInsApprDetailService) getBean("workCalPmInsApprDetailService", request);
        
        WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = workCalPmInsApprDetailForm.getWorkCalPmInsApprDetailDTO();
        
        workCalPmInsApprDetailService.updateStatus(workCalPmInsApprDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void checkAppr(WorkCalPmInsApprDetailForm workCalPmInsApprDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkCalPmInsApprDetailService workCalPmInsApprDetailService = (WorkCalPmInsApprDetailService) getBean("workCalPmInsApprDetailService", request);
    	
    	WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = workCalPmInsApprDetailForm.getWorkCalPmInsApprDetailDTO();
    	
    	String isValid = workCalPmInsApprDetailService.checkAppr(workCalPmInsApprDetailDTO, getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
}