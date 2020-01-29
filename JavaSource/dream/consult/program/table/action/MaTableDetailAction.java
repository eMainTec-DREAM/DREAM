package dream.consult.program.table.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.program.table.dto.MaTableDetailDTO;
import dream.consult.program.table.form.MaTableDetailForm;
import dream.consult.program.table.service.MaTableDetailService;

/**
 * 데이터 테이블 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaTableDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maTableDetail" name="maTableDetailForm"
 *                input="/dream/consult/program/table/maTableDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maTableDetail" path="/dream/consult/program/table/maTableDetail.jsp"
 *                        redirect="false"
 */
public class MaTableDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LISTTYPE_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int LISTTYPE_DETAIL_UPDATE 		= 1002;
    /** 입력 */
    public static final int LISTTYPE_DETAIL_INPUT 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaTableDetailForm maTableDetailForm = (MaTableDetailForm) form;
        
        switch (maTableDetailForm.getStrutsAction())
        {
            case MaTableDetailAction.LISTTYPE_DETAIL_INIT:
                this.findDetail(request, maTableDetailForm);
                returnActionForward = mapping.findForward("maTableDetail");
                break;
            case MaTableDetailAction.LISTTYPE_DETAIL_UPDATE:
            	updateDetail(maTableDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaTableDetailAction.LISTTYPE_DETAIL_INPUT:
            	insertDetail(maTableDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maTableDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 데이터 테이블 상세 조회
     * @author kim2107
     * @version $Id: MaTableDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maTableDetailForm
     */
    private void findDetail(HttpServletRequest request, MaTableDetailForm maTableDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaTableDetailService maTableDetailService = (MaTableDetailService)getBean("maTableDetailService");

        // 넘겨진 tableMId 구함
        String tableMId = maTableDetailForm.getMaTableCommonDTO().getTableMId();
        
        // 조회된 상세 부분
        MaTableDetailDTO maTableDetailDTO = maTableDetailService.findDetail(tableMId, getUser(request).getLangId());
        
        // 조회된 상세  셋팅한다.
        maTableDetailForm.setMaTableDetailDTO(maTableDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaTableDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableDetailForm
     * @param request
     */
    private void insertDetail(MaTableDetailForm maTableDetailForm, HttpServletRequest request) throws Exception
    {
    	MaTableDetailService maTableDetailService = (MaTableDetailService) getBean("maTableDetailService");
        
        MaTableDetailDTO maTableDetailDTO = maTableDetailForm.getMaTableDetailDTO();
        
        maTableDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maTableDetailService.insertDetail(maTableDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaTableDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableDetailForm
     * @param request
     */
    private void updateDetail(MaTableDetailForm maTableDetailForm, HttpServletRequest request) throws Exception
    {
    	MaTableDetailService maTableDetailService = (MaTableDetailService) getBean("maTableDetailService");
        
        MaTableDetailDTO maTableDetailDTO = maTableDetailForm.getMaTableDetailDTO();
        
        maTableDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maTableDetailService.updateDetail(maTableDetailDTO);
        
        setAjaxStatus(request);
    }
}