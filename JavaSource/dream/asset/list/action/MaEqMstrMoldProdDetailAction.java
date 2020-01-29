package dream.asset.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdDetailDTO;
import dream.asset.list.form.MaEqMstrMoldProdDetailForm;
import dream.asset.list.service.MaEqMstrMoldProdDetailService;

/**
 * 구성자재
 * @author  kim21017
 * @version $Id: MaEqMstrPartDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrMoldProdDetail" name="maEqMstrMoldProdDetailForm"
 *                input="/dream/asset/list/maEqMstrMoldProdDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMoldMstrProdDetail" name="maEqMstrMoldProdDetailForm"
 *                input="/dream/asset/list/maEqMoldMstrProdDetail.jsp" scope="request"
 *                validate="false" 
 * @struts.action-forward name="maEqMoldMstrProdDetail" path="/dream/asset/list/maEqMoldMstrProdDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrMoldProdDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_MOLDPROD_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int EQ_MSTR_MOLDPROD_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int EQ_MSTR_MOLDPROD_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrMoldProdDetailForm maEqMstrMoldProdDetailForm = (MaEqMstrMoldProdDetailForm) form;
        
        super.updateAudit(maEqMstrMoldProdDetailForm.getMaEqMstrMoldProdDetailDTO().getAuditKey()==""?maEqMstrMoldProdDetailForm.getMaEqMstrMoldProdListDTO().getAuditKey():maEqMstrMoldProdDetailForm.getMaEqMstrMoldProdDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqMstrMoldProdDetailForm.getStrutsAction())
        {
            case MaEqMstrMoldProdDetailAction.EQ_MSTR_MOLDPROD_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqMstrMoldProdDetailForm);
                returnActionForward = mapping.findForward("maEqMoldMstrProdDetail");
                break;
            case MaEqMstrMoldProdDetailAction.EQ_MSTR_MOLDPROD_DETAIL_UPDATE:
            	updateDetail(maEqMstrMoldProdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrMoldProdDetailAction.EQ_MSTR_MOLDPROD_DETAIL_INPUT:
            	insertDetail(maEqMstrMoldProdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMoldMstrProdDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 구성자재 조회 
     * @author kim2107
     * @version $Id: MaEqMstrPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrMoldProdDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrMoldProdDetailForm maEqMstrMoldProdDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqMstrMoldProdDetailService maEqMstrMoldProdDetailService = (MaEqMstrMoldProdDetailService)getBean("maEqMstrMoldProdDetailService");

    	// 설비Id 구함
        String equipId = maEqMstrMoldProdDetailForm.getMaEqMstrCommonDTO().getEquipId();
        // 설비자재id 구함
        String eqMoldProductId = maEqMstrMoldProdDetailForm.getMaEqMstrMoldProdListDTO().getEqMoldProductId();
        
        // 조회된 상세 부분
        MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO = maEqMstrMoldProdDetailService.findDetail(equipId, eqMoldProductId, getUser(request).getCompNo());
        
        // 조회된 상세  셋팅한다.
        maEqMstrMoldProdDetailForm.setMaEqMstrMoldProdDetailDTO(maEqMstrMoldProdDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldProdDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrMoldProdDetailForm maEqMstrMoldProdDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrMoldProdDetailService maEqMstrMoldProdDetailService = (MaEqMstrMoldProdDetailService) getBean("maEqMstrMoldProdDetailService");
        
        MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO = maEqMstrMoldProdDetailForm.getMaEqMstrMoldProdDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrMoldProdDetailForm.getMaEqMstrCommonDTO();
        maEqMstrMoldProdDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrMoldProdDetailService.updateDetail(maEqMstrMoldProdDetailDTO,maEqMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldProdDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrMoldProdDetailForm maEqMstrMoldProdDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrMoldProdDetailService maEqMstrMoldProdDetailService = (MaEqMstrMoldProdDetailService) getBean("maEqMstrMoldProdDetailService");
        
        MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO = maEqMstrMoldProdDetailForm.getMaEqMstrMoldProdDetailDTO();
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrMoldProdDetailForm.getMaEqMstrCommonDTO();
        maEqMstrMoldProdDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrMoldProdDetailService.insertDetail(maEqMstrMoldProdDetailDTO, maEqMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}