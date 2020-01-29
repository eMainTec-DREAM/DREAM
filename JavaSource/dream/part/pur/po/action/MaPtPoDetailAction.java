package dream.part.pur.po.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.pur.po.dto.MaPtPoCommonDTO;
import dream.part.pur.po.dto.MaPtPoDetailDTO;
import dream.part.pur.po.form.MaPtPoDetailForm;
import dream.part.pur.po.service.MaPtPoDetailService;

/**
 * 발주이력 - 상세 action
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtPoDetail" name="maPtPoDetailForm"
 *                input="/dream/part/pur/po/maPtPoDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtPoDetail" path="/dream/part/pur/po/maPtPoDetail.jsp"
 *                        redirect="false"
 */
public class MaPtPoDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTPO_DETAIL_INIT         = 1001;
    /** 저장 */
    public static final int PTPO_DETAIL_INPUT        = 1002;
    /** 수정 */
    public static final int PTPO_DETAIL_UPDATE       = 1003;
    /** 상태수정 */
    public static final int PTPO_DETAIL_STATUS_UPDATE= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtPoDetailForm maPtPoDetailForm = (MaPtPoDetailForm) form;
        
        switch (maPtPoDetailForm.getStrutsAction())
        {
            case MaPtPoDetailAction.PTPO_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPtPoDetailForm);
                returnActionForward = mapping.findForward("maPtPoDetail");
                break;
            case MaPtPoDetailAction.PTPO_DETAIL_INPUT:
            	insertDetail(maPtPoDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtPoDetailAction.PTPO_DETAIL_UPDATE:
            	updateDetail(maPtPoDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtPoDetailAction.PTPO_DETAIL_STATUS_UPDATE:
                updatePtPoListStatus(maPtPoDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtPoDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 발주이력 상세 조회
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtPoDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtPoDetailForm maPtPoDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService)getBean("maPtPoDetailService");
    	
    	MaPtPoCommonDTO maPtPoCommonDTO = maPtPoDetailForm.getMaPtPoCommonDTO();
    	
        // 조회된 상세 부분
        MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailService.findDetail(maPtPoCommonDTO.getPoListId(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        maPtPoDetailForm.setMaPtPoDetailDTO(maPtPoDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailForm
     * @param request
     */
    private void insertDetail(MaPtPoDetailForm maPtPoDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) getBean("maPtPoDetailService");
        
        MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailForm.getMaPtPoDetailDTO();
        
        maPtPoDetailService.insertDetail(maPtPoDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailForm
     * @param request
     */
    private void updateDetail(MaPtPoDetailForm maPtPoDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) getBean("maPtPoDetailService");
        
        MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailForm.getMaPtPoDetailDTO();
        
        maPtPoDetailService.updateDetail(maPtPoDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail 상태 update
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailForm
     * @param request
     */
    private void updatePtPoListStatus(MaPtPoDetailForm maPtPoDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) getBean("maPtPoDetailService");
        
        MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailForm.getMaPtPoDetailDTO();
        
        maPtPoDetailService.updatePtPoListStatus(maPtPoDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
}