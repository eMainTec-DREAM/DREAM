package dream.asset.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;
import dream.asset.list.form.MaEqMstrPartDetailForm;
import dream.asset.list.service.MaEqMstrPartDetailService;

/**
 * ��������
 * @author  kim21017
 * @version $Id: MaEqMstrPartDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPartDetail" name="maEqMstrPartDetailForm"
 *                input="/dream/asset/list/maEqMstrPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPartDetail" path="/dream/asset/list/maEqMstrPartDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPartDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_MSTR_PART_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int EQ_MSTR_PART_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int EQ_MSTR_PART_DETAIL_INPUT 	= 5003;
    /** ���� */
    public static final int DETAIL_COPY 				= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPartDetailForm maEqMstrPartDetailForm = (MaEqMstrPartDetailForm) form;
        
        super.updateAudit(maEqMstrPartDetailForm.getMaEqMstrPartDetailDTO().getAuditKey()==""?maEqMstrPartDetailForm.getMaEqMstrPartListDTO().getAuditKey():maEqMstrPartDetailForm.getMaEqMstrPartDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maEqMstrPartDetailForm.getStrutsAction())
        {
            case MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqMstrPartDetailForm);
                returnActionForward = mapping.findForward("maEqMstrPartDetail");
                break;
            case MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_UPDATE:
            	updateDetail(maEqMstrPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_INPUT:
            	insertDetail(maEqMstrPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPartDetailAction.DETAIL_COPY:
            	copyDetail(request,response, maEqMstrPartDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPartDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �������� ��ȸ 
     * @author kim2107
     * @version $Id: MaEqMstrPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPartDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrPartDetailForm maEqMstrPartDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqMstrPartDetailService maEqMstrPartDetailService = (MaEqMstrPartDetailService)getBean("maEqMstrPartDetailService");

    	MaEqMstrPartListDTO maEqMstrPartListDTO = maEqMstrPartDetailForm.getMaEqMstrPartListDTO();
        
        // ��ȸ�� �� �κ�
        MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = maEqMstrPartDetailService.findDetail(maEqMstrPartListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maEqMstrPartDetailForm.setMaEqMstrPartDetailDTO(maEqMstrPartDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrPartDetailForm maEqMstrPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPartDetailService maEqMstrPartDetailService = (MaEqMstrPartDetailService) getBean("maEqMstrPartDetailService");
        
        MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = maEqMstrPartDetailForm.getMaEqMstrPartDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPartDetailForm.getMaEqMstrCommonDTO();
        
        maEqMstrPartDetailService.updateDetail(maEqMstrPartDetailDTO, maEqMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrPartDetailForm maEqMstrPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPartDetailService maEqMstrPartDetailService = (MaEqMstrPartDetailService) getBean("maEqMstrPartDetailService");
        
        MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = maEqMstrPartDetailForm.getMaEqMstrPartDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPartDetailForm.getMaEqMstrCommonDTO();
        
        maEqMstrPartDetailService.insertDetail(maEqMstrPartDetailDTO, maEqMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void copyDetail(HttpServletRequest request, HttpServletResponse response, MaEqMstrPartDetailForm maEqMstrPartDetailForm) throws Exception
    {
    	MaEqMstrPartDetailService maEqMstrPartDetailService = (MaEqMstrPartDetailService) getBean("maEqMstrPartDetailService");
    	
    	MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = maEqMstrPartDetailForm.getMaEqMstrPartDetailDTO();

    	User user = getUser(request);

    	String oldEquipId = maEqMstrPartDetailDTO.getEquipId();
    	String newEquipId = maEqMstrPartDetailDTO.getEquipId();
    	String oldKeyId = maEqMstrPartDetailDTO.getOldEqPartId();
    	String newKeyId = maEqMstrPartDetailDTO.getEqPartId();
    	
    	
    	String result = maEqMstrPartDetailService.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, user);
    	
    	setAjaxStatus(request, result);
    }
}