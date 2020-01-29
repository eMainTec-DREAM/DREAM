package dream.edu.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpDetailDTO;
import dream.edu.list.form.EduEmpDetailForm;
import dream.edu.list.service.EduEmpDetailService;

/**
 * 자격증 - 사원
 * @author  kim21017
 * @version $Id: EduEmpDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/eduEmpDetail" name="eduEmpDetailForm"
 *                input="/dream/edu/list/eduEmpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="eduEmpDetail" path="/dream/edu/list/eduEmpDetail.jsp"
 *                        redirect="false"
 */
public class EduEmpDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EDU_EMP_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int EDU_EMP_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int EDU_EMP_DETAIL_INPUT 	= 5003;
    /** 같은 자격증 중복 EMP 검사 */
    public static final int EDU_EMP_DETAIL_CHECK	= 1004;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        EduEmpDetailForm eduEmpDetailForm = (EduEmpDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") eduEmpDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(eduEmpDetailForm.getEduEmpDetailDTO().getAuditKey()==""?eduEmpDetailForm.getEduEmpListDTO().getAuditKey():eduEmpDetailForm.getEduEmpDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (eduEmpDetailForm.getStrutsAction())
        {
            case EduEmpDetailAction.EDU_EMP_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, eduEmpDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case EduEmpDetailAction.EDU_EMP_DETAIL_UPDATE:
            	updateDetail(eduEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case EduEmpDetailAction.EDU_EMP_DETAIL_INPUT:
            	insertDetail(eduEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case EduEmpDetailAction.EDU_EMP_DETAIL_CHECK:
            	validEmp(eduEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    /**
     * 자격증-사원 상세 조회
     * @author kim2107
     * @version $Id: EduEmpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param eduEmpDetailForm
     */
    private void findDetail(HttpServletRequest request, EduEmpDetailForm eduEmpDetailForm)throws Exception
    {
        // Service 객체 생성
    	EduEmpDetailService eduEmpDetailService = (EduEmpDetailService)getBean("eduEmpDetailService");
    	// 자격증Id 구함
        String empTrainListId = eduEmpDetailForm.getEduEmpListDTO().getEmpTrainListId();
        // 조회된 상세 부분
        EduEmpDetailDTO eduEmpDetailDTO = eduEmpDetailService.findDetail(empTrainListId, getUser(request));
        // 조회된 상세  셋팅한다.
        eduEmpDetailForm.setEduEmpDetailDTO(eduEmpDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: EduEmpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param eduEmpDetailForm
     * @param request
     */
    private void updateDetail(EduEmpDetailForm eduEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	EduEmpDetailService eduEmpDetailService = (EduEmpDetailService) getBean("eduEmpDetailService");

        EduEmpDetailDTO eduEmpDetailDTO = eduEmpDetailForm.getEduEmpDetailDTO();
        EduCommonDTO eduCommonDTO = eduEmpDetailForm.getEduCommonDTO();

        eduEmpDetailService.updateDetail(eduEmpDetailDTO,eduCommonDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: EduEmpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param eduEmpDetailForm
     * @param request
     */
    private void insertDetail(EduEmpDetailForm eduEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	EduEmpDetailService eduEmpDetailService = (EduEmpDetailService) getBean("eduEmpDetailService");

        EduEmpDetailDTO eduEmpDetailDTO = eduEmpDetailForm.getEduEmpDetailDTO();

        EduCommonDTO eduCommonDTO = eduEmpDetailForm.getEduCommonDTO();
        eduEmpDetailService.insertDetail(eduEmpDetailDTO, eduCommonDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * 사원 중복 검사
     */
    private void validEmp(EduEmpDetailForm eduEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	EduEmpDetailService eduEmpDetailService = (EduEmpDetailService) getBean("eduEmpDetailService");

        EduEmpDetailDTO eduEmpDetailDTO = eduEmpDetailForm.getEduEmpDetailDTO();

        EduCommonDTO eduCommonDTO = eduEmpDetailForm.getEduCommonDTO();
        String isValid = eduEmpDetailService.validEmp(eduEmpDetailDTO,eduCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}