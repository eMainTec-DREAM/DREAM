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
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;
import dream.asset.list.form.MaEqMstrSpecDetailForm;
import dream.asset.list.service.MaEqMstrSpecDetailService;

/**
 * 설비제원(스펙)
 * @author  kim21017
 * @version $Id: MaEqMstrSpecDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrSpecDetail" name="maEqMstrSpecDetailForm"
 *                input="/dream/asset/list/maEqMstrSpecDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrSpecDetail" path="/dream/asset/list/maEqMstrSpecDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrSpecDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_SPEC_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int EQ_MSTR_SPEC_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int EQ_MSTR_SPEC_DETAIL_INPUT 	= 5003;
    /** 복사 */
    public static final int DETAIL_COPY 				= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrSpecDetailForm maEqMstrSpecDetailForm = (MaEqMstrSpecDetailForm) form;
        
        super.updateAudit(maEqMstrSpecDetailForm.getMaEqMstrSpecDetailDTO().getAuditKey()==""?maEqMstrSpecDetailForm.getMaEqMstrSpecListDTO().getAuditKey():maEqMstrSpecDetailForm.getMaEqMstrSpecDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
 
        switch (maEqMstrSpecDetailForm.getStrutsAction())
        {
            case MaEqMstrSpecDetailAction.EQ_MSTR_SPEC_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqMstrSpecDetailForm);
                returnActionForward = mapping.findForward("maEqMstrSpecDetail");
                break;
            case MaEqMstrSpecDetailAction.EQ_MSTR_SPEC_DETAIL_UPDATE:
            	updateDetail(maEqMstrSpecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrSpecDetailAction.EQ_MSTR_SPEC_DETAIL_INPUT:
            	insertDetail(maEqMstrSpecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrSpecDetailAction.DETAIL_COPY:
            	copyDetail(maEqMstrSpecDetailForm, request, response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMstrSpecDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 설비제원 조회 
     * @author kim2107
     * @version $Id: MaEqMstrSpecDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrSpecDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrSpecDetailForm maEqMstrSpecDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqMstrSpecDetailService maEqMstrSpecDetailService = (MaEqMstrSpecDetailService)getBean("maEqMstrSpecDetailService");

    	MaEqMstrSpecListDTO maEqMstrSpecListDTO = maEqMstrSpecDetailForm.getMaEqMstrSpecListDTO();
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrSpecDetailForm.getMaEqMstrCommonDTO();
        
        // 조회된 상세 부분
        MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = maEqMstrSpecDetailService.findDetail(maEqMstrSpecListDTO, maEqMstrCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maEqMstrSpecDetailForm.setMaEqMstrSpecDetailDTO(maEqMstrSpecDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrSpecDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrSpecDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrSpecDetailForm maEqMstrSpecDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrSpecDetailService maEqMstrSpecDetailService = (MaEqMstrSpecDetailService) getBean("maEqMstrSpecDetailService");
        
        MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = maEqMstrSpecDetailForm.getMaEqMstrSpecDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrSpecDetailForm.getMaEqMstrCommonDTO();
        
        User user = getUser(request);
        
        maEqMstrSpecDetailService.updateDetail(maEqMstrSpecDetailDTO,maEqMstrCommonDTO, user);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrSpecDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrSpecDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrSpecDetailForm maEqMstrSpecDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrSpecDetailService maEqMstrSpecDetailService = (MaEqMstrSpecDetailService) getBean("maEqMstrSpecDetailService");
        MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = maEqMstrSpecDetailForm.getMaEqMstrSpecDetailDTO();
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrSpecDetailForm.getMaEqMstrCommonDTO();
        
        maEqMstrSpecDetailService.insertDetail(maEqMstrSpecDetailDTO, maEqMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void copyDetail(MaEqMstrSpecDetailForm maEqMstrSpecDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	MaEqMstrSpecDetailService maEqMstrSpecDetailService = (MaEqMstrSpecDetailService) getBean("maEqMstrSpecDetailService");
    	MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = maEqMstrSpecDetailForm.getMaEqMstrSpecDetailDTO();
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrSpecDetailForm.getMaEqMstrCommonDTO();

    	String oldEquipId = maEqMstrCommonDTO.getEquipId();
    	String newEquipId = maEqMstrCommonDTO.getEquipId();
    	String oldKeyId   = maEqMstrSpecDetailDTO.getOldEqSpecId();
    	String newKeyId   = maEqMstrSpecDetailDTO.getEqSpecId();
    	
    	User user = getUser(request);
    	
    	String result = maEqMstrSpecDetailService.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, user);
    	setAjaxStatus(request, result);
    }
}