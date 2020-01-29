package dream.mgr.usrrpt.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.MaUserRptColDetailDTO;
import dream.mgr.usrrpt.form.MaUserRptColDetailForm;
import dream.mgr.usrrpt.service.MaUserRptColDetailService;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maUserRptColDetail" name="maUserRptColDetailForm"
 *                input="/dream/mgr/usrrpt/maUserRptColDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptColDetail" path="/dream/mgr/usrrpt/maUserRptColDetail.jsp"
 *                        redirect="false"
 */
public class MaUserRptColDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USER_COL_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int USER_COL_DETAIL_UPDATE  = 6002;
    /** 입력 */
    public static final int USER_COL_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptColDetailForm maUserRptColDetailForm = (MaUserRptColDetailForm) form;
        
        super.updateAudit(maUserRptColDetailForm.getMaUserRptColDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maUserRptColDetailForm.getStrutsAction())
        {
            case MaUserRptColDetailAction.USER_COL_DETAIL_INIT:
                this.findDetail(request, maUserRptColDetailForm);
                returnActionForward = mapping.findForward("maUserRptColDetail");
                break;
            case MaUserRptColDetailAction.USER_COL_DETAIL_UPDATE:
            	updateDetail(maUserRptColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptColDetailAction.USER_COL_DETAIL_INPUT:
            	insertDetail(maUserRptColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maUserRptColDetail");
                break;
        }

        return returnActionForward;
    }

	/**
     *  상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptColDetailForm
     */
    private void findDetail(HttpServletRequest request, MaUserRptColDetailForm maUserRptColDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaUserRptColDetailService maUserRptColDetailService = (MaUserRptColDetailService)getBean("maUserRptColDetailService");

        // 조회된 상세 부분
        MaUserRptColDetailDTO maUserRptColDetailDTO = maUserRptColDetailService.findDetail(maUserRptColDetailForm.getMaUserRptCommonDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        maUserRptColDetailForm.setMaUserRptColDetailDTO(maUserRptColDetailDTO);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptColDetailForm
     * @param request
     */
    private void updateDetail(MaUserRptColDetailForm maUserRptColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptColDetailService maUserRptColDetailService = (MaUserRptColDetailService) getBean("maUserRptColDetailService");
        
        MaUserRptColDetailDTO maUserRptColDetailDTO = maUserRptColDetailForm.getMaUserRptColDetailDTO();
        
        maUserRptColDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maUserRptColDetailService.updateDetail(maUserRptColDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptColDetailForm
     * @param request
     */
    private void insertDetail(MaUserRptColDetailForm maUserRptColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptColDetailService maUserRptColDetailService = (MaUserRptColDetailService) getBean("maUserRptColDetailService");
        
        MaUserRptColDetailDTO maUserRptColDetailDTO = maUserRptColDetailForm.getMaUserRptColDetailDTO();
        
        maUserRptColDetailDTO.setCompNo((getUser(request).getCompNo()));
        maUserRptColDetailService.insertDetail(maUserRptColDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}