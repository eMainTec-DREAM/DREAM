package dream.consult.comp.time.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDetailDTO;
import dream.consult.comp.time.form.MaLineTimeDetailForm;
import dream.consult.comp.time.service.MaLineTimeDetailService;

/**
 * �����ð����� - ��
 * 
 * @author kim2107
 * @version $Id: MaLineTimeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maLineTimeDetail" name="maLineTimeDetailForm"
 *                input="/dream/consult/comp/time/maLineTimeDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLineTimeDetail" path="/dream/consult/comp/time/maLineTimeDetail.jsp"
 *                        redirect="false"
 */
public class MaLineTimeDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int LINE_DETAIL_INIT 			= 1001;
    /** ���� */
    public static final int LINE_DETAIL_INPUT 			= 1002;
    /** ���� */
    public static final int LINE_DETAIL_UPDATE          = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaLineTimeDetailForm maLineTimeDetailForm = (MaLineTimeDetailForm) form;
        
        switch (maLineTimeDetailForm.getStrutsAction())
        {
            case MaLineTimeDetailAction.LINE_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maLineTimeDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaLineTimeDetailAction.LINE_DETAIL_INPUT:
                insertDetail(maLineTimeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaLineTimeDetailAction.LINE_DETAIL_UPDATE:
            	updateDetail(maLineTimeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    

    /**
     * ������ġ �� ��ȸ
     * @author kim2107
     * @version $Id: MaLineTimeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLineTimeDetailForm
     */
    private void findDetail(HttpServletRequest request, MaLineTimeDetailForm maLineTimeDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaLineTimeDetailService maLineTimeDetailService = (MaLineTimeDetailService)getBean("maLineTimeDetailService");

    	MaLineTimeCommonDTO maLineTimeCommonDTO = maLineTimeDetailForm.getMaLineTimeCommonDTO();
        // ��ȸ�� �� �κ�
    	MaLineTimeDetailDTO maLineTimeDetailDTO = maLineTimeDetailService.findDetail(maLineTimeCommonDTO,getUser(request));
        // ��ȸ�� ��  �����Ѵ�.
        maLineTimeDetailForm.setMaLineTimeDetailDTO(maLineTimeDetailDTO);
    }
    
    /**
     * insert update
     * @author  ghlee
     * @version $Id: MaLineTimeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDetailForm
     * @param request
     */
    private void insertDetail(MaLineTimeDetailForm maLineTimeDetailForm, HttpServletRequest request) throws Exception
    {
        MaLineTimeDetailService maLineTimeDetailService = (MaLineTimeDetailService) getBean("maLineTimeDetailService");
        
        MaLineTimeDetailDTO maLineTimeDetailDTO = maLineTimeDetailForm.getMaLineTimeDetailDTO();
        
        maLineTimeDetailService.insertDetail(maLineTimeDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaLineTimeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDetailForm
     * @param request
     */
    private void updateDetail(MaLineTimeDetailForm maLineTimeDetailForm, HttpServletRequest request) throws Exception
    {
    	MaLineTimeDetailService maLineTimeDetailService = (MaLineTimeDetailService) getBean("maLineTimeDetailService");
        
        MaLineTimeDetailDTO maLineTimeDetailDTO = maLineTimeDetailForm.getMaLineTimeDetailDTO();
        
        maLineTimeDetailService.updateDetail(maLineTimeDetailDTO);
        
        setAjaxStatus(request);
    }
}