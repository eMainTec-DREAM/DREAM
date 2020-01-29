package dream.consult.program.page.action;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.ConsultPgmPgLinkedFuncDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.form.ConsultPgmPgLinkedFuncDetailForm;
import dream.consult.program.page.service.ConsultPgmPgLinkedFuncDetailService;
import dream.main.service.MainService;

/**
 * ȭ�麰 ������ ��
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/consultPgmPgLinkedFuncDetail" name="consultPgmPgLinkedFuncDetailForm"
 *                input="/dream/consult/program/page/consultPgmPgLinkedFuncDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmPgLinkedFuncDetail" path="/dream/consult/program/page/consultPgmPgLinkedFuncDetail.jsp"
 *                        redirect="false"
 */
public class ConsultPgmPgLinkedFuncDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PG_FIELD_DETAIL_INIT 	= 1001;
    /** ���� */
    public static final int PG_FIELD_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int PG_FIELD_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmPgLinkedFuncDetailForm consultPgmPgLinkedFuncDetailForm = (ConsultPgmPgLinkedFuncDetailForm) form;
        switch (consultPgmPgLinkedFuncDetailForm.getStrutsAction())
        {
            case ConsultPgmPgLinkedFuncDetailAction.PG_FIELD_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, consultPgmPgLinkedFuncDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case ConsultPgmPgLinkedFuncDetailAction.PG_FIELD_DETAIL_UPDATE:
            	updateDetail(consultPgmPgLinkedFuncDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmPgLinkedFuncDetailAction.PG_FIELD_DETAIL_INPUT:
            	insertDetail(consultPgmPgLinkedFuncDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȭ�麰 ������ �� ��ȸ
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param consultPgmPgLinkedFuncDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultPgmPgLinkedFuncDetailForm consultPgmPgLinkedFuncDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	ConsultPgmPgLinkedFuncDetailService consultPgmPgLinkedFuncDetailService = (ConsultPgmPgLinkedFuncDetailService)getBean("consultPgmPgLinkedFuncDetailService");

    	ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO = consultPgmPgLinkedFuncDetailForm.getConsultPgmPgLinkedFuncDetailDTO();

        consultPgmPgLinkedFuncDetailDTO.setPageId(consultPgmPgLinkedFuncDetailForm.getMaPgMngCommonDTO().getPageId());
        consultPgmPgLinkedFuncDetailDTO.setPgLinkedFuncId(consultPgmPgLinkedFuncDetailForm.getConsultPgmPgLinkedFuncListDTO().getPgLinkedFuncId());

        User user = getUser(request);
        
        // ��ȸ�� �� �κ�
        ConsultPgmPgLinkedFuncDetailDTO resultDTO = consultPgmPgLinkedFuncDetailService.findDetail(consultPgmPgLinkedFuncDetailDTO, user);
        
        // ��ȸ�� ��  �����Ѵ�.
        consultPgmPgLinkedFuncDetailForm.setConsultPgmPgLinkedFuncDetailDTO(resultDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultPgmPgLinkedFuncDetailForm
     * @param request
     */
    private void updateDetail(ConsultPgmPgLinkedFuncDetailForm consultPgmPgLinkedFuncDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultPgmPgLinkedFuncDetailService consultPgmPgLinkedFuncDetailService = (ConsultPgmPgLinkedFuncDetailService) getBean("consultPgmPgLinkedFuncDetailService");
        
        ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO = consultPgmPgLinkedFuncDetailForm.getConsultPgmPgLinkedFuncDetailDTO();
        MaPgMngCommonDTO maPgMngCommonDTO = consultPgmPgLinkedFuncDetailForm.getMaPgMngCommonDTO();
        consultPgmPgLinkedFuncDetailDTO.setEnterBy(getUser(request).getUserId());
        
        consultPgmPgLinkedFuncDetailService.updateDetail(consultPgmPgLinkedFuncDetailDTO,maPgMngCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultPgmPgLinkedFuncDetailForm
     * @param request
     */
    private void insertDetail(ConsultPgmPgLinkedFuncDetailForm consultPgmPgLinkedFuncDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultPgmPgLinkedFuncDetailService consultPgmPgLinkedFuncDetailService = (ConsultPgmPgLinkedFuncDetailService) getBean("consultPgmPgLinkedFuncDetailService");
        
        ConsultPgmPgLinkedFuncDetailDTO consultPgmPgLinkedFuncDetailDTO = consultPgmPgLinkedFuncDetailForm.getConsultPgmPgLinkedFuncDetailDTO();
        
        MaPgMngCommonDTO maPgMngCommonDTO = consultPgmPgLinkedFuncDetailForm.getMaPgMngCommonDTO();
        consultPgmPgLinkedFuncDetailDTO.setEnterBy(getUser(request).getUserId());
        
        consultPgmPgLinkedFuncDetailService.insertDetail(consultPgmPgLinkedFuncDetailDTO, maPgMngCommonDTO);
        
        setAjaxStatus(request);
    }
}