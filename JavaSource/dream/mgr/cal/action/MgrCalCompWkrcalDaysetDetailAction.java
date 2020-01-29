package dream.mgr.cal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetDetailDTO;
import dream.mgr.cal.form.MgrCalCompWkrcalDaysetDetailForm;
import dream.mgr.cal.service.MgrCalCompWkrcalDaysetDetailService;

/**
 * 휴무일 설정 - 상세 action
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDaysetDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/mgrCalCompWkrcalDaysetDetail" name="mgrCalCompWkrcalDaysetDetailForm"
 *                input="/dream/mgr/cal/mgrCalCompWkrcalDaysetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCalCompWkrcalDaysetDetail" path="/dream/mgr/cal/mgrCalCompWkrcalDaysetDetail.jsp"
 *                        redirect="false"
 */
public class MgrCalCompWkrcalDaysetDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WRKCAL_DAYSET_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int WRKCAL_DAYSET_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int WRKCAL_DAYSET_DETAIL_UPDATE 		= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCalCompWkrcalDaysetDetailForm mgrCalCompWkrcalDaysetDetailForm = (MgrCalCompWkrcalDaysetDetailForm) form;

        switch (mgrCalCompWkrcalDaysetDetailForm.getStrutsAction())
        {
            case MgrCalCompWkrcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, mgrCalCompWkrcalDaysetDetailForm);
                returnActionForward = mapping.findForward("mgrCalCompWkrcalDaysetDetail");
                break;
            case MgrCalCompWkrcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_INPUT:
            	insertDetail(mgrCalCompWkrcalDaysetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrCalCompWkrcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_UPDATE:
            	updateDetail(mgrCalCompWkrcalDaysetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrCalCompWkrcalDaysetDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * 휴무일설정 상세 조회
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrCalCompWkrcalDaysetDetailForm
     */
    private void findDetail(HttpServletRequest request, MgrCalCompWkrcalDaysetDetailForm mgrCalCompWkrcalDaysetDetailForm)throws Exception
    {
        // Service 객체 생성
    	MgrCalCompWkrcalDaysetDetailService mgrCalCompWkrcalDaysetDetailService = (MgrCalCompWkrcalDaysetDetailService)getBean("mgrCalCompWkrcalDaysetDetailService");
    	
    	String wrkcalDaysetId = mgrCalCompWkrcalDaysetDetailForm.getMgrCalCompWkrcalDaysetListDTO().getWrkcalDaysetId();

        // 조회된 상세 부분
        MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO = mgrCalCompWkrcalDaysetDetailService.findDetail(wrkcalDaysetId);

        // 조회된 상세  셋팅한다.
        mgrCalCompWkrcalDaysetDetailForm.setMgrCalCompWkrcalDaysetDetailDTO(mgrCalCompWkrcalDaysetDetailDTO);
    }
    
    /**
     * detail insert
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDaysetDetailForm
     * @param request
     */
    private void insertDetail(MgrCalCompWkrcalDaysetDetailForm mgrCalCompWkrcalDaysetDetailForm, HttpServletRequest request) throws Exception
    {
        MgrCalCompWkrcalDaysetDetailService mgrCalCompWkrcalDaysetDetailService = (MgrCalCompWkrcalDaysetDetailService) getBean("mgrCalCompWkrcalDaysetDetailService");

        MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO = mgrCalCompWkrcalDaysetDetailForm.getMgrCalCompWkrcalCommonDTO();
        MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO = mgrCalCompWkrcalDaysetDetailForm.getMgrCalCompWkrcalDaysetDetailDTO();

        mgrCalCompWkrcalDaysetDetailService.insertDetail(mgrCalCompWkrcalDaysetDetailDTO, mgrCalCompWkrcalCommonDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDaysetDetailForm
     * @param request
     */
    private void updateDetail(MgrCalCompWkrcalDaysetDetailForm mgrCalCompWkrcalDaysetDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrCalCompWkrcalDaysetDetailService mgrCalCompWkrcalDaysetDetailService = (MgrCalCompWkrcalDaysetDetailService) getBean("mgrCalCompWkrcalDaysetDetailService");

        MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO = mgrCalCompWkrcalDaysetDetailForm.getMgrCalCompWkrcalDaysetDetailDTO();

        mgrCalCompWkrcalDaysetDetailService.updateDetail(mgrCalCompWkrcalDaysetDetailDTO);

        setAjaxStatus(request);
    }
}