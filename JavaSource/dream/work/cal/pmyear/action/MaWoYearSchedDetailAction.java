package dream.work.cal.pmyear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.cal.pmyear.dto.MaWoYearSchedDetailDTO;
import dream.work.cal.pmyear.form.MaWoYearSchedDetailForm;
import dream.work.cal.pmyear.service.MaWoYearSchedDetailService;

/**
 * 연간작업일정 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaWoYearSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maWoYearSchedDetail" name="maWoYearSchedDetailForm"
 *                input="/dream/work/cal/pmyear/maWoYearSchedDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoYearSchedDetail" path="/dream/work/cal/pmyear/maWoYearSchedDetail.jsp"
 *                        redirect="false"
 */
public class MaWoYearSchedDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int YEAR_SCHED_DETAIL_INIT			= 1001;
    /** 수정 */
    public static final int YEAR_SCHED_DETAIL_UPDATE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoYearSchedDetailForm maWoYearSchedDetailForm = (MaWoYearSchedDetailForm) form;
        
        switch (maWoYearSchedDetailForm.getStrutsAction())
        {
            case MaWoYearSchedDetailAction.YEAR_SCHED_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maWoYearSchedDetailForm);
                returnActionForward = mapping.findForward("maWoYearSchedDetail");
                break;
            case MaWoYearSchedDetailAction.YEAR_SCHED_DETAIL_UPDATE:
            	updateDetail(maWoYearSchedDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maWoYearSchedDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 연간작업일정 상세 조회
     * @author kim2107
     * @version $Id: MaWoYearSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoYearSchedDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoYearSchedDetailForm maWoYearSchedDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaWoYearSchedDetailService maWoYearSchedDetailService = (MaWoYearSchedDetailService)getBean("maWoYearSchedDetailService");

        // 넘겨진 PM스케쥴Id 구함
        String pmSchedId = maWoYearSchedDetailForm.getMaWoYearSchedCommonDTO().getPmSchedId();
        
        // 조회된 상세 부분
        MaWoYearSchedDetailDTO maWoYearSchedDetailDTO = maWoYearSchedDetailService.findDetail(pmSchedId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maWoYearSchedDetailForm.setMaWoYearSchedDetailDTO(maWoYearSchedDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoYearSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoYearSchedDetailForm
     * @param request
     */
    private void updateDetail(MaWoYearSchedDetailForm maWoYearSchedDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoYearSchedDetailService maWoYearSchedDetailService = (MaWoYearSchedDetailService) getBean("maWoYearSchedDetailService");
        
        MaWoYearSchedDetailDTO maWoYearSchedDetailDTO = maWoYearSchedDetailForm.getMaWoYearSchedDetailDTO();
        
        maWoYearSchedDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoYearSchedDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoYearSchedDetailService.updateDetail(maWoYearSchedDetailDTO);
        
        setAjaxStatus(request);
    }
}