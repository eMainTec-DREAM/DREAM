package dream.consult.program.help.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.DateUtil;
import dream.consult.program.help.dto.MaHelpCommonDTO;
import dream.consult.program.help.dto.MaHelpDetailDTO;
import dream.consult.program.help.form.MaHelpDetailForm;
import dream.consult.program.help.service.MaHelpDetailService;

/**
 * �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maHelpDetail" name="maHelpDetailForm"
 *                input="/dream/consult/program/help/maHelpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maHelpDetail" path="/dream/consult/program/help/maHelpDetail.jsp"
 *                        redirect="false"
 */
public class MaHelpDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int HELP_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int HELP_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int HELP_DETAIL_UPDATE 		= 6003;
    /** ��û */
    public static final int HELP_DETAIL_REQUEST		= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaHelpDetailForm maHelpDetailForm = (MaHelpDetailForm) form;
        
        super.updateAudit(maHelpDetailForm.getMaHelpDetailDTO().getAuditKey()==""?maHelpDetailForm.getMaHelpCommonDTO().getAuditKey():maHelpDetailForm.getMaHelpDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maHelpDetailForm.getStrutsAction())
        {
            case MaHelpDetailAction.HELP_DETAIL_INIT:
                this.findDetail(request, maHelpDetailForm);
                returnActionForward = mapping.findForward("maHelpDetail");
                break;
            case MaHelpDetailAction.HELP_DETAIL_INPUT:
            	insertDetail(maHelpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaHelpDetailAction.HELP_DETAIL_UPDATE:
            	updateDetail(maHelpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaHelpDetailAction.HELP_DETAIL_REQUEST:
            	requestDetail(maHelpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maHelpDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void requestDetail(MaHelpDetailForm maHelpDetailForm, HttpServletRequest request) throws Exception 
    {
    	MaHelpDetailService maHelpDetailService = (MaHelpDetailService) getBean("maHelpDetailService");
        
        MaHelpDetailDTO maHelpDetailDTO = maHelpDetailForm.getMaHelpDetailDTO();
        maHelpDetailDTO.setCompNo((getUser(request).getCompNo()));
        maHelpDetailDTO.setReqBy(getUser(request).getEmpId());
        maHelpDetailDTO.setReqDate(DateUtil.getDateTime("yyyy-MM-dd"));
        maHelpDetailDTO.setLoginUser(getUser(request));
        
        maHelpDetailService.requestDetail(maHelpDetailDTO);
        
        setAjaxStatus(request);
	}

	/**
     * �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maHelpDetailForm
     */
    private void findDetail(HttpServletRequest request, MaHelpDetailForm maHelpDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaHelpDetailService maHelpDetailService = (MaHelpDetailService)getBean("maHelpDetailService");
        
    	MaHelpCommonDTO maHelpCommonDTO = maHelpDetailForm.getMaHelpCommonDTO();
    	maHelpCommonDTO.setCompNo(getUser(request).getCompNo());
    	maHelpCommonDTO.setLoginUser(getUser(request));
    	
        // ��ȸ�� �� �κ�
        MaHelpDetailDTO maHelpDetailDTO = maHelpDetailService.findDetail( maHelpDetailForm.getMaHelpCommonDTO());
        
        // ��ȸ�� ��  �����Ѵ�.
        maHelpDetailForm.setMaHelpDetailDTO(maHelpDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maHelpDetailForm
     * @param request
     */
    private void insertDetail(MaHelpDetailForm maHelpDetailForm, HttpServletRequest request) throws Exception
    {
        MaHelpDetailService maHelpDetailService = (MaHelpDetailService) getBean("maHelpDetailService");
        
        MaHelpDetailDTO maHelpDetailDTO = maHelpDetailForm.getMaHelpDetailDTO();
        
        maHelpDetailDTO.setCompNo((getUser(request).getCompNo()));
        maHelpDetailDTO.setLoginUser(getUser(request));
        
        maHelpDetailService.insertDetail(maHelpDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maHelpDetailForm
     * @param request
     */
    private void updateDetail(MaHelpDetailForm maHelpDetailForm, HttpServletRequest request) throws Exception
    {
    	MaHelpDetailService maHelpDetailService = (MaHelpDetailService) getBean("maHelpDetailService");
        
        MaHelpDetailDTO maHelpDetailDTO = maHelpDetailForm.getMaHelpDetailDTO();
        
        maHelpDetailDTO.setCompNo((getUser(request).getCompNo()));
        maHelpDetailDTO.setLoginUser(getUser(request));
        
        maHelpDetailService.updateDetail(maHelpDetailDTO);
        
        setAjaxStatus(request);
    }

}