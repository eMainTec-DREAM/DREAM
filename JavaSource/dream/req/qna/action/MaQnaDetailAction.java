package dream.req.qna.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.dto.MaQnaDetailDTO;
import dream.req.qna.form.MaQnaDetailForm;
import dream.req.qna.service.MaQnaDetailService;

/**
 * 질의 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaQnaDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maQnaDetail" name="maQnaDetailForm"
 *                input="/dream/req/qna/maQnaDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maQnaDetail" path="/dream/req/qna/maQnaDetail.jsp"
 *                        redirect="false"
 */
public class MaQnaDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int QNA_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int QNA_DETAIL_INPUT 		= 5003;
    /** 신청완료 */
    public static final int QNA_DETAIL_CONFIRM 		= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaQnaDetailForm maQnaDetailForm = (MaQnaDetailForm) form;
        
        super.updateAudit(maQnaDetailForm.getMaQnaDetailDTO().getAuditKey()==""?maQnaDetailForm.getMaQnaCommonDTO().getAuditKey():maQnaDetailForm.getMaQnaDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maQnaDetailForm.getStrutsAction())
        {
            case MaQnaDetailAction.QNA_DETAIL_INIT:
                this.findDetail(request, maQnaDetailForm);
                returnActionForward = mapping.findForward("maQnaDetail");
                break;
            case MaQnaDetailAction.QNA_DETAIL_UPDATE:
            	updateDetail(maQnaDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaQnaDetailAction.QNA_DETAIL_INPUT:
            	insertDetail(maQnaDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaQnaDetailAction.QNA_DETAIL_CONFIRM:
            	confirmDetail(maQnaDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maQnaDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 질의 상세 조회
     * @author kim2107
     * @version $Id: MaQnaDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maQnaDetailForm
     */
    private void findDetail(HttpServletRequest request, MaQnaDetailForm maQnaDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaQnaDetailService maQnaDetailService = (MaQnaDetailService)getBean("maQnaDetailService");
    	MaQnaCommonDTO maQnaCommonDTO = maQnaDetailForm.getMaQnaCommonDTO();
    	maQnaCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        MaQnaDetailDTO maQnaDetailDTO = maQnaDetailService.findDetail(maQnaCommonDTO);
        
        // 조회된 상세  셋팅한다.
        maQnaDetailForm.setMaQnaDetailDTO(maQnaDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaQnaDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailForm
     * @param request
     */
    private void insertDetail(MaQnaDetailForm maQnaDetailForm, HttpServletRequest request) throws Exception
    {
    	MaQnaDetailService maQnaDetailService = (MaQnaDetailService) getBean("maQnaDetailService");
        
        MaQnaDetailDTO maQnaDetailDTO = maQnaDetailForm.getMaQnaDetailDTO();
        maQnaDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maQnaDetailService.insertDetail(maQnaDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaQnaDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailForm
     * @param request
     */
    private void updateDetail(MaQnaDetailForm maQnaDetailForm, HttpServletRequest request) throws Exception
    {
    	MaQnaDetailService maQnaDetailService = (MaQnaDetailService) getBean("maQnaDetailService");
        
        MaQnaDetailDTO maQnaDetailDTO = maQnaDetailForm.getMaQnaDetailDTO();
        maQnaDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maQnaDetailService.updateDetail(maQnaDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail confirm
     * @author  kim21017
     * @version $Id: MaQnaDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailForm
     * @param request
     */
    private void confirmDetail(MaQnaDetailForm maQnaDetailForm, HttpServletRequest request) throws Exception
    {
    	MaQnaDetailService maQnaDetailService = (MaQnaDetailService) getBean("maQnaDetailService");
        
        MaQnaDetailDTO maQnaDetailDTO = maQnaDetailForm.getMaQnaDetailDTO();
        maQnaDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maQnaDetailService.confirmDetail(maQnaDetailDTO);
        
        setAjaxStatus(request);
    }
}