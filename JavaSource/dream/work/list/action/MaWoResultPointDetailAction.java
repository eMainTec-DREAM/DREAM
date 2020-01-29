package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointDetailDTO;
import dream.work.list.form.MaWoResultPointDetailForm;
import dream.work.list.service.MaWoResultPointDetailService;

/**
 * 작업결과 - 검사항목
 * @author  kim21017
 * @version $Id: MaWoResultPointDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultPointDetail" name="maWoResultPointDetailForm"
 *                input="/dream/work/list/maWoResultPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultMonthPointDetail" name="maWoResultPointDetailForm"
 *                input="/dream/work/list/month/maWoResultMonthPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmInsPointDetail" name="maWoResultPointDetailForm"
 *                input="/dream/work/list/pmi/maWoResultPmInsPointDetail.jsp" scope="request"
 *                validate="false"
 */
public class MaWoResultPointDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_POINT_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int WO_RESULT_POINT_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int WO_RESULT_POINT_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultPointDetailForm maWoResultPointDetailForm = (MaWoResultPointDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultPointDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(maWoResultPointDetailForm.getMaWoResultPointDetailDTO().getAuditKey()==""?maWoResultPointDetailForm.getMaWoResultPointListDTO().getAuditKey():maWoResultPointDetailForm.getMaWoResultPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maWoResultPointDetailForm.getStrutsAction())
        {
            case MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maWoResultPointDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_UPDATE:
            	updateDetail(maWoResultPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_INPUT:
            	insertDetail(maWoResultPointDetailForm, request);
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
     * @version $Id: MaWoResultPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultPointDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoResultPointDetailForm maWoResultPointDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaWoResultPointDetailService maWoResultPointDetailService = (MaWoResultPointDetailService)getBean("maWoResultPointDetailService");

    	// 작업결과Id 구함
        String wkOrId = maWoResultPointDetailForm.getMaWoResultMstrCommonDTO().getWkOrId();
        // 넘겨진 점검항목id 구함
        String woPointId = maWoResultPointDetailForm.getMaWoResultPointListDTO().getWoPointId();
        // 넘겨진 점검항목id 구함
        String pmPointId = maWoResultPointDetailForm.getMaWoResultPointListDTO().getPmPointId();
        
        // 조회된 상세 부분
        MaWoResultPointDetailDTO maWoResultPointDetailDTO = maWoResultPointDetailService.findDetail(wkOrId, woPointId, pmPointId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maWoResultPointDetailForm.setMaWoResultPointDetailDTO(maWoResultPointDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoResultPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPointDetailForm
     * @param request
     */
    private void updateDetail(MaWoResultPointDetailForm maWoResultPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultPointDetailService maWoResultPointDetailService = (MaWoResultPointDetailService) getBean("maWoResultPointDetailService");
        
        MaWoResultPointDetailDTO maWoResultPointDetailDTO = maWoResultPointDetailForm.getMaWoResultPointDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultPointDetailForm.getMaWoResultMstrCommonDTO();
        maWoResultPointDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultPointDetailService.updateDetail(maWoResultPointDetailDTO,maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaWoResultPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPointDetailForm
     * @param request
     */
    private void insertDetail(MaWoResultPointDetailForm maWoResultPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultPointDetailService maWoResultPointDetailService = (MaWoResultPointDetailService) getBean("maWoResultPointDetailService");
        
        MaWoResultPointDetailDTO maWoResultPointDetailDTO = maWoResultPointDetailForm.getMaWoResultPointDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultPointDetailForm.getMaWoResultMstrCommonDTO();
        maWoResultPointDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultPointDetailService.insertDetail(maWoResultPointDetailDTO, maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}