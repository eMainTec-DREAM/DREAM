package dream.work.pm.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.list.dto.WorkPmListSchdDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.form.WorkPmListSchdDetailForm;
import dream.work.pm.list.service.WorkPmListSchdDetailService;

/**
 * �����۾� ���� ��
 * @author  kim21017
 * @version $Id: WorkPmListSchdDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workPmListSchdDetail" name="workPmListSchdDetailForm"
 *                input="/dream/work/pm/list/workPmListSchdDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmListSchdDetail" path="/dream/work/pm/list/workPmListSchdDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmListSchdDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WORK_PM_SCH_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int WORK_PM_SCH_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int WORK_PM_SCH_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmListSchdDetailForm workPmListSchdDetailForm = (WorkPmListSchdDetailForm) form;
        switch (workPmListSchdDetailForm.getStrutsAction())
        {
            case WorkPmListSchdDetailAction.WORK_PM_SCH_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workPmListSchdDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmListSchdDetailAction.WORK_PM_SCH_DETAIL_UPDATE:
            	updateDetail(workPmListSchdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListSchdDetailAction.WORK_PM_SCH_DETAIL_INPUT:
            	insertDetail(workPmListSchdDetailForm, request);
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
     * @version $Id: WorkPmListSchdDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmListSchdDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkPmListSchdDetailForm workPmListSchdDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkPmListSchdDetailService workPmListSchdDetailService = (WorkPmListSchdDetailService)getBean("workPmListSchdDetailService");

    	// �۾����Id ����
        String pmId = workPmListSchdDetailForm.getMaPmMstrCommonDTO().getPmId();
        // �Ѱ��� ��������id ����
        String pmEventSchedId = workPmListSchdDetailForm.getMaPmMstrCommonDTO().getPmEventSchedId();
        
        // ��ȸ�� �� �κ�
        WorkPmListSchdDetailDTO workPmListSchdDetailDTO = workPmListSchdDetailService.findDetail(pmId, pmEventSchedId, getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        workPmListSchdDetailForm.setWorkPmListSchdDetailDTO(workPmListSchdDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkPmListSchdDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListSchdDetailForm
     * @param request
     */
    private void updateDetail(WorkPmListSchdDetailForm workPmListSchdDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListSchdDetailService workPmListSchdDetailService = (WorkPmListSchdDetailService) getBean("workPmListSchdDetailService");
        
        WorkPmListSchdDetailDTO workPmListSchdDetailDTO = workPmListSchdDetailForm.getWorkPmListSchdDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListSchdDetailForm.getMaPmMstrCommonDTO();
        workPmListSchdDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmListSchdDetailService.updateDetail(workPmListSchdDetailDTO,maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: WorkPmListSchdDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListSchdDetailForm
     * @param request
     */
    private void insertDetail(WorkPmListSchdDetailForm workPmListSchdDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListSchdDetailService workPmListSchdDetailService = (WorkPmListSchdDetailService) getBean("workPmListSchdDetailService");
        
        WorkPmListSchdDetailDTO workPmListSchdDetailDTO = workPmListSchdDetailForm.getWorkPmListSchdDetailDTO();
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListSchdDetailForm.getMaPmMstrCommonDTO();
        workPmListSchdDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmListSchdDetailService.insertDetail(workPmListSchdDetailDTO, maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}