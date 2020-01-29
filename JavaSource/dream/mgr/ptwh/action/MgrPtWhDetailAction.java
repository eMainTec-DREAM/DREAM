package dream.mgr.ptwh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhDetailDTO;
import dream.mgr.ptwh.form.MgrPtWhDetailForm;
import dream.mgr.ptwh.service.MgrPtWhDetailService;

/**
 * ��ǰâ�� - Detail Action
 * 
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/mgrPtWhDetail" name="mgrPtWhDetailForm"
 *                input="/dream/mgr/ptwh/mgrPtWhDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPtWhDetail" path="/dream/mgr/ptwh/mgrPtWhDetail.jsp"
 *                        redirect="false"
 */
public class MgrPtWhDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int DETAIL_UPDATE 		= 6002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrPtWhDetailForm mgrPtWhDetailForm = (MgrPtWhDetailForm) form;
        
        super.updateAudit(mgrPtWhDetailForm.getMgrPtWhDetailDTO().getAuditKey()==""?mgrPtWhDetailForm.getMgrPtWhCommonDTO().getAuditKey():mgrPtWhDetailForm.getMgrPtWhDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrPtWhDetailForm.getStrutsAction())
        {
            case MgrPtWhDetailAction.DETAIL_INIT:
                this.findDetail(request, mgrPtWhDetailForm);
                returnActionForward = mapping.findForward("mgrPtWhDetail");
                break;
            case MgrPtWhDetailAction.DETAIL_UPDATE:
            	updateDetail(request, mgrPtWhDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrPtWhDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ǰâ�� �� ��ȸ
     * @param request
     * @param response
     * @param mgrPtWhDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, MgrPtWhDetailForm mgrPtWhDetailForm) throws Exception 
    {   
    	MgrPtWhDetailService mgrPtWhDetailService = (MgrPtWhDetailService)getBean("mgrPtWhDetailService");
    	
    	MgrPtWhCommonDTO mgrPtWhCommonDTO = mgrPtWhDetailForm.getMgrPtWhCommonDTO(); 
    	
    	// ��ȸ�� �󼼺κ�
    	MgrPtWhDetailDTO mgrPtWhDetailDTO = mgrPtWhDetailService.findDetail(mgrPtWhCommonDTO, getUser(request));
    	
    	// ��ȸ�� �� ����
    	mgrPtWhDetailForm.setMgrPtWhDetailDTO(mgrPtWhDetailDTO);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param mgrPtWhDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, MgrPtWhDetailForm mgrPtWhDetailForm) throws Exception
    {
    	MgrPtWhDetailService mgrPtWhDetailService = (MgrPtWhDetailService)getBean("mgrPtWhDetailService");
    	MgrPtWhDetailDTO  mgrPtWhDetailDTO = mgrPtWhDetailForm.getMgrPtWhDetailDTO();
    	
    	mgrPtWhDetailService.updatePtWhDetail(mgrPtWhDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}