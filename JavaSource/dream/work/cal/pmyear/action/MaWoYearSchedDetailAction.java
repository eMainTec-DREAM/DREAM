package dream.work.cal.pmyear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.cal.pmyear.dto.MaWoYearSchedDetailDTO;
import dream.work.cal.pmyear.form.MaWoYearSchedDetailForm;
import dream.work.cal.pmyear.service.MaWoYearSchedDetailService;

/**
 * �����۾����� - �� action
 * 
 * @author kim2107
 * @version $Id: MaWoYearSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maWoYearSchedDetail" name="maWoYearSchedDetailForm"
 *                input="/dream/work/cal/pmyear/maWoYearSchedDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoYearSchedDetail" path="/dream/work/cal/pmyear/maWoYearSchedDetail.jsp"
 *                        redirect="false"
 */
public class MaWoYearSchedDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int YEAR_SCHED_DETAIL_INIT			= 1001;
    /** ���� */
    public static final int YEAR_SCHED_DETAIL_UPDATE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoYearSchedDetailForm maWoYearSchedDetailForm = (MaWoYearSchedDetailForm) form;
        
        switch (maWoYearSchedDetailForm.getStrutsAction())
        {
            case MaWoYearSchedDetailAction.YEAR_SCHED_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoYearSchedDetailForm);
                returnActionForward = mapping.findForward("maWoYearSchedDetail");
                break;
            case MaWoYearSchedDetailAction.YEAR_SCHED_DETAIL_UPDATE:
            	updateDetail(maWoYearSchedDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maWoYearSchedDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �����۾����� �� ��ȸ
     * @author kim2107
     * @version $Id: MaWoYearSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoYearSchedDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoYearSchedDetailForm maWoYearSchedDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoYearSchedDetailService maWoYearSchedDetailService = (MaWoYearSchedDetailService)getBean("maWoYearSchedDetailService");

        // �Ѱ��� PM������Id ����
        String pmSchedId = maWoYearSchedDetailForm.getMaWoYearSchedCommonDTO().getPmSchedId();
        
        // ��ȸ�� �� �κ�
        MaWoYearSchedDetailDTO maWoYearSchedDetailDTO = maWoYearSchedDetailService.findDetail(pmSchedId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maWoYearSchedDetailForm.setMaWoYearSchedDetailDTO(maWoYearSchedDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoYearSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoYearSchedDetailForm
     * @param request
     */
    private void updateDetail(MaWoYearSchedDetailForm maWoYearSchedDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoYearSchedDetailService maWoYearSchedDetailService = (MaWoYearSchedDetailService) getBean("maWoYearSchedDetailService");
        
        MaWoYearSchedDetailDTO maWoYearSchedDetailDTO = maWoYearSchedDetailForm.getMaWoYearSchedDetailDTO();
        
        maWoYearSchedDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoYearSchedDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoYearSchedDetailService.updateDetail(maWoYearSchedDetailDTO);
        
        setAjaxStatus(request);
    }
}