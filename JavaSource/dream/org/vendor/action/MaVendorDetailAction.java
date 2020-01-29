package dream.org.vendor.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.org.vendor.dto.MaVendorDetailDTO;
import dream.org.vendor.form.MaVendorDetailForm;
import dream.org.vendor.service.MaVendorDetailService;

/**
 * ���þ�ü - �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maVendorDetail" name="maVendorDetailForm"
 *                input="/dream/org/vendor/maVendorDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maVendorDetail" path="/dream/org/vendor/maVendorDetail.jsp"
 *                        redirect="false"
 */
public class MaVendorDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int VENDOR_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int VENDOR_DETAIL_INPUT 	= 5002;
    /** ���� */
    public static final int VENDOR_DETAIL_UPDATE 	= 6003;
    /** �ߺ� üũ */
    public static final int VENDOR_DETAIL_CHECK 	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaVendorDetailForm maVendorDetailForm = (MaVendorDetailForm) form;
        
        super.updateAudit(maVendorDetailForm.getMaVendorDetailDTO().getAuditKey()==""?maVendorDetailForm.getMaVendorCommonDTO().getAuditKey():maVendorDetailForm.getMaVendorDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maVendorDetailForm.getStrutsAction())
        {
            case MaVendorDetailAction.VENDOR_DETAIL_INIT:
                this.findDetail(request, maVendorDetailForm);
                returnActionForward = mapping.findForward("maVendorDetail");
                break;
            case MaVendorDetailAction.VENDOR_DETAIL_INPUT:
            	insertDetail(maVendorDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaVendorDetailAction.VENDOR_DETAIL_UPDATE:
            	updateDetail(maVendorDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaVendorDetailAction.VENDOR_DETAIL_CHECK:
                validVendorNo(maVendorDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maVendorDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���þ�ü �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maVendorDetailForm
     */
    private void findDetail(HttpServletRequest request, MaVendorDetailForm maVendorDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaVendorDetailService maVendorDetailService = (MaVendorDetailService)getBean("maVendorDetailService");

        // GET Parameter
        //String compNo = maVendorDetailForm.getMaVendorCommonDTO().getCompNo();
    	User user = getUser(request);
        String vendorId = maVendorDetailForm.getMaVendorCommonDTO().getVendorId();
        
        // ��ȸ�� �� �κ�
        MaVendorDetailDTO maVendorDetailDTO = maVendorDetailService.findDetail(user.getCompNo(), vendorId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maVendorDetailForm.setMaVendorDetailDTO(maVendorDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maVendorDetailForm
     * @param request
     */
    private void insertDetail(MaVendorDetailForm maVendorDetailForm, HttpServletRequest request) throws Exception
    {
        MaVendorDetailService maVendorDetailService = (MaVendorDetailService) getBean("maVendorDetailService");
        
        MaVendorDetailDTO maVendorDetailDTO = maVendorDetailForm.getMaVendorDetailDTO();
        
        maVendorDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maVendorDetailService.insertDetail(maVendorDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorDetailForm
     * @param request
     */
    private void updateDetail(MaVendorDetailForm maVendorDetailForm, HttpServletRequest request) throws Exception
    {
    	MaVendorDetailService maVendorDetailService = (MaVendorDetailService) getBean("maVendorDetailService");
        
        MaVendorDetailDTO maVendorDetailDTO = maVendorDetailForm.getMaVendorDetailDTO();
        
        maVendorDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maVendorDetailService.updateDetail(maVendorDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * valid no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorDetailForm
     * @param request
     */
    private void validVendorNo(MaVendorDetailForm maVendorDetailForm, HttpServletRequest request) throws Exception
    {
    	MaVendorDetailService maVendorDetailService = (MaVendorDetailService) getBean("maVendorDetailService");
        
        MaVendorDetailDTO maVendorDetailDTO = maVendorDetailForm.getMaVendorDetailDTO();
        
        maVendorDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maVendorDetailService.validVendorNo(maVendorDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
    
}