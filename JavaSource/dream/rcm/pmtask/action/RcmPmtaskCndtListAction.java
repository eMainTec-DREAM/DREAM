package dream.rcm.pmtask.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.form.RcmPmtaskCndtListForm;
import dream.rcm.pmtask.service.RcmPmtaskCndtListService;

/**
 * 목록
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmPmtaskCndtList" name="rcmPmtaskCndtListForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskCndtList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmPmtaskCndtList" path="/dream/rcm/pmtask/rcmPmtaskCndtList.jsp"
 *                        redirect="false"
 */
public class RcmPmtaskCndtListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PMTASK_CNDT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PMTASK_CNDT_LIST_DELETE 	= 7002;

    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmPmtaskCndtListForm rcmPmtaskCndtListForm = (RcmPmtaskCndtListForm) form;
        
        switch (rcmPmtaskCndtListForm.getStrutsAction())
        {
            case RcmPmtaskListAction.BASE_SET_HEADER:
                super.setHeader(request, response, rcmPmtaskCndtListForm.getListId(), rcmPmtaskCndtListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmPmtaskCndtListAction.PMTASK_CNDT_LIST_FIND:
                findList(request, response, rcmPmtaskCndtListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmPmtaskCndtListAction.PMTASK_CNDT_LIST_DELETE:
            	deleteList(request,rcmPmtaskCndtListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmPmtaskCndtListAction.BASE_GRID_EXPORT:
            	findList(request,response, rcmPmtaskCndtListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmPmtaskCndtList");
                break;
        }

        return returnActionForward;
    }
    
	/**
     * grid find
     * @author  kim2107
     * @version $Id: RcmPmtaskCndtListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmPmtaskCndtListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmPmtaskCndtListForm rcmPmtaskCndtListForm, boolean excelExport) throws Exception
    {
        RcmPmtaskCndtListService rcmPmtaskCndtListService = (RcmPmtaskCndtListService) getBean("rcmPmtaskCndtListService");
        RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskCndtListForm.getRcmPmtaskCommonDTO();
        rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
        RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO = rcmPmtaskCndtListForm.getRcmPmtaskCndtListDTO();
        
    	//Paging
        rcmPmtaskCommonDTO.setIsLoadMaxCount("Y".equals(rcmPmtaskCndtListForm.getIsLoadMaxCount())?true:false);
        rcmPmtaskCommonDTO.setFirstRow(rcmPmtaskCndtListForm.getFirstRow());
        rcmPmtaskCommonDTO.setOrderBy(rcmPmtaskCndtListForm.getOrderBy());
        rcmPmtaskCommonDTO.setDirection(rcmPmtaskCndtListForm.getDirection());
        
        List resultList = rcmPmtaskCndtListService.findList(rcmPmtaskCommonDTO, rcmPmtaskCndtListDTO);
    	String totalCount = "";
        if(Integer.parseInt(rcmPmtaskCndtListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmPmtaskCndtListService.findTotalCount(rcmPmtaskCommonDTO,rcmPmtaskCndtListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmPmtaskCndtListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
   }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmPmtaskCndtListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmPmtaskCndtListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, RcmPmtaskCndtListForm rcmPmtaskCndtListForm) throws Exception
    {
    	RcmPmtaskCndtListService rcmPmtaskCndtListService = (RcmPmtaskCndtListService) getBean("rcmPmtaskCndtListService");
    	
    	rcmPmtaskCndtListService.deleteList(rcmPmtaskCndtListForm.getDeleteRows());
    	
    	setAjaxStatus(request);
    }
}