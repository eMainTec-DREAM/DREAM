package dream.asset.categ.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;
import dream.asset.categ.list.form.MaEqCtgSpecDetailForm;
import dream.asset.categ.list.service.MaEqCtgSpecDetailService;

/**
 * ���������� ǥ������
 * @author  syyang
 * @version $Id: MaEqCtgSpecDetailAction.java,v 1.0 2015/12/04 09:09:30 syyang Exp $
 * @since   1.0
 * @struts:action path="/maEqCtgSpecDetail" name="maEqCtgSpecDetailForm"
 *                input="/dream/asset/categ/list/maEqCtgSpecDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCtgSpecDetail" path="/dream/asset/categ/list/maEqCtgSpecDetail.jsp"
 *                        redirect="false"
 */
public class MaEqCtgSpecDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_CTG_SPEC_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int EQ_CTG_SPEC_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int EQ_CTG_SPEC_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqCtgSpecDetailForm maEqCtgSpecDetailForm = (MaEqCtgSpecDetailForm) form;
        
        super.updateAudit(maEqCtgSpecDetailForm.getMaEqCtgSpecDetailDTO().getAuditKey()==""?maEqCtgSpecDetailForm.getMaEqCtgSpecListDTO().getAuditKey():maEqCtgSpecDetailForm.getMaEqCtgSpecDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqCtgSpecDetailForm.getStrutsAction())
        {
            case MaEqCtgSpecDetailAction.EQ_CTG_SPEC_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqCtgSpecDetailForm);
                returnActionForward = mapping.findForward("maEqCtgSpecDetail");
                break;
            case MaEqCtgSpecDetailAction.EQ_CTG_SPEC_DETAIL_UPDATE:
            	updateDetail(maEqCtgSpecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCtgSpecDetailAction.EQ_CTG_SPEC_DETAIL_INPUT:
            	insertDetail(maEqCtgSpecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqCtgSpecDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���������� ���� �� ��ȸ
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgSpecDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqCtgSpecDetailForm maEqCtgSpecDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqCtgSpecDetailService maEqCtgSpecDetailService = (MaEqCtgSpecDetailService)getBean("maEqCtgSpecDetailService");

    	MaEqCtgSpecListDTO maEqCtgSpecListDTO = maEqCtgSpecDetailForm.getMaEqCtgSpecListDTO();

    	// ��ȸ�� �� �κ�
        MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO = maEqCtgSpecDetailService.findDetail(maEqCtgSpecListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maEqCtgSpecDetailForm.setMaEqCtgSpecDetailDTO(maEqCtgSpecDetailDTO);
    }
    /**
     * detail update
     * @author  syyang
     * @version $Id: MaEqCtgSpecDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecDetailForm
     * @param request
     */
    private void updateDetail(MaEqCtgSpecDetailForm maEqCtgSpecDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqCtgSpecDetailService maEqCtgSpecDetailService = (MaEqCtgSpecDetailService) getBean("maEqCtgSpecDetailService");
        
        MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO = maEqCtgSpecDetailForm.getMaEqCtgSpecDetailDTO();
        
        maEqCtgSpecDetailService.updateDetail(maEqCtgSpecDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  syyang
     * @version $Id: MaEqCtgSpecDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecDetailForm
     * @param request
     */
    private void insertDetail(MaEqCtgSpecDetailForm maEqCtgSpecDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqCtgSpecDetailService maEqCtgSpecDetailService = (MaEqCtgSpecDetailService) getBean("maEqCtgSpecDetailService");
        
        MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO = maEqCtgSpecDetailForm.getMaEqCtgSpecDetailDTO();
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCtgSpecDetailForm.getMaEqCatalogCommonDTO();
        
        maEqCtgSpecDetailService.insertDetail(maEqCtgSpecDetailDTO, maEqCatalogCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}