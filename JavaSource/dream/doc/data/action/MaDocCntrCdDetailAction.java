package dream.doc.data.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdDetailDTO;
import dream.doc.data.form.MaDocCntrCdDetailForm;
import dream.doc.data.service.MaDocCntrCdDetailService;

/**
 * 일반자료실 - 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maDocCntrCdDetail" name="maDocCntrCdDetailForm"
 *                input="/dream/doc/data/maDocCntrCdDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maDocCntrCdDetail" path="/dream/doc/data/maDocCntrCdDetail.jsp"
 *                        redirect="false"
 */
public class MaDocCntrCdDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DOCCNTR_CD_DETAIL_INIT 	    = 8001;
    /** 저장 */
    public static final int DOCCNTR_CD_DETAIL_INPUT     = 5002;
    /** 수정 */
    public static final int DOCCNTR_CD_DETAIL_UPDATE    = 6003;
    /** 중복 체크 */
    public static final int DOCCNTR_CD_DETAIL_CHECK     = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaDocCntrCdDetailForm maDocCntrCdDetailForm = (MaDocCntrCdDetailForm) form;
        
        super.updateAudit(maDocCntrCdDetailForm.getMaDocCntrCdDetailDTO().getAuditKey()==""?maDocCntrCdDetailForm.getMaDocCntrCdCommonDTO().getAuditKey():maDocCntrCdDetailForm.getMaDocCntrCdDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maDocCntrCdDetailForm.getStrutsAction())
        {
            case MaDocCntrCdDetailAction.DOCCNTR_CD_DETAIL_INIT:
                this.findDetail(request, maDocCntrCdDetailForm);
                returnActionForward = mapping.findForward("maDocCntrCdDetail");
                break;
            case MaDocCntrCdDetailAction.DOCCNTR_CD_DETAIL_INPUT:
            	insertDetail(maDocCntrCdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaDocCntrCdDetailAction.DOCCNTR_CD_DETAIL_UPDATE:
            	updateDetail(maDocCntrCdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maDocCntrCdDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 일반자료실 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maDocCntrCdDetailForm
     */
    private void findDetail(HttpServletRequest request, MaDocCntrCdDetailForm maDocCntrCdDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaDocCntrCdDetailService maDocCntrCdDetailService = (MaDocCntrCdDetailService)getBean("maDocCntrCdDetailService");
    	
    	maDocCntrCdDetailForm.getMaDocCntrCdCommonDTO().setCompNo(getUser(request).getCompNo());
        
    	// 조회된 상세 부분
        MaDocCntrCdDetailDTO maDocCntrCdDetailDTO = maDocCntrCdDetailService.findDetail(maDocCntrCdDetailForm.getMaDocCntrCdCommonDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        maDocCntrCdDetailForm.setMaDocCntrCdDetailDTO(maDocCntrCdDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maDocCntrCdDetailForm
     * @param request
     */
    private void insertDetail(MaDocCntrCdDetailForm maDocCntrCdDetailForm, HttpServletRequest request) throws Exception
    {
        MaDocCntrCdDetailService maDocCntrCdDetailService = (MaDocCntrCdDetailService) getBean("maDocCntrCdDetailService");
        
        MaDocCntrCdDetailDTO maDocCntrCdDetailDTO = maDocCntrCdDetailForm.getMaDocCntrCdDetailDTO();
        MaDocCntrCdCommonDTO maDocCntrCdCommonDTO = maDocCntrCdDetailForm.getMaDocCntrCdCommonDTO();
        
        maDocCntrCdDetailDTO.setCompNo((getUser(request).getCompNo()));
        maDocCntrCdDetailDTO.setDocCntrType(maDocCntrCdCommonDTO.getDocCntrType());
        
        maDocCntrCdDetailService.insertDetail(maDocCntrCdDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDocCntrCdDetailForm
     * @param request
     */
    private void updateDetail(MaDocCntrCdDetailForm maDocCntrCdDetailForm, HttpServletRequest request) throws Exception
    {
    	MaDocCntrCdDetailService maDocCntrCdDetailService = (MaDocCntrCdDetailService) getBean("maDocCntrCdDetailService");
        
        MaDocCntrCdDetailDTO maDocCntrCdDetailDTO = maDocCntrCdDetailForm.getMaDocCntrCdDetailDTO();
        
        maDocCntrCdDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maDocCntrCdDetailService.updateDetail(maDocCntrCdDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
}