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
 * �ڰ����з� - �� action
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
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EDU_DETAIL_INIT         = 8001;
    /** ���� */
    public static final int EDU_DETAIL_INPUT        = 5002;
    /** ���� */
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
                // ������ ��ȸ
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
     * �ڰ����з� �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param eduDetailForm
     */
    private void findDetail(HttpServletRequest request, EduDetailForm eduDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	EduDetailService eduDetailService = (EduDetailService)getBean("eduDetailService");
    	
    	EduCommonDTO eduCommonDTO = eduDetailForm.getEduCommonDTO();
    	
        // ��ȸ�� �� �κ�
        EduDetailDTO eduDetailDTO = eduDetailService.findDetail(getUser(request), eduCommonDTO.getCourseListId());
        
        // ��ȸ�� ��  �����Ѵ�.
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