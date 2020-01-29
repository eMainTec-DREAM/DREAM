package dream.rcm.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.RcmSysFDefListDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.form.RcmSysFEnvListForm;
import dream.rcm.system.service.RcmSysFEnvListService;

/**
 * 운전환경  목록
 * @author  kim21017
 * @version $Id: RcmSysFEnvListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysFEnvList" name="rcmSysFEnvListForm"
 *                input="/dream/rcm/system/rcmSysFEnvList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysFEnvList" path="/dream/rcm/system/rcmSysFEnvList.jsp"
 *                        redirect="false"
 */
public class RcmSysFEnvListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_SYS_FENV_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int RCM_SYS_FENV_LIST_DELETE 		= 1002;
    /** 입력 */
    public static final int RCM_SYS_FENV_LIST_INPUT 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysFEnvListForm rcmSysFEnvListForm = (RcmSysFEnvListForm) form;
        
        switch (rcmSysFEnvListForm.getStrutsAction())
        {
            case RcmSysFEnvListAction.BASE_SET_HEADER:
                super.setHeader(request, response, rcmSysFEnvListForm.getListId(), rcmSysFEnvListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysFEnvListAction.RCM_SYS_FENV_LIST_FIND:
                findList(request, response, rcmSysFEnvListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysFEnvListAction.RCM_SYS_FENV_LIST_DELETE:
            	deleteList(request,rcmSysFEnvListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysFEnvListAction.RCM_SYS_FENV_LIST_INPUT:
            	inputList(request,rcmSysFEnvListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysFEnvListAction.BASE_GRID_EXPORT:
            	findList(request,response, rcmSysFEnvListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmSysFEnvList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmSysFEnvListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysFEnvListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmSysFEnvListForm rcmSysFEnvListForm) throws Exception
    {
        RcmSysFEnvListService rcmSysFEnvListService = (RcmSysFEnvListService) getBean("rcmSysFEnvListService");
        
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysFEnvListForm.getRcmSysCommonDTO();
        
        RcmSysFEnvListDTO rcmSysFEnvListDTO = rcmSysFEnvListForm.getRcmSysFEnvListDTO();
        
        List resultList = rcmSysFEnvListService.findList(rcmSysCommonDTO, rcmSysFEnvListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, rcmSysFEnvListForm.getListId());
    }
    
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmSysFEnvListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysFEnvListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, RcmSysFEnvListForm rcmSysFEnvListForm) throws Exception
    {
    	RcmSysFEnvListService rcmSysFEnvListService = (RcmSysFEnvListService) getBean("rcmSysFEnvListService");
        
    	rcmSysFEnvListService.deleteList(rcmSysFEnvListForm.getDeleteRows(), rcmSysFEnvListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  kim2107
     * @version $Id: RcmSysFEnvListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysFEnvListForm
     * @throws Exception
     */
    private void inputList(HttpServletRequest request, RcmSysFEnvListForm rcmSysFEnvListForm) throws Exception
    {
    	RcmSysFEnvListService rcmSysFEnvListService = (RcmSysFEnvListService) getBean("rcmSysFEnvListService");
    	
    	RcmSysCommonDTO rcmSysCommonDTO = rcmSysFEnvListForm.getRcmSysCommonDTO();
        
        RcmSysFEnvListDTO rcmSysFEnvListDTO = rcmSysFEnvListForm.getRcmSysFEnvListDTO();

        rcmSysCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysFEnvListService.inputList(rcmSysCommonDTO, rcmSysFEnvListDTO);
        
    	setAjaxStatus(request);
    }
}