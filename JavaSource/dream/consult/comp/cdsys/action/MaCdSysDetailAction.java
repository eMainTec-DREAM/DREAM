package dream.consult.comp.cdsys.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.comp.cdsys.dto.MaCdSysDetailDTO;
import dream.consult.comp.cdsys.form.MaCdSysDetailForm;
import dream.consult.comp.cdsys.service.MaCdSysDetailService;

/**
 * 시스템코드 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaCdSysDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maCdSysDetail" name="maCdSysDetailForm"
 *                input="/dream/consult/comp/cdsys/maCdSysDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCdSysDetail" path="/dream/consult/comp/cdsys/maCdSysDetail.jsp"
 *                        redirect="false"
 */
public class MaCdSysDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LISTTYPE_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int LISTTYPE_DETAIL_UPDATE 		= 1002;
    /** 입력 */
    public static final int LISTTYPE_DETAIL_INPUT 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaCdSysDetailForm maCdSysDetailForm = (MaCdSysDetailForm) form;
        
        switch (maCdSysDetailForm.getStrutsAction())
        {
            case MaCdSysDetailAction.LISTTYPE_DETAIL_INIT:
                this.findDetail(request, maCdSysDetailForm);
                returnActionForward = mapping.findForward("maCdSysDetail");
                break;
            case MaCdSysDetailAction.LISTTYPE_DETAIL_UPDATE:
            	updateDetail(maCdSysDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaCdSysDetailAction.LISTTYPE_DETAIL_INPUT:
            	insertDetail(maCdSysDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maCdSysDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 시스템코드 상세 조회
     * @author kim2107
     * @version $Id: MaCdSysDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maCdSysDetailForm
     */
    private void findDetail(HttpServletRequest request, MaCdSysDetailForm maCdSysDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaCdSysDetailService maCdSysDetailService = (MaCdSysDetailService)getBean("maCdSysDetailService");

        // 넘겨진 cdSysMId 구함
        String cdSysMId = maCdSysDetailForm.getMaCdSysCommonDTO().getCdSysMId();
        
        // 조회된 상세 부분
        MaCdSysDetailDTO maCdSysDetailDTO = maCdSysDetailService.findDetail(cdSysMId, getUser(request).getLangId());
        
        // 조회된 상세  셋팅한다.
        maCdSysDetailForm.setMaCdSysDetailDTO(maCdSysDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaCdSysDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDetailForm
     * @param request
     */
    private void insertDetail(MaCdSysDetailForm maCdSysDetailForm, HttpServletRequest request) throws Exception
    {
    	MaCdSysDetailService maCdSysDetailService = (MaCdSysDetailService) getBean("maCdSysDetailService");
        
        MaCdSysDetailDTO maCdSysDetailDTO = maCdSysDetailForm.getMaCdSysDetailDTO();
        
        maCdSysDetailDTO.setEnterBy(getUser(request).getUserId());
        maCdSysDetailDTO.setUserLang(getUser(request).getLangId());
        
        maCdSysDetailService.insertDetail(maCdSysDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaCdSysDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDetailForm
     * @param request
     */
    private void updateDetail(MaCdSysDetailForm maCdSysDetailForm, HttpServletRequest request) throws Exception
    {
    	MaCdSysDetailService maCdSysDetailService = (MaCdSysDetailService) getBean("maCdSysDetailService");
        
        MaCdSysDetailDTO maCdSysDetailDTO = maCdSysDetailForm.getMaCdSysDetailDTO();
        
        maCdSysDetailDTO.setEnterBy(getUser(request).getUserId());
        maCdSysDetailDTO.setUserLang(getUser(request).getLangId());
        
        maCdSysDetailService.updateDetail(maCdSysDetailDTO);
        
        setAjaxStatus(request);
    }
}