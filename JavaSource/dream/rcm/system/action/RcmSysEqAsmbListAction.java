package dream.rcm.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.form.RcmSysEqAsmbListForm;
import dream.rcm.system.service.RcmSysEqAsmbListService;

/**
 * 설비부위  목록
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysEqAsmbList" name="rcmSysEqAsmbListForm"
 *                input="/dream/rcm/system/rcmSysEqAsmbList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysEqAsmbList" path="/dream/rcm/system/rcmSysEqAsmbList.jsp"
 *                        redirect="false"
 */
public class RcmSysEqAsmbListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_SYS_EQASMB_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int RCM_SYS_EQASMB_LIST_DELETE 		= 1002;
    /** 입력 */
    public static final int RCM_SYS_EQASMB_LIST_INPUT 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysEqAsmbListForm rcmSysEqAsmbListForm = (RcmSysEqAsmbListForm) form;
        
        switch (rcmSysEqAsmbListForm.getStrutsAction())
        {
            case RcmSysEqAsmbListAction.BASE_SET_HEADER:
                super.setHeader(request, response, rcmSysEqAsmbListForm.getListId(), rcmSysEqAsmbListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysEqAsmbListAction.RCM_SYS_EQASMB_LIST_FIND:
                findList(request, response, rcmSysEqAsmbListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysEqAsmbListAction.RCM_SYS_EQASMB_LIST_DELETE:
            	deleteList(request,rcmSysEqAsmbListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysEqAsmbListAction.RCM_SYS_EQASMB_LIST_INPUT:
            	inputList(request,rcmSysEqAsmbListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysEqAsmbListAction.BASE_GRID_EXPORT:
            	findList(request,response, rcmSysEqAsmbListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmSysEqAsmbListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEqAsmbListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmSysEqAsmbListForm rcmSysEqAsmbListForm) throws Exception
    {
        RcmSysEqAsmbListService rcmSysEqAsmbListService = (RcmSysEqAsmbListService) getBean("rcmSysEqAsmbListService");
        
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysEqAsmbListForm.getRcmSysCommonDTO();
        
        RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO = rcmSysEqAsmbListForm.getRcmSysEqAsmbListDTO();
        
        List resultList = rcmSysEqAsmbListService.findList(rcmSysCommonDTO, rcmSysEqAsmbListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, rcmSysEqAsmbListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmSysEqAsmbListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEqAsmbListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, RcmSysEqAsmbListForm rcmSysEqAsmbListForm) throws Exception
    {
    	RcmSysEqAsmbListService rcmSysEqAsmbListService = (RcmSysEqAsmbListService) getBean("rcmSysEqAsmbListService");
        
    	rcmSysEqAsmbListService.deleteList(rcmSysEqAsmbListForm.getDeleteRows(), rcmSysEqAsmbListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmSysEqAsmbListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEqAsmbListForm
     * @throws Exception
     */
    private void inputList(HttpServletRequest request, RcmSysEqAsmbListForm rcmSysEqAsmbListForm) throws Exception
    {
        RcmSysEqAsmbListService rcmSysEqAsmbListService = (RcmSysEqAsmbListService) getBean("rcmSysEqAsmbListService");
        
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysEqAsmbListForm.getRcmSysCommonDTO();
        RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO = rcmSysEqAsmbListForm.getRcmSysEqAsmbListDTO();
        
        rcmSysCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysEqAsmbListService.inputList(rcmSysCommonDTO, rcmSysEqAsmbListDTO);
        
        setAjaxStatus(request);
    }
}