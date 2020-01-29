package dream.consult.program.config.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.config.dto.MaConfigCommonDTO;
import dream.consult.program.config.dto.MaConfigDetailDTO;
import dream.consult.program.config.form.MaConfigDetailForm;
import dream.consult.program.config.service.MaConfigDetailService;

/**
 * �ý��� ȯ�溯�� - �� action
 * 
 * @author kim2107
 * @version $Id: MaConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maConfigDetail" name="maConfigDetailForm"
 *                input="/dream/consult/program/config/maConfigDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maConfigDetail" path="/dream/consult/program/config/maConfigDetail.jsp"
 *                        redirect="false"
 */
public class MaConfigDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int CONFIG_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int CONFIG_DETAIL_UPDATE 	= 1002;
    /** ���� */
    public static final int CONFIG_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaConfigDetailForm maConfigDetailForm = (MaConfigDetailForm) form;
        
        switch (maConfigDetailForm.getStrutsAction())
        {
            case MaConfigDetailAction.CONFIG_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maConfigDetailForm);
                returnActionForward = mapping.findForward("maConfigDetail");
                break;
            case MaConfigDetailAction.CONFIG_DETAIL_UPDATE:
            	updateDetail(maConfigDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaConfigDetailAction.CONFIG_DETAIL_INPUT:
            	insertDetail(maConfigDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maConfigDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �ý���ȯ�溯�� �� ��ȸ
     * @author kim2107
     * @version $Id: MaConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maConfigDetailForm
     */
    private void findDetail(HttpServletRequest request, MaConfigDetailForm maConfigDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaConfigDetailService maConfigDetailService = (MaConfigDetailService)getBean("maConfigDetailService");

    	MaConfigCommonDTO maConfigCommonDTO = maConfigDetailForm.getMaConfigCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaConfigDetailDTO maConfigDetailDTO = maConfigDetailService.findDetail(maConfigCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maConfigDetailForm.setMaConfigDetailDTO(maConfigDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailForm
     * @param request
     */
    private void updateDetail(MaConfigDetailForm maConfigDetailForm, HttpServletRequest request) throws Exception
    {
    	MaConfigDetailService maConfigDetailService = (MaConfigDetailService) getBean("maConfigDetailService");
        
        MaConfigDetailDTO maConfigDetailDTO = maConfigDetailForm.getMaConfigDetailDTO();
        
        maConfigDetailService.updateDetail(maConfigDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailForm
     * @param request
     */
    private void insertDetail(MaConfigDetailForm maConfigDetailForm, HttpServletRequest request) throws Exception
    {
        MaConfigDetailService maConfigDetailService = (MaConfigDetailService) getBean("maConfigDetailService");
        
        MaConfigDetailDTO maConfigDetailDTO = maConfigDetailForm.getMaConfigDetailDTO();
        
        maConfigDetailService.insertDetail(maConfigDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}