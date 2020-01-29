package dream.consult.program.guide.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
import dream.consult.program.guide.dto.ConsultPgmGuideDetailDTO;
import dream.consult.program.guide.form.ConsultPgmGuideDetailForm;
import dream.consult.program.guide.service.ConsultPgmGuideDetailService;

/**
 * Guide Page - Detail Action
 * 
 * @author kim21017
 * @version $Id: ConsultPgmGuideDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/consultPgmGuideDetail" name="consultPgmGuideDetailForm"
 *                input="/dream/consult/program/guide/consultPgmGuideDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmGuideDetail" path="/dream/consult/program/guide/consultPgmGuideDetail.jsp"
 *                        redirect="false"
 */
public class ConsultPgmGuideDetailAction extends ConsultAuthAction
{
	//일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmGuideDetailForm consultPgmGuideDetailForm = (ConsultPgmGuideDetailForm) form;
        
        switch (consultPgmGuideDetailForm.getStrutsAction())
        {
            case ConsultPgmGuideDetailAction.DETAIL_INIT:
                this.findDetail(request, response, consultPgmGuideDetailForm);
                returnActionForward = mapping.findForward("consultPgmGuideDetail");
                break;
            case ConsultPgmGuideDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, consultPgmGuideDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmGuideDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, consultPgmGuideDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultPgmGuideDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param consultPgmGuideDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmGuideDetailForm consultPgmGuideDetailForm) throws Exception 
    {   
    	ConsultPgmGuideDetailService consultPgmGuideDetailService = (ConsultPgmGuideDetailService)getBean("consultPgmGuideDetailService");
    	
    	ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO = consultPgmGuideDetailForm.getConsultPgmGuideCommonDTO(); 

    	//컨설트 페이지에서 user객체에 회사코드가 담겨있지않습니다. 이 페이지는 교육용 페이지이기 때문에 여기에서 회사코드를 임의로 설정했습니다.
    	User user = getUser(request);
    	user.setCompNo("100");
    	
    	ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO = consultPgmGuideDetailService.findPgmGuideDetail(consultPgmGuideCommonDTO, user);
    	consultPgmGuideDetailForm.setConsultPgmGuideDetailDTO(consultPgmGuideDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param consultPgmGuideDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmGuideDetailForm consultPgmGuideDetailForm) throws Exception
    {
    	ConsultPgmGuideDetailService consultPgmGuideDetailService = (ConsultPgmGuideDetailService)getBean("consultPgmGuideDetailService");
    	ConsultPgmGuideDetailDTO  consultPgmGuideDetailDTO = consultPgmGuideDetailForm.getConsultPgmGuideDetailDTO(); 
    	
    	//컨설트 페이지에서 user객체에 회사코드가 담겨있지않습니다. 이 페이지는 교육용 페이지이기 때문에 여기에서 회사코드를 임의로 설정했습니다.
    	User user = getUser(request);
    	user.setCompNo("100");
    	
    	consultPgmGuideDetailService.insertPgmGuideDetail(consultPgmGuideDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultPgmGuideDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmGuideDetailForm consultPgmGuideDetailForm) throws Exception
    {
    	ConsultPgmGuideDetailService consultPgmGuideDetailService = (ConsultPgmGuideDetailService)getBean("consultPgmGuideDetailService");
    	ConsultPgmGuideDetailDTO  consultPgmGuideDetailDTO = consultPgmGuideDetailForm.getConsultPgmGuideDetailDTO();
    	
    	//컨설트 페이지에서 user객체에 회사코드가 담겨있지않습니다. 이 페이지는 교육용 페이지이기 때문에 여기에서 회사코드를 임의로 설정했습니다.
    	User user = getUser(request);
    	user.setCompNo("100");
    	
    	consultPgmGuideDetailService.updatePgmGuideDetail(consultPgmGuideDetailDTO, user);
        
        setAjaxStatus(request);
    }

}