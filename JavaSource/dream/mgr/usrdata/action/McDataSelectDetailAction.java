package dream.mgr.usrdata.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;
import dream.mgr.usrdata.form.McDataSelectDetailForm;
import dream.mgr.usrdata.service.McDataSelectDetailService;

/**
 * 메뉴 - 상세 action
 * 
 * @author kim2107
 * @version $Id: McDataSelectDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/mcDataSelectDetail" name="mcDataSelectDetailForm"
 *                input="/dream/mgr/usrdata/mcDataSelectDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcDataSelectDetail" path="/dream/mgr/usrdata/mcDataSelectDetail.jsp"
 *                        redirect="false"
 */
public class McDataSelectDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DATA_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DATA_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DATA_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        McDataSelectDetailForm mcDataSelectDetailForm = (McDataSelectDetailForm) form;
        
        super.updateAudit(mcDataSelectDetailForm.getMcDataSelectDetailDTO().getAuditKey()==""?mcDataSelectDetailForm.getMcDataSelectCommonDTO().getAuditKey():mcDataSelectDetailForm.getMcDataSelectDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mcDataSelectDetailForm.getStrutsAction())
        {
            case McDataSelectDetailAction.DATA_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, mcDataSelectDetailForm);
                returnActionForward = mapping.findForward("mcDataSelectDetail");
                break;
            case McDataSelectDetailAction.DATA_DETAIL_INPUT:
            	insertDetail(mcDataSelectDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case McDataSelectDetailAction.DATA_DETAIL_UPDATE:
            	updateDetail(mcDataSelectDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mcDataSelectDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 버튼 상세 조회
     * @author kim2107
     * @version $Id: McDataSelectDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mcDataSelectDetailForm
     */
    private void findDetail(HttpServletRequest request, McDataSelectDetailForm mcDataSelectDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	McDataSelectDetailService mcDataSelectDetailService = (McDataSelectDetailService)getBean("mcDataSelectDetailService");

        // 넘겨진 메뉴Id 구함
        String usrrptId = mcDataSelectDetailForm.getMcDataSelectCommonDTO().getUsrrptId();
        
        // 조회된 상세 부분
        McDataSelectDetailDTO mcDataSelectDetailDTO = mcDataSelectDetailService.findDetail(usrrptId, getUser(request).getLangId());
        
        // 조회된 상세  셋팅한다.
        mcDataSelectDetailForm.setMcDataSelectDetailDTO(mcDataSelectDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: McDataSelectDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectDetailForm
     * @param request
     */
    private void insertDetail(McDataSelectDetailForm mcDataSelectDetailForm, HttpServletRequest request) throws Exception
    {
        McDataSelectDetailService mcDataSelectDetailService = (McDataSelectDetailService) getBean("mcDataSelectDetailService");
        
        McDataSelectDetailDTO mcDataSelectDetailDTO = mcDataSelectDetailForm.getMcDataSelectDetailDTO();
        
        mcDataSelectDetailDTO.setEnterBy(getUser(request).getUserId());
        
        mcDataSelectDetailService.insertDetail(mcDataSelectDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: McDataSelectDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectDetailForm
     * @param request
     */
    private void updateDetail(McDataSelectDetailForm mcDataSelectDetailForm, HttpServletRequest request) throws Exception
    {
    	McDataSelectDetailService mcDataSelectDetailService = (McDataSelectDetailService) getBean("mcDataSelectDetailService");
        
        McDataSelectDetailDTO mcDataSelectDetailDTO = mcDataSelectDetailForm.getMcDataSelectDetailDTO();
        
        mcDataSelectDetailDTO.setEnterBy(getUser(request).getUserId());
        
        mcDataSelectDetailService.updateDetail(mcDataSelectDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}