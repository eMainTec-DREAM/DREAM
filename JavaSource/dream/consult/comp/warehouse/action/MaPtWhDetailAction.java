package dream.consult.comp.warehouse.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;

import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;
import dream.consult.comp.warehouse.dto.MaPtWhDetailDTO;
import dream.consult.comp.warehouse.form.MaPtWhDetailForm;
import dream.consult.comp.warehouse.service.MaPtWhDetailService;

/**
 * 부품창고 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtWhDetail" name="maPtWhDetailForm"
 *                input="/dream/consult/comp/warehouse/maPtWhDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtWhDetail" path="/dream/consult/comp/warehouse/maPtWhDetail.jsp"
 *                        redirect="false"
 */
public class MaPtWhDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTWH_DETAIL_INIT         = 1001;
    /** 저장 */
    public static final int PTWH_DETAIL_INPUT        = 1002;
    /** 수정 */
    public static final int PTWH_DETAIL_UPDATE       = 1003;
    /** 중복체크 */
    public static final int PTWH_DETAIL_CHECK        = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtWhDetailForm maPtWhDetailForm = (MaPtWhDetailForm) form;
        
        switch (maPtWhDetailForm.getStrutsAction())
        {
            case MaPtWhDetailAction.PTWH_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPtWhDetailForm);
                returnActionForward = mapping.findForward("maPtWhDetail");
                break;
            case MaPtWhDetailAction.PTWH_DETAIL_INPUT:
            	insertDetail(maPtWhDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtWhDetailAction.PTWH_DETAIL_UPDATE:
            	updateDetail(maPtWhDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtWhDetailAction.PTWH_DETAIL_CHECK:
                validPtWh(maPtWhDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtWhDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 자재재고 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtWhDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtWhDetailForm maPtWhDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtWhDetailService maPtWhDetailService = (MaPtWhDetailService)getBean("maPtWhDetailService");
    	
    	MaPtWhCommonDTO maPtWhCommonDTO = maPtWhDetailForm.getMaPtWhCommonDTO();
    	
        // 조회된 상세 부분
        MaPtWhDetailDTO maPtWhDetailDTO = maPtWhDetailService.findDetail(maPtWhCommonDTO,getUser(request));
        
        // 조회된 상세  셋팅한다.
        maPtWhDetailForm.setMaPtWhDetailDTO(maPtWhDetailDTO);
    }
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhDetailForm
     * @param request
     */
    private void insertDetail(MaPtWhDetailForm maPtWhDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtWhDetailService maPtWhDetailService = (MaPtWhDetailService) getBean("maPtWhDetailService");
        
        MaPtWhDetailDTO maPtWhDetailDTO = maPtWhDetailForm.getMaPtWhDetailDTO();
        
        maPtWhDetailService.insertDetail(maPtWhDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhDetailForm
     * @param request
     */
    private void updateDetail(MaPtWhDetailForm maPtWhDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtWhDetailService maPtWhDetailService = (MaPtWhDetailService) getBean("maPtWhDetailService");
        
        MaPtWhDetailDTO maPtWhDetailDTO = maPtWhDetailForm.getMaPtWhDetailDTO();
        
        maPtWhDetailService.updateDetail(maPtWhDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid PtStock
     * @author  
     * @version $Id: $ 
     * @since   1.0
     * 
     * @param maPtWhDetailForm
     * @param request
     */
    private void validPtWh(MaPtWhDetailForm maPtWhDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtWhDetailService maPtWhDetailService = (MaPtWhDetailService) getBean("maPtWhDetailService");
        
        MaPtWhDetailDTO maPtWhDetailDTO = maPtWhDetailForm.getMaPtWhDetailDTO();
        
//        String isValid = maPtWhDetailService.validPtWh(maPtWhDetailDTO);
//        
//        setAjaxDesc(request, isValid);
    }
}