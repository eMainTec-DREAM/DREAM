package dream.consult.program.page.action;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.form.MaPgMngFieldDetailForm;
import dream.consult.program.page.service.MaPgMngFieldDetailService;
import dream.main.service.MainService;

/**
 * 화면별 필드 상세
 * @author  kim21017
 * @version $Id: MaPgMngFieldDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPgMngFieldDetail" name="maPgMngFieldDetailForm"
 *                input="/dream/consult/program/page/maPgMngFieldDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPgFieldMngDetail" name="maPgMngFieldDetailForm"
 *                input="/dream/consult/program/page/maPgFieldMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngFieldDetail" path="/dream/consult/program/page/maPgMngFieldDetail.jsp"
 *                        redirect="false"
 */
public class MaPgMngFieldDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PG_FIELD_DETAIL_INIT 	= 1001;
    /** 수정 */
    public static final int PG_FIELD_DETAIL_UPDATE 	= 1002;
    /** 입력 */
    public static final int PG_FIELD_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngFieldDetailForm maPgMngFieldDetailForm = (MaPgMngFieldDetailForm) form;
        switch (maPgMngFieldDetailForm.getStrutsAction())
        {
            case MaPgMngFieldDetailAction.PG_FIELD_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPgMngFieldDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPgMngFieldDetailAction.PG_FIELD_DETAIL_UPDATE:
            	updateDetail(maPgMngFieldDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngFieldDetailAction.PG_FIELD_DETAIL_INPUT:
            	insertDetail(maPgMngFieldDetailForm, request);
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
     * @version $Id: MaPgMngFieldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngFieldDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPgMngFieldDetailForm maPgMngFieldDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPgMngFieldDetailService maPgMngFieldDetailService = (MaPgMngFieldDetailService)getBean("maPgMngFieldDetailService");

    	MaPgMngFieldDetailDTO maPgMngFieldDetailDTO = maPgMngFieldDetailForm.getMaPgMngFieldDetailDTO();

        maPgMngFieldDetailDTO.setPageId(maPgMngFieldDetailForm.getMaPgMngCommonDTO().getPageId());
        maPgMngFieldDetailDTO.setUserLang(getUser(request).getLangId());
        maPgMngFieldDetailDTO.setPgFieldId(maPgMngFieldDetailForm.getMaPgMngFieldListDTO().getPgFieldId());

        // 조회된 상세 부분
        MaPgMngFieldDetailDTO resultDTO = maPgMngFieldDetailService.findDetail(maPgMngFieldDetailDTO);
        
        // 조회된 상세  셋팅한다.
        maPgMngFieldDetailForm.setMaPgMngFieldDetailDTO(resultDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPgMngFieldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldDetailForm
     * @param request
     */
    private void updateDetail(MaPgMngFieldDetailForm maPgMngFieldDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngFieldDetailService maPgMngFieldDetailService = (MaPgMngFieldDetailService) getBean("maPgMngFieldDetailService");
        
        MaPgMngFieldDetailDTO maPgMngFieldDetailDTO = maPgMngFieldDetailForm.getMaPgMngFieldDetailDTO();
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngFieldDetailForm.getMaPgMngCommonDTO();
        maPgMngFieldDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maPgMngFieldDetailService.updateDetail(maPgMngFieldDetailDTO,maPgMngCommonDTO);
        
        MainService mainService = (MainService) getBean("mainService"); 
        HttpSession session = request.getSession();
        Hashtable pageFields = mainService.findPageFields(getUser(request));
        session.setAttribute("PAGEFIELD", pageFields);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPgMngFieldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldDetailForm
     * @param request
     */
    private void insertDetail(MaPgMngFieldDetailForm maPgMngFieldDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPgMngFieldDetailService maPgMngFieldDetailService = (MaPgMngFieldDetailService) getBean("maPgMngFieldDetailService");
        
        MaPgMngFieldDetailDTO maPgMngFieldDetailDTO = maPgMngFieldDetailForm.getMaPgMngFieldDetailDTO();
        
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngFieldDetailForm.getMaPgMngCommonDTO();
        maPgMngFieldDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maPgMngFieldDetailService.insertDetail(maPgMngFieldDetailDTO, maPgMngCommonDTO);
        
        MainService mainService = (MainService) getBean("mainService"); 
        HttpSession session = request.getSession();
        Hashtable pageFields = mainService.findPageFields(getUser(request));
        session.setAttribute("PAGEFIELD", pageFields);
        
        setAjaxStatus(request);
    }
}