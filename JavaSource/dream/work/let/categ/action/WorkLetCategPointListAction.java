package dream.work.let.categ.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;
import dream.work.let.categ.form.WorkLetCategPointListForm;
import dream.work.let.categ.service.WorkLetCategPointListService;

/**
 * 안전작업유형 - 점검항목 리스트 페이지 - List Action
 * 
 * @author euna0207
 * @version $Id: WorkLetCategPointListAction.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/workLetCategPointList" name="workLetCategPointListForm"
 *                input="/dream/work/let/categ/workLetCategPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetCategPointList" path="/dream/work/let/categ/workLetCategPointList.jsp"
 *                        redirect="false"
 */

public class WorkLetCategPointListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetCategPointListForm workLetCategPointListForm = (WorkLetCategPointListForm) form;
        
        //super.updateAudit(workLetCategPointListForm.getWorkLetCategPointListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetCategPointListForm.getStrutsAction())
        {
            case WorkLetCategPointListAction.BASE_SET_HEADER:
                setHeader(request, response, workLetCategPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetCategPointListAction.LIST_FIND:
                findList(request, response, workLetCategPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkLetCategPointListAction.LIST_DELETE:
            	deleteList(request, workLetCategPointListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkLetCategPointListAction.BASE_GRID_EXPORT:
            	findList(request, response, workLetCategPointListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("workLetCategPointList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkLetCategPointListForm workLetCategPointListForm) throws IOException
    {
        super.setHeader(request, response, workLetCategPointListForm.getListId(), workLetCategPointListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workLetCategPointListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkLetCategPointListForm workLetCategPointListForm, boolean excelExport) throws Exception
    {
    	WorkLetCategPointListService workLetCategPointListService = (WorkLetCategPointListService) getBean("workLetCategPointListService");
    	WorkLetCategPointListDTO workLetCategPointListDTO = workLetCategPointListForm.getWorkLetCategPointListDTO();
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategPointListForm.getWorkLetCategCommonDTO();

    	//Paging
    	workLetCategPointListDTO.setIsLoadMaxCount("Y".equals(workLetCategPointListForm.getIsLoadMaxCount())?true:false);
    	workLetCategPointListDTO.setFirstRow(workLetCategPointListForm.getFirstRow());
    	workLetCategPointListDTO.setOrderBy(workLetCategPointListForm.getOrderBy());
    	workLetCategPointListDTO.setDirection(workLetCategPointListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = workLetCategPointListService.findList(workLetCategCommonDTO, workLetCategPointListDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workLetCategPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workLetCategPointListService.findTotalCount(workLetCategCommonDTO, workLetCategPointListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workLetCategPointListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workLetCategPointListForm
     */
    private void deleteList(HttpServletRequest request, WorkLetCategPointListForm workLetCategPointListForm) throws Exception
    {
    	WorkLetCategPointListService workLetCategPointListService = (WorkLetCategPointListService) getBean("workLetCategPointListService");
        String[] deleteRows = workLetCategPointListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        workLetCategPointListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
