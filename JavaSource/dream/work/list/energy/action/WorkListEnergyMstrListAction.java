package dream.work.list.energy.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.form.WorkListEnergyMstrListForm;
import dream.work.list.energy.service.WorkListEnergyMstrListService;

/**
 * 에너지관리 - 에너지값 등록 목록 action
 * @author  sy.yang
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/workListEnergyMstrList" name="workListEnergyMstrListForm"
 *                input="/dream/work/list/energy/workListEnergyMstrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListEnergyMstrList" path="/dream/work/list/energy/workListEnergyMstrList.jsp"
 *                        redirect="false"
 */
public class WorkListEnergyMstrListAction extends AuthAction
{
    /** 조회 */
    public static final int WORK_PMU_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WORK_PMU_LIST_DELETE 	= 7002;
    /** 저장 */
    public static final int WORK_PMU_LIST_INPUT    	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListEnergyMstrListForm workListEnergyMstrListForm = (WorkListEnergyMstrListForm) form;
        
        super.updateAudit(workListEnergyMstrListForm.getWorkListEnergyMstrListCommonDTO().getAuditKey()==""?workListEnergyMstrListForm.getWorkListEnergyMstrListCommonDTO().getAuditKey():workListEnergyMstrListForm.getWorkListEnergyMstrListCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListEnergyMstrListForm.getStrutsAction())
        {
            case WorkListEnergyMstrListAction.WORK_PMU_LIST_FIND:
            	findList(request, workListEnergyMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListEnergyMstrListAction.BASE_SET_HEADER:
                setHeader(request, response, workListEnergyMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListEnergyMstrListAction.WORK_PMU_LIST_DELETE:
            	deleteList(request, workListEnergyMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkListEnergyMstrListAction.WORK_PMU_LIST_INPUT:
                insertList(request, workListEnergyMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkListEnergyMstrListAction.BASE_GRID_EXPORT:
            	findList(request, workListEnergyMstrListForm,response, true);
            	returnActionForward =new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workListEnergyMstrList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkListEnergyMstrListForm workListEnergyMstrListForm) throws IOException
    {
        super.setHeader(request, response, workListEnergyMstrListForm.getListId(), workListEnergyMstrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workListEnergyMstrListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findList(HttpServletRequest request, WorkListEnergyMstrListForm workListEnergyMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkListEnergyMstrListService workListEnergyMstrListService = (WorkListEnergyMstrListService) getBean("workListEnergyMstrListService");        

    	WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyMstrListForm.getWorkListEnergyMstrListCommonDTO();
    	workListEnergyMstrListCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	workListEnergyMstrListCommonDTO.setIsLoadMaxCount("Y".equals(workListEnergyMstrListForm.getIsLoadMaxCount())?true:false);
    	workListEnergyMstrListCommonDTO.setFirstRow(workListEnergyMstrListForm.getFirstRow());
    	workListEnergyMstrListCommonDTO.setOrderBy(workListEnergyMstrListForm.getOrderBy());
    	workListEnergyMstrListCommonDTO.setDirection(workListEnergyMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = workListEnergyMstrListService.findList(workListEnergyMstrListCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(workListEnergyMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListEnergyMstrListService.findTotalCount(workListEnergyMstrListCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workListEnergyMstrListForm.getListId(),workListEnergyMstrListForm.getCurrentPageId(), workListEnergyMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  sy.yang
     * @version $Id: WorkListEnergyMstrListAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, WorkListEnergyMstrListForm workListEnergyMstrListForm) throws Exception
    {
    	WorkListEnergyMstrListService workListEnergyMstrListService = (WorkListEnergyMstrListService) getBean("workListEnergyMstrListService");
    	String[] deleteRows = workListEnergyMstrListForm.getDeleteRows();    // sheet 내역
        
        workListEnergyMstrListService.deleteList(deleteRows,getUser(request).getCompNo());
        
        setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workListEnergyMstrListForm
     */
    private void insertList(HttpServletRequest request, WorkListEnergyMstrListForm workListEnergyMstrListForm) throws Exception
    {
    	WorkListEnergyMstrListService workListEnergyMstrListService = (WorkListEnergyMstrListService) getBean("workListEnergyMstrListService");
    	WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyMstrListForm.getWorkListEnergyMstrListCommonDTO();
    	
    	WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrListForm.getWorkListEnergyMstrDetailDTO();
        User loginUser = getUser(request);
        
        workListEnergyMstrListService.insertList(workListEnergyMstrListCommonDTO, workListEnergyMstrDetailDTO, loginUser);
        
        setAjaxStatus(request);
    }
}
