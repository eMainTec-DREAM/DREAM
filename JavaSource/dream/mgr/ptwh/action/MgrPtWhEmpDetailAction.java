package dream.mgr.ptwh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.ptwh.dto.MgrPtWhEmpDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;
import dream.mgr.ptwh.form.MgrPtWhEmpDetailForm;
import dream.mgr.ptwh.service.MgrPtWhEmpDetailService;

/**
 * 부품창고 담당자 - Detail Action
 * 
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrPtWhEmpDetail" name="mgrPtWhEmpDetailForm"
 *                input="/dream/mgr/ptwh/mgrPtWhEmpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPtWhEmpDetail" path="/dream/mgr/ptwh/mgrPtWhEmpDetail.jsp"
 *                        redirect="false"
 */
public class MgrPtWhEmpDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** 중복 체크 */
    public static final int DETAIL_CHECK		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrPtWhEmpDetailForm mgrPtWhEmpDetailForm = (MgrPtWhEmpDetailForm) form;
        
        super.updateAudit(mgrPtWhEmpDetailForm.getMgrPtWhEmpDetailDTO().getAuditKey()==""?mgrPtWhEmpDetailForm.getMgrPtWhEmpListDTO().getAuditKey():mgrPtWhEmpDetailForm.getMgrPtWhEmpDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrPtWhEmpDetailForm.getStrutsAction())
        {
            case MgrPtWhEmpDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrPtWhEmpDetailForm);
                returnActionForward = mapping.findForward("mgrPtWhEmpDetail");
                break;
            case MgrPtWhEmpDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, mgrPtWhEmpDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrPtWhEmpDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, mgrPtWhEmpDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrPtWhEmpDetailAction.DETAIL_CHECK:
            	validEmpNo(mgrPtWhEmpDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrPtWhEmpDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 부품창고 담당자 FIND DETAIL
     * @author sy.yang
     * @param request
     * @param response
     * @param mgrPtWhEmpDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrPtWhEmpDetailForm mgrPtWhEmpDetailForm) throws Exception 
    {   
    	MgrPtWhEmpDetailService mgrPtWhEmpDetailService = (MgrPtWhEmpDetailService)getBean("mgrPtWhEmpDetailService");
    	
    	MgrPtWhEmpListDTO mgrPtWhEmpListDTO = mgrPtWhEmpDetailForm.getMgrPtWhEmpListDTO(); 

    	User user = getUser(request);
    	
    	MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO = mgrPtWhEmpDetailService.findPtWhEmpDetail(mgrPtWhEmpListDTO, user);
    	mgrPtWhEmpDetailForm.setMgrPtWhEmpDetailDTO(mgrPtWhEmpDetailDTO);
    }

    /**
     * 부품창고 담당자 INSERT DETAIL
     * @author sy.yang
     * @param request
     * @param response
     * @param mgrPtWhEmpDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, MgrPtWhEmpDetailForm mgrPtWhEmpDetailForm) throws Exception
    {
    	MgrPtWhEmpDetailService mgrPtWhEmpDetailService = (MgrPtWhEmpDetailService)getBean("mgrPtWhEmpDetailService");
    	MgrPtWhEmpDetailDTO  mgrPtWhEmpDetailDTO = mgrPtWhEmpDetailForm.getMgrPtWhEmpDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	mgrPtWhEmpDetailService.insertPtWhEmpDetail(mgrPtWhEmpDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * 부품창고 담당자 UPDATE DETAIL
     * @author sy.yang
     * @param request
     * @param response
     * @param mgrPtWhEmpDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrPtWhEmpDetailForm mgrPtWhEmpDetailForm) throws Exception
    {
    	MgrPtWhEmpDetailService mgrPtWhEmpDetailService = (MgrPtWhEmpDetailService)getBean("mgrPtWhEmpDetailService");
    	MgrPtWhEmpDetailDTO  mgrPtWhEmpDetailDTO = mgrPtWhEmpDetailForm.getMgrPtWhEmpDetailDTO();
    	
    	User user = getUser(request);
    	
    	mgrPtWhEmpDetailService.updatePtWhEmpDetail(mgrPtWhEmpDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * 부품창고 담당자 중복체크 (valid emp id)  
     * @author sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailForm
     * @param request
     */
    private void validEmpNo(MgrPtWhEmpDetailForm mgrPtWhEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrPtWhEmpDetailService mgrPtWhEmpDetailService = (MgrPtWhEmpDetailService)getBean("mgrPtWhEmpDetailService");
    	MgrPtWhEmpListDTO mgrPtWhEmpListDTO = mgrPtWhEmpDetailForm.getMgrPtWhEmpListDTO();
    	MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO = mgrPtWhEmpDetailForm.getMgrPtWhEmpDetailDTO();
    	mgrPtWhEmpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = mgrPtWhEmpDetailService.validEmpNo(mgrPtWhEmpListDTO, mgrPtWhEmpDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }

}