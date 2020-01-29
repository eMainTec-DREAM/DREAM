package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.form.WoPlanDetailForm;
import dream.work.list.service.WoPlanDetailService;

/**
 * 작업계획목록 - 상세 action
 * 
 * @author kim2107
 * @version $Id: WoPlanDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/woPlanDetail" name="woPlanDetailForm"
 *                input="/dream/work/list/woPlanDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="woPlanDetail" path=/dream/work/list/woPlanDetail.jsp"
 *                        redirect="false"
 */
public class WoPlanDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_PLAN_DETAIL_INIT 			= 8001;
    /** 저장 */
    public static final int WO_PLAN_DETAIL_INPUT 			= 5002;
    /** 수정 */
    public static final int WO_PLAN_DETAIL_UPDATE 			= 6003;
    /** 완료 */
    public static final int WO_PLAN_DETAIL_COMPLETE 		= 6004;
    /** 점검항목 검사 */
    public static final int WO_PLAN_DETAIL_CHECKPOINT		= 8006;
    
    public static final int WO_PLAN_FIND_PAGE       		= 1011;
    /** 작업계획목록 존재여부 검사 */
    public static final int WO_PLAN_CHECK       	    	= 1012;
    /** 완료취소 */
    public static final int WO_PLAN_DETAIL_COMPLETE_CANCEL	= 1013;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WoPlanDetailForm woPlanDetailForm = (WoPlanDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") woPlanDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(woPlanDetailForm.getWoPlanDetailDTO().getAuditKey()==""?woPlanDetailForm.getWoPlanCommonDTO().getAuditKey():woPlanDetailForm.getWoPlanDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (woPlanDetailForm.getStrutsAction())
        {
            case WoPlanDetailAction.WO_PLAN_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, woPlanDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WoPlanDetailAction.WO_PLAN_DETAIL_INPUT:
            	insertDetail(woPlanDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanDetailAction.WO_PLAN_DETAIL_UPDATE:
            	updateDetail(woPlanDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanDetailAction.WO_PLAN_DETAIL_COMPLETE:
                completeDetail(woPlanDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WoPlanDetailAction.WO_PLAN_DETAIL_CHECKPOINT:
            	checkPoint(woPlanDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanDetailAction.WO_PLAN_DETAIL_COMPLETE_CANCEL:
            	reverseWoPlan(woPlanDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanDetailAction.WO_PLAN_CHECK:
                woPlanCheck(woPlanDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

	private void woPlanCheck(WoPlanDetailForm woPlanDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	    WoPlanDetailService woPlanDetailService = (WoPlanDetailService)getBean("woPlanDetailService");

	    WoPlanCommonDTO woPlanCommonDTO = woPlanDetailForm.getWoPlanCommonDTO();
        
        String isExist = woPlanDetailService.woPlanCheck(woPlanCommonDTO, getUser(request));
        
        String jsonStr = "{\"isExist\":\""+isExist+"\"}";
        
        response.getWriter().print(jsonStr);
    }

    /**
     * 작업계획목록 상세 조회
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanDetailForm
     */
    private void findDetail(HttpServletRequest request, WoPlanDetailForm woPlanDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WoPlanDetailService woPlanDetailService = (WoPlanDetailService)getBean("woPlanDetailService");

    	WoPlanCommonDTO woPlanCommonDTO = woPlanDetailForm.getWoPlanCommonDTO();
        // 조회된 상세 부분
        WoPlanDetailDTO woPlanDetailDTO = woPlanDetailService.findDetail(woPlanCommonDTO, getUser(request));
        // 조회된 상세  셋팅한다.
        woPlanDetailForm.setWoPlanDetailDTO(woPlanDetailDTO);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanDetailForm
     * @param request
     */
    private void insertDetail(WoPlanDetailForm woPlanDetailForm, HttpServletRequest request) throws Exception
    {
        WoPlanDetailService woPlanDetailService = (WoPlanDetailService) getBean("woPlanDetailService");
        
        WoPlanDetailDTO woPlanDetailDTO = woPlanDetailForm.getWoPlanDetailDTO();
        WoPlanCommonDTO woPlanCommonDTO = woPlanDetailForm.getWoPlanCommonDTO();
    	woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanDetailDTO.setEnterBy(getUser(request).getUserId());
        woPlanDetailDTO.setCompNo(getUser(request).getCompNo());
        
        User loginUser = getUser(request);
        
        woPlanDetailService.insertDetail(woPlanDetailDTO, woPlanCommonDTO, loginUser);
        
        woPlanDetailService.insertWoEquip(woPlanDetailDTO, loginUser);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanDetailForm
     * @param request
     */
    private void updateDetail(WoPlanDetailForm woPlanDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanDetailService woPlanDetailService = (WoPlanDetailService) getBean("woPlanDetailService");
        
        WoPlanDetailDTO woPlanDetailDTO = woPlanDetailForm.getWoPlanDetailDTO();
        
        woPlanDetailService.updateDetail(woPlanDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail complete
     * @author  kim21017
     * @version $Id: WoPlanDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param woPlanDetailForm
     * @param request
     */
    private void completeDetail(WoPlanDetailForm woPlanDetailForm, HttpServletRequest request) throws Exception
    {
        WoPlanDetailService woPlanDetailService = (WoPlanDetailService) getBean("woPlanDetailService",request);
        
        WoPlanDetailDTO woPlanDetailDTO = woPlanDetailForm.getWoPlanDetailDTO();
        
        woPlanDetailDTO.setEnterBy(getUser(request).getUserId());
        woPlanDetailDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanDetailService.completeDetail(woPlanDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void checkPoint(WoPlanDetailForm woPlanDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanDetailService woPlanDetailService = (WoPlanDetailService) getBean("woPlanDetailService");
    	
    	WoPlanDetailDTO woPlanDetailDTO = woPlanDetailForm.getWoPlanDetailDTO();
    	
    	String isValid = woPlanDetailService.checkPoint(woPlanDetailDTO, getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
    /**
     * CANCEL COMPLETE DETAIL
     * @author  nhkim8548
     * @version $Id: WoPlanDetailAction.java, v1.0 2019/11/14 08:30:30 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param woPlanDetailForm
     * @param request
     */
    private void reverseWoPlan(WoPlanDetailForm woPlanDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanDetailService woPlanDetailService = (WoPlanDetailService) getBean("woPlanDetailService", request);
    	
    	WoPlanDetailDTO woPlanDetailDTO = woPlanDetailForm.getWoPlanDetailDTO();
    	
    	String isValid = woPlanDetailService.reverseWoPlan(woPlanDetailDTO, getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
}