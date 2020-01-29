package dream.consult.program.table.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.program.table.dto.MaTableColDetailDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;
import dream.consult.program.table.form.MaTableColDetailForm;
import dream.consult.program.table.service.MaTableColDetailService;

/**
 * Ref테이블 팝업 - 분류 팝업
 * @author  kim21017
 * @version $Id: MaTableColDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maTableColDetail" name="maTableColDetailForm"
 *                input="/dream/consult/program/table/maTableColDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maTableColDetail" path="/dream/consult/program/table/maTableColDetail.jsp"
 *                        redirect="false"
 */
public class MaTableColDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LISTTYPE_CODE_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int LISTTYPE_CODE_DETAIL_UPDATE 	= 1002;
    /** 입력 */
    public static final int LISTTYPE_CODE_DETAIL_INPUT 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaTableColDetailForm maTableColDetailForm = (MaTableColDetailForm) form;
        switch (maTableColDetailForm.getStrutsAction())
        {
            case MaTableColDetailAction.LISTTYPE_CODE_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maTableColDetailForm);
                returnActionForward = mapping.findForward("maTableColDetail");
                break;
            case MaTableColDetailAction.LISTTYPE_CODE_DETAIL_UPDATE:
            	updateDetail(maTableColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaTableColDetailAction.LISTTYPE_CODE_DETAIL_INPUT:
            	insertDetail(maTableColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maTableColDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 데이터 테이블 분류 조회
     * @author kim2107
     * @version $Id: MaTableColDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maTableColDetailForm
     */
    private void findDetail(HttpServletRequest request, MaTableColDetailForm maTableColDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaTableColDetailService maTableColDetailService = (MaTableColDetailService)getBean("maTableColDetailService");

    	// 넘겨진 tableMId 구함
        String tableMId = maTableColDetailForm.getMaTableCommonDTO().getTableMId();
        // 넘겨진 tabColId 구함
        String tabColId = maTableColDetailForm.getMaTableColListDTO().getTableDId();
        
        // 조회된 상세 부분
        MaTableColDetailDTO maTableColDetailDTO = maTableColDetailService.findDetail(tableMId, tabColId, getUser(request).getLangId());
        
        // 조회된 상세  셋팅한다.
        maTableColDetailForm.setMaTableColDetailDTO(maTableColDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaTableColDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableColDetailForm
     * @param request
     */
    private void updateDetail(MaTableColDetailForm maTableColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaTableColDetailService maTableColDetailService = (MaTableColDetailService) getBean("maTableColDetailService");
        
        MaTableColDetailDTO maTableColDetailDTO = maTableColDetailForm.getMaTableColDetailDTO();
        MaTableCommonDTO maTableCommonDTO = maTableColDetailForm.getMaTableCommonDTO();
        maTableColDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maTableColDetailService.updateDetail(maTableColDetailDTO,maTableCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaTableColDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableColDetailForm
     * @param request
     */
    private void insertDetail(MaTableColDetailForm maTableColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaTableColDetailService maTableColDetailService = (MaTableColDetailService) getBean("maTableColDetailService");
        
        MaTableColDetailDTO maTableColDetailDTO = maTableColDetailForm.getMaTableColDetailDTO();
        
        MaTableCommonDTO maTableCommonDTO = maTableColDetailForm.getMaTableCommonDTO();
        maTableColDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maTableColDetailService.insertDetail(maTableColDetailDTO, maTableCommonDTO);
        
        setAjaxStatus(request);
    }
}