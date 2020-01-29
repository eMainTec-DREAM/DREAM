package dream.part.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrVendorDetailDTO;
import dream.part.list.form.MaPtMstrVendorDetailForm;
import dream.part.list.service.MaPtMstrVendorDetailService;

/**
 * ��ǰ�ŷ�ó
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtMstrVendorDetail" name="maPtMstrVendorDetailForm"
 *                input="/dream/part/list/maPtMstrVendorDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrVendorDetail" path="/dream/part/list/maPtMstrVendorDetail.jsp"
 *                        redirect="false"
 */
public class MaPtMstrVendorDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTMSTR_VENDOR_DETAIL_INIT 	= 1001;
    /** ���� */
    public static final int PTMSTR_VENDOR_DETAIL_UPDATE = 1002;
    /** �Է� */
    public static final int PTMSTR_VENDOR_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrVendorDetailForm maPtMstrVendorDetailForm = (MaPtMstrVendorDetailForm) form;
        switch (maPtMstrVendorDetailForm.getStrutsAction())
        {
            case MaPtMstrVendorDetailAction.PTMSTR_VENDOR_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPtMstrVendorDetailForm);
                returnActionForward = mapping.findForward("maPtMstrVendorDetail");
                break;
            case MaPtMstrVendorDetailAction.PTMSTR_VENDOR_DETAIL_UPDATE:
            	updateDetail(maPtMstrVendorDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrVendorDetailAction.PTMSTR_VENDOR_DETAIL_INPUT:
            	insertDetail(maPtMstrVendorDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPtMstrVendorDetail");
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
     * @param maPtMstrVendorDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtMstrVendorDetailForm maPtMstrVendorDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtMstrVendorDetailService maPtMstrVendorDetailService = (MaPtMstrVendorDetailService)getBean("maPtMstrVendorDetailService");

        // ��ȸ�� �� �κ�
        MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO 
            = maPtMstrVendorDetailService.findDetail(maPtMstrVendorDetailForm.getMaPtMstrCommonDTO(), getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtMstrVendorDetailForm.setMaPtMstrVendorDetailDTO(maPtMstrVendorDetailDTO);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrVendorDetailForm
     * @param request
     */
    private void updateDetail(MaPtMstrVendorDetailForm maPtMstrVendorDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtMstrVendorDetailService maPtMstrVendorDetailService = (MaPtMstrVendorDetailService) getBean("maPtMstrVendorDetailService");
        
        MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO = maPtMstrVendorDetailForm.getMaPtMstrVendorDetailDTO();
        
        maPtMstrVendorDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPtMstrVendorDetailService.updateDetail(maPtMstrVendorDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrVendorDetailForm
     * @param request
     */
    private void insertDetail(MaPtMstrVendorDetailForm maPtMstrVendorDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtMstrVendorDetailService maPtMstrVendorDetailService = (MaPtMstrVendorDetailService) getBean("maPtMstrVendorDetailService");
        
        MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO = maPtMstrVendorDetailForm.getMaPtMstrVendorDetailDTO();
        
        maPtMstrVendorDetailDTO.setCompNo((getUser(request).getCompNo()));
        // ����Id ����
        String partId = maPtMstrVendorDetailForm.getMaPtMstrCommonDTO().getPartId();
        maPtMstrVendorDetailDTO.setPartId(partId);
        
        maPtMstrVendorDetailService.insertDetail(maPtMstrVendorDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}