package dream.org.emp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertDetailDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;
import dream.org.emp.form.OrgEmpCertDetailForm;
import dream.org.emp.service.OrgEmpCertDetailService;

/**
 * ���Ž�û item ��
 * @author  kim21017
 * @version $Id: OrgEmpCertDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/orgEmpCertDetail" name="orgEmpCertDetailForm"
 *                input="/dream/org/emp/orgEmpCertDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="orgEmpCertDetail" path="/dream/org/emp/orgEmpCertDetail.jsp"
 *                        redirect="false"
 */
public class OrgEmpCertDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int BUY_ITEM_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int BUY_ITEM_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int BUY_ITEM_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        OrgEmpCertDetailForm orgEmpCertDetailForm = (OrgEmpCertDetailForm) form;
        
        super.updateAudit(orgEmpCertDetailForm.getOrgEmpCertDetailDTO().getAuditKey()==""?orgEmpCertDetailForm.getOrgEmpCertListDTO().getAuditKey():orgEmpCertDetailForm.getOrgEmpCertDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (orgEmpCertDetailForm.getStrutsAction())
        {
            case OrgEmpCertDetailAction.BUY_ITEM_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, orgEmpCertDetailForm);
                returnActionForward = mapping.findForward("orgEmpCertDetail");
                break;
            case OrgEmpCertDetailAction.BUY_ITEM_DETAIL_UPDATE:
            	updateDetail(orgEmpCertDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case OrgEmpCertDetailAction.BUY_ITEM_DETAIL_INPUT:
            	insertDetail(orgEmpCertDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("orgEmpCertDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���Ž�û item ��ȸ
     * @author kim2107
     * @version $Id: OrgEmpCertDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param orgEmpCertDetailForm
     */
    private void findDetail(HttpServletRequest request, OrgEmpCertDetailForm orgEmpCertDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	OrgEmpCertDetailService orgEmpCertDetailService = (OrgEmpCertDetailService)getBean("orgEmpCertDetailService");
    	MaEmpCommonDTO maEmpCommonDTO = orgEmpCertDetailForm.getMaEmpCommonDTO();
    	OrgEmpCertListDTO orgEmpCertListDTO = orgEmpCertDetailForm.getOrgEmpCertListDTO();
    	maEmpCommonDTO.setCompNo(getUser(request).getCompNo());
    	maEmpCommonDTO.setUserLang(getUser(request).getLangId());
        // ��ȸ�� �� �κ�
        OrgEmpCertDetailDTO orgEmpCertDetailDTO = orgEmpCertDetailService.findDetail(orgEmpCertListDTO,maEmpCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        orgEmpCertDetailForm.setOrgEmpCertDetailDTO(orgEmpCertDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: OrgEmpCertDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpCertDetailForm
     * @param request
     */
    private void updateDetail(OrgEmpCertDetailForm orgEmpCertDetailForm, HttpServletRequest request) throws Exception
    {
    	OrgEmpCertDetailService orgEmpCertDetailService = (OrgEmpCertDetailService) getBean("orgEmpCertDetailService");
        
        OrgEmpCertDetailDTO orgEmpCertDetailDTO = orgEmpCertDetailForm.getOrgEmpCertDetailDTO();
        MaEmpCommonDTO maEmpCommonDTO = orgEmpCertDetailForm.getMaEmpCommonDTO();
    	maEmpCommonDTO.setCompNo(getUser(request).getCompNo());
        
        orgEmpCertDetailService.updateDetail(orgEmpCertDetailDTO,maEmpCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: OrgEmpCertDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpCertDetailForm
     * @param request
     */
    private void insertDetail(OrgEmpCertDetailForm orgEmpCertDetailForm, HttpServletRequest request) throws Exception
    {
    	OrgEmpCertDetailService orgEmpCertDetailService = (OrgEmpCertDetailService) getBean("orgEmpCertDetailService");
        
        OrgEmpCertDetailDTO orgEmpCertDetailDTO = orgEmpCertDetailForm.getOrgEmpCertDetailDTO();
        
        MaEmpCommonDTO maEmpCommonDTO = orgEmpCertDetailForm.getMaEmpCommonDTO();
    	maEmpCommonDTO.setCompNo(getUser(request).getCompNo());
        
        orgEmpCertDetailService.insertDetail(orgEmpCertDetailDTO, maEmpCommonDTO);
        
        setAjaxStatus(request);
    }
}