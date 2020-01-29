package dream.asset.categ.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;
import dream.asset.categ.list.form.MaEqCtgAsmbDetailForm;
import dream.asset.categ.list.service.MaEqCtgAsmbDetailService;

/**
 * 설비종류별 작업부위
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqCtgAsmbDetail" name="maEqCtgAsmbDetailForm"
 *                input="/dream/asset/categ/list/maEqCtgAsmbDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqCtgAsmbDetail" path="/dream/asset/categ/list/maEqCtgAsmbDetail.jsp"
 *                        redirect="false"
 */
public class MaEqCtgAsmbDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_CTG_ASMB_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int EQ_CTG_ASMB_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int EQ_CTG_ASMB_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqCtgAsmbDetailForm maEqCtgAsmbDetailForm = (MaEqCtgAsmbDetailForm) form;
        
        super.updateAudit(maEqCtgAsmbDetailForm.getMaEqCtgAsmbDetailDTO().getAuditKey()==""?maEqCtgAsmbDetailForm.getMaEqCtgAsmbListDTO().getAuditKey():maEqCtgAsmbDetailForm.getMaEqCtgAsmbDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqCtgAsmbDetailForm.getStrutsAction())
        {
            case MaEqCtgAsmbDetailAction.EQ_CTG_ASMB_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqCtgAsmbDetailForm);
                returnActionForward = mapping.findForward("maEqCtgAsmbDetail");
                break;
            case MaEqCtgAsmbDetailAction.EQ_CTG_ASMB_DETAIL_UPDATE:
            	updateDetail(maEqCtgAsmbDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqCtgAsmbDetailAction.EQ_CTG_ASMB_DETAIL_INPUT:
            	insertDetail(maEqCtgAsmbDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqCtgAsmbDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 설비종류별 부위 상세 조회
     * @author kim2107
     * @version $Id: MaEqCtgAsmbDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqCtgAsmbDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqCtgAsmbDetailForm maEqCtgAsmbDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqCtgAsmbDetailService maEqCtgAsmbDetailService = (MaEqCtgAsmbDetailService)getBean("maEqCtgAsmbDetailService");
    	
    	MaEqCtgAsmbListDTO maEqCtgAsmbListDTO = maEqCtgAsmbDetailForm.getMaEqCtgAsmbListDTO();

        // 조회된 상세 부분
    	MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO = maEqCtgAsmbDetailService.findDetail(maEqCtgAsmbListDTO,getUser(request));
    	
        // 조회된 상세부분 셋팅한다.
        maEqCtgAsmbDetailForm.setMaEqCtgAsmbDetailDTO(maEqCtgAsmbDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqCtgAsmbDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbDetailForm
     * @param request
     */
    private void updateDetail(MaEqCtgAsmbDetailForm maEqCtgAsmbDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqCtgAsmbDetailService maEqCtgAsmbDetailService = (MaEqCtgAsmbDetailService) getBean("maEqCtgAsmbDetailService");
        
        MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO = maEqCtgAsmbDetailForm.getMaEqCtgAsmbDetailDTO();
        
        maEqCtgAsmbDetailService.updateDetail(maEqCtgAsmbDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqCtgAsmbDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbDetailForm
     * @param request
     */
    private void insertDetail(MaEqCtgAsmbDetailForm maEqCtgAsmbDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqCtgAsmbDetailService maEqCtgAsmbDetailService = (MaEqCtgAsmbDetailService) getBean("maEqCtgAsmbDetailService");
        
        MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO = maEqCtgAsmbDetailForm.getMaEqCtgAsmbDetailDTO();
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = maEqCtgAsmbDetailForm.getMaEqCatalogCommonDTO();

        maEqCtgAsmbDetailService.insertDetail(maEqCtgAsmbDetailDTO, maEqCatalogCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}