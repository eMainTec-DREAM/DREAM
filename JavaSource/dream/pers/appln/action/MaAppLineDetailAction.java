package dream.pers.appln.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineDetailDTO;
import dream.pers.appln.form.MaAppLineDetailForm;
import dream.pers.appln.service.MaAppLineDetailService;

/**
 * �� action
 * 
 * @author kim2107
 * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maAppLineDetail" name="maAppLineDetailForm"
 *                input="/dream/pers/appln/maAppLineDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maAppLinePopupDetail" name="maAppLineDetailForm"
 *                input="/dream/pers/appln/maAppLinePopupDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maAppLineDetail" path="/dream/pers/appln/maAppLineDetail.jsp"
 *                        redirect="false"
 */
public class MaAppLineDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int QNA_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int QNA_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int QNA_DETAIL_INPUT 		= 5003;
    /** ��û�Ϸ� */
    public static final int QNA_DETAIL_CONFIRM 		= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaAppLineDetailForm maAppLineDetailForm = (MaAppLineDetailForm) form;
        
        super.updateAudit(maAppLineDetailForm.getMaAppLineDetailDTO().getAuditKey()==""?maAppLineDetailForm.getMaAppLineCommonDTO().getAuditKey():maAppLineDetailForm.getMaAppLineDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maAppLineDetailForm.getStrutsAction())
        {
            case MaAppLineDetailAction.QNA_DETAIL_INIT:
                this.findDetail(request, maAppLineDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaAppLineDetailAction.QNA_DETAIL_UPDATE:
            	updateDetail(maAppLineDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaAppLineDetailAction.QNA_DETAIL_INPUT:
            	insertDetail(maAppLineDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaAppLineDetailAction.QNA_DETAIL_CONFIRM:
            	confirmDetail(maAppLineDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���� �� ��ȸ
     * @author kim2107
     * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maAppLineDetailForm
     */
    private void findDetail(HttpServletRequest request, MaAppLineDetailForm maAppLineDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaAppLineDetailService maAppLineDetailService = (MaAppLineDetailService)getBean("maAppLineDetailService");
    	MaAppLineCommonDTO maAppLineCommonDTO = maAppLineDetailForm.getMaAppLineCommonDTO();
        // ��ȸ�� �� �κ�
        MaAppLineDetailDTO maAppLineDetailDTO = maAppLineDetailService.findDetail(maAppLineCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maAppLineDetailForm.setMaAppLineDetailDTO(maAppLineDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailForm
     * @param request
     */
    private void insertDetail(MaAppLineDetailForm maAppLineDetailForm, HttpServletRequest request) throws Exception
    {
    	MaAppLineDetailService maAppLineDetailService = (MaAppLineDetailService) getBean("maAppLineDetailService");
        
        MaAppLineDetailDTO maAppLineDetailDTO = maAppLineDetailForm.getMaAppLineDetailDTO();
        
        maAppLineDetailService.insertDetail(maAppLineDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailForm
     * @param request
     */
    private void updateDetail(MaAppLineDetailForm maAppLineDetailForm, HttpServletRequest request) throws Exception
    {
    	MaAppLineDetailService maAppLineDetailService = (MaAppLineDetailService) getBean("maAppLineDetailService");
        
        MaAppLineDetailDTO maAppLineDetailDTO = maAppLineDetailForm.getMaAppLineDetailDTO();
        
        maAppLineDetailService.updateDetail(maAppLineDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail confirm
     * @author  kim21017
     * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailForm
     * @param request
     */
    private void confirmDetail(MaAppLineDetailForm maAppLineDetailForm, HttpServletRequest request) throws Exception
    {
    	MaAppLineDetailService maAppLineDetailService = (MaAppLineDetailService) getBean("maAppLineDetailService");
        
        MaAppLineDetailDTO maAppLineDetailDTO = maAppLineDetailForm.getMaAppLineDetailDTO();
        
        maAppLineDetailService.confirmDetail(maAppLineDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}