package dream.consult.program.btn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.btn.dto.MaBtnMngDetailDTO;
import dream.consult.program.btn.form.MaBtnMngDetailForm;
import dream.consult.program.btn.service.MaBtnMngDetailService;

/**
 * ��ư - �� action
 * 
 * @author kim2107
 * @version $Id: MaBtnMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maBtnMngDetail" name="maBtnMngDetailForm"
 *                input="/dream/consult/program/btn/maBtnMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBtnMngDetail" path="/dream/consult/program/btn/maBtnMngDetail.jsp"
 *                        redirect="false"
 */
public class MaBtnMngDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int BTN_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int BTN_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int BTN_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaBtnMngDetailForm maBtnMngDetailForm = (MaBtnMngDetailForm) form;
        
        switch (maBtnMngDetailForm.getStrutsAction())
        {
            case MaBtnMngDetailAction.BTN_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maBtnMngDetailForm);
                returnActionForward = mapping.findForward("maBtnMngDetail");
                break;
            case MaBtnMngDetailAction.BTN_DETAIL_INPUT:
            	insertDetail(maBtnMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBtnMngDetailAction.BTN_DETAIL_UPDATE:
            	updateDetail(maBtnMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maBtnMngDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: MaBtnMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBtnMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaBtnMngDetailForm maBtnMngDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaBtnMngDetailService maBtnMngDetailService = (MaBtnMngDetailService)getBean("maBtnMngDetailService");

        // �Ѱ��� ��ưId ����
        String buttonId = maBtnMngDetailForm.getMaBtnMngCommonDTO().getButtonId();
        
        // ��ȸ�� �� �κ�
        MaBtnMngDetailDTO maBtnMngDetailDTO = maBtnMngDetailService.findDetail(buttonId);
        
        // ��ȸ�� ��  �����Ѵ�.
        maBtnMngDetailForm.setMaBtnMngDetailDTO(maBtnMngDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaBtnMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailForm
     * @param request
     */
    private void insertDetail(MaBtnMngDetailForm maBtnMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaBtnMngDetailService maBtnMngDetailService = (MaBtnMngDetailService) getBean("maBtnMngDetailService");
        
        MaBtnMngDetailDTO maBtnMngDetailDTO = maBtnMngDetailForm.getMaBtnMngDetailDTO();
        
        maBtnMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maBtnMngDetailService.insertDetail(maBtnMngDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaBtnMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailForm
     * @param request
     */
    private void updateDetail(MaBtnMngDetailForm maBtnMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaBtnMngDetailService maBtnMngDetailService = (MaBtnMngDetailService) getBean("maBtnMngDetailService");
        
        MaBtnMngDetailDTO maBtnMngDetailDTO = maBtnMngDetailForm.getMaBtnMngDetailDTO();
        
        maBtnMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maBtnMngDetailService.updateDetail(maBtnMngDetailDTO);
        
        setAjaxStatus(request);
    }
}