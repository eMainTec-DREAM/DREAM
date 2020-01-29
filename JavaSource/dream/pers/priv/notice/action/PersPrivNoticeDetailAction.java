package dream.pers.priv.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.notice.dto.PersPrivNoticeDetailDTO;
import dream.pers.priv.notice.form.PersPrivNoticeDetailForm;
import dream.pers.priv.notice.service.PersPrivNoticeDetailService;

/**
 * Notice 설정 - 상세
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 * @struts:action path="/persPrivNoticeDetail" name="persPrivNoticeDetailForm"
 *                input="/dream/pers/priv/notice/persPrivNoticeDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivNoticeDetail" path="/dream/pers/priv/notice/persPrivNoticeDetail.jsp"
 *                        redirect="false"
 */
public class PersPrivNoticeDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 	= 8001;
    /** 입력 */
    public static final int DETAIL_INPUT 	= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 	= 6003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PersPrivNoticeDetailForm persPrivNoticeDetailForm = (PersPrivNoticeDetailForm) form;
        
        switch (persPrivNoticeDetailForm.getStrutsAction())
        {
            case PersPrivNoticeDetailAction.DETAIL_INIT:
                this.findDetail(request, persPrivNoticeDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case PersPrivNoticeDetailAction.DETAIL_UPDATE:
            	this.updateDetail(persPrivNoticeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PersPrivNoticeDetailAction.DETAIL_INPUT:
            	this.insertDetail(persPrivNoticeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * detail find
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param persPrivNoticeDetailForm
     */
    private void findDetail(HttpServletRequest request, PersPrivNoticeDetailForm persPrivNoticeDetailForm)throws Exception 
    {   
    	PersPrivNoticeDetailService persPrivNoticeDetailService = (PersPrivNoticeDetailService)getBean("persPrivNoticeDetailService");
    	
    	MaMyInfoCommonDTO maMyInfoCommonDTO = persPrivNoticeDetailForm.getMaMyInfoCommonDTO();
        
        PersPrivNoticeDetailDTO persPrivNoticeDetailDTO = persPrivNoticeDetailService.findDetail(maMyInfoCommonDTO, getUser(request));
        
        persPrivNoticeDetailForm.setPersPrivNoticeDetailDTO(persPrivNoticeDetailDTO);
    }
    
    /**
     * detail update
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivNoticeDetailForm
     * @param request
     */
    private void updateDetail(PersPrivNoticeDetailForm persPrivNoticeDetailForm, HttpServletRequest request) throws Exception
    {
    	PersPrivNoticeDetailService persPrivNoticeDetailService = (PersPrivNoticeDetailService) getBean("persPrivNoticeDetailService");
        
        PersPrivNoticeDetailDTO persPrivNoticeDetailDTO = persPrivNoticeDetailForm.getPersPrivNoticeDetailDTO();
        
        persPrivNoticeDetailDTO.setEnterBy(getUser(request).getUserId());
        
        persPrivNoticeDetailService.updateDetail(persPrivNoticeDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivNoticeDetailForm
     * @param request
     */
    private void insertDetail(PersPrivNoticeDetailForm persPrivNoticeDetailForm, HttpServletRequest request) throws Exception
    {
    	PersPrivNoticeDetailService persPrivNoticeDetailService = (PersPrivNoticeDetailService) getBean("persPrivNoticeDetailService");
        
        PersPrivNoticeDetailDTO persPrivNoticeDetailDTO = persPrivNoticeDetailForm.getPersPrivNoticeDetailDTO();
        
        MaMyInfoCommonDTO maMyInfoCommonDTO = persPrivNoticeDetailForm.getMaMyInfoCommonDTO();
        persPrivNoticeDetailDTO.setEnterBy(getUser(request).getUserId());
        persPrivNoticeDetailDTO.setCompNo(getUser(request).getCompNo());
 
        persPrivNoticeDetailService.insertDetail(persPrivNoticeDetailDTO, maMyInfoCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}