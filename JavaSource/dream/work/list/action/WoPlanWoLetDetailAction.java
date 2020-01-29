package dream.work.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;
import dream.work.list.form.WoPlanWoLetDetailForm;
import dream.work.list.service.WoPlanWoLetDetailService;

/**
 * 작업계획목록 - 안전작업 상세
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @struts:action path="/woPlanWoLetDetail" name="woPlanWoLetDetailForm"
 *                input="/dream/work/list/woPlanWoLetDetail.jsp" scope="request"
 *                validate="false"
 */
public class WoPlanWoLetDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_PLAN_WO_LET_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int WO_PLAN_WO_LET_DETAIL_UPDATE 	= 6001;
    /** 입력 */
    public static final int WO_PLAN_WO_LET_DETAIL_INPUT 	= 5001;
    /** 같은 WO 중복 안전작업 검사 */
    public static final int WO_PLAN_WO_LET_DETAIL_CHECK		= 8002;
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WoPlanWoLetDetailForm woPlanWoLetDetailForm = (WoPlanWoLetDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") woPlanWoLetDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (woPlanWoLetDetailForm.getStrutsAction())
        {
            case WoPlanWoLetDetailAction.WO_PLAN_WO_LET_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, woPlanWoLetDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WoPlanWoLetDetailAction.WO_PLAN_WO_LET_DETAIL_UPDATE:
            	updateDetail(woPlanWoLetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanWoLetDetailAction.WO_PLAN_WO_LET_DETAIL_INPUT:
            	insertDetail(woPlanWoLetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanWoLetDetailAction.WO_PLAN_WO_LET_DETAIL_CHECK:
            	validWoLet(woPlanWoLetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업계획목록 - 안전작업 상세 조회
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanWoLetDetailForm
     */
    private void findDetail(HttpServletRequest request, WoPlanWoLetDetailForm woPlanWoLetDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WoPlanWoLetDetailService woPlanWoLetDetailService = (WoPlanWoLetDetailService)getBean("woPlanWoLetDetailService");

    	// 작업결과Id 구함
        String wkOrId = woPlanWoLetDetailForm.getWoPlanCommonDTO().getWkOrId();
        // 넘겨진 안전작업유형id 구함
        String woWoLetListId = woPlanWoLetDetailForm.getWoPlanWoLetListDTO().getWoWoLetListId();
        
        // 조회된 상세 부분
        WoPlanWoLetDetailDTO woPlanWoLetDetailDTO = woPlanWoLetDetailService.findDetail(wkOrId, woWoLetListId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        woPlanWoLetDetailForm.setWoPlanWoLetDetailDTO(woPlanWoLetDetailDTO);
    }
    
    /**
     * detail update
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanWoLetDetailForm
     * @param request
     */
    private void updateDetail(WoPlanWoLetDetailForm woPlanWoLetDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanWoLetDetailService woPlanWoLetDetailService = (WoPlanWoLetDetailService) getBean("woPlanWoLetDetailService");
        
        WoPlanWoLetDetailDTO woPlanWoLetDetailDTO = woPlanWoLetDetailForm.getWoPlanWoLetDetailDTO();
        WoPlanCommonDTO woPlanCommonDTO = woPlanWoLetDetailForm.getWoPlanCommonDTO();
        woPlanWoLetDetailDTO.setEnterBy(getUser(request).getUserId());
        woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanWoLetDetailService.updateDetail(woPlanCommonDTO,woPlanWoLetDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanWoLetDetailForm
     * @param request
     */
    private void insertDetail(WoPlanWoLetDetailForm woPlanWoLetDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanWoLetDetailService woPlanWoLetDetailService = (WoPlanWoLetDetailService) getBean("woPlanWoLetDetailService");
        
        WoPlanWoLetDetailDTO woPlanWoLetDetailDTO = woPlanWoLetDetailForm.getWoPlanWoLetDetailDTO();
        
        WoPlanCommonDTO woPlanCommonDTO = woPlanWoLetDetailForm.getWoPlanCommonDTO();
        woPlanWoLetDetailDTO.setEnterBy(getUser(request).getUserId());
        woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanWoLetDetailService.insertDetail(woPlanCommonDTO,woPlanWoLetDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * 안전작업 중복 검사
     */
    private void validWoLet(WoPlanWoLetDetailForm woPlanWoLetDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanWoLetDetailService woPlanWoLetDetailService = (WoPlanWoLetDetailService) getBean("woPlanWoLetDetailService");
        
        WoPlanWoLetDetailDTO woPlanWoLetDetailDTO = woPlanWoLetDetailForm.getWoPlanWoLetDetailDTO();
        WoPlanCommonDTO woPlanCommonDTO = woPlanWoLetDetailForm.getWoPlanCommonDTO();
        
        String isValid = woPlanWoLetDetailService.validWoLet(woPlanCommonDTO, woPlanWoLetDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
}