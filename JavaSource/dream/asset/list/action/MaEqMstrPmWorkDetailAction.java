package dream.asset.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;
import dream.asset.list.form.MaEqMstrPmWorkDetailForm;
import dream.asset.list.service.MaEqMstrPmWorkDetailService;

/**
 * 설비 정기작업
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPmWorkDetail" name="maEqMstrPmWorkDetailForm"
 *                input="/dream/asset/list/maEqMstrPmWorkDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPmWorkDetail" path="/dream/asset/list/maEqMstrPmWorkDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPmWorkDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_PMWORK_DETAIL_INIT 			= 1001;
    /** 수정 */
    public static final int EQ_MSTR_PMWORK_DETAIL_UPDATE 		= 1002;
    /** 입력 */
    public static final int EQ_MSTR_PMWORK_DETAIL_INPUT 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPmWorkDetailForm maEqMstrPmWorkDetailForm = (MaEqMstrPmWorkDetailForm) form;
        switch (maEqMstrPmWorkDetailForm.getStrutsAction())
        {
            case MaEqMstrPmWorkDetailAction.EQ_MSTR_PMWORK_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqMstrPmWorkDetailForm);
                returnActionForward = mapping.findForward("maEqMstrPmWorkDetail");
                break;
            case MaEqMstrPmWorkDetailAction.EQ_MSTR_PMWORK_DETAIL_UPDATE:
            	updateDetail(maEqMstrPmWorkDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPmWorkDetailAction.EQ_MSTR_PMWORK_DETAIL_INPUT:
            	insertDetail(maEqMstrPmWorkDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPmWorkDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 점검 조회 
     * @author kim2107
     * @version $Id: MaEqMstrPmWorkDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmWorkDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrPmWorkDetailForm maEqMstrPmWorkDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqMstrPmWorkDetailService maEqMstrPmWorkDetailService = (MaEqMstrPmWorkDetailService)getBean("maEqMstrPmWorkDetailService");

    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmWorkDetailForm.getMaEqMstrCommonDTO();
    	MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO = maEqMstrPmWorkDetailForm.getMaEqMstrPmWorkListDTO();
        
        // 조회된 상세 부분
        MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = maEqMstrPmWorkDetailService.findDetail(maEqMstrCommonDTO, maEqMstrPmWorkListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maEqMstrPmWorkDetailForm.setMaEqMstrPmWorkDetailDTO(maEqMstrPmWorkDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrPmWorkDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPmWorkDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrPmWorkDetailForm maEqMstrPmWorkDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPmWorkDetailService maEqMstrPmWorkDetailService = (MaEqMstrPmWorkDetailService) getBean("maEqMstrPmWorkDetailService");
        
        MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = maEqMstrPmWorkDetailForm.getMaEqMstrPmWorkDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmWorkDetailForm.getMaEqMstrCommonDTO();
        
        maEqMstrPmWorkDetailService.updateDetail(maEqMstrCommonDTO,maEqMstrPmWorkDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrPmWorkDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPmWorkDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrPmWorkDetailForm maEqMstrPmWorkDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPmWorkDetailService maEqMstrPmWorkDetailService = (MaEqMstrPmWorkDetailService) getBean("maEqMstrPmWorkDetailService");
        
        MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = maEqMstrPmWorkDetailForm.getMaEqMstrPmWorkDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmWorkDetailForm.getMaEqMstrCommonDTO();
        
        maEqMstrPmWorkDetailService.insertDetail(maEqMstrCommonDTO, maEqMstrPmWorkDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
}