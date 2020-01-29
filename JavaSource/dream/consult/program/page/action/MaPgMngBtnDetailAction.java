package dream.consult.program.page.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngBtnDetailDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.form.MaPgMngBtnDetailForm;
import dream.consult.program.page.service.MaPgMngBtnDetailService;

/**
 * ȭ�麰 ��ư ��
 * @author  kim21017
 * @version $Id: MaPgMngBtnDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPgMngBtnDetail" name="maPgMngBtnDetailForm"
 *                input="/dream/consult/program/page/maPgMngBtnDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngBtnDetail" path="/dream/consult/program/page/maPgMngBtnDetail.jsp"
 *                        redirect="false"
 */
public class MaPgMngBtnDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PG_BTN_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int PG_BTN_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int PG_BTN_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngBtnDetailForm maPgMngBtnDetailForm = (MaPgMngBtnDetailForm) form;
        switch (maPgMngBtnDetailForm.getStrutsAction())
        {
            case MaPgMngBtnDetailAction.PG_BTN_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPgMngBtnDetailForm);
                returnActionForward = mapping.findForward("maPgMngBtnDetail");
                break;
            case MaPgMngBtnDetailAction.PG_BTN_DETAIL_UPDATE:
            	updateDetail(maPgMngBtnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngBtnDetailAction.PG_BTN_DETAIL_INPUT:
            	insertDetail(maPgMngBtnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPgMngBtnDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȭ�麰 ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: MaPgMngBtnDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngBtnDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPgMngBtnDetailForm maPgMngBtnDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPgMngBtnDetailService maPgMngBtnDetailService = (MaPgMngBtnDetailService)getBean("maPgMngBtnDetailService");

    	MaPgMngBtnDetailDTO maPgMngBtnDetailDTO = maPgMngBtnDetailForm.getMaPgMngBtnDetailDTO();

        maPgMngBtnDetailDTO.setPageId(maPgMngBtnDetailForm.getMaPgMngCommonDTO().getPageId());
        maPgMngBtnDetailDTO.setUserLang(getUser(request).getLangId());
        maPgMngBtnDetailDTO.setPgBtnId(maPgMngBtnDetailForm.getMaPgMngBtnListDTO().getPgBtnId());

        // ��ȸ�� �� �κ�
        MaPgMngBtnDetailDTO resultDTO = maPgMngBtnDetailService.findDetail(maPgMngBtnDetailDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        maPgMngBtnDetailForm.setMaPgMngBtnDetailDTO(resultDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPgMngBtnDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngBtnDetailForm
     * @param request
     */
    private void updateDetail(MaPgMngBtnDetailForm maPgMngBtnDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngBtnDetailService maPgMngBtnDetailService = (MaPgMngBtnDetailService) getBean("maPgMngBtnDetailService");
        
        MaPgMngBtnDetailDTO maPgMngBtnDetailDTO = maPgMngBtnDetailForm.getMaPgMngBtnDetailDTO();
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngBtnDetailForm.getMaPgMngCommonDTO();
        maPgMngBtnDetailDTO.setEnterBy(getUser(request).getUserId());
        maPgMngBtnDetailDTO.setUserLang(getUser(request).getLangId());
        
        maPgMngBtnDetailService.updateDetail(maPgMngBtnDetailDTO,maPgMngCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPgMngBtnDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngBtnDetailForm
     * @param request
     */
    private void insertDetail(MaPgMngBtnDetailForm maPgMngBtnDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngBtnDetailService maPgMngBtnDetailService = (MaPgMngBtnDetailService) getBean("maPgMngBtnDetailService");
        
        MaPgMngBtnDetailDTO maPgMngBtnDetailDTO = maPgMngBtnDetailForm.getMaPgMngBtnDetailDTO();
        
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngBtnDetailForm.getMaPgMngCommonDTO();
        maPgMngBtnDetailDTO.setEnterBy(getUser(request).getUserId());
        maPgMngBtnDetailDTO.setUserLang(getUser(request).getLangId());
        
        maPgMngBtnDetailService.insertDetail(maPgMngBtnDetailDTO, maPgMngCommonDTO);
        
        setAjaxStatus(request);
    }
}