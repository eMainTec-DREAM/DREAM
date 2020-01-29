package dream.work.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;
import dream.work.list.form.WoPlanPartDetailForm;
import dream.work.list.service.WoPlanPartDetailService;

/**
 * 작업계획목록 - 투입부품 상세
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/woPlanPartDetail" name="woPlanPartDetailForm"
 *                input="/dream/work/list/woPlanPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="woPlanPartDetail" path="/dream/work/list/woPlanPartDetail.jsp"
 *                        redirect="false"
 */
public class WoPlanPartDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_PART_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int WO_RESULT_PART_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int WO_RESULT_PART_DETAIL_INPUT 	= 5003;
    /** 재고확인 */
    public static final int WO_RESULT_PART_STOCK_CHECK		= 8004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WoPlanPartDetailForm woPlanPartDetailForm = (WoPlanPartDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") woPlanPartDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (woPlanPartDetailForm.getStrutsAction())
        {
            case WoPlanPartDetailAction.WO_RESULT_PART_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, woPlanPartDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WoPlanPartDetailAction.WO_RESULT_PART_DETAIL_UPDATE:
            	updateDetail(woPlanPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanPartDetailAction.WO_RESULT_PART_DETAIL_INPUT:
            	insertDetail(woPlanPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanPartDetailAction.WO_RESULT_PART_STOCK_CHECK:
            	getStockQty(woPlanPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업계획목록 - 투입부품 상세 조회
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanPartDetailForm
     */
    private void findDetail(HttpServletRequest request, WoPlanPartDetailForm woPlanPartDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WoPlanPartDetailService woPlanPartDetailService = (WoPlanPartDetailService)getBean("woPlanPartDetailService");

    	// 작업결과Id 구함
        String wkOrId = woPlanPartDetailForm.getWoPlanCommonDTO().getWkOrId();
        // 넘겨진 투입자재id 구함
        String woPartId = woPlanPartDetailForm.getWoPlanPartListDTO().getWoPartId();
        
        // 조회된 상세 부분
        WoPlanPartDetailDTO woPlanPartDetailDTO = woPlanPartDetailService.findDetail(wkOrId, woPartId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        woPlanPartDetailForm.setWoPlanPartDetailDTO(woPlanPartDetailDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanPartDetailForm
     * @param request
     */
    private void updateDetail(WoPlanPartDetailForm woPlanPartDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanPartDetailService woPlanPartDetailService = (WoPlanPartDetailService) getBean("woPlanPartDetailService");
        
        WoPlanPartDetailDTO woPlanPartDetailDTO = woPlanPartDetailForm.getWoPlanPartDetailDTO();
        WoPlanCommonDTO woPlanCommonDTO = woPlanPartDetailForm.getWoPlanCommonDTO();
        woPlanPartDetailDTO.setEnterBy(getUser(request).getUserId());
        woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanPartDetailService.updateDetail(woPlanPartDetailDTO,woPlanCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanPartDetailForm
     * @param request
     */
    private void insertDetail(WoPlanPartDetailForm woPlanPartDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanPartDetailService woPlanPartDetailService = (WoPlanPartDetailService) getBean("woPlanPartDetailService");
        
        WoPlanPartDetailDTO woPlanPartDetailDTO = woPlanPartDetailForm.getWoPlanPartDetailDTO();
        
        WoPlanCommonDTO woPlanCommonDTO = woPlanPartDetailForm.getWoPlanCommonDTO();
        woPlanPartDetailDTO.setEnterBy(getUser(request).getUserId());
        woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanPartDetailService.insertDetail(woPlanPartDetailDTO, woPlanCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * 재고확인
     */
    private void getStockQty(WoPlanPartDetailForm woPlanPartDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanPartDetailService woPlanPartDetailService = (WoPlanPartDetailService) getBean("woPlanPartDetailService");
        
        WoPlanPartDetailDTO woPlanPartDetailDTO = woPlanPartDetailForm.getWoPlanPartDetailDTO();
        String isValid = woPlanPartDetailService.getStockQty(woPlanPartDetailDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}