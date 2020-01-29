package dream.consult.program.wrkimp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;
import dream.consult.program.wrkimp.dto.MaWrkimpDetailDTO;
import dream.consult.program.wrkimp.form.MaWrkimpDetailForm;
import dream.consult.program.wrkimp.service.MaWrkimpDetailService;


/**
 * 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maWrkImpDetail" name="maWrkimpDetailForm"
 *                input="/dream/consult/program/wrkimp/maWrkimpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWrkImpDetail" path="/dream/consult/program/wrkimp/maWrkImpDetail.jsp"
 *                        redirect="false"
 */
public class MaWrkimpDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WRKIMP_DETAIL_INIT 			= 1001;
    /** 저장 */
    public static final int WRKIMP_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int WRKIMP_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWrkimpDetailForm maWrkimpDetailForm = (MaWrkimpDetailForm) form;
        
        switch (maWrkimpDetailForm.getStrutsAction())
        {
            case MaWrkimpDetailAction.WRKIMP_DETAIL_INIT:
                this.findDetail(request, maWrkimpDetailForm);
                returnActionForward = mapping.findForward("maWrkImpDetail");
                break;
            case MaWrkimpDetailAction.WRKIMP_DETAIL_INPUT:
            	insertDetail(maWrkimpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWrkimpDetailAction.WRKIMP_DETAIL_UPDATE:
            	updateDetail(maWrkimpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maWrkImpDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maWrkimpDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWrkimpDetailForm maWrkimpDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaWrkimpDetailService maWrkimpDetailService = (MaWrkimpDetailService)getBean("maWrkimpDetailService");
        
    	MaWrkimpCommonDTO maWrkimpCommonDTO = maWrkimpDetailForm.getMaWrkimpCommonDTO();
    	maWrkimpCommonDTO.setLoginUser(getUser(request));
    	maWrkimpCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        MaWrkimpDetailDTO maWrkimpDetailDTO = maWrkimpDetailService.findDetail( maWrkimpDetailForm.getMaWrkimpCommonDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        maWrkimpDetailForm.setMaWrkimpDetailDTO(maWrkimpDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maWrkimpDetailForm
     * @param request
     */
    private void insertDetail(MaWrkimpDetailForm maWrkimpDetailForm, HttpServletRequest request) throws Exception
    {
        MaWrkimpDetailService maWrkimpDetailService = (MaWrkimpDetailService) getBean("maWrkimpDetailService");
        
        MaWrkimpDetailDTO maWrkimpDetailDTO = maWrkimpDetailForm.getMaWrkimpDetailDTO();
        
        maWrkimpDetailService.insertDetail(maWrkimpDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWrkimpDetailForm
     * @param request
     */
    private void updateDetail(MaWrkimpDetailForm maWrkimpDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWrkimpDetailService maWrkimpDetailService = (MaWrkimpDetailService) getBean("maWrkimpDetailService");
        
        MaWrkimpDetailDTO maWrkimpDetailDTO = maWrkimpDetailForm.getMaWrkimpDetailDTO();
        
        maWrkimpDetailService.updateDetail(maWrkimpDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}