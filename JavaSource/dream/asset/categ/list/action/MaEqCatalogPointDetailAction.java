package dream.asset.categ.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointDetailDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;
import dream.asset.categ.list.form.MaEqCatalogPointDetailForm;
import dream.asset.categ.list.service.MaEqCatalogPointDetailService;

/**
 * ���������� �����׸� �� ������ action
 * @author  euna0207
 * @version $Id: MaEqCatalogPointDetailAction.java,v 1.0 2015/12/04 09:09:30 euna0207 Exp $
 * @since   1.0
 * @struts:action path="/maEqCatalogPointDetail" name="maEqCatalogPointDetailForm"
 *                input="/dream/asset/categ/list/maEqCatalogPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCatalogPointDetail" path="/dream/asset/categ/list/maEqCatalogPointDetail.jsp"
 *                        redirect="false"
 */
public class MaEqCatalogPointDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_MSTR_PMWORK_DETAIL_INIT 			= 1001;
    /** ���� */
    public static final int EQ_MSTR_PMWORK_DETAIL_UPDATE 		= 1002;
    /** �Է� */
    public static final int EQ_MSTR_PMWORK_DETAIL_INPUT 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqCatalogPointDetailForm maEqCatalogPointDetailForm = (MaEqCatalogPointDetailForm) form;

        super.updateAudit(maEqCatalogPointDetailForm.getMaEqCatalogPointDetailDTO().getAuditKey()==""?maEqCatalogPointDetailForm.getMaEqCatalogPointListDTO().getAuditKey():maEqCatalogPointDetailForm.getMaEqCatalogPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
           
        switch (maEqCatalogPointDetailForm.getStrutsAction())
        {
            case MaEqCatalogPointDetailAction.EQ_MSTR_PMWORK_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqCatalogPointDetailForm);
                returnActionForward = mapping.findForward("maEqCatalogPointDetail");
                break;
            case MaEqCatalogPointDetailAction.EQ_MSTR_PMWORK_DETAIL_UPDATE:
            	updateDetail(maEqCatalogPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCatalogPointDetailAction.EQ_MSTR_PMWORK_DETAIL_INPUT:
            	insertDetail(maEqCatalogPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqCatalogPointDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���� ��ȸ 
     * @author euna0207
     * @version $Id: MaEqCatalogPointDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCatalogPointDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqCatalogPointDetailForm maEqCatalogPointDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqCatalogPointDetailService maEqCatalogPointDetailService = (MaEqCatalogPointDetailService)getBean("maEqCatalogPointDetailService");

    	MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCatalogPointDetailForm.getMaEqCatalogCommonDTO();
    	MaEqCatalogPointListDTO maEqCatalogPointListDTO = maEqCatalogPointDetailForm.getMaEqCatalogPointListDTO();

        // ��ȸ�� �� �κ�
        MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO = maEqCatalogPointDetailService.findDetail(maEqCatalogCommonDTO, maEqCatalogPointListDTO, getUser(request));
        
        // ��ȸ�� �� �����Ѵ�.
        maEqCatalogPointDetailForm.setMaEqCatalogPointDetailDTO(maEqCatalogPointDetailDTO);
    }
    /**
     * detail update
     * @author  euna0207
     * @version $Id: MaEqCatalogPointDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogPointDetailForm
     * @param request
     */
    private void updateDetail(MaEqCatalogPointDetailForm maEqCatalogPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqCatalogPointDetailService maEqCatalogPointDetailService = (MaEqCatalogPointDetailService) getBean("maEqCatalogPointDetailService");
        
        MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO = maEqCatalogPointDetailForm.getMaEqCatalogPointDetailDTO();
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCatalogPointDetailForm.getMaEqCatalogCommonDTO();
        
        maEqCatalogPointDetailService.updateDetail(maEqCatalogCommonDTO,maEqCatalogPointDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  euna0207
     * @version $Id: MaEqCatalogPointDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogPointDetailForm
     * @param request
     */
    private void insertDetail(MaEqCatalogPointDetailForm maEqCatalogPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqCatalogPointDetailService maEqCatalogPointDetailService = (MaEqCatalogPointDetailService) getBean("maEqCatalogPointDetailService");
        
        MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO = maEqCatalogPointDetailForm.getMaEqCatalogPointDetailDTO();
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCatalogPointDetailForm.getMaEqCatalogCommonDTO();
        
        maEqCatalogPointDetailService.insertDetail(maEqCatalogCommonDTO, maEqCatalogPointDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
}