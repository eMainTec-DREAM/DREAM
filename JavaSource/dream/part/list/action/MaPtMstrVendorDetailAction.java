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
 * 부품거래처
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
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTMSTR_VENDOR_DETAIL_INIT 	= 1001;
    /** 수정 */
    public static final int PTMSTR_VENDOR_DETAIL_UPDATE = 1002;
    /** 입력 */
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
                // 페이지 조회
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
     *  상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrVendorDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtMstrVendorDetailForm maPtMstrVendorDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtMstrVendorDetailService maPtMstrVendorDetailService = (MaPtMstrVendorDetailService)getBean("maPtMstrVendorDetailService");

        // 조회된 상세 부분
        MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO 
            = maPtMstrVendorDetailService.findDetail(maPtMstrVendorDetailForm.getMaPtMstrCommonDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
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
        // 자재Id 구함
        String partId = maPtMstrVendorDetailForm.getMaPtMstrCommonDTO().getPartId();
        maPtMstrVendorDetailDTO.setPartId(partId);
        
        maPtMstrVendorDetailService.insertDetail(maPtMstrVendorDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}