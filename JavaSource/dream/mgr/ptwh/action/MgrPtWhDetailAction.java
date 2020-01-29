package dream.mgr.ptwh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhDetailDTO;
import dream.mgr.ptwh.form.MgrPtWhDetailForm;
import dream.mgr.ptwh.service.MgrPtWhDetailService;

/**
 * 부품창고 - Detail Action
 * 
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/mgrPtWhDetail" name="mgrPtWhDetailForm"
 *                input="/dream/mgr/ptwh/mgrPtWhDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPtWhDetail" path="/dream/mgr/ptwh/mgrPtWhDetail.jsp"
 *                        redirect="false"
 */
public class MgrPtWhDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrPtWhDetailForm mgrPtWhDetailForm = (MgrPtWhDetailForm) form;
        
        super.updateAudit(mgrPtWhDetailForm.getMgrPtWhDetailDTO().getAuditKey()==""?mgrPtWhDetailForm.getMgrPtWhCommonDTO().getAuditKey():mgrPtWhDetailForm.getMgrPtWhDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrPtWhDetailForm.getStrutsAction())
        {
            case MgrPtWhDetailAction.DETAIL_INIT:
                this.findDetail(request, mgrPtWhDetailForm);
                returnActionForward = mapping.findForward("mgrPtWhDetail");
                break;
            case MgrPtWhDetailAction.DETAIL_UPDATE:
            	updateDetail(request, mgrPtWhDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrPtWhDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 부품창고 상세 조회
     * @param request
     * @param response
     * @param mgrPtWhDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, MgrPtWhDetailForm mgrPtWhDetailForm) throws Exception 
    {   
    	MgrPtWhDetailService mgrPtWhDetailService = (MgrPtWhDetailService)getBean("mgrPtWhDetailService");
    	
    	MgrPtWhCommonDTO mgrPtWhCommonDTO = mgrPtWhDetailForm.getMgrPtWhCommonDTO(); 
    	
    	// 조회된 상세부분
    	MgrPtWhDetailDTO mgrPtWhDetailDTO = mgrPtWhDetailService.findDetail(mgrPtWhCommonDTO, getUser(request));
    	
    	// 조회된 상세 셋팅
    	mgrPtWhDetailForm.setMgrPtWhDetailDTO(mgrPtWhDetailDTO);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param mgrPtWhDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, MgrPtWhDetailForm mgrPtWhDetailForm) throws Exception
    {
    	MgrPtWhDetailService mgrPtWhDetailService = (MgrPtWhDetailService)getBean("mgrPtWhDetailService");
    	MgrPtWhDetailDTO  mgrPtWhDetailDTO = mgrPtWhDetailForm.getMgrPtWhDetailDTO();
    	
    	mgrPtWhDetailService.updatePtWhDetail(mgrPtWhDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}