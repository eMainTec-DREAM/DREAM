package dream.consult.program.page.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaGrdMngDetailDTO;
import dream.consult.program.page.form.MaGrdMngDetailForm;
import dream.consult.program.page.service.MaGrdMngDetailService;

/**
 * ȭ�� - �� action
 * 
 * @author kim2107
 * @version $Id: MaGrdMngDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
 * @since 1.0
 * @struts:action path="/maGrdMngDetail" name="maGrdMngDetailForm"
 *                input="/dream/consult/program/page/maGrdMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPgMngGrdDetail" name="maGrdMngDetailForm"
 *                input="/dream/consult/program/page/maPgMngGrdDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maGrdMngDetail" path="/dream/consult/program/page/maGrdMngDetail.jsp"
 *                        redirect="false"
 */
public class MaGrdMngDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int GRD_DETAIL_INIT 			= 1001;
    /** ���� */
    public static final int GRD_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int GRD_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaGrdMngDetailForm maGrdMngDetailForm = (MaGrdMngDetailForm) form;
        
        switch (maGrdMngDetailForm.getStrutsAction())
        {
            case MaGrdMngDetailAction.GRD_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maGrdMngDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaGrdMngDetailAction.GRD_DETAIL_INPUT:
            	insertDetail(maGrdMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaGrdMngDetailAction.GRD_DETAIL_UPDATE:
            	updateDetail(maGrdMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȭ�� �� ��ȸ
     * @author kim2107
     * @version $Id: MaGrdMngDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaGrdMngDetailForm maGrdMngDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaGrdMngDetailService maGrdMngDetailService = (MaGrdMngDetailService)getBean("maGrdMngDetailService");

        // �Ѱ��� ������Id ����
        String pgGridId = maGrdMngDetailForm.getMaGrdMngCommonDTO().getPgGridId();
        
        // ��ȸ�� �� �κ�
        MaGrdMngDetailDTO maGrdMngDetailDTO = maGrdMngDetailService.findDetail(pgGridId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maGrdMngDetailForm.setMaGrdMngDetailDTO(maGrdMngDetailDTO);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaGrdMngDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDetailForm
     * @param request
     */
    private void insertDetail(MaGrdMngDetailForm maGrdMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaGrdMngDetailService maGrdMngDetailService = (MaGrdMngDetailService) getBean("maGrdMngDetailService");
        
        MaGrdMngDetailDTO maGrdMngDetailDTO = maGrdMngDetailForm.getMaGrdMngDetailDTO();
        
        maGrdMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdMngDetailService.insertDetail(maGrdMngDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaGrdMngDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDetailForm
     * @param request
     */
    private void updateDetail(MaGrdMngDetailForm maGrdMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdMngDetailService maGrdMngDetailService = (MaGrdMngDetailService) getBean("maGrdMngDetailService");
        
        MaGrdMngDetailDTO maGrdMngDetailDTO = maGrdMngDetailForm.getMaGrdMngDetailDTO();
        
        maGrdMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdMngDetailService.updateDetail(maGrdMngDetailDTO);
        
        setAjaxStatus(request);
    }
}