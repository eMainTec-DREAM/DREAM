package dream.asset.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;
import dream.asset.list.form.MaEqMstrPmInsDetailForm;
import dream.asset.list.service.MaEqMstrPmInsDetailService;

/**
 * 설비 정기점검
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPmInsDetail" name="maEqMstrPmInsDetailForm"
 *                input="/dream/asset/list/maEqMstrPmInsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPmInsDetail" path="/dream/asset/list/maEqMstrPmInsDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPmInsDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_PMINS_DETAIL_INIT 			= 1001;
    /** 수정 */
    public static final int EQ_MSTR_PMINS_DETAIL_UPDATE 		= 1002;
    /** 입력 */
    public static final int EQ_MSTR_PMINS_DETAIL_INPUT 			= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPmInsDetailForm maEqMstrPmInsDetailForm = (MaEqMstrPmInsDetailForm) form;
        switch (maEqMstrPmInsDetailForm.getStrutsAction())
        {
            case MaEqMstrPmInsDetailAction.EQ_MSTR_PMINS_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqMstrPmInsDetailForm);
                returnActionForward = mapping.findForward("maEqMstrPmInsDetail");
                break;
            case MaEqMstrPmInsDetailAction.EQ_MSTR_PMINS_DETAIL_UPDATE:
            	updateDetail(maEqMstrPmInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPmInsDetailAction.EQ_MSTR_PMINS_DETAIL_INPUT:
            	insertDetail(maEqMstrPmInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPmInsDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 점검 조회 
     * @author kim2107
     * @version $Id: MaEqMstrPmInsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmInsDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrPmInsDetailForm maEqMstrPmInsDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqMstrPmInsDetailService maEqMstrPmInsDetailService = (MaEqMstrPmInsDetailService)getBean("maEqMstrPmInsDetailService");

    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmInsDetailForm.getMaEqMstrCommonDTO();
    	MaEqMstrPmInsListDTO maEqMstrPmInsListDTO = maEqMstrPmInsDetailForm.getMaEqMstrPmInsListDTO();
        
        // 조회된 상세 부분
        MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = maEqMstrPmInsDetailService.findDetail(maEqMstrCommonDTO, maEqMstrPmInsListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maEqMstrPmInsDetailForm.setMaEqMstrPmInsDetailDTO(maEqMstrPmInsDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrPmInsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPmInsDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrPmInsDetailForm maEqMstrPmInsDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPmInsDetailService maEqMstrPmInsDetailService = (MaEqMstrPmInsDetailService) getBean("maEqMstrPmInsDetailService");
        
        MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = maEqMstrPmInsDetailForm.getMaEqMstrPmInsDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmInsDetailForm.getMaEqMstrCommonDTO();
        
        maEqMstrPmInsDetailService.updateDetail(maEqMstrCommonDTO,maEqMstrPmInsDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrPmInsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPmInsDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrPmInsDetailForm maEqMstrPmInsDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPmInsDetailService maEqMstrPmInsDetailService = (MaEqMstrPmInsDetailService) getBean("maEqMstrPmInsDetailService");
        
        MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = maEqMstrPmInsDetailForm.getMaEqMstrPmInsDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPmInsDetailForm.getMaEqMstrCommonDTO();
        
        maEqMstrPmInsDetailService.insertDetail(maEqMstrCommonDTO, maEqMstrPmInsDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
}