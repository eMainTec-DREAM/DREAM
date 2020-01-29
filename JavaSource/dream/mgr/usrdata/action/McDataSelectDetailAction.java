package dream.mgr.usrdata.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;
import dream.mgr.usrdata.form.McDataSelectDetailForm;
import dream.mgr.usrdata.service.McDataSelectDetailService;

/**
 * �޴� - �� action
 * 
 * @author kim2107
 * @version $Id: McDataSelectDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/mcDataSelectDetail" name="mcDataSelectDetailForm"
 *                input="/dream/mgr/usrdata/mcDataSelectDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcDataSelectDetail" path="/dream/mgr/usrdata/mcDataSelectDetail.jsp"
 *                        redirect="false"
 */
public class McDataSelectDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DATA_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int DATA_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int DATA_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        McDataSelectDetailForm mcDataSelectDetailForm = (McDataSelectDetailForm) form;
        
        super.updateAudit(mcDataSelectDetailForm.getMcDataSelectDetailDTO().getAuditKey()==""?mcDataSelectDetailForm.getMcDataSelectCommonDTO().getAuditKey():mcDataSelectDetailForm.getMcDataSelectDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mcDataSelectDetailForm.getStrutsAction())
        {
            case McDataSelectDetailAction.DATA_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, mcDataSelectDetailForm);
                returnActionForward = mapping.findForward("mcDataSelectDetail");
                break;
            case McDataSelectDetailAction.DATA_DETAIL_INPUT:
            	insertDetail(mcDataSelectDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case McDataSelectDetailAction.DATA_DETAIL_UPDATE:
            	updateDetail(mcDataSelectDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mcDataSelectDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: McDataSelectDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mcDataSelectDetailForm
     */
    private void findDetail(HttpServletRequest request, McDataSelectDetailForm mcDataSelectDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	McDataSelectDetailService mcDataSelectDetailService = (McDataSelectDetailService)getBean("mcDataSelectDetailService");

        // �Ѱ��� �޴�Id ����
        String usrrptId = mcDataSelectDetailForm.getMcDataSelectCommonDTO().getUsrrptId();
        
        // ��ȸ�� �� �κ�
        McDataSelectDetailDTO mcDataSelectDetailDTO = mcDataSelectDetailService.findDetail(usrrptId, getUser(request).getLangId());
        
        // ��ȸ�� ��  �����Ѵ�.
        mcDataSelectDetailForm.setMcDataSelectDetailDTO(mcDataSelectDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: McDataSelectDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectDetailForm
     * @param request
     */
    private void insertDetail(McDataSelectDetailForm mcDataSelectDetailForm, HttpServletRequest request) throws Exception
    {
        McDataSelectDetailService mcDataSelectDetailService = (McDataSelectDetailService) getBean("mcDataSelectDetailService");
        
        McDataSelectDetailDTO mcDataSelectDetailDTO = mcDataSelectDetailForm.getMcDataSelectDetailDTO();
        
        mcDataSelectDetailDTO.setEnterBy(getUser(request).getUserId());
        
        mcDataSelectDetailService.insertDetail(mcDataSelectDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: McDataSelectDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectDetailForm
     * @param request
     */
    private void updateDetail(McDataSelectDetailForm mcDataSelectDetailForm, HttpServletRequest request) throws Exception
    {
    	McDataSelectDetailService mcDataSelectDetailService = (McDataSelectDetailService) getBean("mcDataSelectDetailService");
        
        McDataSelectDetailDTO mcDataSelectDetailDTO = mcDataSelectDetailForm.getMcDataSelectDetailDTO();
        
        mcDataSelectDetailDTO.setEnterBy(getUser(request).getUserId());
        
        mcDataSelectDetailService.updateDetail(mcDataSelectDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}