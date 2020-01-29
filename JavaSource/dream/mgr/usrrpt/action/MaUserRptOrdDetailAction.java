package dream.mgr.usrrpt.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.MaUserRptOrdDetailDTO;
import dream.mgr.usrrpt.form.MaUserRptOrdDetailForm;
import dream.mgr.usrrpt.service.MaUserRptOrdDetailService;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maUserRptOrdDetail" name="maUserRptOrdDetailForm"
 *                input="/dream/mgr/usrrpt/maUserRptOrdDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptOrdDetail" path="/dream/mgr/usrrpt/maUserRptOrdDetail.jsp"
 *                        redirect="false"
 */
public class MaUserRptOrdDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int USER_ORD_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int USER_ORD_DETAIL_UPDATE  = 6002;
    /** �Է� */
    public static final int USER_ORD_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptOrdDetailForm maUserRptOrdDetailForm = (MaUserRptOrdDetailForm) form;
        
        super.updateAudit(maUserRptOrdDetailForm.getMaUserRptOrdDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maUserRptOrdDetailForm.getStrutsAction())
        {
            case MaUserRptOrdDetailAction.USER_ORD_DETAIL_INIT:
                this.findDetail(request, maUserRptOrdDetailForm);
                returnActionForward = mapping.findForward("maUserRptOrdDetail");
                break;
            case MaUserRptOrdDetailAction.USER_ORD_DETAIL_UPDATE:
            	updateDetail(maUserRptOrdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptOrdDetailAction.USER_ORD_DETAIL_INPUT:
            	insertDetail(maUserRptOrdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maUserRptOrdDetail");
                break;
        }

        return returnActionForward;
    }

	/**
     *  �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptOrdDetailForm
     */
    private void findDetail(HttpServletRequest request, MaUserRptOrdDetailForm maUserRptOrdDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaUserRptOrdDetailService maUserRptOrdDetailService = (MaUserRptOrdDetailService)getBean("maUserRptOrdDetailService");

        // ��ȸ�� �� �κ�
        MaUserRptOrdDetailDTO maUserRptOrdDetailDTO = maUserRptOrdDetailService.findDetail(maUserRptOrdDetailForm.getMaUserRptCommonDTO(), getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maUserRptOrdDetailForm.setMaUserRptOrdDetailDTO(maUserRptOrdDetailDTO);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptOrdDetailForm
     * @param request
     */
    private void updateDetail(MaUserRptOrdDetailForm maUserRptOrdDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptOrdDetailService maUserRptOrdDetailService = (MaUserRptOrdDetailService) getBean("maUserRptOrdDetailService");
        
        MaUserRptOrdDetailDTO maUserRptOrdDetailDTO = maUserRptOrdDetailForm.getMaUserRptOrdDetailDTO();
        
        maUserRptOrdDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maUserRptOrdDetailService.updateDetail(maUserRptOrdDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptOrdDetailForm
     * @param request
     */
    private void insertDetail(MaUserRptOrdDetailForm maUserRptOrdDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptOrdDetailService maUserRptOrdDetailService = (MaUserRptOrdDetailService) getBean("maUserRptOrdDetailService");
        
        MaUserRptOrdDetailDTO maUserRptOrdDetailDTO = maUserRptOrdDetailForm.getMaUserRptOrdDetailDTO();
        
        maUserRptOrdDetailDTO.setCompNo((getUser(request).getCompNo()));
        maUserRptOrdDetailService.insertDetail(maUserRptOrdDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}