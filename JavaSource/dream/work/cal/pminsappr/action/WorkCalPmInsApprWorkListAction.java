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
import dream.work.cal.pminsappr.form.WorkCalPmInsApprWorkListForm;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprWorkListService;

/**
 * 예방점검계획승인-점검작업  목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workCalPmInsApprWorkList" name="workCalPmInsApprWorkListForm"
 *                input="/dream/work/cal/pminsappr/workCalPmInsApprWorkList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmInsApprWorkList" path="/dream/work/cal/pminsappr/workCalPmInsApprWorkList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmInsApprWorkListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmInsApprWorkListForm workCalPmInsApprWorkListForm = (WorkCalPmInsApprWorkListForm) form;
        
        switch (workCalPmInsApprWorkListForm.getStrutsAction())
        {
            case WorkCalPmInsApprWorkListAction.LIST_FIND:
            	findList(request, workCalPmInsApprWorkListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmInsApprWorkListAction.BASE_GRID_EXPORT:
            	findList(request, workCalPmInsApprWorkListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case WorkCalPmInsApprWorkListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmInsApprWorkListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workCalPmInsApprWorkList");
                break;
        }

        return returnActionForward;
    }


    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmInsApprWorkListForm workCalPmInsApprWorkListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmInsApprWorkListForm.getListId(), workCalPmInsApprWorkListForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, WorkCalPmInsApprWorkListForm workCalPmInsApprWorkListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkCalPmInsApprWorkListService workCalPmInsApprWorkListService = (WorkCalPmInsApprWorkListService) getBean("workCalPmInsApprWorkListService");        

    	WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = workCalPmInsApprWorkListForm.getWorkCalPmInsApprCommonDTO();
    	WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = workCalPmInsApprWorkListForm.getWorkCalPmInsApprDetailDTO();
    	
    	//Paging
    	workCalPmInsApprCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmInsApprWorkListForm.getIsLoadMaxCount())?true:false);
    	workCalPmInsApprCommonDTO.setFirstRow(workCalPmInsApprWorkListForm.getFirstRow());
    	workCalPmInsApprCommonDTO.setOrderBy(workCalPmInsApprWorkListForm.getOrderBy());
    	workCalPmInsApprCommonDTO.setDirection(workCalPmInsApprWorkListForm.getDirection());
        
        User user = getUser(request);

        //리스트를 조회한다.
        List resultList = workCalPmInsApprWorkListService.findList(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workCalPmInsApprWorkListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmInsApprWorkListService.findTotalCount(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workCalPmInsApprWorkListForm.getListId(),workCalPmInsApprWorkListForm.getCurrentPageId(), workCalPmInsApprWorkListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
   
}
