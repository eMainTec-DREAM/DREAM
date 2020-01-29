package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;
import dream.work.list.form.MaWoResultToolDetailForm;
import dream.work.list.service.MaWoResultToolDetailService;

/**
 * 작업결과 - 투입공기구
 * @author  kim21017
 * @version $Id: MaWoResultToolDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultToolDetail" name="maWoResultToolDetailForm"
 *                input="/dream/work/list/maWoResultToolDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRplToolDetail" name="maWoResultToolDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmRplToolDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmOilToolDetail" name="maWoResultToolDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmOilToolDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultToolDetail" path="/dream/work/list/maWoResultToolDetail.jsp"
 *                        redirect="false"
 */
public class MaWoResultToolDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_TOOL_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int WO_RESULT_TOOL_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int WO_RESULT_TOOL_DETAIL_INPUT 	= 5003;
    /** 재고확인 */
    public static final int WO_RESULT_TOOL_STOCK_CHECK		= 8004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultToolDetailForm maWoResultToolDetailForm = (MaWoResultToolDetailForm) form;
        
        super.updateAudit(maWoResultToolDetailForm.getMaWoResultToolDetailDTO().getAuditKey()==""?maWoResultToolDetailForm.getMaWoResultToolListDTO().getAuditKey():maWoResultToolDetailForm.getMaWoResultToolDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultToolDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (maWoResultToolDetailForm.getStrutsAction())
        {
            case MaWoResultToolDetailAction.WO_RESULT_TOOL_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maWoResultToolDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoResultToolDetailAction.WO_RESULT_TOOL_DETAIL_UPDATE:
            	updateDetail(maWoResultToolDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultToolDetailAction.WO_RESULT_TOOL_DETAIL_INPUT:
            	insertDetail(maWoResultToolDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultToolDetailAction.WO_RESULT_TOOL_STOCK_CHECK:
            	getStockQty(maWoResultToolDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업결과-투입공기구 상세 조회
     * @author kim2107
     * @version $Id: MaWoResultToolDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultToolDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoResultToolDetailForm maWoResultToolDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaWoResultToolDetailService maWoResultToolDetailService = (MaWoResultToolDetailService)getBean("maWoResultToolDetailService");

    	// 작업결과Id 구함
        String wkOrId = maWoResultToolDetailForm.getMaWoResultMstrCommonDTO().getWkOrId();
        // 넘겨진 투입자재id 구함
        String woToolId = maWoResultToolDetailForm.getMaWoResultToolListDTO().getWoToolId();
        
        // 조회된 상세 부분
        MaWoResultToolDetailDTO maWoResultToolDetailDTO = maWoResultToolDetailService.findDetail(wkOrId, woToolId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maWoResultToolDetailForm.setMaWoResultToolDetailDTO(maWoResultToolDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoResultToolDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultToolDetailForm
     * @param request
     */
    private void updateDetail(MaWoResultToolDetailForm maWoResultToolDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultToolDetailService maWoResultToolDetailService = (MaWoResultToolDetailService) getBean("maWoResultToolDetailService");
        
        MaWoResultToolDetailDTO maWoResultToolDetailDTO = maWoResultToolDetailForm.getMaWoResultToolDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultToolDetailForm.getMaWoResultMstrCommonDTO();
//        maWoResultToolDetailDTO.setEnterBy(getUser(request).getUserId());
//        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultToolDetailService.updateDetail(maWoResultToolDetailDTO,maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaWoResultToolDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultToolDetailForm
     * @param request
     */
    private void insertDetail(MaWoResultToolDetailForm maWoResultToolDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultToolDetailService maWoResultToolDetailService = (MaWoResultToolDetailService) getBean("maWoResultToolDetailService");
        
        MaWoResultToolDetailDTO maWoResultToolDetailDTO = maWoResultToolDetailForm.getMaWoResultToolDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultToolDetailForm.getMaWoResultMstrCommonDTO();
        maWoResultToolDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultToolDetailService.insertDetail(maWoResultToolDetailDTO, maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * 재고확인
     */
    private void getStockQty(MaWoResultToolDetailForm maWoResultToolDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultToolDetailService maWoResultToolDetailService = (MaWoResultToolDetailService) getBean("maWoResultToolDetailService");
        
        MaWoResultToolDetailDTO maWoResultToolDetailDTO = maWoResultToolDetailForm.getMaWoResultToolDetailDTO();
        String isValid = maWoResultToolDetailService.getStockQty(maWoResultToolDetailDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}