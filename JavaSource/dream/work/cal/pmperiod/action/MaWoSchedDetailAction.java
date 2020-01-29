package dream.work.cal.pmperiod.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;
import dream.work.cal.pmperiod.form.MaWoSchedDetailForm;
import dream.work.cal.pmperiod.service.MaWoSchedDetailService;

/**
 * �����۾�����(�Ⱓ) - �� action
 * 
 * @author kim2107
 * @version $Id: MaWoSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maWoSchedDetail" name="maWoSchedDetailForm"
 *                input="/dream/work/cal/pmperiod/maWoSchedDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workCalPmcPeriodDetail" name="maWoSchedDetailForm"
 *                input="/dream/work/cal/pmperiod/workCalPmcPeriodDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoSchedDetail" path="/dream/work/cal/pmperiod/maWoSchedDetail.jsp"
 *                        redirect="false"
 */
public class MaWoSchedDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int MONTH_SCHED_DETAIL_INIT			= 8001;
    /** ���� */
    public static final int MONTH_SCHED_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int MONTH_SCHED_DETAIL_INPUT 		= 5003;
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoSchedDetailForm maWoSchedDetailForm = (MaWoSchedDetailForm) form;
        
        super.updateAudit(maWoSchedDetailForm.getMaWoSchedDetailDTO().getAuditKey()==""?maWoSchedDetailForm.getMaWoSchedCommonDTO().getAuditKey():maWoSchedDetailForm.getMaWoSchedDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        
        switch (maWoSchedDetailForm.getStrutsAction())
        {
            case MaWoSchedDetailAction.MONTH_SCHED_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoSchedDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoSchedDetailAction.MONTH_SCHED_DETAIL_UPDATE:
            	updateDetail(maWoSchedDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoSchedDetailAction.MONTH_SCHED_DETAIL_INPUT:
            	inputDetail(maWoSchedDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �����۾�����(�Ⱓ) �� ��ȸ
     * @author kim2107
     * @version $Id: MaWoSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoSchedDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoSchedDetailForm maWoSchedDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoSchedDetailService maWoSchedDetailService = (MaWoSchedDetailService)getBean("maWoSchedDetailService");

        // �Ѱ��� PM������Id ����
        String pmSchedId = maWoSchedDetailForm.getMaWoSchedCommonDTO().getPmSchedId();
        
        // ��ȸ�� �� �κ�
        MaWoSchedDetailDTO maWoSchedDetailDTO = maWoSchedDetailService.findDetail(pmSchedId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maWoSchedDetailForm.setMaWoSchedDetailDTO(maWoSchedDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoSchedDetailForm
     * @param request
     */
    private void updateDetail(MaWoSchedDetailForm maWoSchedDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoSchedDetailService maWoSchedDetailService = (MaWoSchedDetailService) getBean("maWoSchedDetailService");
        
        MaWoSchedDetailDTO maWoSchedDetailDTO = maWoSchedDetailForm.getMaWoSchedDetailDTO();
        
        maWoSchedDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoSchedDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoSchedDetailService.updateDetail(maWoSchedDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoSchedDetailForm
     * @param request
     */
    private void inputDetail(MaWoSchedDetailForm maWoSchedDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoSchedDetailService maWoSchedDetailService = (MaWoSchedDetailService) getBean("maWoSchedDetailService");
        
        MaWoSchedDetailDTO maWoSchedDetailDTO = maWoSchedDetailForm.getMaWoSchedDetailDTO();
        
        maWoSchedDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoSchedDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoSchedDetailService.inputDetail(maWoSchedDetailDTO);
        
        setAjaxStatus(request);
    }
}