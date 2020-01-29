package dream.work.cal.pmmonth.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedDetailDTO;
import dream.work.cal.pmmonth.form.MaWoMonthSchedDetailForm;
import dream.work.cal.pmmonth.service.MaWoMonthSchedDetailService;

/**
 * 월간예방일정 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaWoMonthSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maWoMonthSchedDetail" name="maWoMonthSchedDetailForm"
 *                input="/dream/work/cal/pmmonth/maWoMonthSchedDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoMonthSchedDetail" path="/dream/work/cal/pmmonth/maWoMonthSchedDetail.jsp"
 *                        redirect="false"
 */
public class MaWoMonthSchedDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MONTH_SCHED_DETAIL_INIT			= 1001;
    /** 수정 */
    public static final int MONTH_SCHED_DETAIL_UPDATE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoMonthSchedDetailForm maWoMonthSchedDetailForm = (MaWoMonthSchedDetailForm) form;
        
        switch (maWoMonthSchedDetailForm.getStrutsAction())
        {
            case MaWoMonthSchedDetailAction.MONTH_SCHED_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maWoMonthSchedDetailForm);
                returnActionForward = mapping.findForward("maWoMonthSchedDetail");
                break;
            case MaWoMonthSchedDetailAction.MONTH_SCHED_DETAIL_UPDATE:
            	updateDetail(maWoMonthSchedDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maWoMonthSchedDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 월간예방일정 상세 조회
     * @author kim2107
     * @version $Id: MaWoMonthSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoMonthSchedDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoMonthSchedDetailForm maWoMonthSchedDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaWoMonthSchedDetailService maWoMonthSchedDetailService = (MaWoMonthSchedDetailService)getBean("maWoMonthSchedDetailService");

        // 넘겨진 PM스케쥴Id 구함
        String pmSchedId = maWoMonthSchedDetailForm.getMaWoMonthSchedCommonDTO().getPmSchedId();
        
        // 조회된 상세 부분
        MaWoMonthSchedDetailDTO maWoMonthSchedDetailDTO = maWoMonthSchedDetailService.findDetail(pmSchedId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maWoMonthSchedDetailForm.setMaWoMonthSchedDetailDTO(maWoMonthSchedDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoMonthSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedDetailForm
     * @param request
     */
    private void updateDetail(MaWoMonthSchedDetailForm maWoMonthSchedDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoMonthSchedDetailService maWoMonthSchedDetailService = (MaWoMonthSchedDetailService) getBean("maWoMonthSchedDetailService");
        
        MaWoMonthSchedDetailDTO maWoMonthSchedDetailDTO = maWoMonthSchedDetailForm.getMaWoMonthSchedDetailDTO();
        
        maWoMonthSchedDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoMonthSchedDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maWoMonthSchedDetailService.updateDetail(maWoMonthSchedDetailDTO);
        
        setAjaxStatus(request);
    }
}