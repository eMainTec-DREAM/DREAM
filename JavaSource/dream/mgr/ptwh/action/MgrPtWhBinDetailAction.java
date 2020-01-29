package dream.mgr.ptwh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.ptwh.dto.MgrPtWhBinDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;
import dream.mgr.ptwh.form.MgrPtWhBinDetailForm;
import dream.mgr.ptwh.service.MgrPtWhBinDetailService;

/**
 * 부품창고 보관위치 - Detail Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrPtWhBinDetail" name="mgrPtWhBinDetailForm"
 *                input="/dream/mgr/ptwh/mgrPtWhBinDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPtWhBinDetail" path="/dream/mgr/ptwh/mgrPtWhBinDetail.jsp"
 *                        redirect="false"
 */
public class MgrPtWhBinDetailAction extends AuthAction
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
        MgrPtWhBinDetailForm mgrPtWhBinDetailForm = (MgrPtWhBinDetailForm) form;
        
        super.updateAudit(mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO().getAuditKey()==""?mgrPtWhBinDetailForm.getMgrPtWhBinListDTO().getAuditKey():mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrPtWhBinDetailForm.getStrutsAction())
        {
            case MgrPtWhBinDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrPtWhBinDetailForm);
                returnActionForward = mapping.findForward("mgrPtWhBinDetail");
                break;
            case MgrPtWhBinDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, mgrPtWhBinDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrPtWhBinDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, mgrPtWhBinDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrPtWhBinDetailAction.DETAIL_CHECK:
            	validEmpNo(mgrPtWhBinDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrPtWhBinDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 부품창고 보관위치 FIND DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param mgrPtWhBinDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrPtWhBinDetailForm mgrPtWhBinDetailForm) throws Exception 
    {   
    	MgrPtWhBinDetailService mgrPtWhBinDetailService = (MgrPtWhBinDetailService)getBean("mgrPtWhBinDetailService");
    	
    	MgrPtWhBinListDTO mgrPtWhBinListDTO = mgrPtWhBinDetailForm.getMgrPtWhBinListDTO(); 

    	User user = getUser(request);
    	
    	MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO = mgrPtWhBinDetailService.findPtWhEmpDetail(mgrPtWhBinListDTO, user);
    	mgrPtWhBinDetailForm.setMgrPtWhBinDetailDTO(mgrPtWhBinDetailDTO);
    }

    /**
     * 부품창고 보관위치 INSERT DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param mgrPtWhBinDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, MgrPtWhBinDetailForm mgrPtWhBinDetailForm) throws Exception
    {
    	MgrPtWhBinDetailService mgrPtWhBinDetailService = (MgrPtWhBinDetailService)getBean("mgrPtWhBinDetailService");
    	MgrPtWhBinDetailDTO  mgrPtWhBinDetailDTO = mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	mgrPtWhBinDetailService.insertPtWhEmpDetail(mgrPtWhBinDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * 부품창고 보관위치 UPDATE DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param mgrPtWhBinDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrPtWhBinDetailForm mgrPtWhBinDetailForm) throws Exception
    {
    	MgrPtWhBinDetailService mgrPtWhBinDetailService = (MgrPtWhBinDetailService)getBean("mgrPtWhBinDetailService");
    	MgrPtWhBinDetailDTO  mgrPtWhBinDetailDTO = mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO();
    	
    	User user = getUser(request);
    	
    	mgrPtWhBinDetailService.updatePtWhEmpDetail(mgrPtWhBinDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * 부품창고 보관위치 중복체크 (valid emp id)  
     * @author cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailForm
     * @param request
     */
    private void validEmpNo(MgrPtWhBinDetailForm mgrPtWhBinDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrPtWhBinDetailService mgrPtWhBinDetailService = (MgrPtWhBinDetailService)getBean("mgrPtWhBinDetailService");
    	MgrPtWhBinListDTO mgrPtWhBinListDTO = mgrPtWhBinDetailForm.getMgrPtWhBinListDTO();
    	MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO = mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO();
    	mgrPtWhBinDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = mgrPtWhBinDetailService.validEmpNo(mgrPtWhBinListDTO, mgrPtWhBinDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }

}