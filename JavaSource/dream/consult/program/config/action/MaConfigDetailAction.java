package dream.consult.program.config.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.config.dto.MaConfigCommonDTO;
import dream.consult.program.config.dto.MaConfigDetailDTO;
import dream.consult.program.config.form.MaConfigDetailForm;
import dream.consult.program.config.service.MaConfigDetailService;

/**
 * 시스템 환경변수 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maConfigDetail" name="maConfigDetailForm"
 *                input="/dream/consult/program/config/maConfigDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maConfigDetail" path="/dream/consult/program/config/maConfigDetail.jsp"
 *                        redirect="false"
 */
public class MaConfigDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int CONFIG_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int CONFIG_DETAIL_UPDATE 	= 1002;
    /** 수정 */
    public static final int CONFIG_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaConfigDetailForm maConfigDetailForm = (MaConfigDetailForm) form;
        
        switch (maConfigDetailForm.getStrutsAction())
        {
            case MaConfigDetailAction.CONFIG_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maConfigDetailForm);
                returnActionForward = mapping.findForward("maConfigDetail");
                break;
            case MaConfigDetailAction.CONFIG_DETAIL_UPDATE:
            	updateDetail(maConfigDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaConfigDetailAction.CONFIG_DETAIL_INPUT:
            	insertDetail(maConfigDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maConfigDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 시스템환경변수 상세 조회
     * @author kim2107
     * @version $Id: MaConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maConfigDetailForm
     */
    private void findDetail(HttpServletRequest request, MaConfigDetailForm maConfigDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaConfigDetailService maConfigDetailService = (MaConfigDetailService)getBean("maConfigDetailService");

    	MaConfigCommonDTO maConfigCommonDTO = maConfigDetailForm.getMaConfigCommonDTO();
    	
        // 조회된 상세 부분
        MaConfigDetailDTO maConfigDetailDTO = maConfigDetailService.findDetail(maConfigCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maConfigDetailForm.setMaConfigDetailDTO(maConfigDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailForm
     * @param request
     */
    private void updateDetail(MaConfigDetailForm maConfigDetailForm, HttpServletRequest request) throws Exception
    {
    	MaConfigDetailService maConfigDetailService = (MaConfigDetailService) getBean("maConfigDetailService");
        
        MaConfigDetailDTO maConfigDetailDTO = maConfigDetailForm.getMaConfigDetailDTO();
        
        maConfigDetailService.updateDetail(maConfigDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailForm
     * @param request
     */
    private void insertDetail(MaConfigDetailForm maConfigDetailForm, HttpServletRequest request) throws Exception
    {
        MaConfigDetailService maConfigDetailService = (MaConfigDetailService) getBean("maConfigDetailService");
        
        MaConfigDetailDTO maConfigDetailDTO = maConfigDetailForm.getMaConfigDetailDTO();
        
        maConfigDetailService.insertDetail(maConfigDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}