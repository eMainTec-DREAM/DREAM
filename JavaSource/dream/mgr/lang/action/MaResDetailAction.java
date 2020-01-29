package dream.mgr.lang.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.message.DataBaseMessageResources;
import common.struts.AuthAction;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.dto.MaResDetailDTO;
import dream.mgr.lang.form.MaResDetailForm;
import dream.mgr.lang.service.MaResDetailService;

/**
 * 언어 - 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maResDetail" name="maResDetailForm"
 *                input="/dream/mgr/lang/maResDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maResDetail" path="/dream/mgr/lang/maResDetail.jsp"
 *                        redirect="false"
 */
public class MaResDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RES_DETAIL_INIT 		= 8001;
    /** 생성 */
    public static final int RES_DETAIL_INPUT 		= 6003;
    /** 수정 */
    public static final int RES_DETAIL_UPDATE 		= 6002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaResDetailForm maResDetailForm = (MaResDetailForm) form;
        
        super.updateAudit(maResDetailForm.getMaResDetailDTO().getAuditKey()==""?maResDetailForm.getMaResCommonDTO().getAuditKey():maResDetailForm.getMaResDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maResDetailForm.getStrutsAction())
        {
            case MaResDetailAction.RES_DETAIL_INIT:
                this.findDetail(request, maResDetailForm);
                returnActionForward = mapping.findForward("maResDetail");
                break;
            case MaResDetailAction.RES_DETAIL_INPUT:
            	insertDetail(maResDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaResDetailAction.RES_DETAIL_UPDATE:
            	updateDetail(maResDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maResDetail");
                break;
        }

        return returnActionForward;
    }

	/**
     * 언어 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maResDetailForm
     */
    private void findDetail(HttpServletRequest request, MaResDetailForm maResDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaResDetailService maResDetailService = (MaResDetailService)getBean("maResDetailService");

    	MaResCommonDTO maResCommonDTO = maResDetailForm.getMaResCommonDTO();
        
        MaResDetailDTO maResDetailDTO = maResDetailService.findDetail(getUser(request), maResCommonDTO);
        maResDetailForm.setMaResDetailDTO(maResDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maResDetailForm
     * @param request
     * @throws Exception 
     */
    private void insertDetail(MaResDetailForm maResDetailForm, HttpServletRequest request) throws Exception {
    	MaResDetailService maResDetailService = (MaResDetailService) getBean("maResDetailService");
        
    	MaResDetailDTO maResDetailDTO = maResDetailForm.getMaResDetailDTO();
    	MaResCommonDTO maResCommonDTO = maResDetailForm.getMaResCommonDTO();
        User user = getUser(request);
        
        maResDetailService.insertDetail(maResDetailDTO, maResCommonDTO, user);
        
        setAjaxStatus(request);
	}
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maResDetailForm
     * @param request
     */
    private void updateDetail(MaResDetailForm maResDetailForm, HttpServletRequest request) throws Exception
    {
    	MaResDetailService maResDetailService = (MaResDetailService) getBean("maResDetailService");
        
        MaResDetailDTO maResDetailDTO = maResDetailForm.getMaResDetailDTO();
        
        maResDetailDTO.setCompNo((getUser(request).getCompNo()));
        User user = getUser(request);
        
        maResDetailService.updateDetail(maResDetailDTO, user);
        setAjaxStatus(request);

        DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
        dataBaseMessageResources.reloadMessage();
    }

}