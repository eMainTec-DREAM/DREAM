package dream.asset.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistDetailDTO;
import dream.asset.list.form.MaEqMstrMoldModHistDetailForm;
import dream.asset.list.service.MaEqMstrMoldModHistDetailService;

/**
 * ��������
 * @author  kim21017
 * @version $Id: MaEqMstrMoldModHistDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrMoldModHistDetail" name="maEqMstrMoldModHistDetailForm"
 *                input="/dream/asset/list/maEqMstrMoldModHistDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMoldMstrModHistDetail" name="maEqMstrMoldModHistDetailForm"
 *                input="/dream/asset/list/maEqMoldMstrModHistDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMoldMstrModHistDetail" path="/dream/asset/list/maEqMoldMstrModHistDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrMoldModHistDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_MSTR_MOLDPROD_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int EQ_MSTR_MOLDPROD_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int EQ_MSTR_MOLDPROD_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrMoldModHistDetailForm maEqMstrMoldModHistDetailForm = (MaEqMstrMoldModHistDetailForm) form;
       
        super.updateAudit(maEqMstrMoldModHistDetailForm.getMaEqMstrMoldModHistDetailDTO().getAuditKey()==""?maEqMstrMoldModHistDetailForm.getMaEqMstrMoldModHistListDTO().getAuditKey():maEqMstrMoldModHistDetailForm.getMaEqMstrMoldModHistDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqMstrMoldModHistDetailForm.getStrutsAction())
        {
            case MaEqMstrMoldModHistDetailAction.EQ_MSTR_MOLDPROD_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqMstrMoldModHistDetailForm);
                returnActionForward = mapping.findForward("maEqMoldMstrModHistDetail");
                break;
            case MaEqMstrMoldModHistDetailAction.EQ_MSTR_MOLDPROD_DETAIL_UPDATE:
            	updateDetail(maEqMstrMoldModHistDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrMoldModHistDetailAction.EQ_MSTR_MOLDPROD_DETAIL_INPUT:
            	insertDetail(maEqMstrMoldModHistDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMoldMstrModHistDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �������� ��ȸ 
     * @author kim2107
     * @version $Id: MaEqMstrMoldModHistDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrMoldModHistDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrMoldModHistDetailForm maEqMstrMoldModHistDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqMstrMoldModHistDetailService maEqMstrMoldModHistDetailService = (MaEqMstrMoldModHistDetailService)getBean("maEqMstrMoldModHistDetailService");

    	// ����Id ����
        String equipId = maEqMstrMoldModHistDetailForm.getMaEqMstrCommonDTO().getEquipId();
        // ��������id ����
        String eqMoldModHistId = maEqMstrMoldModHistDetailForm.getMaEqMstrMoldModHistListDTO().getEqMoldModHistId();
        
        // ��ȸ�� �� �κ�
        MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO = maEqMstrMoldModHistDetailService.findDetail(equipId, eqMoldModHistId, getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        maEqMstrMoldModHistDetailForm.setMaEqMstrMoldModHistDetailDTO(maEqMstrMoldModHistDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrMoldModHistDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldModHistDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrMoldModHistDetailForm maEqMstrMoldModHistDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrMoldModHistDetailService maEqMstrMoldModHistDetailService = (MaEqMstrMoldModHistDetailService) getBean("maEqMstrMoldModHistDetailService");
        
        MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO = maEqMstrMoldModHistDetailForm.getMaEqMstrMoldModHistDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrMoldModHistDetailForm.getMaEqMstrCommonDTO();
        maEqMstrMoldModHistDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrMoldModHistDetailService.updateDetail(maEqMstrMoldModHistDetailDTO,maEqMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrMoldModHistDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldModHistDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrMoldModHistDetailForm maEqMstrMoldModHistDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrMoldModHistDetailService maEqMstrMoldModHistDetailService = (MaEqMstrMoldModHistDetailService) getBean("maEqMstrMoldModHistDetailService");
        
        MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO = maEqMstrMoldModHistDetailForm.getMaEqMstrMoldModHistDetailDTO();
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrMoldModHistDetailForm.getMaEqMstrCommonDTO();
        maEqMstrMoldModHistDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrMoldModHistDetailService.insertDetail(maEqMstrMoldModHistDetailDTO, maEqMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}