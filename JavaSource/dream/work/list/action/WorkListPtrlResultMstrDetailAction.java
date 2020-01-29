package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.dto.WorkListPtrlResultMstrDetailDTO;
import dream.work.list.form.WorkListPtrlResultMstrDetailForm;
import dream.work.list.service.WorkListPtrlResultMstrDetailService;

/**
 * �۾��� 
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultMstrDetailAction.java,v 1.0 2015/12/04 09:09:30 youngjoo38 Exp $
 * @since   1.0
 * @struts:action path="/workListPtrlResultMstrDetail" name="workListPtrlResultMstrDetailForm"
 *                input="/dream/work/list/workListPtrlResultMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListPtrlResultMstrDetail" path="/dream/work/list/workListPtrlResultMstrDetail.jsp"
 *                        redirect="false"
 */
public class WorkListPtrlResultMstrDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WORK_LIST_PTRL_RESULT_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int WORK_LIST_PTRL_RESULT_DETAIL_UPDATE 	= 6002;
    /** �����׸� �ۼ� üũ */
    public static final int DETAIL_CHECK                            = 1003;
    /** ��ȸ�Ϸ� �� ���� �Ϸ����� �������� */
    public static final int GET_PTRL_COM_DATE                       = 8004;
    /** ��ȸ�Ϸ� �� ���� �Ϸ�ð� �������� */
    public static final int GET_PTRL_COM_TIME                       = 8005;
    /** ��ȸ�Ϸ� */
    public static final int DETAIL_PTRLCOMPLETED 	                = 6006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListPtrlResultMstrDetailForm workListPtrlResultMstrDetailForm = (WorkListPtrlResultMstrDetailForm) form;
        
        super.updateAudit(workListPtrlResultMstrDetailForm.getWorkListPtrlResultMstrDetailDTO().getAuditKey()==""?workListPtrlResultMstrDetailForm.getWorkListPtrlResultCommonDTO().getAuditKey():workListPtrlResultMstrDetailForm.getWorkListPtrlResultMstrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListPtrlResultMstrDetailForm.getStrutsAction())
        {
            case WorkListPtrlResultMstrDetailAction.WORK_LIST_PTRL_RESULT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workListPtrlResultMstrDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkListPtrlResultMstrDetailAction.WORK_LIST_PTRL_RESULT_DETAIL_UPDATE:
            	updateDetail(workListPtrlResultMstrDetailForm, request, response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListPtrlResultMstrDetailAction.DETAIL_CHECK:
                checkDetail(request, response, workListPtrlResultMstrDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkListPtrlResultMstrDetailAction.GET_PTRL_COM_DATE:
                getPtrlComDate(request, response, workListPtrlResultMstrDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkListPtrlResultMstrDetailAction.GET_PTRL_COM_TIME:
                getPtrlComTime(request, response, workListPtrlResultMstrDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkListPtrlResultMstrDetailAction.DETAIL_PTRLCOMPLETED:
                ptrlCompletedDetail(workListPtrlResultMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �� ��ȸ
     * @author kim2107
     * @version $Id: WorkListPtrlResultMstrDetailAction.java,v 1.2 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListPtrlResultMstrDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkListPtrlResultMstrDetailForm workListPtrlResultMstrDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkListPtrlResultMstrDetailService workListPtrlResultMstrDetailService = (WorkListPtrlResultMstrDetailService)getBean("workListPtrlResultMstrDetailService");

        // ��ȸ�� �� �κ�
        WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO = workListPtrlResultMstrDetailService.findDetail(workListPtrlResultMstrDetailForm.getWorkListPtrlResultCommonDTO(), workListPtrlResultMstrDetailForm.getWorkCalPmPtrlMonthCommonDTO(), getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        workListPtrlResultMstrDetailForm.setWorkListPtrlResultMstrDetailDTO(workListPtrlResultMstrDetailDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id: WorkListPtrlResultMstrDetailAction.java,v 1.2 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultMstrDetailForm
     * @param request
     */
    private void updateDetail(WorkListPtrlResultMstrDetailForm workListPtrlResultMstrDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	WorkListPtrlResultMstrDetailService workListPtrlResultMstrDetailService = (WorkListPtrlResultMstrDetailService) getBean("workListPtrlResultMstrDetailService");
        
        WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO = workListPtrlResultMstrDetailForm.getWorkListPtrlResultMstrDetailDTO();
     //   WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = workListPtrlResultMstrDetailForm.getWorkListPtrlResultCommonDTO();
    //    workListPtrlResultMstrDetailDTO.setEnterBy(getUser(request).getUserId());
    //    workListPtrlResultCommonDTO.setCompNo(getUser(request).getCompNo());
        
        User user = getUser(request);
        
        workListPtrlResultMstrDetailService.updateDetail(workListPtrlResultMstrDetailDTO, user);
        
        setAjaxStatus(request);
    }
    /**
     * CHECK DETAIL
     * @param request
     * @param response
     * @param workListPtrlResultMstrDetailForm
     * @throws Exception
     */
    private void checkDetail(HttpServletRequest request, HttpServletResponse response, WorkListPtrlResultMstrDetailForm workListPtrlResultMstrDetailForm) throws Exception
    {
        WorkListPtrlResultMstrDetailService workListPtrlResultMstrDetailService = (WorkListPtrlResultMstrDetailService) getBean("workListPtrlResultMstrDetailService");
        WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO = workListPtrlResultMstrDetailForm.getWorkListPtrlResultMstrDetailDTO();
        
        String checkDetail = workListPtrlResultMstrDetailService.checkDetail(workListPtrlResultMstrDetailDTO, getUser(request));
        setAjaxDesc(request, checkDetail);
    }
    /**
     * PTRL COMPLETED DETAIL
     * @param request
     * @param response
     * @param workListPtrlResultMstrDetailForm
     * @throws Exception
     */
    private void ptrlCompletedDetail(WorkListPtrlResultMstrDetailForm workListPtrlResultMstrDetailForm, HttpServletRequest request) throws Exception
    {
        WorkListPtrlResultMstrDetailService workListPtrlResultMstrDetailService = (WorkListPtrlResultMstrDetailService)getBean("workListPtrlResultMstrDetailService");
        WorkListPtrlResultMstrDetailDTO  workListPtrlResultMstrDetailDTO = workListPtrlResultMstrDetailForm.getWorkListPtrlResultMstrDetailDTO();
        WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = workListPtrlResultMstrDetailForm.getWorkListPtrlResultCommonDTO();
        
        String ptrlComDate = workListPtrlResultMstrDetailDTO.getPtrlComDate().replace("-", "");
        
        User user = getUser(request);
        workListPtrlResultMstrDetailService.ptrlCompletedDetail(workListPtrlResultMstrDetailDTO, user);
        setAjaxStatus(request);
    }
    
    /**
     * GET PTRL COMDATE
     * @param request
     * @param response
     * @param workListPtrlResultMstrDetailForm
     * @throws Exception
     */
    private void getPtrlComDate(HttpServletRequest request, HttpServletResponse response, WorkListPtrlResultMstrDetailForm workListPtrlResultMstrDetailForm) throws Exception
    {
        WorkListPtrlResultMstrDetailService workListPtrlResultMstrDetailService = (WorkListPtrlResultMstrDetailService) getBean("workListPtrlResultMstrDetailService");
        WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO = workListPtrlResultMstrDetailForm.getWorkListPtrlResultMstrDetailDTO();
        
        String checkDetail = workListPtrlResultMstrDetailService.getPtrlComDate(workListPtrlResultMstrDetailDTO, getUser(request));
        setAjaxDesc(request, checkDetail);
    }
    /**
     * GET PTRL COMTIME
     * @param request
     * @param response
     * @param workListPtrlResultMstrDetailForm
     * @throws Exception
     */
    private void getPtrlComTime(HttpServletRequest request, HttpServletResponse response, WorkListPtrlResultMstrDetailForm workListPtrlResultMstrDetailForm) throws Exception
    {
        WorkListPtrlResultMstrDetailService workListPtrlResultMstrDetailService = (WorkListPtrlResultMstrDetailService) getBean("workListPtrlResultMstrDetailService");
        WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO = workListPtrlResultMstrDetailForm.getWorkListPtrlResultMstrDetailDTO();
        
        String checkDetail = workListPtrlResultMstrDetailService.getPtrlComTime(workListPtrlResultMstrDetailDTO, getUser(request));
        setAjaxDesc(request, checkDetail);
    }
}