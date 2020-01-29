package dream.edu.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduDetailDTO;
import dream.edu.list.form.EduDetailForm;
import dream.edu.list.service.EduDetailService;

/**
 * 자격증분류 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/eduDetail" name="eduDetailForm"
 *                input="/dream/edu/list/eduDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="eduDetail" path="/dream/edu/list/eduDetail.jsp"
 *                        redirect="false"
 */
public class EduDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EDU_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int EDU_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int EDU_DETAIL_UPDATE       = 6003;

    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        EduDetailForm eduDetailForm = (EduDetailForm) form;
        
        super.updateAudit(eduDetailForm.getEduDetailDTO().getAuditKey()==""?eduDetailForm.getEduCommonDTO().getAuditKey():eduDetailForm.getEduDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (eduDetailForm.getStrutsAction())
        {
            case EduDetailAction.EDU_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, eduDetailForm);
                returnActionForward = mapping.findForward("eduDetail");
                break;
            case EduDetailAction.EDU_DETAIL_INPUT:
            	insertDetail(eduDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case EduDetailAction.EDU_DETAIL_UPDATE:
            	updateDetail(eduDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("eduDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 자격증분류 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param eduDetailForm
     */
    private void findDetail(HttpServletRequest request, EduDetailForm eduDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	EduDetailService eduDetailService = (EduDetailService)getBean("eduDetailService");
    	
    	EduCommonDTO eduCommonDTO = eduDetailForm.getEduCommonDTO();
    	
        // 조회된 상세 부분
        EduDetailDTO eduDetailDTO = eduDetailService.findDetail(getUser(request), eduCommonDTO.getCourseListId());
        
        // 조회된 상세  셋팅한다.
        eduDetailForm.setEduDetailDTO(eduDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduDetailForm
     * @param request
     */
    private void insertDetail(EduDetailForm eduDetailForm, HttpServletRequest request) throws Exception
    {
        EduDetailService eduDetailService = (EduDetailService) getBean("eduDetailService");
        
        EduDetailDTO eduDetailDTO = eduDetailForm.getEduDetailDTO();
        
        eduDetailDTO.setCompNo(getUser(request).getCompNo());
        
        
        eduDetailService.insertDetail(eduDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduDetailForm
     * @param request
     */
    private void updateDetail(EduDetailForm eduDetailForm, HttpServletRequest request) throws Exception
    {
    	EduDetailService eduDetailService = (EduDetailService) getBean("eduDetailService");
        
        EduDetailDTO eduDetailDTO = eduDetailForm.getEduDetailDTO();
        
        eduDetailDTO.setCompNo(getUser(request).getCompNo());
        
        eduDetailService.updateDetail(eduDetailDTO);
        
        setAjaxStatus(request);
    }

}