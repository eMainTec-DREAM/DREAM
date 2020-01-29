package dream.rcm.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEmpListDTO;
import dream.rcm.system.form.RcmSysEmpListForm;
import dream.rcm.system.service.RcmSysEmpListService;

/**
 * 분석자 목록
 * @author  kim21017
 * @version $Id: RcmSysEmpListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysEmpList" name="rcmSysEmpListForm"
 *                input="/dream/rcm/system/rcmSysEmpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysEmpList" path="/dream/rcm/system/rcmSysEmpList.jsp"
 *                        redirect="false"
 */
public class RcmSysEmpListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_SYS_EMP_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int RCM_SYS_EMP_LIST_DELETE 	= 7002;
    /** 입력 */
    public static final int RCM_SYS_EMP_LIST_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysEmpListForm rcmSysEmpListForm = (RcmSysEmpListForm) form;
        
        switch (rcmSysEmpListForm.getStrutsAction())
        {
        
            case RcmSysEmpListAction.RCM_SYS_EMP_LIST_FIND:
                findList(request,response, rcmSysEmpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysEmpListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, rcmSysEmpListForm.getListId(), rcmSysEmpListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysEmpListAction.RCM_SYS_EMP_LIST_DELETE:
            	deleteList(request,rcmSysEmpListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysEmpListAction.RCM_SYS_EMP_LIST_INPUT:
            	inputList(request,rcmSysEmpListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysEmpListAction.BASE_GRID_EXPORT:
            	findList(request,response, rcmSysEmpListForm, true);
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
     * @version $Id: RcmSysEmpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEmpListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, RcmSysEmpListForm rcmSysEmpListForm, boolean excelExport) throws Exception
    {
        RcmSysEmpListService rcmSysEmpListService = (RcmSysEmpListService) getBean("rcmSysEmpListService");
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysEmpListForm.getRcmSysCommonDTO();
        RcmSysEmpListDTO rcmSysEmpListDTO = rcmSysEmpListForm.getRcmSysEmpListDTO();
    	//Paging
    	rcmSysCommonDTO.setIsLoadMaxCount("Y".equals(rcmSysEmpListForm.getIsLoadMaxCount())?true:false);
    	rcmSysCommonDTO.setFirstRow(rcmSysEmpListForm.getFirstRow());
    	rcmSysCommonDTO.setOrderBy(rcmSysEmpListForm.getOrderBy());
    	rcmSysCommonDTO.setDirection(rcmSysEmpListForm.getDirection());
    	
        List resultList = rcmSysEmpListService.findList(rcmSysCommonDTO, rcmSysEmpListDTO, getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(rcmSysEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmSysEmpListService.findTotalCount(rcmSysCommonDTO,rcmSysEmpListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmSysEmpListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
   
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmSysEmpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEmpListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, RcmSysEmpListForm rcmSysEmpListForm) throws Exception
    {
    	RcmSysEmpListService rcmSysEmpListService = (RcmSysEmpListService) getBean("rcmSysEmpListService");
        
    	String[] deleteRows = rcmSysEmpListForm.getDeleteRows();
    
    	rcmSysEmpListService.deleteList(deleteRows, getUser(request).getCompNo());
    	
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
    private void inputList(HttpServletRequest request, RcmSysEmpListForm rcmSysEmpListForm) throws Exception
    {
    	RcmSysEmpListService rcmSysEmpListService = (RcmSysEmpListService) getBean("rcmSysEmpListService");
    	
    	RcmSysCommonDTO rcmSysCommonDTO = rcmSysEmpListForm.getRcmSysCommonDTO();
        
        RcmSysEmpListDTO rcmSysEmpListDTO = rcmSysEmpListForm.getRcmSysEmpListDTO();

        rcmSysCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysEmpListService.inputList(rcmSysCommonDTO, rcmSysEmpListDTO);
        
    	setAjaxStatus(request);
    }
}