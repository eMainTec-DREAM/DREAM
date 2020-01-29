package dream.consult.program.page.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;
import dream.consult.program.page.form.MaPgMngFieldValueDetailForm;
import dream.consult.program.page.service.MaPgMngFieldValueDetailService;

/**
 * 화면별 필드 기본값 상세
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPgMngFieldValueDetail" name="maPgMngFieldValueDetailForm"
 *                input="/dream/consult/program/page/maPgMngFieldValueDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngFieldValueDetail" path="/dream/consult/program/page/maPgMngFieldValueDetail.jsp"
 *                        redirect="false"
 */
public class MaPgMngFieldValueDetailAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int INIT_DETAIL 	= 1001;
    /** 수정 */
    public static final int UPDATE_DETAIL 	= 1002;
    /** 입력 */
    public static final int INPUT_DETAIL 	= 1003;
    /** 중복회사코드 검사 */
    public static final int CHECK_COMP_NO	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngFieldValueDetailForm maPgMngFieldValueDetailForm = (MaPgMngFieldValueDetailForm) form;
        switch (maPgMngFieldValueDetailForm.getStrutsAction())
        {
            case MaPgMngFieldValueDetailAction.INIT_DETAIL:
                this.findDetail(request, maPgMngFieldValueDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPgMngFieldValueDetailAction.UPDATE_DETAIL:
            	this.updateDetail(maPgMngFieldValueDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngFieldValueDetailAction.INPUT_DETAIL:
            	this.insertDetail(maPgMngFieldValueDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngFieldValueDetailAction.CHECK_COMP_NO:
            	validCompNo(maPgMngFieldValueDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 화면별 필드 상세 조회
     * @author kim2107
     * @version $Id: MaPgMngFieldValueDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngFieldValueDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPgMngFieldValueDetailForm maPgMngFieldValueDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPgMngFieldValueDetailService maPgMngFieldValueDetailService = (MaPgMngFieldValueDetailService)getBean("maPgMngFieldValueDetailService");

    	MaPgMngCommonDTO maPgMngCommonDTO = maPgMngFieldValueDetailForm.getMaPgMngCommonDTO();
    	MaPgMngFieldDetailDTO maPgMngFieldDetailDTO = maPgMngFieldValueDetailForm.getMaPgMngFieldDetailDTO();
    	MaPgMngFieldValueListDTO maPgMngFieldValueListDTO = maPgMngFieldValueDetailForm.getMaPgMngFieldValueListDTO();

        // 조회된 상세 부분
        MaPgMngFieldValueDetailDTO resultDTO = maPgMngFieldValueDetailService.findDetail(maPgMngCommonDTO, maPgMngFieldDetailDTO,maPgMngFieldValueListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maPgMngFieldValueDetailForm.setMaPgMngFieldValueDetailDTO(resultDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPgMngFieldValueDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldValueDetailForm
     * @param request
     */
    private void updateDetail(MaPgMngFieldValueDetailForm maPgMngFieldValueDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngFieldValueDetailService maPgMngFieldValueDetailService = (MaPgMngFieldValueDetailService) getBean("maPgMngFieldValueDetailService");
        
    	MaPgMngCommonDTO maPgMngCommonDTO = maPgMngFieldValueDetailForm.getMaPgMngCommonDTO();
        MaPgMngFieldDetailDTO maPgMngFieldDetailDTO = maPgMngFieldValueDetailForm.getMaPgMngFieldDetailDTO();
        MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO = maPgMngFieldValueDetailForm.getMaPgMngFieldValueDetailDTO();
        
        maPgMngFieldValueDetailService.updateDetail(maPgMngCommonDTO, maPgMngFieldDetailDTO, maPgMngFieldValueDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPgMngFieldValueDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldValueDetailForm
     * @param request
     */
    private void insertDetail(MaPgMngFieldValueDetailForm maPgMngFieldValueDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngFieldValueDetailService maPgMngFieldValueDetailService = (MaPgMngFieldValueDetailService) getBean("maPgMngFieldValueDetailService");
        
    	MaPgMngCommonDTO maPgMngCommonDTO = maPgMngFieldValueDetailForm.getMaPgMngCommonDTO();
        MaPgMngFieldDetailDTO maPgMngFieldDetailDTO = maPgMngFieldValueDetailForm.getMaPgMngFieldDetailDTO();
        MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO = maPgMngFieldValueDetailForm.getMaPgMngFieldValueDetailDTO();
        
        maPgMngFieldValueDetailService.insertDetail(maPgMngCommonDTO, maPgMngFieldDetailDTO, maPgMngFieldValueDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void validCompNo(MaPgMngFieldValueDetailForm maPgMngFieldValueDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngFieldValueDetailService maPgMngFieldValueDetailService = (MaPgMngFieldValueDetailService) getBean("maPgMngFieldValueDetailService");
        
    	MaPgMngCommonDTO maPgMngCommonDTO = maPgMngFieldValueDetailForm.getMaPgMngCommonDTO();
        MaPgMngFieldDetailDTO maPgMngFieldDetailDTO = maPgMngFieldValueDetailForm.getMaPgMngFieldDetailDTO();
        MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO = maPgMngFieldValueDetailForm.getMaPgMngFieldValueDetailDTO();
        
        String isValid = maPgMngFieldValueDetailService.validCompNo(maPgMngCommonDTO, maPgMngFieldDetailDTO, maPgMngFieldValueDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
}