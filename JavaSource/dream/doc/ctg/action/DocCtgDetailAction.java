package dream.doc.ctg.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.doc.ctg.dto.DocCtgCommonDTO;
import dream.doc.ctg.dto.DocCtgDetailDTO;
import dream.doc.ctg.form.DocCtgDetailForm;
import dream.doc.ctg.service.DocCtgDetailService;

/**
 * 문서분류체계 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/docCtgDetail" name="docCtgDetailForm"
 *                input="/dream/doc/ctg/docCtgDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docCtgDetail" path="/dream/doc/ctg/docCtgDetail.jsp"
 *                        redirect="false"
 */
public class DocCtgDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DOCCTG_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int DOCCTG_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int DOCCTG_DETAIL_UPDATE       = 6003;
    /** 중복체크 */
    public static final int DOCCTG_DETAIL_CHECK        = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        DocCtgDetailForm docCtgDetailForm = (DocCtgDetailForm) form;
        
        super.updateAudit(docCtgDetailForm.getDocCtgDetailDTO().getAuditKey()==""?docCtgDetailForm.getDocCtgCommonDTO().getAuditKey():docCtgDetailForm.getDocCtgDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (docCtgDetailForm.getStrutsAction())
        {
            case DocCtgDetailAction.DOCCTG_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, docCtgDetailForm);
                returnActionForward = mapping.findForward("docCtgDetail");
                break;
            case DocCtgDetailAction.DOCCTG_DETAIL_INPUT:
            	insertDetail(docCtgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case DocCtgDetailAction.DOCCTG_DETAIL_UPDATE:
            	updateDetail(docCtgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case DocCtgDetailAction.DOCCTG_DETAIL_CHECK:
                validPtWh(docCtgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("docCtgDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 자재재고 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param docCtgDetailForm
     */
    private void findDetail(HttpServletRequest request, DocCtgDetailForm docCtgDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	DocCtgDetailService docCtgDetailService = (DocCtgDetailService)getBean("docCtgDetailService");
    	
    	DocCtgCommonDTO docCtgCommonDTO = docCtgDetailForm.getDocCtgCommonDTO();
    	
    	docCtgCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	String docCtgId = docCtgDetailForm.getDocCtgCommonDTO().getDocctgId();
    	
        // 조회된 상세 부분
        DocCtgDetailDTO docCtgDetailDTO = docCtgDetailService.findDetail(docCtgId,getUser(request));
        
        // 조회된 상세  셋팅한다.
        docCtgDetailForm.setDocCtgDetailDTO(docCtgDetailDTO);
    }
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgDetailForm
     * @param request
     */
    private void insertDetail(DocCtgDetailForm docCtgDetailForm, HttpServletRequest request) throws Exception
    {
        DocCtgDetailService docCtgDetailService = (DocCtgDetailService) getBean("docCtgDetailService");
        
        DocCtgDetailDTO docCtgDetailDTO = docCtgDetailForm.getDocCtgDetailDTO();
        
        docCtgDetailDTO.setEnterBy(getUser(request).getUserId());
        docCtgDetailDTO.setCompNo(getUser(request).getCompNo());
        
        docCtgDetailService.insertDetail(docCtgDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgDetailForm
     * @param request
     */
    private void updateDetail(DocCtgDetailForm docCtgDetailForm, HttpServletRequest request) throws Exception
    {
    	DocCtgDetailService docCtgDetailService = (DocCtgDetailService) getBean("docCtgDetailService");
        
        DocCtgDetailDTO docCtgDetailDTO = docCtgDetailForm.getDocCtgDetailDTO();
        
        docCtgDetailDTO.setEnterBy(getUser(request).getUserId());
        docCtgDetailDTO.setCompNo(getUser(request).getCompNo());
        
        docCtgDetailService.updateDetail(docCtgDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid PtStock
     * @author  
     * @version $Id: $ 
     * @since   1.0
     * 
     * @param docCtgDetailForm
     * @param request
     */
    private void validPtWh(DocCtgDetailForm docCtgDetailForm, HttpServletRequest request) throws Exception
    {
        DocCtgDetailService docCtgDetailService = (DocCtgDetailService) getBean("docCtgDetailService");
        
        DocCtgDetailDTO docCtgDetailDTO = docCtgDetailForm.getDocCtgDetailDTO();
        
        docCtgDetailDTO.setCompNo((getUser(request).getCompNo()));
        
//        String isValid = docCtgDetailService.validPtWh(docCtgDetailDTO);
//        
//        setAjaxDesc(request, isValid);
    }
}