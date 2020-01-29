package dream.pers.priv.pgm.action;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;

import dream.main.service.MainService;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldDetailDTO;
import dream.pers.priv.pgm.form.PersPrivUsrFieldDetailForm;
import dream.pers.priv.pgm.service.PersPrivUsrFieldDetailService;

/**
 * 화면별 필드 상세
 * @author  kim21017
 * @version $Id: MaPgUsrFieldDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/persPrivUsrFieldDetail" name="persPrivUsrFieldDetailForm"
 *                input="/dream/pers/priv/pgm/persPrivUsrFieldDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivUsrFieldDetail" path="/dream/pers/priv/pgm/persPrivUsrFieldDetail.jsp"
 *                        redirect="false"
 */
public class PersPrivUsrFieldDetailAction extends AuthAction
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
        PersPrivUsrFieldDetailForm maPgUsrFieldDetailForm = (PersPrivUsrFieldDetailForm) form;
        switch (maPgUsrFieldDetailForm.getStrutsAction())
        {
            case PersPrivUsrFieldDetailAction.PG_FIELD_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPgUsrFieldDetailForm);
                returnActionForward = mapping.findForward("persPrivUsrFieldDetail");
                break;
            case PersPrivUsrFieldDetailAction.PG_FIELD_DETAIL_UPDATE:
            	updateDetail(maPgUsrFieldDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PersPrivUsrFieldDetailAction.PG_FIELD_DETAIL_INPUT:
            	insertDetail(maPgUsrFieldDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("persPrivUsrFieldDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 화면별 필드 상세 조회
     * @author kim2107
     * @version $Id: MaPgUsrFieldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgUsrFieldDetailForm
     */
    private void findDetail(HttpServletRequest request, PersPrivUsrFieldDetailForm maPgUsrFieldDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	PersPrivUsrFieldDetailService maPgUsrFieldDetailService = (PersPrivUsrFieldDetailService)getBean("persPrivUsrFieldDetailService");

    	PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO = maPgUsrFieldDetailForm.getPersPrivUsrFieldDetailDTO();

        persPrivUsrFieldDetailDTO.setPgFieldId(maPgUsrFieldDetailForm.getPersPrivUsrFieldCommonDTO().getPgFieldId());
        persPrivUsrFieldDetailDTO.setUserLang(getUser(request).getLangId());

        // 조회된 상세 부분
        PersPrivUsrFieldDetailDTO resultDTO = maPgUsrFieldDetailService.findDetail(persPrivUsrFieldDetailDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maPgUsrFieldDetailForm.setPersPrivUsrFieldDetailDTO(resultDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPgUsrFieldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgUsrFieldDetailForm
     * @param request
     */
    private void updateDetail(PersPrivUsrFieldDetailForm maPgUsrFieldDetailForm, HttpServletRequest request) throws Exception
    {
    	PersPrivUsrFieldDetailService maPgUsrFieldDetailService = (PersPrivUsrFieldDetailService) getBean("persPrivUsrFieldDetailService");
        
        PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO = maPgUsrFieldDetailForm.getPersPrivUsrFieldDetailDTO();
        PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO = maPgUsrFieldDetailForm.getPersPrivUsrFieldCommonDTO();
        persPrivUsrFieldDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maPgUsrFieldDetailService.updateDetail(persPrivUsrFieldDetailDTO,persPrivUsrFieldCommonDTO);
        
        MainService mainService = (MainService) getBean("mainService"); 
        HttpSession session = request.getSession();
        Hashtable pageFields = mainService.findPageFields(getUser(request));
        session.setAttribute("PAGEFIELD", pageFields);
        
        setAjaxDesc(request, persPrivUsrFieldDetailDTO.getUsrPgFieldId());
        
//        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPgUsrFieldDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgUsrFieldDetailForm
     * @param request
     */
    private void insertDetail(PersPrivUsrFieldDetailForm maPgUsrFieldDetailForm, HttpServletRequest request) throws Exception
    {
    	PersPrivUsrFieldDetailService maPgUsrFieldDetailService = (PersPrivUsrFieldDetailService) getBean("persPrivUsrFieldDetailService");
        
        PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO = maPgUsrFieldDetailForm.getPersPrivUsrFieldDetailDTO();

        persPrivUsrFieldDetailDTO.setEnterBy(getUser(request).getUserId());
        
        String usrPgfieldId = maPgUsrFieldDetailService.insertDetail(persPrivUsrFieldDetailDTO, getUser(request));
        
        MainService mainService = (MainService) getBean("mainService"); 
        HttpSession session = request.getSession();
        Hashtable pageFields = mainService.findPageFields(getUser(request));
        session.setAttribute("PAGEFIELD", pageFields);
        
        setAjaxDesc(request, usrPgfieldId);
        
//        setAjaxStatus(request);
    }
}