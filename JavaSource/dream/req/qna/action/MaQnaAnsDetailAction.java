package dream.req.qna.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.qna.dto.MaQnaAnsDetailDTO;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.form.MaQnaAnsDetailForm;
import dream.req.qna.service.MaQnaAnsDetailService;

/**
 * 응답 상세
 * @author  kim21017
 * @version $Id: MaQnaAnsDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maQnaAnsDetail" name="maQnaAnsDetailForm"
 *                input="/dream/req/qna/maQnaAnsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maQnaAnsDetail" path="/dream/req/qna/maQnaAnsDetail.jsp"
 *                        redirect="false"
 */
public class MaQnaAnsDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_ANS_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int QNA_ANS_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int QNA_ANS_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaQnaAnsDetailForm maQnaAnsDetailForm = (MaQnaAnsDetailForm) form;
        
        super.updateAudit(maQnaAnsDetailForm.getMaQnaAnsDetailDTO().getAuditKey()==""?maQnaAnsDetailForm.getMaQnaAnsListDTO().getAuditKey():maQnaAnsDetailForm.getMaQnaAnsDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maQnaAnsDetailForm.getStrutsAction())
        {
            case MaQnaAnsDetailAction.QNA_ANS_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maQnaAnsDetailForm);
                returnActionForward = mapping.findForward("maQnaAnsDetail");
                break;
            case MaQnaAnsDetailAction.QNA_ANS_DETAIL_UPDATE:
            	updateDetail(maQnaAnsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaQnaAnsDetailAction.QNA_ANS_DETAIL_INPUT:
            	insertDetail(maQnaAnsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maQnaAnsDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 답변 - 상세 조회
     * @author kim2107
     * @version $Id: MaQnaAnsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maQnaAnsDetailForm
     */
    private void findDetail(HttpServletRequest request, MaQnaAnsDetailForm maQnaAnsDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaQnaAnsDetailService maQnaAnsDetailService = (MaQnaAnsDetailService)getBean("maQnaAnsDetailService");
    	MaQnaCommonDTO maQnaCommonDTO = maQnaAnsDetailForm.getMaQnaCommonDTO();
    	MaQnaAnsListDTO maQnaAnsListDTO = maQnaAnsDetailForm.getMaQnaAnsListDTO();
    	maQnaCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        MaQnaAnsDetailDTO maQnaAnsDetailDTO = maQnaAnsDetailService.findDetail(maQnaAnsListDTO,maQnaCommonDTO);
        
        // 조회된 상세  셋팅한다.
        maQnaAnsDetailForm.setMaQnaAnsDetailDTO(maQnaAnsDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaQnaAnsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsDetailForm
     * @param request
     */
    private void updateDetail(MaQnaAnsDetailForm maQnaAnsDetailForm, HttpServletRequest request) throws Exception
    {
    	MaQnaAnsDetailService maQnaAnsDetailService = (MaQnaAnsDetailService) getBean("maQnaAnsDetailService");
        
        MaQnaAnsDetailDTO maQnaAnsDetailDTO = maQnaAnsDetailForm.getMaQnaAnsDetailDTO();
        MaQnaCommonDTO maQnaCommonDTO = maQnaAnsDetailForm.getMaQnaCommonDTO();
    	maQnaCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maQnaAnsDetailService.updateDetail(maQnaAnsDetailDTO,maQnaCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaQnaAnsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsDetailForm
     * @param request
     */
    private void insertDetail(MaQnaAnsDetailForm maQnaAnsDetailForm, HttpServletRequest request) throws Exception
    {
    	MaQnaAnsDetailService maQnaAnsDetailService = (MaQnaAnsDetailService) getBean("maQnaAnsDetailService");
        
        MaQnaAnsDetailDTO maQnaAnsDetailDTO = maQnaAnsDetailForm.getMaQnaAnsDetailDTO();
        
        MaQnaCommonDTO maQnaCommonDTO = maQnaAnsDetailForm.getMaQnaCommonDTO();
    	maQnaCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maQnaAnsDetailService.insertDetail(maQnaAnsDetailDTO, maQnaCommonDTO);
        
        setAjaxStatus(request);
    }
}