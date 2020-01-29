package dream.part.adj.stktake.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;
import dream.part.adj.stktake.form.PartAdjStkTakeItemDetailForm;
import dream.part.adj.stktake.service.PartAdjStkTakeItemDetailService;

/**
 * 부품실사 item 상세
 * @author  kim21017
 * @version $Id: PartAdjStkTakeItemDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/partAdjStkTakeItemDetail" name="partAdjStkTakeItemDetailForm"
 *                input="/dream/part/adj/stktake/partAdjStkTakeItemDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partAdjStkTakeItemDetail" path="/dream/part/adj/stktake/partAdjStkTakeItemDetail.jsp"
 *                        redirect="false"
 */
public class PartAdjStkTakeItemDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int BUY_ITEM_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int BUY_ITEM_DETAIL_UPDATE 		= 6001;
    /** 입력 */
    public static final int BUY_ITEM_DETAIL_INPUT 		= 5001;
    /** 중복 체크 */
    public static final int BUY_ITEM_DETAIL_CHECK 		= 8002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PartAdjStkTakeItemDetailForm partAdjStkTakeItemDetailForm = (PartAdjStkTakeItemDetailForm) form;
        
        super.updateAudit(partAdjStkTakeItemDetailForm.getPartAdjStkTakeItemDetailDTO().getAuditKey()==""?partAdjStkTakeItemDetailForm.getPartAdjStkTakeCommonDTO().getAuditKey():partAdjStkTakeItemDetailForm.getPartAdjStkTakeItemDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (partAdjStkTakeItemDetailForm.getStrutsAction())
        {
            case PartAdjStkTakeItemDetailAction.BUY_ITEM_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, partAdjStkTakeItemDetailForm);
                returnActionForward = mapping.findForward("partAdjStkTakeItemDetail");
                break;
            case PartAdjStkTakeItemDetailAction.BUY_ITEM_DETAIL_UPDATE:
            	updateDetail(partAdjStkTakeItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartAdjStkTakeItemDetailAction.BUY_ITEM_DETAIL_INPUT:
            	insertDetail(partAdjStkTakeItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartAdjStkTakeItemDetailAction.BUY_ITEM_DETAIL_CHECK:
            	validItem(request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("partAdjStkTakeItemDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 구매신청 item 조회
     * @author kim2107
     * @version $Id: PartAdjStkTakeItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param partAdjStkTakeItemDetailForm
     */
    private void findDetail(HttpServletRequest request, PartAdjStkTakeItemDetailForm partAdjStkTakeItemDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	PartAdjStkTakeItemDetailService partAdjStkTakeItemDetailService = (PartAdjStkTakeItemDetailService)getBean("partAdjStkTakeItemDetailService");
    	PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = partAdjStkTakeItemDetailForm.getPartAdjStkTakeCommonDTO();
    	PartAdjStkTakeListDTO partAdjStkTakeListDTO = partAdjStkTakeItemDetailForm.getPartAdjStkTakeListDTO();
    	partAdjStkTakeCommonDTO.setCompNo(getUser(request).getCompNo());
    	partAdjStkTakeCommonDTO.setUserLang(getUser(request).getLangId());
        // 조회된 상세 부분
        PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO = partAdjStkTakeItemDetailService.findDetail(partAdjStkTakeListDTO,partAdjStkTakeCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        partAdjStkTakeItemDetailForm.setPartAdjStkTakeItemDetailDTO(partAdjStkTakeItemDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: PartAdjStkTakeItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailForm
     * @param request
     */
    private void updateDetail(PartAdjStkTakeItemDetailForm partAdjStkTakeItemDetailForm, HttpServletRequest request) throws Exception
    {
    	PartAdjStkTakeItemDetailService partAdjStkTakeItemDetailService = (PartAdjStkTakeItemDetailService) getBean("partAdjStkTakeItemDetailService");
        
        PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO = partAdjStkTakeItemDetailForm.getPartAdjStkTakeItemDetailDTO();
        PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = partAdjStkTakeItemDetailForm.getPartAdjStkTakeCommonDTO();
        
        partAdjStkTakeItemDetailService.updateDetail(partAdjStkTakeItemDetailDTO,partAdjStkTakeCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: PartAdjStkTakeItemDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailForm
     * @param request
     */
    private void insertDetail(PartAdjStkTakeItemDetailForm partAdjStkTakeItemDetailForm, HttpServletRequest request) throws Exception
    {
    	PartAdjStkTakeItemDetailService partAdjStkTakeItemDetailService = (PartAdjStkTakeItemDetailService) getBean("partAdjStkTakeItemDetailService");
        
        PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO = partAdjStkTakeItemDetailForm.getPartAdjStkTakeItemDetailDTO();
        PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = partAdjStkTakeItemDetailForm.getPartAdjStkTakeCommonDTO();
        
        partAdjStkTakeItemDetailService.insertDetail(partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid part_id/part_grade
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailForm
     * @param request
     */
    private void validItem(HttpServletRequest request) throws Exception
    {
    	PartAdjStkTakeItemDetailService partAdjStkTakeItemDetailService = (PartAdjStkTakeItemDetailService) getBean("partAdjStkTakeItemDetailService");
    	
    	PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO = new PartAdjStkTakeItemDetailDTO();
    	PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = new PartAdjStkTakeCommonDTO();
    	
    	User user = getUser(request);
        
        String ptstktakeitemId = request.getParameterValues("ptstktakeitemId")[0];
        String partId = request.getParameterValues("partId")[0];
        String partGrade = request.getParameterValues("partGrade")[0];
        String ptstktakelistId = request.getParameterValues("ptstktakelistId")[0];
        
        partAdjStkTakeItemDetailDTO.setPtStkTakeItemId(ptstktakeitemId);
        partAdjStkTakeItemDetailDTO.setPartId(partId);
        partAdjStkTakeItemDetailDTO.setPartGrade(partGrade);
        partAdjStkTakeCommonDTO.setPtStkTakeListId(ptstktakelistId);
        
        String isValid = partAdjStkTakeItemDetailService.validItem(partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, user);
        
        setAjaxDesc(request, isValid);
    }
}