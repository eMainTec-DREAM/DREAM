package dream.mgr.msgrec.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;
import dream.mgr.msgrec.dto.MgrMsgRecDetailDTO;
import dream.mgr.msgrec.form.MgrMsgRecDetailForm;
import dream.mgr.msgrec.service.MgrMsgRecDetailService;

/**
 * MgrMsgRec Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/mgrMsgRecDetail" name="mgrMsgRecDetailForm"
 *                input="/dream/mgr/msgrec/mgrMsgRecDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrMsgRecDetail" path="/dream/mgr/msgrec/mgrMsgRecDetail.jsp"
 *                        redirect="false"
 */
public class MgrMsgRecDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT     = 8001;
    /** 저장 */
    public static final int DETAIL_INPUT    = 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE	= 6003;
    /** 중복확인 */
    public static final int DETAIL_CHECK	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrMsgRecDetailForm mgrMsgRecDetailForm = (MgrMsgRecDetailForm) form;
        
        switch (mgrMsgRecDetailForm.getStrutsAction())
        {
            case MgrMsgRecDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrMsgRecDetailForm);
                returnActionForward = mapping.findForward("mgrMsgRecDetail");
                break;
            case MgrMsgRecDetailAction.DETAIL_INPUT:
            	insertDetail(request, mgrMsgRecDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrMsgRecDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, mgrMsgRecDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrMsgRecDetailAction.DETAIL_CHECK:
            	validMsgObjType(request, mgrMsgRecDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    

	/**
     * FIND DETAIL
     * @param request
     * @param response
     * @param mgrMsgRecDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrMsgRecDetailForm mgrMsgRecDetailForm) throws Exception 
    {   
        MgrMsgRecDetailService mgrMsgRecDetailService = (MgrMsgRecDetailService)getBean("mgrMsgRecDetailService");
        MgrMsgRecCommonDTO mgrMsgRecCommonDTO = mgrMsgRecDetailForm.getMgrMsgRecCommonDTO(); 

        User user = getUser(request);
        MgrMsgRecDetailDTO mgrMsgRecDetailDTO = mgrMsgRecDetailService.findDetail(mgrMsgRecCommonDTO, user);
        mgrMsgRecDetailForm.setMgrMsgRecDetailDTO(mgrMsgRecDetailDTO);
    }
    
    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param mgrMsgRecDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, MgrMsgRecDetailForm mgrMsgRecDetailForm) throws Exception 
    {
    	MgrMsgRecDetailService mgrMsgRecDetailService = (MgrMsgRecDetailService)getBean("mgrMsgRecDetailService");
    	MgrMsgRecDetailDTO mgrMsgRecDetailDTO = mgrMsgRecDetailForm.getMgrMsgRecDetailDTO(); 
    	User user = getUser(request);
    	
    	mgrMsgRecDetailService.insertDetail(mgrMsgRecDetailDTO, user);
        setAjaxStatus(request);
    }
    
    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param mgrMsgRecDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrMsgRecDetailForm mgrMsgRecDetailForm) throws Exception 
    {
    	MgrMsgRecDetailService mgrMsgRecDetailService = (MgrMsgRecDetailService)getBean("mgrMsgRecDetailService");
    	MgrMsgRecDetailDTO mgrMsgRecDetailDTO = mgrMsgRecDetailForm.getMgrMsgRecDetailDTO(); 
    	User user = getUser(request);
    	
    	mgrMsgRecDetailService.updateDetail(mgrMsgRecDetailDTO, user);
    	setAjaxStatus(request);
    }
    
    /**
     * detail validation
     * @param request
     * @param mgrMsgRecDetailForm
     * @throws Exception
     */
    private void validMsgObjType(HttpServletRequest request, MgrMsgRecDetailForm mgrMsgRecDetailForm) throws Exception 
    {
    	MgrMsgRecDetailService mgrMsgRecDetailService = (MgrMsgRecDetailService)getBean("mgrMsgRecDetailService");
    	MgrMsgRecDetailDTO mgrMsgRecDetailDTO = mgrMsgRecDetailForm.getMgrMsgRecDetailDTO(); 
    	User user = getUser(request);
    	
    	String isValid = mgrMsgRecDetailService.validMsgObjType(mgrMsgRecDetailDTO, user);
    	setAjaxDesc(request, isValid);
    }
}
