package dream.consult.program.page.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngDetailDTO;
import dream.consult.program.page.form.MaPgMngDetailForm;
import dream.consult.program.page.service.MaPgMngDetailService;

/**
 * ȭ�� - �� action
 * 
 * @author kim2107
 * @version $Id: MaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maPgMngDetail" name="maPgMngDetailForm"
 *                input="/dream/consult/program/page/maPgMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngDetail" path="/dream/consult/program/page/maPgMngDetail.jsp"
 *                        redirect="false"
 */
public class MaPgMngDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PG_DETAIL_INIT 			= 1001;
    /** ���� */
    public static final int PG_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int PG_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngDetailForm maPgMngDetailForm = (MaPgMngDetailForm) form;
        
        switch (maPgMngDetailForm.getStrutsAction())
        {
            case MaPgMngDetailAction.PG_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPgMngDetailForm);
                returnActionForward = mapping.findForward("maPgMngDetail");
                break;
            case MaPgMngDetailAction.PG_DETAIL_INPUT:
            	insertDetail(maPgMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngDetailAction.PG_DETAIL_UPDATE:
            	updateDetail(maPgMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPgMngDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȭ�� �� ��ȸ
     * @author kim2107
     * @version $Id: MaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPgMngDetailForm maPgMngDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPgMngDetailService maPgMngDetailService = (MaPgMngDetailService)getBean("maPgMngDetailService");

        // �Ѱ��� ������Id ����
        String pageId = maPgMngDetailForm.getMaPgMngCommonDTO().getPageId();
        
        // ��ȸ�� �� �κ�
        MaPgMngDetailDTO maPgMngDetailDTO = maPgMngDetailService.findDetail(pageId,getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPgMngDetailForm.setMaPgMngDetailDTO(maPgMngDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailForm
     * @param request
     */
    private void insertDetail(MaPgMngDetailForm maPgMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaPgMngDetailService maPgMngDetailService = (MaPgMngDetailService) getBean("maPgMngDetailService");
        
        MaPgMngDetailDTO maPgMngDetailDTO = maPgMngDetailForm.getMaPgMngDetailDTO();
        
        maPgMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maPgMngDetailService.insertDetail(maPgMngDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailForm
     * @param request
     */
    private void updateDetail(MaPgMngDetailForm maPgMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngDetailService maPgMngDetailService = (MaPgMngDetailService) getBean("maPgMngDetailService");
        
        MaPgMngDetailDTO maPgMngDetailDTO = maPgMngDetailForm.getMaPgMngDetailDTO();
        
        maPgMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maPgMngDetailService.updateDetail(maPgMngDetailDTO);
        
        setAjaxStatus(request);
    }
}