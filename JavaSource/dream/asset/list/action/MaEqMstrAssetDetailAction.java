package dream.asset.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrAssetDetailDTO;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.form.MaEqMstrAssetDetailForm;
import dream.asset.list.service.MaEqMstrAssetDetailService;

/**
 * 관련자산 상세 action
 * @author  kim21017
 * @version $Id: MaEqMstrAssetDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrAssetDetail" name="maEqMstrAssetDetailForm"
 *                input="/dream/asset/list/maEqMstrAssetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrAssetDetail" path="/dream/asset/list/maEqMstrAssetDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrAssetDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_ASSET_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int EQ_MSTR_ASSET_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int EQ_MSTR_ASSET_DETAIL_INPUT 		= 5003;
    /** 복사 */
    public static final int EQ_MSTR_ASSET_DETAIL_COPY 		= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrAssetDetailForm maEqMstrAssetDetailForm = (MaEqMstrAssetDetailForm) form;
        
        super.updateAudit(maEqMstrAssetDetailForm.getMaEqMstrAssetDetailDTO().getAuditKey()==""?maEqMstrAssetDetailForm.getMaEqMstrAssetListDTO().getAuditKey():maEqMstrAssetDetailForm.getMaEqMstrAssetDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqMstrAssetDetailForm.getStrutsAction())
        {
            case MaEqMstrAssetDetailAction.EQ_MSTR_ASSET_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqMstrAssetDetailForm);
                returnActionForward = mapping.findForward("maEqMstrAssetDetail");
                break;
            case MaEqMstrAssetDetailAction.EQ_MSTR_ASSET_DETAIL_UPDATE:
            	updateDetail(maEqMstrAssetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrAssetDetailAction.EQ_MSTR_ASSET_DETAIL_INPUT:
            	insertDetail(maEqMstrAssetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrAssetDetailAction.EQ_MSTR_ASSET_DETAIL_COPY:
            	copyDetail(maEqMstrAssetDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMstrAssetDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 관련자산 조회 
     * @author kim2107
     * @version $Id: MaEqMstrAssetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrAssetDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrAssetDetailForm maEqMstrAssetDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqMstrAssetDetailService maEqMstrAssetDetailService = (MaEqMstrAssetDetailService)getBean("maEqMstrAssetDetailService");
    	
    	MaEqMstrAssetListDTO maEqMstrAssetListDTO = maEqMstrAssetDetailForm.getMaEqMstrAssetListDTO();
    	
        // 조회된 상세 부분
        MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO = maEqMstrAssetDetailService.findDetail(maEqMstrAssetListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maEqMstrAssetDetailForm.setMaEqMstrAssetDetailDTO(maEqMstrAssetDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrAssetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrAssetDetailForm maEqMstrAssetDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrAssetDetailService maEqMstrAssetDetailService = (MaEqMstrAssetDetailService) getBean("maEqMstrAssetDetailService");
        
        MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO = maEqMstrAssetDetailForm.getMaEqMstrAssetDetailDTO();
        
        maEqMstrAssetDetailService.updateDetail(maEqMstrAssetDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrAssetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrAssetDetailForm maEqMstrAssetDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrAssetDetailService maEqMstrAssetDetailService = (MaEqMstrAssetDetailService) getBean("maEqMstrAssetDetailService");
        
        MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO = maEqMstrAssetDetailForm.getMaEqMstrAssetDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrAssetDetailForm.getMaEqMstrCommonDTO();
        
        maEqMstrAssetDetailService.insertDetail(maEqMstrAssetDetailDTO, maEqMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail copy
     * @author  syyang
     * @version $Id: MaEqMstrAssetDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param maEqMstrAssetDetailForm
     * @param request
     */
    private void copyDetail(MaEqMstrAssetDetailForm maEqMstrAssetDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrAssetDetailService maEqMstrAssetDetailService = (MaEqMstrAssetDetailService) getBean("maEqMstrAssetDetailService");
    	
    	MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO = maEqMstrAssetDetailForm.getMaEqMstrAssetDetailDTO();
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrAssetDetailForm.getMaEqMstrCommonDTO();

    	String oldEquipId = maEqMstrCommonDTO.getEquipId();
    	String newEquipId = maEqMstrCommonDTO.getEquipId();
    	String oldKeyId = maEqMstrAssetDetailDTO.getOldEqAssetId();
    	String newKeyId = maEqMstrAssetDetailDTO.getEqAssetId();
    	
    	maEqMstrAssetDetailService.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
}