package dream.consult.comp.plant.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.plant.dto.MaPlantMngDetailDTO;
import dream.consult.comp.plant.form.MaPlantMngDetailForm;
import dream.consult.comp.plant.service.MaPlantMngDetailService;
/**
 * 회사설정 - 상세 action
 *
 * @author kim2107
 * @version $Id: MaPlantMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maPlantMngDetail" name="maPlantMngDetailForm"
 *                input="/dream/consult/comp/plant/maPlantMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPlantMngDetail" path="/dream/consult/comp/plant/maPlantMngDetail.jsp"
 *                        redirect="false"
 */
public class MaPlantMngDetailAction extends ConsultAuthAction
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
        MaPlantMngDetailForm maPlantMngDetailForm = (MaPlantMngDetailForm) form;

        switch (maPlantMngDetailForm.getStrutsAction())
        {
            case MaPlantMngDetailAction.PLANT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPlantMngDetailForm);
                returnActionForward = mapping.findForward("maPlantMngDetail");
                break;
            case MaPlantMngDetailAction.PLANT_DETAIL_INPUT:
            	insertDetail(maPlantMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPlantMngDetailAction.PLANT_DETAIL_UPDATE:
            	updateDetail(maPlantMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPlantMngDetailAction.PLANT_DETAIL_CHECK:
            	valPlantNo(request, response, maPlantMngDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;	
            default:
                returnActionForward = mapping.findForward("maPlantMngDetail");
                break;
        }

        return returnActionForward;
    }

	/**
     * 회사설정 상세 조회
     * @author kim2107
     * @version $Id: MaPlantMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param maPlantMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPlantMngDetailForm maPlantMngDetailForm)throws Exception
    {
        // Service 객체 생성
    	MaPlantMngDetailService maPlantMngDetailService = (MaPlantMngDetailService)getBean("maPlantMngDetailService");

        // 조회된 상세 부분
        MaPlantMngDetailDTO maPlantMngDetailDTO = maPlantMngDetailService.findDetail(maPlantMngDetailForm.getMaPlantMngCommonDTO(), getUser(request));

        // 조회된 상세  셋팅한다.
        maPlantMngDetailForm.setMaPlantMngDetailDTO(maPlantMngDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPlantMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngDetailForm
     * @param request
     */
    private void insertDetail(MaPlantMngDetailForm maPlantMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaPlantMngDetailService maPlantMngDetailService = (MaPlantMngDetailService) getBean("maPlantMngDetailService");

        MaPlantMngDetailDTO maPlantMngDetailDTO = maPlantMngDetailForm.getMaPlantMngDetailDTO();

        maPlantMngDetailDTO.setEnterBy(getUser(request).getUserId());

        maPlantMngDetailService.insertDetail(maPlantMngDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPlantMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngDetailForm
     * @param request
     */
    private void updateDetail(MaPlantMngDetailForm maPlantMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPlantMngDetailService maPlantMngDetailService = (MaPlantMngDetailService) getBean("maPlantMngDetailService");

        MaPlantMngDetailDTO maPlantMngDetailDTO = maPlantMngDetailForm.getMaPlantMngDetailDTO();

        maPlantMngDetailDTO.setEnterBy(getUser(request).getUserId());

        maPlantMngDetailService.updateDetail(maPlantMngDetailDTO, getUser(request));


        setAjaxStatus(request);
    }
    
    private void valPlantNo(HttpServletRequest request, HttpServletResponse response, MaPlantMngDetailForm maPlantMngDetailForm) throws Exception {

    	MaPlantMngDetailService maPlantMngDetailService = (MaPlantMngDetailService) getBean("maPlantMngDetailService");
    	MaPlantMngDetailDTO maPlantMngDetailDTO = maPlantMngDetailForm.getMaPlantMngDetailDTO();
                
        int plantCnt = maPlantMngDetailService.valPlantNo(maPlantMngDetailDTO, getUser(request));
        
        setAjaxDesc(request, plantCnt);
    	
    }
}