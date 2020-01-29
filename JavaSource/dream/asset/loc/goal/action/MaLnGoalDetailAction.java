package dream.asset.loc.goal.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.loc.goal.dto.MaLnGoalDetailDTO;
import dream.asset.loc.goal.form.MaLnGoalDetailForm;
import dream.asset.loc.goal.service.MaLnGoalDetailService;

/**
 * �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maLnGoalDetail" name="maLnGoalDetailForm"
 *                input="/dream/asset/loc/goal/maLnGoalDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLnGoalDetail" path="/dream/asset/loc/goal/maLnGoalDetail.jsp"
 *                        redirect="false"
 */
public class MaLnGoalDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int MTGOAL_DETAIL_INIT 		    = 8001;
    /** ���� */
    public static final int MTGOAL_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int MTGOAL_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaLnGoalDetailForm maLnGoalDetailForm = (MaLnGoalDetailForm) form;
        
        super.updateAudit(maLnGoalDetailForm.getMaLnGoalDetailDTO().getAuditKey()==""?maLnGoalDetailForm.getMaLnGoalCommonDTO().getAuditKey():maLnGoalDetailForm.getMaLnGoalDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maLnGoalDetailForm.getStrutsAction())
        {
            case MaLnGoalDetailAction.MTGOAL_DETAIL_INIT:
                this.findDetail(request, maLnGoalDetailForm);
                returnActionForward = mapping.findForward("maLnGoalDetail");
                break;
            case MaLnGoalDetailAction.MTGOAL_DETAIL_INPUT:
            	insertDetail(maLnGoalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaLnGoalDetailAction.MTGOAL_DETAIL_UPDATE:
            	updateDetail(maLnGoalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maLnGoalDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * ��������з�(������) �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maLnGoalDetailForm
     */
    private void findDetail(HttpServletRequest request, MaLnGoalDetailForm maLnGoalDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaLnGoalDetailService maLnGoalDetailService = (MaLnGoalDetailService)getBean("maLnGoalDetailService");

        // �Ѱ��� �μ��ڵ� ����
//        String compNo = maLnGoalDetailForm.getMaLnGoalCommonDTO().getCompNo();
//        String partId = maLnGoalDetailForm.getMaLnGoalCommonDTO().getPartId();
        
        // ��ȸ�� �� �κ�
        MaLnGoalDetailDTO maLnGoalDetailDTO = maLnGoalDetailService.findDetail(maLnGoalDetailForm.getMaLnGoalCommonDTO(), getUser(request));

        // ��ȸ�� ��  �����Ѵ�.
        maLnGoalDetailForm.setMaLnGoalDetailDTO(maLnGoalDetailDTO);
    }
    
    
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maLnGoalDetailForm
     * @param request
     */
    private void insertDetail(MaLnGoalDetailForm maLnGoalDetailForm, HttpServletRequest request) throws Exception
    {
        MaLnGoalDetailService maLnGoalDetailService = (MaLnGoalDetailService) getBean("maLnGoalDetailService");
        
        MaLnGoalDetailDTO maLnGoalDetailDTO = maLnGoalDetailForm.getMaLnGoalDetailDTO();
        
        maLnGoalDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maLnGoalDetailService.insertDetail(maLnGoalDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalDetailForm
     * @param request
     */
    private void updateDetail(MaLnGoalDetailForm maLnGoalDetailForm, HttpServletRequest request) throws Exception
    {
    	MaLnGoalDetailService maLnGoalDetailService = (MaLnGoalDetailService) getBean("maLnGoalDetailService");
        
        MaLnGoalDetailDTO maLnGoalDetailDTO = maLnGoalDetailForm.getMaLnGoalDetailDTO();
        
        maLnGoalDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maLnGoalDetailService.updateDetail(maLnGoalDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}