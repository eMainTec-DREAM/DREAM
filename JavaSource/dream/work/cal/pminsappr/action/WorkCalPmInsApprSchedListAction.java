package dream.work.cal.pminsappr.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;
import dream.work.cal.pminsappr.form.WorkCalPmInsApprSchedListForm;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprSchedListService;

/**
 * 예방점검계획승인-점검작업  목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workCalPmInsApprSchedList" name="workCalPmInsApprSchedListForm"
 *                input="/dream/work/cal/pminsappr/workCalPmInsApprSchedList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmInsApprSchedList" path="/dream/work/cal/pminsappr/workCalPmInsApprSchedList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmInsApprSchedListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmInsApprSchedListForm workCalPmInsApprSchedListForm = (WorkCalPmInsApprSchedListForm) form;
        
        switch (workCalPmInsApprSchedListForm.getStrutsAction())
        {
            case WorkCalPmInsApprSchedListAction.LIST_FIND:
            	findList(request, workCalPmInsApprSchedListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmInsApprSchedListAction.BASE_GRID_EXPORT:
            	findList(request, workCalPmInsApprSchedListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case WorkCalPmInsApprSchedListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmInsApprSchedListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workCalPmInsApprSchedList");
                break;
        }

        return returnActionForward;
    }


    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmInsApprSchedListForm workCalPmInsApprSchedListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmInsApprSchedListForm.getListId(), workCalPmInsApprSchedListForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, WorkCalPmInsApprSchedListForm workCalPmInsApprSchedListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkCalPmInsApprSchedListService workCalPmInsApprSchedListService = (WorkCalPmInsApprSchedListService) getBean("workCalPmInsApprSchedListService");        

    	WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = workCalPmInsApprSchedListForm.getWorkCalPmInsApprCommonDTO();
    	WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = workCalPmInsApprSchedListForm.getWorkCalPmInsApprDetailDTO();
    	
    	//Paging
    	workCalPmInsApprCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmInsApprSchedListForm.getIsLoadMaxCount())?true:false);
    	workCalPmInsApprCommonDTO.setFirstRow(workCalPmInsApprSchedListForm.getFirstRow());
    	workCalPmInsApprCommonDTO.setOrderBy(workCalPmInsApprSchedListForm.getOrderBy());
    	workCalPmInsApprCommonDTO.setDirection(workCalPmInsApprSchedListForm.getDirection());
        
        User user = getUser(request);

        //리스트를 조회한다.
        List resultList = workCalPmInsApprSchedListService.findList(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workCalPmInsApprSchedListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmInsApprSchedListService.findTotalCount(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workCalPmInsApprSchedListForm.getListId(),workCalPmInsApprSchedListForm.getCurrentPageId(), workCalPmInsApprSchedListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
