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
import dream.pers.priv.db.set.dto.PersPrivDbSetDetailDTO;
import dream.pers.priv.db.set.form.PersPrivDbSetDetailForm;
import dream.pers.priv.db.set.service.PersPrivDbSetDetailService;

/**
 * Guide Page - Detail Action
 * 
 * @author nhkim8548
 * @version $Id: PersPrivDbSetDetailAction.java,v 1.0 2018/08/01 14:30:40 nhkim8548 Exp $
 * @since 1.0
 * @struts:action path="/persPrivDbSetDetail" name="persPrivDbSetDetailForm"
 *                input="/dream/pers/priv/db/set/persPrivDbSetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivDbSetDetail" path="/dream/pers/priv/db/set/persPrivDbSetDetail.jsp"
 *                        redirect="false"
 */
public class PersPrivDbSetDetailAction extends AuthAction
{
	//일반 페이지 적용시 AuthAction 으로 변경해야합니다.
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
        PersPrivDbSetDetailForm persPrivDbSetDetailForm = (PersPrivDbSetDetailForm) form;
        
        super.updateAudit(persPrivDbSetDetailForm.getPersPrivDbSetDetailDTO().getAuditKey()==""?persPrivDbSetDetailForm.getPersPrivDbSetCommonDTO().getAuditKey():persPrivDbSetDetailForm.getPersPrivDbSetDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (persPrivDbSetDetailForm.getStrutsAction())
        {
            case PersPrivDbSetDetailAction.DETAIL_INIT:
                this.findDetail(request, response, persPrivDbSetDetailForm);
                returnActionForward = mapping.findForward("persPrivDbSetDetail");
                break;
            case PersPrivDbSetDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, persPrivDbSetDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PersPrivDbSetDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, persPrivDbSetDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("persPrivDbSetDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param persPrivDbSetDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetDetailForm persPrivDbSetDetailForm) throws Exception 
    {   
    	PersPrivDbSetDetailService persPrivDbSetDetailService = (PersPrivDbSetDetailService)getBean("persPrivDbSetDetailService");
    	
    	PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = persPrivDbSetDetailForm.getPersPrivDbSetCommonDTO(); 

    	User user = getUser(request);
    	PersPrivDbSetDetailDTO persPrivDbSetDetailDTO = persPrivDbSetDetailService.findDetail(persPrivDbSetCommonDTO, user);
    	persPrivDbSetDetailForm.setPersPrivDbSetDetailDTO(persPrivDbSetDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param persPrivDbSetDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetDetailForm persPrivDbSetDetailForm) throws Exception
    {
    	PersPrivDbSetDetailService persPrivDbSetDetailService = (PersPrivDbSetDetailService)getBean("persPrivDbSetDetailService");
    	PersPrivDbSetDetailDTO  persPrivDbSetDetailDTO = persPrivDbSetDetailForm.getPersPrivDbSetDetailDTO(); 
    	
    	User user = getUser(request);
    	persPrivDbSetDetailService.fineDbMenu(persPrivDbSetDetailDTO, user);
    	
    	persPrivDbSetDetailService.insertDetail(persPrivDbSetDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param persPrivDbSetDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetDetailForm persPrivDbSetDetailForm) throws Exception
    {
    	PersPrivDbSetDetailService persPrivDbSetDetailService = (PersPrivDbSetDetailService)getBean("persPrivDbSetDetailService");
    	PersPrivDbSetDetailDTO  persPrivDbSetDetailDTO = persPrivDbSetDetailForm.getPersPrivDbSetDetailDTO();
    	
    	//컨설트 페이지에서 user객체에 회사코드가 담겨있지않습니다. 이 페이지는 교육용 페이지이기 때문에 여기에서 회사코드를 임의로 설정했습니다.
    	User user = getUser(request);
    	    	
    	persPrivDbSetDetailService.updateDetail(persPrivDbSetDetailDTO, user);
        
        setAjaxStatus(request);
    }

}