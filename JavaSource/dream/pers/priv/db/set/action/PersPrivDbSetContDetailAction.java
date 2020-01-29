package dream.pers.priv.db.set.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;
import dream.pers.priv.db.set.form.PersPrivDbSetContDetailForm;
import dream.pers.priv.db.set.service.PersPrivDbSetContDetailService;

/**
 * 대시보드(Contents) - Detail Action
 * 
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContDetailAction.java,v 1.0 2018/08/03 12:52:40 nhkim8548 Exp $
 * @since 1.0
 * @struts:action path="/persPrivDbSetContDetail" name="persPrivDbSetContDetailForm"
 *                input="/dream/pers/priv/db/set/persPrivDbSetContDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivDbSetContDetail" path="/dream/pers/priv/db/set/persPrivDbSetContDetail.jsp"
 *                        redirect="false"
 */
public class PersPrivDbSetContDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PersPrivDbSetContDetailForm persPrivDbSetContDetailForm = (PersPrivDbSetContDetailForm) form;
        
        super.updateAudit(persPrivDbSetContDetailForm.getPersPrivDbSetContDetailDTO().getAuditKey()==""?persPrivDbSetContDetailForm.getPersPrivDbSetContListDTO().getAuditKey():persPrivDbSetContDetailForm.getPersPrivDbSetContDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (persPrivDbSetContDetailForm.getStrutsAction())
        {
            case PersPrivDbSetContDetailAction.DETAIL_INIT:
                this.findDetail(request, response, persPrivDbSetContDetailForm);
                returnActionForward = mapping.findForward("persPrivDbSetContDetail");
                break;
            case PersPrivDbSetContDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, persPrivDbSetContDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PersPrivDbSetContDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, persPrivDbSetContDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("persPrivDbSetContDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param persPrivDbSetContDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetContDetailForm persPrivDbSetContDetailForm) throws Exception 
    {   
    	PersPrivDbSetContDetailService persPrivDbSetContDetailService = (PersPrivDbSetContDetailService)getBean("persPrivDbSetContDetailService");
    	
    	PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = persPrivDbSetContDetailForm.getPersPrivDbSetCommonDTO(); 
    	PersPrivDbSetContListDTO persPrivDbSetContListDTO = persPrivDbSetContDetailForm.getPersPrivDbSetContListDTO();
    	PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO = persPrivDbSetContDetailService.findDetail(persPrivDbSetCommonDTO,persPrivDbSetContListDTO, getUser(request));
    	persPrivDbSetContDetailForm.setPersPrivDbSetContDetailDTO(persPrivDbSetContDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param persPrivDbSetContDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetContDetailForm persPrivDbSetContDetailForm) throws Exception
    {
    	PersPrivDbSetContDetailService persPrivDbSetContDetailService = (PersPrivDbSetContDetailService)getBean("persPrivDbSetContDetailService");
    	PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = persPrivDbSetContDetailForm.getPersPrivDbSetCommonDTO(); 
    	PersPrivDbSetContDetailDTO  persPrivDbSetContDetailDTO = persPrivDbSetContDetailForm.getPersPrivDbSetContDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	persPrivDbSetContDetailService.insertDetail(persPrivDbSetCommonDTO, persPrivDbSetContDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param persPrivDbSetContDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetContDetailForm persPrivDbSetContDetailForm) throws Exception
    {
    	PersPrivDbSetContDetailService persPrivDbSetContDetailService = (PersPrivDbSetContDetailService)getBean("persPrivDbSetContDetailService");
    	PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = persPrivDbSetContDetailForm.getPersPrivDbSetCommonDTO(); 
    	PersPrivDbSetContDetailDTO  persPrivDbSetContDetailDTO = persPrivDbSetContDetailForm.getPersPrivDbSetContDetailDTO();
    	persPrivDbSetContDetailService.updateDetail(persPrivDbSetCommonDTO, persPrivDbSetContDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}