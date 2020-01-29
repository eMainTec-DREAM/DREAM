package dream.asset.loc.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.loc.list.dto.MaEqLocDetailDTO;
import dream.asset.loc.list.form.MaEqLocDetailForm;
import dream.asset.loc.list.service.MaEqLocDetailService;

/**
 * 설비위치 - 상세
 * 
 * @author kim2107
 * @version $Id: MaEqLocDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maEqLocDetail" name="maEqLocDetailForm"
 *                input="/dream/asset/loc/list/maEqLocDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqLocDetail" path="/dream/asset/loc/list/maEqLocDetail.jsp"
 *                        redirect="false"
 */
public class MaEqLocDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_LOC_DETAIL_INIT 			= 8001;
    /** 저장 */
    public static final int EQ_LOC_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int EQ_LOC_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqLocDetailForm maEqLocDetailForm = (MaEqLocDetailForm) form;
        
        super.updateAudit(maEqLocDetailForm.getMaEqLocDetailDTO().getAuditKey()==""?maEqLocDetailForm.getMaEqLocCommonDTO().getAuditKey():maEqLocDetailForm.getMaEqLocDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqLocDetailForm.getStrutsAction())
        {
            case MaEqLocDetailAction.EQ_LOC_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqLocDetailForm);
                returnActionForward = mapping.findForward("maEqLocDetail");
                break;
            case MaEqLocDetailAction.EQ_LOC_DETAIL_INPUT:
            	insertDetail(maEqLocDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqLocDetailAction.EQ_LOC_DETAIL_UPDATE:
            	updateDetail(maEqLocDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqLocDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 설비위치 상세 조회
     * @author kim2107
     * @version $Id: MaEqLocDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqLocDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqLocDetailForm maEqLocDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqLocDetailService maEqLocDetailService = (MaEqLocDetailService)getBean("maEqLocDetailService");

        // 위치분류ID
        String eqLocId = maEqLocDetailForm.getMaEqLocCommonDTO().getEqLocId();
        // 공장코드
        String compNo = getUser(request).getCompNo();
        
        // 조회된 상세 부분
        MaEqLocDetailDTO maEqLocDetailDTO = maEqLocDetailService.findDetail(eqLocId,getUser(request));
        
        // 조회된 상세  셋팅한다.
        maEqLocDetailForm.setMaEqLocDetailDTO(maEqLocDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqLocDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailForm
     * @param request
     */
    private void insertDetail(MaEqLocDetailForm maEqLocDetailForm, HttpServletRequest request) throws Exception
    {
        MaEqLocDetailService maEqLocDetailService = (MaEqLocDetailService) getBean("maEqLocDetailService");
        
        MaEqLocDetailDTO maEqLocDetailDTO = maEqLocDetailForm.getMaEqLocDetailDTO();
        
        maEqLocDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqLocDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maEqLocDetailService.insertDetail(maEqLocDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqLocDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailForm
     * @param request
     */
    private void updateDetail(MaEqLocDetailForm maEqLocDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqLocDetailService maEqLocDetailService = (MaEqLocDetailService) getBean("maEqLocDetailService");
        
        MaEqLocDetailDTO maEqLocDetailDTO = maEqLocDetailForm.getMaEqLocDetailDTO();
        
        maEqLocDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqLocDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maEqLocDetailService.updateDetail(maEqLocDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}