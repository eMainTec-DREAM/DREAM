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
import dream.work.cal.pminsappr.form.WorkCalPmInsApprRsltListForm;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprRsltListService;

/**
 * 예방점검계획승인-점검작업  목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workCalPmInsApprRsltList" name="workCalPmInsApprRsltListForm"
 *                input="/dream/work/cal/pminsappr/workCalPmInsApprRsltList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmInsApprRsltList" path="/dream/work/cal/pminsappr/workCalPmInsApprRsltList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmInsApprRsltListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmInsApprRsltListForm workCalPmInsApprRsltListForm = (WorkCalPmInsApprRsltListForm) form;
        
        switch (workCalPmInsApprRsltListForm.getStrutsAction())
        {
            case WorkCalPmInsApprRsltListAction.LIST_FIND:
            	findList(request, workCalPmInsApprRsltListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmInsApprRsltListAction.BASE_GRID_EXPORT:
            	findList(request, workCalPmInsApprRsltListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case WorkCalPmInsApprRsltListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmInsApprRsltListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workCalPmInsApprRsltList");
                break;
        }

        return returnActionForward;
    }


    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmInsApprRsltListForm workCalPmInsApprRsltListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmInsApprRsltListForm.getListId(), workCalPmInsApprRsltListForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, WorkCalPmInsApprRsltListForm workCalPmInsApprRsltListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkCalPmInsApprRsltListService workCalPmInsApprRsltListService = (WorkCalPmInsApprRsltListService) getBean("workCalPmInsApprRsltListService");        

    	WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = workCalPmInsApprRsltListForm.getWorkCalPmInsApprCommonDTO();
    	WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = workCalPmInsApprRsltListForm.getWorkCalPmInsApprDetailDTO();
    	
    	//Paging
    	workCalPmInsApprCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmInsApprRsltListForm.getIsLoadMaxCount())?true:false);
    	workCalPmInsApprCommonDTO.setFirstRow(workCalPmInsApprRsltListForm.getFirstRow());
    	workCalPmInsApprCommonDTO.setOrderBy(workCalPmInsApprRsltListForm.getOrderBy());
    	workCalPmInsApprCommonDTO.setDirection(workCalPmInsApprRsltListForm.getDirection());
        
        User user = getUser(request);

        //리스트를 조회한다.
        List resultList = workCalPmInsApprRsltListService.findList(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workCalPmInsApprRsltListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmInsApprRsltListService.findTotalCount(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workCalPmInsApprRsltListForm.getListId(),workCalPmInsApprRsltListForm.getCurrentPageId(), workCalPmInsApprRsltListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
