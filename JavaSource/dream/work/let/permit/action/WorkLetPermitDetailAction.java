package dream.work.let.permit.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.form.WorkLetPermitDetailForm;
import dream.work.let.permit.service.WorkLetPermitDetailService;

/**
 * �����۾� - �����۾��㰡�� ��
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @struts:action path="/workLetPermitDetail" name="workLetPermitDetailForm"
 *                input="/dream/work/let/permit/workLetPermitDetail.jsp" scope="request"
 *                validate="false"
 */
public class WorkLetPermitDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WO_LET_PERMIT_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int WO_LET_PERMIT_DETAIL_UPDATE 	= 6001;
    /** �Է� */
    public static final int WO_LET_PERMIT_DETAIL_INPUT 		= 5001;
    /** �Ϸ� */
    public static final int WO_LET_PERMIT_DETAIL_COMPLETE 			= 6002;
    /** �Ϸ���� */
    public static final int WO_LET_PERMIT_DETAIL_COMPLETE_CANCEL 	= 6003;
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetPermitDetailForm workLetPermitDetailForm = (WorkLetPermitDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workLetPermitDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (workLetPermitDetailForm.getStrutsAction())
        {
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workLetPermitDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_UPDATE:
            	updateDetail(workLetPermitDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_INPUT:
            	insertDetail(workLetPermitDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_COMPLETE:
                completeDetail(workLetPermitDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_COMPLETE_CANCEL:
            	completeCancelDetail(workLetPermitDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾���ȹ��� - �����۾� �� ��ȸ
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workLetPermitDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkLetPermitDetailForm workLetPermitDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService)getBean("workLetPermitDetailService");

    	// �����۾��㰡�� ID ����
        String woLetId = workLetPermitDetailForm.getWorkLetCommonDTO().getWoLetId();
        // �����۾�����id ����
        String woLetListId = workLetPermitDetailForm.getWorkLetPermitListDTO().getWoLetListId();
        
        // ��ȸ�� �� �κ�
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailService.findDetail(woLetId, woLetListId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        workLetPermitDetailForm.setWorkLetPermitDetailDTO(workLetPermitDetailDTO);
    }
    
    /**
     * detail update
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetPermitDetailForm
     * @param request
     */
    private void updateDetail(WorkLetPermitDetailForm workLetPermitDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService) getBean("workLetPermitDetailService");
        
    	WorkLetCommonDTO workLetCommonDTO = workLetPermitDetailForm.getWorkLetCommonDTO();
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailForm.getWorkLetPermitDetailDTO();
        
        workLetPermitDetailService.updateDetail(workLetCommonDTO, workLetPermitDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetPermitDetailForm
     * @param request
     */
    private void insertDetail(WorkLetPermitDetailForm workLetPermitDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService) getBean("workLetPermitDetailService");
        
    	WorkLetCommonDTO workLetCommonDTO = workLetPermitDetailForm.getWorkLetCommonDTO();
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailForm.getWorkLetPermitDetailDTO();
        
        workLetPermitDetailService.insertDetail(workLetCommonDTO, workLetPermitDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    
    /**
     * detail complete
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetPermitDetailForm
     * @param request
     */
    private void completeDetail(WorkLetPermitDetailForm workLetPermitDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService) getBean("workLetPermitDetailService");
        
    	WorkLetDetailDTO workLetDetailDTO = workLetPermitDetailForm.getWorkLetDetailDTO();
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailForm.getWorkLetPermitDetailDTO();
        
        String status = workLetPermitDetailService.completeDetail(workLetDetailDTO, workLetPermitDetailDTO, getUser(request));
        
        setAjaxDesc(request, status);
    }
    
    /**
     * detail complete cancel
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetPermitDetailForm
     * @param request
     */
    private void completeCancelDetail(WorkLetPermitDetailForm workLetPermitDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService) getBean("workLetPermitDetailService");
        
    	WorkLetDetailDTO workLetDetailDTO = workLetPermitDetailForm.getWorkLetDetailDTO();
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailForm.getWorkLetPermitDetailDTO();
        
        String status = workLetPermitDetailService.completeCancelDetail(workLetDetailDTO, workLetPermitDetailDTO, getUser(request));
        
        setAjaxDesc(request, status);
    }
    
}