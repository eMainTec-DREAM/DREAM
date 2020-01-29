package dream.pers.appln.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineDetailDTO;
import dream.pers.appln.form.MaAppLineDetailForm;
import dream.pers.appln.service.MaAppLineDetailService;

/**
 * 상세 action
 * 
 * @author kim2107
 * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maAppLineDetail" name="maAppLineDetailForm"
 *                input="/dream/pers/appln/maAppLineDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maAppLinePopupDetail" name="maAppLineDetailForm"
 *                input="/dream/pers/appln/maAppLinePopupDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maAppLineDetail" path="/dream/pers/appln/maAppLineDetail.jsp"
 *                        redirect="false"
 */
public class MaAppLineDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int QNA_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int QNA_DETAIL_INPUT 		= 5003;
    /** 신청완료 */
    public static final int QNA_DETAIL_CONFIRM 		= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaAppLineDetailForm maAppLineDetailForm = (MaAppLineDetailForm) form;
        
        super.updateAudit(maAppLineDetailForm.getMaAppLineDetailDTO().getAuditKey()==""?maAppLineDetailForm.getMaAppLineCommonDTO().getAuditKey():maAppLineDetailForm.getMaAppLineDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maAppLineDetailForm.getStrutsAction())
        {
            case MaAppLineDetailAction.QNA_DETAIL_INIT:
                this.findDetail(request, maAppLineDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaAppLineDetailAction.QNA_DETAIL_UPDATE:
            	updateDetail(maAppLineDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaAppLineDetailAction.QNA_DETAIL_INPUT:
            	insertDetail(maAppLineDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaAppLineDetailAction.QNA_DETAIL_CONFIRM:
            	confirmDetail(maAppLineDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 질의 상세 조회
     * @author kim2107
     * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maAppLineDetailForm
     */
    private void findDetail(HttpServletRequest request, MaAppLineDetailForm maAppLineDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaAppLineDetailService maAppLineDetailService = (MaAppLineDetailService)getBean("maAppLineDetailService");
    	MaAppLineCommonDTO maAppLineCommonDTO = maAppLineDetailForm.getMaAppLineCommonDTO();
        // 조회된 상세 부분
        MaAppLineDetailDTO maAppLineDetailDTO = maAppLineDetailService.findDetail(maAppLineCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maAppLineDetailForm.setMaAppLineDetailDTO(maAppLineDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailForm
     * @param request
     */
    private void insertDetail(MaAppLineDetailForm maAppLineDetailForm, HttpServletRequest request) throws Exception
    {
    	MaAppLineDetailService maAppLineDetailService = (MaAppLineDetailService) getBean("maAppLineDetailService");
        
        MaAppLineDetailDTO maAppLineDetailDTO = maAppLineDetailForm.getMaAppLineDetailDTO();
        
        maAppLineDetailService.insertDetail(maAppLineDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailForm
     * @param request
     */
    private void updateDetail(MaAppLineDetailForm maAppLineDetailForm, HttpServletRequest request) throws Exception
    {
    	MaAppLineDetailService maAppLineDetailService = (MaAppLineDetailService) getBean("maAppLineDetailService");
        
        MaAppLineDetailDTO maAppLineDetailDTO = maAppLineDetailForm.getMaAppLineDetailDTO();
        
        maAppLineDetailService.updateDetail(maAppLineDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail confirm
     * @author  kim21017
     * @version $Id: MaAppLineDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailForm
     * @param request
     */
    private void confirmDetail(MaAppLineDetailForm maAppLineDetailForm, HttpServletRequest request) throws Exception
    {
    	MaAppLineDetailService maAppLineDetailService = (MaAppLineDetailService) getBean("maAppLineDetailService");
        
        MaAppLineDetailDTO maAppLineDetailDTO = maAppLineDetailForm.getMaAppLineDetailDTO();
        
        maAppLineDetailService.confirmDetail(maAppLineDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}