package dream.part.pur.buy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;
import dream.part.pur.buy.form.MaPtBuyReqDetailForm;
import dream.part.pur.buy.service.MaPtBuyReqDetailService;

/**
 * 구매신청 item 상세
 * @author  kim21017
 * @version $Id: MaPtBuyReqDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPtBuyReqDetail" name="maPtBuyReqDetailForm"
 *                input="/dream/part/pur/buy/maPtBuyReqDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtBuyReqDetail" path="/dream/part/pur/buy/maPtBuyReqDetail.jsp"
 *                        redirect="false"
 */
public class MaPtBuyReqDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int BUY_ITEM_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int BUY_ITEM_DETAIL_UPDATE 		= 1002;
    /** 입력 */
    public static final int BUY_ITEM_DETAIL_INPUT 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtBuyReqDetailForm maPtBuyReqDetailForm = (MaPtBuyReqDetailForm) form;
        switch (maPtBuyReqDetailForm.getStrutsAction())
        {
            case MaPtBuyReqDetailAction.BUY_ITEM_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPtBuyReqDetailForm);
                returnActionForward = mapping.findForward("maPtBuyReqDetail");
                break;
            case MaPtBuyReqDetailAction.BUY_ITEM_DETAIL_UPDATE:
            	updateDetail(maPtBuyReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtBuyReqDetailAction.BUY_ITEM_DETAIL_INPUT:
            	insertDetail(maPtBuyReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPtBuyReqDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 구매신청 item 조회
     * @author kim2107
     * @version $Id: MaPtBuyReqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPtBuyReqDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtBuyReqDetailForm maPtBuyReqDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtBuyReqDetailService maPtBuyReqDetailService = (MaPtBuyReqDetailService)getBean("maPtBuyReqDetailService");
    	MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = maPtBuyReqDetailForm.getMaPtBuyReqHdrCommonDTO();
    	MaPtBuyReqListDTO maPtBuyReqListDTO = maPtBuyReqDetailForm.getMaPtBuyReqListDTO();
    	maPtBuyReqHdrCommonDTO.setCompNo(getUser(request).getCompNo());
    	maPtBuyReqHdrCommonDTO.setUserLang(getUser(request).getLangId());
    	
    	User user = getUser(request);
        // 조회된 상세 부분
        MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = maPtBuyReqDetailService.findDetail(maPtBuyReqListDTO,maPtBuyReqHdrCommonDTO, user);
        
        // 조회된 상세  셋팅한다.
        maPtBuyReqDetailForm.setMaPtBuyReqDetailDTO(maPtBuyReqDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPtBuyReqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqDetailForm
     * @param request
     */
    private void updateDetail(MaPtBuyReqDetailForm maPtBuyReqDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtBuyReqDetailService maPtBuyReqDetailService = (MaPtBuyReqDetailService) getBean("maPtBuyReqDetailService");
        
        MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = maPtBuyReqDetailForm.getMaPtBuyReqDetailDTO();
        MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = maPtBuyReqDetailForm.getMaPtBuyReqHdrCommonDTO();
    	maPtBuyReqHdrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPtBuyReqDetailService.updateDetail(maPtBuyReqDetailDTO,maPtBuyReqHdrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPtBuyReqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqDetailForm
     * @param request
     */
    private void insertDetail(MaPtBuyReqDetailForm maPtBuyReqDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtBuyReqDetailService maPtBuyReqDetailService = (MaPtBuyReqDetailService) getBean("maPtBuyReqDetailService");
        
        MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = maPtBuyReqDetailForm.getMaPtBuyReqDetailDTO();
        
        MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = maPtBuyReqDetailForm.getMaPtBuyReqHdrCommonDTO();
    	maPtBuyReqHdrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPtBuyReqDetailService.insertDetail(maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}