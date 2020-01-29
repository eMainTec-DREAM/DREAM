package mobile.dream.work.pm.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.MobileAuthAction;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.dto.WorkPmWorkDetailDTO;
import mobile.dream.work.pm.list.form.WorkPmWorkDetailForm;
import mobile.dream.work.pm.list.service.WorkPmWorkDetailService;

/**
 * ��ȹ�۾� - �� action
 * 
 * @author jung7126
 * @version $Id: WorkPmWorkDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workPmWorkDetail" name="workPmWorkDetailForm"
 *                input="/mobile/dream/work/pm/list/workPmWorkDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmWorkDetail" path="/mobile/dream/work/pm/list/workPmWorkDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmWorkDetailAction extends MobileAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PM_WORK_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int PM_WORK_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int PM_WORK_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmWorkDetailForm workPmWorkDetailForm = (WorkPmWorkDetailForm) form;
        
        switch (workPmWorkDetailForm.getStrutsAction())
        {
            case WorkPmWorkDetailAction.PM_WORK_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workPmWorkDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmWorkDetailAction.PM_WORK_DETAIL_INPUT:
            	insertDetail(workPmWorkDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmWorkDetailAction.PM_WORK_DETAIL_UPDATE:
            	updateDetail(workPmWorkDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �������� �� ��ȸ
     * @author jung7126
     * @version $Id: WorkPmWorkDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmWorkDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkPmWorkDetailForm workPmWorkDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkPmWorkDetailService workPmWorkDetailService = (WorkPmWorkDetailService)getBean("workPmWorkDetailService");

        WorkPmWorkCommonDTO dto = workPmWorkDetailForm.getWorkPmWorkCommonDTO();
        
        // ��ȸ�� �� �κ�
        WorkPmWorkDetailDTO workPmWorkDetailDTO = workPmWorkDetailService.findDetail(dto, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        workPmWorkDetailForm.setWorkPmWorkDetailDTO(workPmWorkDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: WorkPmWorkDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailForm
     * @param request
     */
    private void insertDetail(WorkPmWorkDetailForm workPmWorkDetailForm, HttpServletRequest request) throws Exception
    {
        WorkPmWorkDetailService workPmWorkDetailService = (WorkPmWorkDetailService) getBean("workPmWorkDetailService");
        
        WorkPmWorkDetailDTO workPmWorkDetailDTO = workPmWorkDetailForm.getWorkPmWorkDetailDTO();
        
        workPmWorkDetailDTO.setEnterBy(getUser(request).getUserId());
        workPmWorkDetailDTO.setCompNo(getUser(request).getCompNo());
        
        workPmWorkDetailService.insertDetail(workPmWorkDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkPmWorkDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailForm
     * @param request
     */
    private void updateDetail(WorkPmWorkDetailForm workPmWorkDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmWorkDetailService workPmWorkDetailService = (WorkPmWorkDetailService) getBean("workPmWorkDetailService");
        
        WorkPmWorkDetailDTO workPmWorkDetailDTO = workPmWorkDetailForm.getWorkPmWorkDetailDTO();
        
        workPmWorkDetailDTO.setEnterBy(getUser(request).getUserId());
        workPmWorkDetailDTO.setCompNo(getUser(request).getCompNo());
        
        workPmWorkDetailService.updateDetail(workPmWorkDetailDTO);
        
        setAjaxStatus(request);
    }
}