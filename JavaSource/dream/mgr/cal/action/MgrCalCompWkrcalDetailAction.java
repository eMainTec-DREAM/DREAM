package dream.mgr.cal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.cal.dto.MgrCalCompWkrcalDetailDTO;
import dream.mgr.cal.form.MgrCalCompWkrcalDetailForm;
import dream.mgr.cal.service.MgrCalCompWkrcalDetailService;

/**
 * 근무일달력설정 - 상세 action
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/mgrCalCompWkrcalDetail" name="mgrCalCompWkrcalDetailForm"
 *                input="/dream/mgr/cal/mgrCalCompWkrcalDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCalCompWkrcalDetail" path="/dream/mgr/cal/mgrCalCompWkrcalDetail.jsp"
 *                        redirect="false"
 */
public class MgrCalCompWkrcalDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WRKCAL_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int WRKCAL_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int WRKCAL_DETAIL_UPDATE 		= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCalCompWkrcalDetailForm mgrCalCompWkrcalDetailForm = (MgrCalCompWkrcalDetailForm) form;

        switch (mgrCalCompWkrcalDetailForm.getStrutsAction())
        {
            case MgrCalCompWkrcalDetailAction.WRKCAL_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, mgrCalCompWkrcalDetailForm);
                returnActionForward = mapping.findForward("mgrCalCompWkrcalDetail");
                break;
            case MgrCalCompWkrcalDetailAction.WRKCAL_DETAIL_INPUT:
            	insertDetail(mgrCalCompWkrcalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrCalCompWkrcalDetailAction.WRKCAL_DETAIL_UPDATE:
            	updateDetail(mgrCalCompWkrcalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrCalCompWkrcalDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * 회사설정 상세 조회
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrCalCompWkrcalDetailForm
     */
    private void findDetail(HttpServletRequest request, MgrCalCompWkrcalDetailForm mgrCalCompWkrcalDetailForm)throws Exception
    {
        // Service 객체 생성
    	MgrCalCompWkrcalDetailService mgrCalCompWkrcalDetailService = (MgrCalCompWkrcalDetailService)getBean("mgrCalCompWkrcalDetailService");

        // 조회된 상세 부분
        MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO = mgrCalCompWkrcalDetailService.findDetail(mgrCalCompWkrcalDetailForm.getMgrCalCompWkrcalCommonDTO(), getUser(request));

        // 조회된 상세  셋팅한다.
        mgrCalCompWkrcalDetailForm.setMgrCalCompWkrcalDetailDTO(mgrCalCompWkrcalDetailDTO);
    }
    /**
     * detail insert
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDetailForm
     * @param request
     */
    private void insertDetail(MgrCalCompWkrcalDetailForm mgrCalCompWkrcalDetailForm, HttpServletRequest request) throws Exception
    {
        MgrCalCompWkrcalDetailService mgrCalCompWkrcalDetailService = (MgrCalCompWkrcalDetailService) getBean("mgrCalCompWkrcalDetailService");

        MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO = mgrCalCompWkrcalDetailForm.getMgrCalCompWkrcalDetailDTO();

        mgrCalCompWkrcalDetailService.insertDetail(mgrCalCompWkrcalDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MgrCalCompWkrcalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDetailForm
     * @param request
     */
    private void updateDetail(MgrCalCompWkrcalDetailForm mgrCalCompWkrcalDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrCalCompWkrcalDetailService mgrCalCompWkrcalDetailService = (MgrCalCompWkrcalDetailService) getBean("mgrCalCompWkrcalDetailService");

        MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO = mgrCalCompWkrcalDetailForm.getMgrCalCompWkrcalDetailDTO();

        mgrCalCompWkrcalDetailService.updateDetail(mgrCalCompWkrcalDetailDTO, getUser(request));


        setAjaxStatus(request);
    }
}