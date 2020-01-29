package dream.asset.categ.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;
import dream.asset.categ.list.form.MaEqCtgPartDetailForm;
import dream.asset.categ.list.service.MaEqCtgPartDetailService;

/**
 * ���������� ��ǰ
 * @author  kim21017
 * @version $Id: MaEqCtgPartDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqCtgPartDetail" name="maEqCtgPartDetailForm"
 *                input="/dream/asset/categ/list/maEqCtgPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCtgPartDetail" path="/dream/asset/categ/list/maEqCtgPartDetail.jsp"
 *                        redirect="false"
 */
public class MaEqCtgPartDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_CTG_PART_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int EQ_CTG_PART_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int EQ_CTG_PART_DETAIL_INPUT 	= 5003;
    /** ���� */
    public static final int DETAIL_COPY 				= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqCtgPartDetailForm maEqCtgPartDetailForm = (MaEqCtgPartDetailForm) form;
        
        super.updateAudit(maEqCtgPartDetailForm.getMaEqCtgPartDetailDTO().getAuditKey()==""?maEqCtgPartDetailForm.getMaEqCtgPartListDTO().getAuditKey():maEqCtgPartDetailForm.getMaEqCtgPartDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqCtgPartDetailForm.getStrutsAction())
        {
            case MaEqCtgPartDetailAction.EQ_CTG_PART_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqCtgPartDetailForm);
                returnActionForward = mapping.findForward("maEqCtgPartDetail");
                break;
            case MaEqCtgPartDetailAction.EQ_CTG_PART_DETAIL_UPDATE:
            	updateDetail(maEqCtgPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCtgPartDetailAction.EQ_CTG_PART_DETAIL_INPUT:
            	insertDetail(maEqCtgPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCtgPartDetailAction.DETAIL_COPY:
            	copyDetail(request,response, maEqCtgPartDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqCtgPartDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���������� ��ǰ �� ��ȸ
     * @author kim2107
     * @version $Id: MaEqCtgPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgPartDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqCtgPartDetailForm maEqCtgPartDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqCtgPartDetailService maEqCtgPartDetailService = (MaEqCtgPartDetailService)getBean("maEqCtgPartDetailService");
    	
    	MaEqCtgPartListDTO maEqCtgPartListDTO = maEqCtgPartDetailForm.getMaEqCtgPartListDTO();

        // ��ȸ�� �� �κ�
    	MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = maEqCtgPartDetailService.findDetail(maEqCtgPartListDTO, getUser(request));
    	
        // ��ȸ�� ��  �����Ѵ�.
        maEqCtgPartDetailForm.setMaEqCtgPartDetailDTO(maEqCtgPartDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqCtgPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartDetailForm
     * @param request
     */
    private void updateDetail(MaEqCtgPartDetailForm maEqCtgPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqCtgPartDetailService maEqCtgPartDetailService = (MaEqCtgPartDetailService) getBean("maEqCtgPartDetailService");
        
        MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = maEqCtgPartDetailForm.getMaEqCtgPartDetailDTO();
        
        maEqCtgPartDetailService.updateDetail(maEqCtgPartDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqCtgPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartDetailForm
     * @param request
     */
    private void insertDetail(MaEqCtgPartDetailForm maEqCtgPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqCtgPartDetailService maEqCtgPartDetailService = (MaEqCtgPartDetailService) getBean("maEqCtgPartDetailService");
        
        MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = maEqCtgPartDetailForm.getMaEqCtgPartDetailDTO();
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCtgPartDetailForm.getMaEqCatalogCommonDTO();
        
        maEqCtgPartDetailService.insertDetail(maEqCtgPartDetailDTO, maEqCatalogCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void copyDetail(HttpServletRequest request, HttpServletResponse response, MaEqCtgPartDetailForm maEqCtgPartDetailForm) throws Exception
    {
    	MaEqCtgPartDetailService maEqCtgPartDetailService = (MaEqCtgPartDetailService) getBean("maEqCtgPartDetailService");
    	
    	MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = maEqCtgPartDetailForm.getMaEqCtgPartDetailDTO();
    	MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCtgPartDetailForm.getMaEqCatalogCommonDTO();

    	String oldEqCtgId = maEqCatalogCommonDTO.getEqCtgId();
    	String newEqCtgId = maEqCatalogCommonDTO.getEqCtgId();
    	String oldKeyId   = maEqCtgPartDetailDTO.getOldEqCtgPartId();
    	String newKeyId   = maEqCtgPartDetailDTO.getEqCtgPartId();
    	
    	User user = getUser(request);
    	
    	String result = maEqCtgPartDetailService.copyDetail(oldEqCtgId, newEqCtgId, oldKeyId, newKeyId, user);
    	setAjaxStatus(request, result);
    }
}