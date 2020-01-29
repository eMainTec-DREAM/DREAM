package dream.asset.categ.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;
import dream.asset.categ.list.form.MaEqCatalogDetailForm;
import dream.asset.categ.list.service.MaEqCatalogDetailService;

/**
 * 설비종류 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaEqCatalogDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maEqCatalogDetail" name="maEqCatalogDetailForm"
 *                input="/dream/asset/categ/list/maEqCatalogDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCatalogDetail" path="/dream/asset/categ/list/maEqCatalogDetail.jsp"
 *                        redirect="false"
 */
public class MaEqCatalogDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_CATALOG_DETAIL_INIT 			= 8001;
    /** 저장 */
    public static final int EQ_CATALOG_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int EQ_CATALOG_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqCatalogDetailForm maEqCatalogDetailForm = (MaEqCatalogDetailForm) form;
        
        super.updateAudit(maEqCatalogDetailForm.getMaEqCatalogDetailDTO().getAuditKey()==""?maEqCatalogDetailForm.getMaEqCatalogCommonDTO().getAuditKey():maEqCatalogDetailForm.getMaEqCatalogDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqCatalogDetailForm.getStrutsAction())
        {
            case MaEqCatalogDetailAction.EQ_CATALOG_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqCatalogDetailForm);
                returnActionForward = mapping.findForward("maEqCatalogDetail");
                break;
            case MaEqCatalogDetailAction.EQ_CATALOG_DETAIL_INPUT:
            	insertDetail(maEqCatalogDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCatalogDetailAction.EQ_CATALOG_DETAIL_UPDATE:
            	updateDetail(maEqCatalogDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqCatalogDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 설비종류 상세 조회
     * @author kim2107
     * @version $Id: MaEqCatalogDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCatalogDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqCatalogDetailForm maEqCatalogDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqCatalogDetailService maEqCatalogDetailService = (MaEqCatalogDetailService)getBean("maEqCatalogDetailService");

        // 설비종류Id 구함
        String eqCtgId = maEqCatalogDetailForm.getMaEqCatalogCommonDTO().getEqCtgId();
        
        // 조회된 상세 부분
        MaEqCatalogDetailDTO maEqCatalogDetailDTO = maEqCatalogDetailService.findDetail(eqCtgId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maEqCatalogDetailForm.setMaEqCatalogDetailDTO(maEqCatalogDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqCatalogDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogDetailForm
     * @param request
     */
    private void insertDetail(MaEqCatalogDetailForm maEqCatalogDetailForm, HttpServletRequest request) throws Exception
    {
        MaEqCatalogDetailService maEqCatalogDetailService = (MaEqCatalogDetailService) getBean("maEqCatalogDetailService");
        
        MaEqCatalogDetailDTO maEqCatalogDetailDTO = maEqCatalogDetailForm.getMaEqCatalogDetailDTO();
        
        maEqCatalogDetailService.insertDetail(maEqCatalogDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqCatalogDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogDetailForm
     * @param request
     */
    private void updateDetail(MaEqCatalogDetailForm maEqCatalogDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqCatalogDetailService maEqCatalogDetailService = (MaEqCatalogDetailService) getBean("maEqCatalogDetailService");
        
        MaEqCatalogDetailDTO maEqCatalogDetailDTO = maEqCatalogDetailForm.getMaEqCatalogDetailDTO();
        
        maEqCatalogDetailService.updateDetail(maEqCatalogDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}