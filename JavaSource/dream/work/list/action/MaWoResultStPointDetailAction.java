package dream.work.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointDetailDTO;
import dream.work.list.form.MaWoResultStPointDetailForm;
import dream.work.list.service.MaWoResultStPointDetailService;

/**
 * 작업결과 - 작업필수검사항목
 * @author  kim21017
 * @version $Id: MaWoResultStPointDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultStPointDetail" name="maWoResultStPointDetailForm"
 *                input="/dream/work/list/maWoResultStPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultMonthStPointDetail" name="maWoResultStPointDetailForm"
 *                input="/dream/work/list/month/maWoResultMonthStPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultStPointDetail" path="/dream/work/list/maWoResultStPointDetail.jsp"
 *                        redirect="false"
 */
public class MaWoResultStPointDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_STPOINT_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int WO_RESULT_STPOINT_DETAIL_UPDATE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultStPointDetailForm maWoResultStPointDetailForm = (MaWoResultStPointDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultStPointDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (maWoResultStPointDetailForm.getStrutsAction())
        {
            case MaWoResultStPointDetailAction.WO_RESULT_STPOINT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maWoResultStPointDetailForm);
                returnActionForward = mapping.findForward("maWoResultStPointDetail");
                break;
            case MaWoResultStPointDetailAction.WO_RESULT_STPOINT_DETAIL_UPDATE:
            	updateDetail(maWoResultStPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업결과-검사항목 상세 조회
     * @author kim2107
     * @version $Id: MaWoResultStPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultStPointDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoResultStPointDetailForm maWoResultStPointDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaWoResultStPointDetailService maWoResultStPointDetailService = (MaWoResultStPointDetailService)getBean("maWoResultStPointDetailService");

    	// 작업결과Id 구함
        String wkOrId = maWoResultStPointDetailForm.getMaWoResultMstrCommonDTO().getWkOrId();
        // 넘겨진 필수점검항목id 구함
        String woStPointId = maWoResultStPointDetailForm.getMaWoResultStPointListDTO().getWoStPointId();
        
        // 조회된 상세 부분
        MaWoResultStPointDetailDTO maWoResultStPointDetailDTO = maWoResultStPointDetailService.findDetail(wkOrId, woStPointId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maWoResultStPointDetailForm.setMaWoResultStPointDetailDTO(maWoResultStPointDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoResultStPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultStPointDetailForm
     * @param request
     */
    private void updateDetail(MaWoResultStPointDetailForm maWoResultStPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultStPointDetailService maWoResultStPointDetailService = (MaWoResultStPointDetailService) getBean("maWoResultStPointDetailService");
        
        MaWoResultStPointDetailDTO maWoResultStPointDetailDTO = maWoResultStPointDetailForm.getMaWoResultStPointDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultStPointDetailForm.getMaWoResultMstrCommonDTO();
        maWoResultStPointDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultStPointDetailService.updateDetail(maWoResultStPointDetailDTO,maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}