package dream.note.dayrpt.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;
import dream.note.dayrpt.form.MaWoDayRptDetailForm;
import dream.note.dayrpt.service.MaWoDayRptDetailService;

/**
 * �������� - �� action
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maWoDayRptDetail" name="maWoDayRptDetailForm"
 *                input="/dream/note/dayrpt/maWoDayRptDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDayRptDetail" path="/dream/note/dayrpt/maWoDayRptDetail.jsp"
 *                        redirect="false"
 */
public class MaWoDayRptDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT         = 8001;
    /** ���� */
    public static final int DETAIL_UPDATE       = 6002;
    /** ���� */
    public static final int DETAIL_INPUT        = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDayRptDetailForm maWoDayRptDetailForm = (MaWoDayRptDetailForm) form;
        
        super.updateAudit(maWoDayRptDetailForm.getMaWoDayRptDetailDTO().getAuditKey()==""?maWoDayRptDetailForm.getMaWoDayRptCommonDTO().getAuditKey():maWoDayRptDetailForm.getMaWoDayRptDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoDayRptDetailForm.getStrutsAction())
        {
            case MaWoDayRptDetailAction.DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoDayRptDetailForm);
                returnActionForward = mapping.findForward("maWoDayRptDetail");
                break;
            case MaWoDayRptDetailAction.DETAIL_UPDATE:
            	updateDetail(maWoDayRptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoDayRptDetailAction.DETAIL_INPUT:
            	insertDetail(maWoDayRptDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maWoDayRptDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     *  �� ��ȸ
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoDayRptDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoDayRptDetailForm maWoDayRptDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoDayRptDetailService maWoDayRptDetailService = (MaWoDayRptDetailService)getBean("maWoDayRptDetailService");
    	
    	MaWoDayRptCommonDTO maWoDayRptCommonDTO = maWoDayRptDetailForm.getMaWoDayRptCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaWoDayRptDetailDTO maWoDayRptDetailDTO = maWoDayRptDetailService.findDetail(maWoDayRptCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maWoDayRptDetailForm.setMaWoDayRptDetailDTO(maWoDayRptDetailDTO);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDayRptDetailForm
     * @param request
     */
    private void updateDetail(MaWoDayRptDetailForm maWoDayRptDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoDayRptDetailService maWoDayRptDetailService = (MaWoDayRptDetailService) getBean("maWoDayRptDetailService");
        
        MaWoDayRptDetailDTO maWoDayRptDetailDTO = maWoDayRptDetailForm.getMaWoDayRptDetailDTO();
        
        maWoDayRptDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoDayRptDetailService.updateDetail(maWoDayRptDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDayRptDetailForm
     * @param request
     */
    private void insertDetail(MaWoDayRptDetailForm maWoDayRptDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoDayRptDetailService maWoDayRptDetailService = (MaWoDayRptDetailService) getBean("maWoDayRptDetailService");
        
        MaWoDayRptDetailDTO maWoDayRptDetailDTO = maWoDayRptDetailForm.getMaWoDayRptDetailDTO();
        
        maWoDayRptDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoDayRptDetailService.insertDetail(maWoDayRptDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}