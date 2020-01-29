package dream.work.let.permit.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;
import dream.work.let.permit.form.WorkLetPermitPointListForm;
import dream.work.let.permit.service.WorkLetPermitPointListService;

/**
 * 안전작업허가서 작업유형  - 점검항목 목록 Action
 * 
 * @author syyang
 * @version $Id: WorkLetPermitPointListAction.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 * @struts:action path="/workLetPermitPointList" name="workLetPermitPointListForm"
 *                input="/dream/work/let/permit/workLetPermitPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetPermitPointList" path="/dream/work/let/permit/workLetPermitPointList.jsp"
 *                        redirect="false"
 */

public class WorkLetPermitPointListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 					= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 				= 7002;
    /** 표준 점검항목 입력 */
    public static final int WO_LET_STDPOINT_LIST_INPUT	= 5001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) 
    		throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetPermitPointListForm workLetPermitPointListForm = (WorkLetPermitPointListForm) form;
        
        //super.updateAudit(workLetPermitPointListForm.getWorkLetPermitPointListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetPermitPointListForm.getStrutsAction())
        {
            case WorkLetPermitPointListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workLetPermitPointListForm.getListId(), workLetPermitPointListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetPermitPointListAction.LIST_FIND:
                findList(request, response, workLetPermitPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;                case WorkLetPermitPointListAction.LIST_DELETE:
            	deleteList(request, workLetPermitPointListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkLetPermitPointListAction.WO_LET_STDPOINT_LIST_INPUT:
            	insertStdPointList(request,workLetPermitPointListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetPermitPointListAction.BASE_GRID_EXPORT:
            	findList(request, response, workLetPermitPointListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    /**
     * FIND LIST
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workLetPermitPointListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkLetPermitPointListForm workLetPermitPointListForm, boolean excelExport) throws Exception
    {
    	WorkLetPermitPointListService workLetPermitPointListService = (WorkLetPermitPointListService) getBean("workLetPermitPointListService");
    	WorkLetPermitPointListDTO workLetPermitPointListDTO = workLetPermitPointListForm.getWorkLetPermitPointListDTO();
    	WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitPointListForm.getWorkLetPermitDetailDTO();

    	//Paging
    	workLetPermitPointListDTO.setIsLoadMaxCount("Y".equals(workLetPermitPointListForm.getIsLoadMaxCount())?true:false);
    	workLetPermitPointListDTO.setFirstRow(workLetPermitPointListForm.getFirstRow());
    	workLetPermitPointListDTO.setOrderBy(workLetPermitPointListForm.getOrderBy());
    	workLetPermitPointListDTO.setDirection(workLetPermitPointListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = workLetPermitPointListService.findList(workLetPermitDetailDTO, workLetPermitPointListDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workLetPermitPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workLetPermitPointListService.findTotalCount(workLetPermitDetailDTO, workLetPermitPointListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workLetPermitPointListForm.getListId(),workLetPermitPointListForm.getCurrentPageId(), workLetPermitPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workLetPermitPointListForm
     */
    private void deleteList(HttpServletRequest request, WorkLetPermitPointListForm workLetPermitPointListForm) throws Exception
    {
    	WorkLetPermitPointListService workLetPermitPointListService = (WorkLetPermitPointListService) getBean("workLetPermitPointListService");
    	
        String[] deleteRows = workLetPermitPointListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        workLetPermitPointListService.deleteList(deleteRows, user);
        
        setAjaxStatus(request);
    }

    /**
     * 표준점검항목 insert
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmMstrPartDetailForm
     * @param request
     */
    private void insertStdPointList(HttpServletRequest request, WorkLetPermitPointListForm workLetPermitPointListForm) throws Exception
    {
    	WorkLetPermitPointListService workLetPermitPointListService = (WorkLetPermitPointListService) getBean("workLetPermitPointListService");
    	
    	WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitPointListForm.getWorkLetPermitDetailDTO();
    	WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO = workLetPermitPointListForm.getWorkLetPermitPointDetailDTO();
    	
    	workLetPermitPointListService.insertStdPointList(workLetPermitDetailDTO, workLetPermitPointDetailDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
}
