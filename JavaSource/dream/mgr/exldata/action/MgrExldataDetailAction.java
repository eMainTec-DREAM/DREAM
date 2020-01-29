package dream.mgr.exldata.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.exldata.dto.MgrExldataCommonDTO;
import dream.mgr.exldata.dto.MgrExldataDetailDTO;
import dream.mgr.exldata.form.MgrExldataDetailForm;
import dream.mgr.exldata.service.MgrExldataDetailService;

/**
 * Excel Data Upload - Detail Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrExldataDetail" name="mgrExldataDetailForm"
 *                input="/dream/mgr/exldata/mgrExldataDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrExldataDetail" path="/dream/mgr/exldata/mgrExldataDetail.jsp"
 *                        redirect="false"
 */
public class MgrExldataDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** set filed */
    public static final int DETAIL_SET_FIELD    = 1004;
    /** create와 update 구별하기위해 사용 */
    public static final int DETAIL_FAKE_ACTION  = 1005;
    /** 업로드 */
    public static final int DETAIL_UPLOAD       = 5006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrExldataDetailForm mgrExldataDetailForm = (MgrExldataDetailForm) form;
        
        super.updateAudit(mgrExldataDetailForm.getMgrExldataDetailDTO().getAuditKey()==""?mgrExldataDetailForm.getMgrExldataCommonDTO().getAuditKey():mgrExldataDetailForm.getMgrExldataDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrExldataDetailForm.getStrutsAction())
        {
            case MgrExldataDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrExldataDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrExldataDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, mgrExldataDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrExldataDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, mgrExldataDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrExldataDetailAction.DETAIL_UPLOAD:
                uploadDetail(request, response, mgrExldataDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrExldataDetailAction.DETAIL_SET_FIELD:
                this.findField(request, response, mgrExldataDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("mgrExldataDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param mgrExldataDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrExldataDetailForm mgrExldataDetailForm) throws Exception 
    {   
    	MgrExldataDetailService mgrExldataDetailService = (MgrExldataDetailService)getBean("mgrExldataDetailService");
    	
    	MgrExldataCommonDTO mgrExldataCommonDTO = mgrExldataDetailForm.getMgrExldataCommonDTO(); 
    	
    	List resultList = mgrExldataDetailService.findExldataDetail(mgrExldataCommonDTO, getUser(request));
    	super.makeJsonResult(resultList, request, response, mgrExldataDetailForm.getListId());
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param mgrExldataDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, MgrExldataDetailForm mgrExldataDetailForm) throws Exception
    {
    	MgrExldataDetailService mgrExldataDetailService = (MgrExldataDetailService)getBean("mgrExldataDetailService");
    	MgrExldataDetailDTO  mgrExldataDetailDTO = mgrExldataDetailForm.getMgrExldataDetailDTO(); 
    	
    	mgrExldataDetailService.insertExldataDetail(mgrExldataDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param mgrExldataDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrExldataDetailForm mgrExldataDetailForm) throws Exception
    {
    	MgrExldataDetailService mgrExldataDetailService = (MgrExldataDetailService)getBean("mgrExldataDetailService");
    	MgrExldataDetailDTO  mgrExldataDetailDTO = mgrExldataDetailForm.getMgrExldataDetailDTO();
    	
    	mgrExldataDetailService.updateExldataDetail(mgrExldataDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void uploadDetail(HttpServletRequest request, HttpServletResponse response, MgrExldataDetailForm mgrExldataDetailForm) throws Exception
    {
        MgrExldataDetailService mgrExldataDetailService = (MgrExldataDetailService)getBean("mgrExldataDetailService");
        MgrExldataDetailDTO  mgrExldataDetailDTO = mgrExldataDetailForm.getMgrExldataDetailDTO();
        
        mgrExldataDetailService.uploadExldataDetail(mgrExldataDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void findField(HttpServletRequest request, HttpServletResponse response, MgrExldataDetailForm mgrExldataDetailForm) throws Exception 
    {   
        MgrExldataDetailService mgrExldataDetailService = (MgrExldataDetailService)getBean("mgrExldataDetailService");
        
        MgrExldataCommonDTO mgrExldataCommonDTO = mgrExldataDetailForm.getMgrExldataCommonDTO(); 
        
        List resultList = mgrExldataDetailService.findField(mgrExldataCommonDTO, getUser(request));
        super.makeJsonResult(resultList, request, response, mgrExldataDetailForm.getListId());
    }

}