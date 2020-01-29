package dream.work.pm.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListShiftDetailDTO;
import dream.work.pm.list.form.WorkPmListShiftDetailForm;
import dream.work.pm.list.service.WorkPmListShiftDetailService;

/**
 * ������
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workPmListPtrlShiftDetail" name="workPmListShiftDetailForm"
 *                input="/dream/work/pm/list/workPmListPtrlShiftDetail.jsp" scope="request"
 *                validate="false"
 */
public class WorkPmListShiftDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WORK_PM_SHIFT_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int WORK_PM_SHIFT_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int WORK_PM_SHIFT_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmListShiftDetailForm workPmListShiftDetailForm = (WorkPmListShiftDetailForm) form;
        switch (workPmListShiftDetailForm.getStrutsAction())
        {
            case WorkPmListShiftDetailAction.WORK_PM_SHIFT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workPmListShiftDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmListShiftDetailAction.WORK_PM_SHIFT_DETAIL_UPDATE:
            	updateDetail(workPmListShiftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListShiftDetailAction.WORK_PM_SHIFT_DETAIL_INPUT:
            	insertDetail(workPmListShiftDetailForm, request);
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
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workPmListShiftDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkPmListShiftDetailForm workPmListShiftDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkPmListShiftDetailService workPmListShiftDetailService = (WorkPmListShiftDetailService)getBean("workPmListShiftDetailService");

    	// ��ȸ�ڽ�Id ����
        String pmId = workPmListShiftDetailForm.getMaPmMstrCommonDTO().getPmId();
        // �Ѱ��� ������id ����
        String pmWrkShiftId = workPmListShiftDetailForm.getMaPmMstrCommonDTO().getPmWrkShiftId();
        
        User loginUser = getUser(request);
        
        // ��ȸ�� �� �κ�
        WorkPmListShiftDetailDTO workPmListShiftDetailDTO = workPmListShiftDetailService.findDetail(pmId, pmWrkShiftId, loginUser);
        
        // ��ȸ�� ��  �����Ѵ�.
        workPmListShiftDetailForm.setWorkPmListShiftDetailDTO(workPmListShiftDetailDTO);
    }
    /**
     * detail update
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmListShiftDetailForm
     * @param request
     */
    private void updateDetail(WorkPmListShiftDetailForm workPmListShiftDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListShiftDetailService workPmListShiftDetailService = (WorkPmListShiftDetailService) getBean("workPmListShiftDetailService");
        
        WorkPmListShiftDetailDTO workPmListShiftDetailDTO = workPmListShiftDetailForm.getWorkPmListShiftDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListShiftDetailForm.getMaPmMstrCommonDTO();
        workPmListShiftDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmListShiftDetailService.updateDetail(workPmListShiftDetailDTO,maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmListShiftDetailForm
     * @param request
     */
    private void insertDetail(WorkPmListShiftDetailForm workPmListShiftDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListShiftDetailService workPmListShiftDetailService = (WorkPmListShiftDetailService) getBean("workPmListShiftDetailService");
        
        WorkPmListShiftDetailDTO workPmListShiftDetailDTO = workPmListShiftDetailForm.getWorkPmListShiftDetailDTO();
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListShiftDetailForm.getMaPmMstrCommonDTO();
        workPmListShiftDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmListShiftDetailService.insertDetail(workPmListShiftDetailDTO, maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}