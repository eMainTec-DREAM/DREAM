package dream.asset.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrHistDetailDTO;
import dream.asset.list.form.MaEqMstrHistDetailForm;
import dream.asset.list.service.MaEqMstrHistDetailService;

/**
 * 설비변경이력- 상세 action
 * 
 * @author kim2107
 * @version $Id: MaEqMstrHistDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maEqMstrHistDetail" name="maEqMstrHistDetailForm"
 *                input="/dream/asset/list/maEqMstrHistDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrHistDetail" path="/dream/asset/list/maEqMstrHistDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrHistDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_HIST_DETAIL_INIT 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrHistDetailForm maEqMstrHistDetailForm = (MaEqMstrHistDetailForm) form;
        
        switch (maEqMstrHistDetailForm.getStrutsAction())
        {
            case MaEqMstrHistDetailAction.EQ_MSTR_HIST_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqMstrHistDetailForm);
                returnActionForward = mapping.findForward("maEqMstrHistDetail");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMstrHistDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 설비변경이력 상세 조회
     * @author kim2107
     * @version $Id: MaEqMstrHistDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrHistDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrHistDetailForm maEqMstrHistDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqMstrHistDetailService maEqMstrHistDetailService = (MaEqMstrHistDetailService)getBean("maEqMstrHistDetailService");

        // 조회된 상세 부분
        MaEqMstrHistDetailDTO maEqMstrHistDetailDTO = maEqMstrHistDetailService.findDetail(maEqMstrHistDetailForm.getMaEqMstrHistListDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        maEqMstrHistDetailForm.setMaEqMstrHistDetailDTO(maEqMstrHistDetailDTO);
    }
}