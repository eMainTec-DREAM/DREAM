package dream.mgr.usrrpt.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.MaUserRptParamDetailDTO;
import dream.mgr.usrrpt.form.MaUserRptParamDetailForm;
import dream.mgr.usrrpt.service.MaUserRptParamDetailService;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maUserRptParamDetail" name="maUserRptParamDetailForm"
 *                input="/dream/mgr/usrrpt/maUserRptParamDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptParamDetail" path="/dream/mgr/usrrpt/maUserRptParamDetail.jsp"
 *                        redirect="false"
 */
public class MaUserRptParamDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int USER_PARAM_DETAIL_INIT 	    = 8001;
    /** ���� */
    public static final int USER_PARAM_DETAIL_UPDATE    = 6002;
    /** �Է� */
    public static final int USER_PARAM_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptParamDetailForm maUserRptParamDetailForm = (MaUserRptParamDetailForm) form;
       
        super.updateAudit(maUserRptParamDetailForm.getMaUserRptParamDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maUserRptParamDetailForm.getStrutsAction())
        {
            case MaUserRptParamDetailAction.USER_PARAM_DETAIL_INIT:
                this.findDetail(request, maUserRptParamDetailForm);
                returnActionForward = mapping.findForward("maUserRptParamDetail");
                break;
            case MaUserRptParamDetailAction.USER_PARAM_DETAIL_UPDATE:
            	updateDetail(maUserRptParamDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptParamDetailAction.USER_PARAM_DETAIL_INPUT:
            	insertDetail(maUserRptParamDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maUserRptParamDetail");
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
     * @param maUserRptParamDetailForm
     */
    private void findDetail(HttpServletRequest request, MaUserRptParamDetailForm maUserRptParamDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaUserRptParamDetailService maUserRptParamDetailService = (MaUserRptParamDetailService)getBean("maUserRptParamDetailService");

        // ��ȸ�� �� �κ�
        MaUserRptParamDetailDTO maUserRptParamDetailDTO = maUserRptParamDetailService.findDetail(maUserRptParamDetailForm.getMaUserRptCommonDTO(), getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maUserRptParamDetailForm.setMaUserRptParamDetailDTO(maUserRptParamDetailDTO);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptParamDetailForm
     * @param request
     */
    private void updateDetail(MaUserRptParamDetailForm maUserRptParamDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptParamDetailService maUserRptParamDetailService = (MaUserRptParamDetailService) getBean("maUserRptParamDetailService");
        
        MaUserRptParamDetailDTO maUserRptParamDetailDTO = maUserRptParamDetailForm.getMaUserRptParamDetailDTO();
        
        maUserRptParamDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maUserRptParamDetailService.updateDetail(maUserRptParamDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptParamDetailForm
     * @param request
     */
    private void insertDetail(MaUserRptParamDetailForm maUserRptParamDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptParamDetailService maUserRptParamDetailService = (MaUserRptParamDetailService) getBean("maUserRptParamDetailService");
        
        MaUserRptParamDetailDTO maUserRptParamDetailDTO = maUserRptParamDetailForm.getMaUserRptParamDetailDTO();
        
        maUserRptParamDetailDTO.setCompNo((getUser(request).getCompNo()));
        maUserRptParamDetailService.insertDetail(maUserRptParamDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}