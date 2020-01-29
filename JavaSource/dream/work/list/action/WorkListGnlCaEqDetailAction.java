package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListGnlCaEqDetailDTO;
import dream.work.list.form.WorkListGnlCaEqDetailForm;
import dream.work.list.service.WorkListGnlCaEqDetailService;

/**
 * �۾���  - �˱��� - ������
 * @author  kim21017
 * @version $Id: WorkListGnlCaEqDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workListGnlCaEqDetail" name="workListGnlCaEqDetailForm"
 *                input="/dream/work/list/pmc/workListGnlCaEqDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListSclCaEqDetail" name="workListGnlCaEqDetailForm"
 *                input="/dream/work/list/pmc/workListSclCaEqDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListPrsCaEqDetail" name="workListGnlCaEqDetailForm"
 *                input="/dream/work/list/pmc/workListPrsCaEqDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListGnlCaEqDetail" path="/dream/work/list/pmc/workListGnlCaEqDetail.jsp"
 *                        redirect="false"
 */
public class WorkListGnlCaEqDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WORK_LIST_GNLCAEQ_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int WORK_LIST_GNLCAEQ_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int WORK_LIST_GNLCAEQ_DETAIL_INPUT 		= 5003;
    /** �ߺ�üũ */
    public static final int WORK_LIST_GNLCAEQ_DETAIL_DCHECK     = 8004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListGnlCaEqDetailForm workListGnlCaEqDetailForm = (WorkListGnlCaEqDetailForm) form;
        
        super.updateAudit(workListGnlCaEqDetailForm.getWorkListGnlCaEqDetailDTO().getAuditKey()==""?workListGnlCaEqDetailForm.getMaWoResultMstrCommonDTO().getAuditKey():workListGnlCaEqDetailForm.getWorkListGnlCaEqDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListGnlCaEqDetailForm.getStrutsAction())
        {
            case WorkListGnlCaEqDetailAction.WORK_LIST_GNLCAEQ_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workListGnlCaEqDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkListGnlCaEqDetailAction.WORK_LIST_GNLCAEQ_DETAIL_UPDATE:
            	updateDetail(workListGnlCaEqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListGnlCaEqDetailAction.WORK_LIST_GNLCAEQ_DETAIL_INPUT:
            	insertDetail(workListGnlCaEqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListGnlCaEqDetailAction.WORK_LIST_GNLCAEQ_DETAIL_DCHECK:
                checkDetail(workListGnlCaEqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾���  - �˱��� - ������ �� ��ȸ
     * @author kim2107
     * @version $Id: WorkListGnlCaEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListGnlCaEqDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkListGnlCaEqDetailForm workListGnlCaEqDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkListGnlCaEqDetailService workListGnlCaEqDetailService = (WorkListGnlCaEqDetailService)getBean("workListGnlCaEqDetailService");
        // ��ȸ�� �� �κ�
        WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO = workListGnlCaEqDetailService.findDetail(workListGnlCaEqDetailForm.getMaWoResultMstrCommonDTO(), getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        workListGnlCaEqDetailForm.setWorkListGnlCaEqDetailDTO(workListGnlCaEqDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkListGnlCaEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCaEqDetailForm
     * @param request
     */
    private void updateDetail(WorkListGnlCaEqDetailForm workListGnlCaEqDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListGnlCaEqDetailService workListGnlCaEqDetailService = (WorkListGnlCaEqDetailService) getBean("workListGnlCaEqDetailService");
        
        WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO = workListGnlCaEqDetailForm.getWorkListGnlCaEqDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListGnlCaEqDetailForm.getMaWoResultMstrCommonDTO();
        workListGnlCaEqDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workListGnlCaEqDetailService.updateDetail(workListGnlCaEqDetailDTO,maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: WorkListGnlCaEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCaEqDetailForm
     * @param request
     */
    private void insertDetail(WorkListGnlCaEqDetailForm workListGnlCaEqDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListGnlCaEqDetailService workListGnlCaEqDetailService = (WorkListGnlCaEqDetailService) getBean("workListGnlCaEqDetailService");
        
        WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO = workListGnlCaEqDetailForm.getWorkListGnlCaEqDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListGnlCaEqDetailForm.getMaWoResultMstrCommonDTO();
        workListGnlCaEqDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workListGnlCaEqDetailService.insertDetail(workListGnlCaEqDetailDTO, maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
        
    }
    
    /**
     * detail check
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param workListGnlCaEqDetailForm
     * @param request
     */
    private void checkDetail(WorkListGnlCaEqDetailForm workListGnlCaEqDetailForm, HttpServletRequest request) throws Exception
    {
        WorkListGnlCaEqDetailService workListGnlCaEqDetailService = (WorkListGnlCaEqDetailService) getBean("workListGnlCaEqDetailService");
        
        WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO = workListGnlCaEqDetailForm.getWorkListGnlCaEqDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListGnlCaEqDetailForm.getMaWoResultMstrCommonDTO();
        String isValid = workListGnlCaEqDetailService.checkDetail(maWoResultMstrCommonDTO,workListGnlCaEqDetailDTO,getUser(request));
        
        setAjaxDesc(request, isValid);
        
    }
}