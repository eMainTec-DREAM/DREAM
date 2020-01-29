package dream.mgr.intf.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfDetailDTO;
import dream.mgr.intf.form.MgrIntfDetailForm;
import dream.mgr.intf.service.MgrIntfDetailService;

/**
 * Interface Page - Detail Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrIntfDetail" name="mgrIntfDetailForm"
 *                input="/dream/mgr/intf/mgrIntfDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrIntfDetail" path="/dream/mgr/intf/mgrIntfDetail.jsp"
 *                        redirect="false"
 */
public class MgrIntfDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrIntfDetailForm mgrIntfDetailForm = (MgrIntfDetailForm) form;
        
        super.updateAudit(mgrIntfDetailForm.getMgrIntfDetailDTO().getAuditKey()==""?mgrIntfDetailForm.getMgrIntfCommonDTO().getAuditKey():mgrIntfDetailForm.getMgrIntfDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrIntfDetailForm.getStrutsAction())
        {
            case MgrIntfDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrIntfDetailForm);
                returnActionForward = mapping.findForward("mgrIntfDetail");
                break;
            case MgrIntfDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, mgrIntfDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrIntfDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, mgrIntfDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrIntfDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param mgrIntfDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrIntfDetailForm mgrIntfDetailForm) throws Exception 
    {   
    	MgrIntfDetailService mgrIntfDetailService = (MgrIntfDetailService)getBean("mgrIntfDetailService");
    	
    	MgrIntfCommonDTO mgrIntfCommonDTO = mgrIntfDetailForm.getMgrIntfCommonDTO(); 

    	User user = getUser(request);
    	
    	MgrIntfDetailDTO mgrIntfDetailDTO = mgrIntfDetailService.findDetail(mgrIntfCommonDTO, user);
    	mgrIntfDetailForm.setMgrIntfDetailDTO(mgrIntfDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param mgrIntfDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, MgrIntfDetailForm mgrIntfDetailForm) throws Exception
    {
    	MgrIntfDetailService mgrIntfDetailService = (MgrIntfDetailService)getBean("mgrIntfDetailService");
    	MgrIntfDetailDTO  mgrIntfDetailDTO = mgrIntfDetailForm.getMgrIntfDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	mgrIntfDetailService.insertDetail(mgrIntfDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param mgrIntfDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrIntfDetailForm mgrIntfDetailForm) throws Exception
    {
    	MgrIntfDetailService mgrIntfDetailService = (MgrIntfDetailService)getBean("mgrIntfDetailService");
    	MgrIntfDetailDTO  mgrIntfDetailDTO = mgrIntfDetailForm.getMgrIntfDetailDTO();
    	
    	User user = getUser(request);
    	
    	mgrIntfDetailService.updateDetail(mgrIntfDetailDTO, user);
        
        setAjaxStatus(request);
    }

}