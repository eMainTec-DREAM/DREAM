package dream.consult.program.page.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngDetailDTO;
import dream.consult.program.page.form.MaPgMngDetailForm;
import dream.consult.program.page.service.MaPgMngDetailService;

/**
 * 화면 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maPgMngDetail" name="maPgMngDetailForm"
 *                input="/dream/consult/program/page/maPgMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngDetail" path="/dream/consult/program/page/maPgMngDetail.jsp"
 *                        redirect="false"
 */
public class MaPgMngDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PG_DETAIL_INIT 			= 1001;
    /** 저장 */
    public static final int PG_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int PG_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngDetailForm maPgMngDetailForm = (MaPgMngDetailForm) form;
        
        switch (maPgMngDetailForm.getStrutsAction())
        {
            case MaPgMngDetailAction.PG_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPgMngDetailForm);
                returnActionForward = mapping.findForward("maPgMngDetail");
                break;
            case MaPgMngDetailAction.PG_DETAIL_INPUT:
            	insertDetail(maPgMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngDetailAction.PG_DETAIL_UPDATE:
            	updateDetail(maPgMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPgMngDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 화면 상세 조회
     * @author kim2107
     * @version $Id: MaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPgMngDetailForm maPgMngDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPgMngDetailService maPgMngDetailService = (MaPgMngDetailService)getBean("maPgMngDetailService");

        // 넘겨진 페이지Id 구함
        String pageId = maPgMngDetailForm.getMaPgMngCommonDTO().getPageId();
        
        // 조회된 상세 부분
        MaPgMngDetailDTO maPgMngDetailDTO = maPgMngDetailService.findDetail(pageId,getUser(request));
        
        // 조회된 상세  셋팅한다.
        maPgMngDetailForm.setMaPgMngDetailDTO(maPgMngDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailForm
     * @param request
     */
    private void insertDetail(MaPgMngDetailForm maPgMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaPgMngDetailService maPgMngDetailService = (MaPgMngDetailService) getBean("maPgMngDetailService");
        
        MaPgMngDetailDTO maPgMngDetailDTO = maPgMngDetailForm.getMaPgMngDetailDTO();
        
        maPgMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maPgMngDetailService.insertDetail(maPgMngDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailForm
     * @param request
     */
    private void updateDetail(MaPgMngDetailForm maPgMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngDetailService maPgMngDetailService = (MaPgMngDetailService) getBean("maPgMngDetailService");
        
        MaPgMngDetailDTO maPgMngDetailDTO = maPgMngDetailForm.getMaPgMngDetailDTO();
        
        maPgMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maPgMngDetailService.updateDetail(maPgMngDetailDTO);
        
        setAjaxStatus(request);
    }
}