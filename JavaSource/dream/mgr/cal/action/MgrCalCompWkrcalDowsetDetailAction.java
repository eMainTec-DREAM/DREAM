package dream.mgr.cal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetDetailDTO;
import dream.mgr.cal.form.MgrCalCompWkrcalDowsetDetailForm;
import dream.mgr.cal.service.MgrCalCompWkrcalDowsetDetailService;

/**
 * 휴무요일설정 - 상세 action
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDowsetDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/mgrCalCompWkrcalDowsetDetail" name="mgrCalCompWkrcalDowsetDetailForm"
 *                input="/dream/mgr/cal/mgrCalCompWkrcalDowsetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCalCompWkrcalDowsetDetail" path="/dream/mgr/cal/mgrCalCompWkrcalDowsetDetail.jsp"
 *                        redirect="false"
 */
public class MgrCalCompWkrcalDowsetDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WRKCAL_DOWSET_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int WRKCAL_DOWSET_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int WRKCAL_DOWSET_DETAIL_UPDATE 		= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCalCompWkrcalDowsetDetailForm mgrCalCompWkrcalDowsetDetailForm = (MgrCalCompWkrcalDowsetDetailForm) form;

        switch (mgrCalCompWkrcalDowsetDetailForm.getStrutsAction())
        {
            case MgrCalCompWkrcalDowsetDetailAction.WRKCAL_DOWSET_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, mgrCalCompWkrcalDowsetDetailForm);
                returnActionForward = mapping.findForward("mgrCalCompWkrcalDowsetDetail");
                break;
            case MgrCalCompWkrcalDowsetDetailAction.WRKCAL_DOWSET_DETAIL_INPUT:
            	insertDetail(mgrCalCompWkrcalDowsetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrCalCompWkrcalDowsetDetailAction.WRKCAL_DOWSET_DETAIL_UPDATE:
            	updateDetail(mgrCalCompWkrcalDowsetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrCalCompWkrcalDowsetDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * 휴무요일설정 상세 조회
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrCalCompWkrcalDowsetDetailForm
     */
    private void findDetail(HttpServletRequest request, MgrCalCompWkrcalDowsetDetailForm mgrCalCompWkrcalDowsetDetailForm)throws Exception
    {
        // Service 객체 생성
    	MgrCalCompWkrcalDowsetDetailService mgrCalCompWkrcalDowsetDetailService = (MgrCalCompWkrcalDowsetDetailService)getBean("mgrCalCompWkrcalDowsetDetailService");
    	
    	String wrkcalDowsetId = mgrCalCompWkrcalDowsetDetailForm.getMgrCalCompWkrcalDowsetListDTO().getWrkcalDowsetId();
    	String lang = getUser(request).getLang();

        // 조회된 상세 부분
        MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO = mgrCalCompWkrcalDowsetDetailService.findDetail(wrkcalDowsetId, lang);

        // 조회된 상세  셋팅한다.
        mgrCalCompWkrcalDowsetDetailForm.setMgrCalCompWkrcalDowsetDetailDTO(mgrCalCompWkrcalDowsetDetailDTO);
    }
    
    /**
     * detail insert
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDowsetDetailForm
     * @param request
     */
    private void insertDetail(MgrCalCompWkrcalDowsetDetailForm mgrCalCompWkrcalDowsetDetailForm, HttpServletRequest request) throws Exception
    {
        MgrCalCompWkrcalDowsetDetailService mgrCalCompWkrcalDowsetDetailService = (MgrCalCompWkrcalDowsetDetailService) getBean("mgrCalCompWkrcalDowsetDetailService");

        MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalDowsetDetailForm.getMgrCalCompWkrcalCommonDTO();
        MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO = mgrCalCompWkrcalDowsetDetailForm.getMgrCalCompWkrcalDowsetDetailDTO();

        mgrCalCompWkrcalDowsetDetailService.insertDetail(mgrCalCompWkrcalDowsetDetailDTO, mgrCalCompWkrcalCommonDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDowsetDetailForm
     * @param request
     */
    private void updateDetail(MgrCalCompWkrcalDowsetDetailForm mgrCalCompWkrcalDowsetDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrCalCompWkrcalDowsetDetailService mgrCalCompWkrcalDowsetDetailService = (MgrCalCompWkrcalDowsetDetailService) getBean("mgrCalCompWkrcalDowsetDetailService");

        MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO = mgrCalCompWkrcalDowsetDetailForm.getMgrCalCompWkrcalDowsetDetailDTO();

        mgrCalCompWkrcalDowsetDetailService.updateDetail(mgrCalCompWkrcalDowsetDetailDTO);

        setAjaxStatus(request);
    }
}