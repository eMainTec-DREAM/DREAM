package dream.mgr.plant.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.plant.dto.MgrPlantMngDetailDTO;
import dream.mgr.plant.form.MgrPlantMngDetailForm;
import dream.mgr.plant.service.MgrPlantMngDetailService;

/**
 * 공장설정 - 상세 action
 *
 * @author euna0207
 * @version $Id: MgrPlantMngDetailAction.java,v 1.2 2019/08/19 01:21:30 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/mgrPlantMngDetail" name="mgrPlantMngDetailForm"
 *                input="/dream/mgr/plant/mgrPlantMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPlantMngDetail" path="/dream/mgr/plant/mgrPlantMngDetail.jsp"
 *                        redirect="false"
 */
public class MgrPlantMngDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PLANT_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int PLANT_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int PLANT_DETAIL_UPDATE 	= 1003;
    /** 중복 체크 */
    public static final int PLANT_DETAIL_CHECK 		= 1004;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrPlantMngDetailForm mgrPlantMngDetailForm = (MgrPlantMngDetailForm) form;

        switch (mgrPlantMngDetailForm.getStrutsAction())
        {
            case MgrPlantMngDetailAction.PLANT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, mgrPlantMngDetailForm);
                returnActionForward = mapping.findForward("mgrPlantMngDetail");
                break;
            case MgrPlantMngDetailAction.PLANT_DETAIL_INPUT:
            	insertDetail(mgrPlantMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrPlantMngDetailAction.PLANT_DETAIL_UPDATE:
            	updateDetail(mgrPlantMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrPlantMngDetailAction.PLANT_DETAIL_CHECK:
            	valPlantDesc(request, response, mgrPlantMngDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;	
            default:
                returnActionForward = mapping.findForward("mgrPlantMngDetail");
                break;
        }

        return returnActionForward;
    }

	/**
     * 회사설정 상세 조회
     * @author kim2107
     * @version $Id: MgrPlantMngDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param request
     * @param mgrPlantMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MgrPlantMngDetailForm mgrPlantMngDetailForm)throws Exception
    {
        // Service 객체 생성
    	MgrPlantMngDetailService mgrPlantMngDetailService = (MgrPlantMngDetailService)getBean("mgrPlantMngDetailService");

        // 조회된 상세 부분
        MgrPlantMngDetailDTO mgrPlantMngDetailDTO = mgrPlantMngDetailService.findDetail(mgrPlantMngDetailForm.getMgrPlantMngCommonDTO(), getUser(request));

        // 조회된 상세  셋팅한다.
        mgrPlantMngDetailForm.setMgrPlantMngDetailDTO(mgrPlantMngDetailDTO);
    }
    /**
     * detail insert
     * @author  euna0207
     * @version $Id: MgrPlantMngDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngDetailForm
     * @param request
     */
    private void insertDetail(MgrPlantMngDetailForm mgrPlantMngDetailForm, HttpServletRequest request) throws Exception
    {
        MgrPlantMngDetailService mgrPlantMngDetailService = (MgrPlantMngDetailService) getBean("mgrPlantMngDetailService");

        MgrPlantMngDetailDTO mgrPlantMngDetailDTO = mgrPlantMngDetailForm.getMgrPlantMngDetailDTO();

        mgrPlantMngDetailDTO.setEnterBy(getUser(request).getUserId());

        mgrPlantMngDetailService.insertDetail(mgrPlantMngDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  euna0207
     * @version $Id: MgrPlantMngDetailAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngDetailForm
     * @param request
     */
    private void updateDetail(MgrPlantMngDetailForm mgrPlantMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrPlantMngDetailService mgrPlantMngDetailService = (MgrPlantMngDetailService) getBean("mgrPlantMngDetailService");

        MgrPlantMngDetailDTO mgrPlantMngDetailDTO = mgrPlantMngDetailForm.getMgrPlantMngDetailDTO();

        mgrPlantMngDetailDTO.setEnterBy(getUser(request).getUserId());

        mgrPlantMngDetailService.updateDetail(mgrPlantMngDetailDTO, getUser(request));


        setAjaxStatus(request);
    }
    
    private void valPlantDesc(HttpServletRequest request, HttpServletResponse response, MgrPlantMngDetailForm mgrPlantMngDetailForm) throws Exception {

    	MgrPlantMngDetailService mgrPlantMngDetailService = (MgrPlantMngDetailService) getBean("mgrPlantMngDetailService");
    	MgrPlantMngDetailDTO mgrPlantMngDetailDTO = mgrPlantMngDetailForm.getMgrPlantMngDetailDTO();
                
        int plantCnt = mgrPlantMngDetailService.valPlantDesc(mgrPlantMngDetailDTO, getUser(request));
        
        setAjaxDesc(request, plantCnt);
    	
    }
}