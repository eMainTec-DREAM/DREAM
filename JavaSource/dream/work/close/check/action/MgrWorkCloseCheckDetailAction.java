package dream.work.close.check.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.MwareConfig;
import common.bean.User;
import common.struts.AuthAction;
import dream.ass.asset.action.AssAssetDetailAction;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckDetailDTO;
import dream.work.close.check.form.MgrWorkCloseCheckDetailForm;
import dream.work.close.check.service.MgrWorkCloseCheckDetailService;
import dream.work.pm.std.action.MaStdPointHdrDetailAction;

/**
 * MgrWorkCloseCheck Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: MgrWorkCloseCheckDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/mgrWorkCloseCheckDetail" name="mgrWorkCloseCheckDetailForm"
 *                input="/dream/work/close/check/mgrWorkCloseCheckDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrWorkCloseCheckDetail" path="/dream/work/close/check/mgrWorkCloseCheckDetail.jsp"
 *                        redirect="false"
 */
public class MgrWorkCloseCheckDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                 = 8001;
    /** 저장 */
    public static final int DETAIL_INPUT                = 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE               = 6003;
    /** 적용 */
    public static final int DETAIL_CONFIRM				= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrWorkCloseCheckDetailForm mgrWorkCloseCheckDetailForm = (MgrWorkCloseCheckDetailForm) form;
        
        super.updateAudit(mgrWorkCloseCheckDetailForm.getMgrWorkCloseCheckDetailDTO().getAuditKey()==""?mgrWorkCloseCheckDetailForm.getMgrWorkCloseCheckCommonDTO().getAuditKey():mgrWorkCloseCheckDetailForm.getMgrWorkCloseCheckDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrWorkCloseCheckDetailForm.getStrutsAction())
        {
            case MgrWorkCloseCheckDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrWorkCloseCheckDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case AssAssetDetailAction.DETAIL_INPUT:
                insertDetail(request, response, mgrWorkCloseCheckDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrWorkCloseCheckDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, mgrWorkCloseCheckDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaStdPointHdrDetailAction.STD_HDR_DETAIL_CONFIRM:
            	confirmDetail(request, mgrWorkCloseCheckDetailForm);
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
     * @param mgrWorkCloseCheckDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrWorkCloseCheckDetailForm mgrWorkCloseCheckDetailForm) throws Exception 
    {   
        MgrWorkCloseCheckDetailService mgrWorkCloseCheckDetailService = (MgrWorkCloseCheckDetailService)getBean("mgrWorkCloseCheckDetailService",request);
        
        MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckDetailForm.getMgrWorkCloseCheckCommonDTO(); 

        User user = getUser(request);
        MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO = mgrWorkCloseCheckDetailService.findDetail(mgrWorkCloseCheckCommonDTO, user);
        mgrWorkCloseCheckDetailForm.setMgrWorkCloseCheckDetailDTO(mgrWorkCloseCheckDetailDTO);
    }
    
    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param mgrWorkCloseCheckDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, MgrWorkCloseCheckDetailForm mgrWorkCloseCheckDetailForm) throws Exception
    {
    	MgrWorkCloseCheckDetailService mgrWorkCloseCheckDetailService = (MgrWorkCloseCheckDetailService)getBean("mgrWorkCloseCheckDetailService", request);
    	MgrWorkCloseCheckDetailDTO  mgrWorkCloseCheckDetailDTO = mgrWorkCloseCheckDetailForm.getMgrWorkCloseCheckDetailDTO();
    	
    	User user = getUser(request);
    	mgrWorkCloseCheckDetailService.insertDetail(mgrWorkCloseCheckDetailDTO, user);
    	
    	if("N".equals(MwareConfig.getIsUsePmRevision())){
    		mgrWorkCloseCheckDetailService.insertRevisionHistAndUpdateMstr(mgrWorkCloseCheckDetailDTO, getUser(request));
        }
    	
    	setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param mgrWorkCloseCheckDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrWorkCloseCheckDetailForm mgrWorkCloseCheckDetailForm) throws Exception
    {
        MgrWorkCloseCheckDetailService mgrWorkCloseCheckDetailService = (MgrWorkCloseCheckDetailService)getBean("mgrWorkCloseCheckDetailService", request);
        MgrWorkCloseCheckDetailDTO  mgrWorkCloseCheckDetailDTO = mgrWorkCloseCheckDetailForm.getMgrWorkCloseCheckDetailDTO();
        
        User user = getUser(request);
        mgrWorkCloseCheckDetailService.updateDetail(mgrWorkCloseCheckDetailDTO, user);
        setAjaxStatus(request);
    }
    
    /**
     * CONFIRM DETAIL
     * @param request
     * @param response
     * @param mgrWorkCloseCheckDetailForm
     * @throws Exception
     */
    private void confirmDetail(HttpServletRequest request, MgrWorkCloseCheckDetailForm mgrWorkCloseCheckDetailForm) throws Exception
    {
    	MgrWorkCloseCheckDetailService mgrWorkCloseCheckDetailService = (MgrWorkCloseCheckDetailService)getBean("mgrWorkCloseCheckDetailService", request);
    	MgrWorkCloseCheckDetailDTO  mgrWorkCloseCheckDetailDTO = mgrWorkCloseCheckDetailForm.getMgrWorkCloseCheckDetailDTO();
    	
    	User user = getUser(request);
    	mgrWorkCloseCheckDetailService.confirmDetail(mgrWorkCloseCheckDetailDTO, user);
    	setAjaxStatus(request);
    }
}
