package dream.consult.program.btn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.btn.dto.MaBtnMngDetailDTO;
import dream.consult.program.btn.form.MaBtnMngDetailForm;
import dream.consult.program.btn.service.MaBtnMngDetailService;

/**
 * 버튼 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaBtnMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maBtnMngDetail" name="maBtnMngDetailForm"
 *                input="/dream/consult/program/btn/maBtnMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBtnMngDetail" path="/dream/consult/program/btn/maBtnMngDetail.jsp"
 *                        redirect="false"
 */
public class MaBtnMngDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int BTN_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int BTN_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int BTN_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaBtnMngDetailForm maBtnMngDetailForm = (MaBtnMngDetailForm) form;
        
        switch (maBtnMngDetailForm.getStrutsAction())
        {
            case MaBtnMngDetailAction.BTN_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maBtnMngDetailForm);
                returnActionForward = mapping.findForward("maBtnMngDetail");
                break;
            case MaBtnMngDetailAction.BTN_DETAIL_INPUT:
            	insertDetail(maBtnMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBtnMngDetailAction.BTN_DETAIL_UPDATE:
            	updateDetail(maBtnMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maBtnMngDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 버튼 상세 조회
     * @author kim2107
     * @version $Id: MaBtnMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBtnMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaBtnMngDetailForm maBtnMngDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaBtnMngDetailService maBtnMngDetailService = (MaBtnMngDetailService)getBean("maBtnMngDetailService");

        // 넘겨진 버튼Id 구함
        String buttonId = maBtnMngDetailForm.getMaBtnMngCommonDTO().getButtonId();
        
        // 조회된 상세 부분
        MaBtnMngDetailDTO maBtnMngDetailDTO = maBtnMngDetailService.findDetail(buttonId);
        
        // 조회된 상세  셋팅한다.
        maBtnMngDetailForm.setMaBtnMngDetailDTO(maBtnMngDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaBtnMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailForm
     * @param request
     */
    private void insertDetail(MaBtnMngDetailForm maBtnMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaBtnMngDetailService maBtnMngDetailService = (MaBtnMngDetailService) getBean("maBtnMngDetailService");
        
        MaBtnMngDetailDTO maBtnMngDetailDTO = maBtnMngDetailForm.getMaBtnMngDetailDTO();
        
        maBtnMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maBtnMngDetailService.insertDetail(maBtnMngDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaBtnMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailForm
     * @param request
     */
    private void updateDetail(MaBtnMngDetailForm maBtnMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaBtnMngDetailService maBtnMngDetailService = (MaBtnMngDetailService) getBean("maBtnMngDetailService");
        
        MaBtnMngDetailDTO maBtnMngDetailDTO = maBtnMngDetailForm.getMaBtnMngDetailDTO();
        
        maBtnMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maBtnMngDetailService.updateDetail(maBtnMngDetailDTO);
        
        setAjaxStatus(request);
    }
}