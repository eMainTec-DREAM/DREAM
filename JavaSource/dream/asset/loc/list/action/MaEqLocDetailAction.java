package dream.asset.loc.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.loc.list.dto.MaEqLocDetailDTO;
import dream.asset.loc.list.form.MaEqLocDetailForm;
import dream.asset.loc.list.service.MaEqLocDetailService;

/**
 * ������ġ - ��
 * 
 * @author kim2107
 * @version $Id: MaEqLocDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maEqLocDetail" name="maEqLocDetailForm"
 *                input="/dream/asset/loc/list/maEqLocDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqLocDetail" path="/dream/asset/loc/list/maEqLocDetail.jsp"
 *                        redirect="false"
 */
public class MaEqLocDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_LOC_DETAIL_INIT 			= 8001;
    /** ���� */
    public static final int EQ_LOC_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int EQ_LOC_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqLocDetailForm maEqLocDetailForm = (MaEqLocDetailForm) form;
        
        super.updateAudit(maEqLocDetailForm.getMaEqLocDetailDTO().getAuditKey()==""?maEqLocDetailForm.getMaEqLocCommonDTO().getAuditKey():maEqLocDetailForm.getMaEqLocDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqLocDetailForm.getStrutsAction())
        {
            case MaEqLocDetailAction.EQ_LOC_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqLocDetailForm);
                returnActionForward = mapping.findForward("maEqLocDetail");
                break;
            case MaEqLocDetailAction.EQ_LOC_DETAIL_INPUT:
            	insertDetail(maEqLocDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqLocDetailAction.EQ_LOC_DETAIL_UPDATE:
            	updateDetail(maEqLocDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqLocDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ������ġ �� ��ȸ
     * @author kim2107
     * @version $Id: MaEqLocDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqLocDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqLocDetailForm maEqLocDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqLocDetailService maEqLocDetailService = (MaEqLocDetailService)getBean("maEqLocDetailService");

        // ��ġ�з�ID
        String eqLocId = maEqLocDetailForm.getMaEqLocCommonDTO().getEqLocId();
        // �����ڵ�
        String compNo = getUser(request).getCompNo();
        
        // ��ȸ�� �� �κ�
        MaEqLocDetailDTO maEqLocDetailDTO = maEqLocDetailService.findDetail(eqLocId,getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maEqLocDetailForm.setMaEqLocDetailDTO(maEqLocDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqLocDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailForm
     * @param request
     */
    private void insertDetail(MaEqLocDetailForm maEqLocDetailForm, HttpServletRequest request) throws Exception
    {
        MaEqLocDetailService maEqLocDetailService = (MaEqLocDetailService) getBean("maEqLocDetailService");
        
        MaEqLocDetailDTO maEqLocDetailDTO = maEqLocDetailForm.getMaEqLocDetailDTO();
        
        maEqLocDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqLocDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maEqLocDetailService.insertDetail(maEqLocDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqLocDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailForm
     * @param request
     */
    private void updateDetail(MaEqLocDetailForm maEqLocDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqLocDetailService maEqLocDetailService = (MaEqLocDetailService) getBean("maEqLocDetailService");
        
        MaEqLocDetailDTO maEqLocDetailDTO = maEqLocDetailForm.getMaEqLocDetailDTO();
        
        maEqLocDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqLocDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maEqLocDetailService.updateDetail(maEqLocDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}