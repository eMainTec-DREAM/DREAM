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
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;
import dream.work.let.categ.form.WorkLetCategEtcListForm;
import dream.work.let.categ.service.WorkLetCategEtcListService;

/**
 * 안전작업유형 보완사항 리스트 페이지 - List Action
 * 
 * @author euna0207
 * @version $Id: WorkLetCategEtcListAction.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/workLetCategEtcList" name="workLetCategEtcListForm"
 *                input="/dream/work/let/categ/workLetCategEtcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetCategEtcList" path="/dream/work/let/categ/workLetCategEtcList.jsp"
 *                        redirect="false"
 */

public class WorkLetCategEtcListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetCategEtcListForm workLetCategEtcListForm = (WorkLetCategEtcListForm) form;
        
        //super.updateAudit(workLetCategEtcListForm.getWorkLetCategEtcListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetCategEtcListForm.getStrutsAction())
        {
            case WorkLetCategEtcListAction.BASE_SET_HEADER:
                setHeader(request, response, workLetCategEtcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetCategEtcListAction.LIST_FIND:
                findList(request, response, workLetCategEtcListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkLetCategEtcListAction.LIST_DELETE:
            	deleteList(request, workLetCategEtcListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkLetCategEtcListAction.BASE_GRID_EXPORT:
            	findList(request, response, workLetCategEtcListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("workLetCategEtcList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkLetCategEtcListForm workLetCategEtcListForm) throws IOException
    {
        super.setHeader(request, response, workLetCategEtcListForm.getListId(), workLetCategEtcListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workLetCategEtcListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkLetCategEtcListForm workLetCategEtcListForm, boolean excelExport) throws Exception
    {
    	WorkLetCategEtcListService workLetCategEtcListService = (WorkLetCategEtcListService) getBean("workLetCategEtcListService");
    	WorkLetCategEtcListDTO workLetCategEtcListDTO = workLetCategEtcListForm.getWorkLetCategEtcListDTO();
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategEtcListForm.getWorkLetCategCommonDTO();
    	
    	//Paging
    	workLetCategEtcListDTO.setIsLoadMaxCount("Y".equals(workLetCategEtcListForm.getIsLoadMaxCount())?true:false);
    	workLetCategEtcListDTO.setFirstRow(workLetCategEtcListForm.getFirstRow());
    	workLetCategEtcListDTO.setOrderBy(workLetCategEtcListForm.getOrderBy());
    	workLetCategEtcListDTO.setDirection(workLetCategEtcListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = workLetCategEtcListService.findList(workLetCategCommonDTO, workLetCategEtcListDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workLetCategEtcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workLetCategEtcListService.findTotalCount(workLetCategCommonDTO, workLetCategEtcListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workLetCategEtcListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workLetCategEtcListForm
     */
    private void deleteList(HttpServletRequest request, WorkLetCategEtcListForm workLetCategEtcListForm) throws Exception
    {
    	WorkLetCategEtcListService workLetCategEtcListService = (WorkLetCategEtcListService) getBean("workLetCategEtcListService");
        String[] deleteRows = workLetCategEtcListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        workLetCategEtcListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
