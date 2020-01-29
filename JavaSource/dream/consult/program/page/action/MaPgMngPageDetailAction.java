package dream.consult.program.page.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageDetailDTO;
import dream.consult.program.page.form.MaPgMngPageDetailForm;
import dream.consult.program.page.service.MaPgMngPageDetailService;

/**
 * 화면별 탭페이지 상세
 * @author  kim21017
 * @version $Id: MaPgMngPageDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPgMngPageDetail" name="maPgMngPageDetailForm"
 *                input="/dream/consult/program/page/maPgMngPageDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngPageDetail" path="/dream/consult/program/page/maPgMngPageDetail.jsp"
 *                        redirect="false"
 */
public class MaPgMngPageDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PG_PAGE_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int PG_PAGE_DETAIL_UPDATE 	= 1002;
    /** 입력 */
    public static final int PG_PAGE_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngPageDetailForm maPgMngPageDetailForm = (MaPgMngPageDetailForm) form;
        switch (maPgMngPageDetailForm.getStrutsAction())
        {
            case MaPgMngPageDetailAction.PG_PAGE_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPgMngPageDetailForm);
                returnActionForward = mapping.findForward("maPgMngPageDetail");
                break;
            case MaPgMngPageDetailAction.PG_PAGE_DETAIL_UPDATE:
            	updateDetail(maPgMngPageDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngPageDetailAction.PG_PAGE_DETAIL_INPUT:
            	insertDetail(maPgMngPageDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPgMngPageDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 화면별 탭페이지 상세 조회
     * @author kim2107
     * @version $Id: MaPgMngPageDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngPageDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPgMngPageDetailForm maPgMngPageDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPgMngPageDetailService maPgMngPageDetailService = (MaPgMngPageDetailService)getBean("maPgMngPageDetailService");

    	// 넘겨진 pageId 구함
        String pageId = maPgMngPageDetailForm.getMaPgMngCommonDTO().getPageId();
        // 넘겨진 pgPageId 구함
        String pgPageId = maPgMngPageDetailForm.getMaPgMngPageListDTO().getPgPageId();
        
        // 조회된 상세 부분
        MaPgMngPageDetailDTO maPgMngPageDetailDTO = maPgMngPageDetailService.findDetail(pageId, pgPageId,getUser(request).getLangId());
        
        // 조회된 상세  셋팅한다.
        maPgMngPageDetailForm.setMaPgMngPageDetailDTO(maPgMngPageDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPgMngPageDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngPageDetailForm
     * @param request
     */
    private void updateDetail(MaPgMngPageDetailForm maPgMngPageDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngPageDetailService maPgMngPageDetailService = (MaPgMngPageDetailService) getBean("maPgMngPageDetailService");
        
        MaPgMngPageDetailDTO maPgMngPageDetailDTO = maPgMngPageDetailForm.getMaPgMngPageDetailDTO();
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngPageDetailForm.getMaPgMngCommonDTO();
        maPgMngPageDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maPgMngPageDetailService.updateDetail(maPgMngPageDetailDTO,maPgMngCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPgMngPageDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngPageDetailForm
     * @param request
     */
    private void insertDetail(MaPgMngPageDetailForm maPgMngPageDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngPageDetailService maPgMngPageDetailService = (MaPgMngPageDetailService) getBean("maPgMngPageDetailService");
        
        MaPgMngPageDetailDTO maPgMngPageDetailDTO = maPgMngPageDetailForm.getMaPgMngPageDetailDTO();
        
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngPageDetailForm.getMaPgMngCommonDTO();
        maPgMngPageDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maPgMngPageDetailService.insertDetail(maPgMngPageDetailDTO, maPgMngCommonDTO);
        
        setAjaxStatus(request);
    }
}