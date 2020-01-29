package dream.consult.comp.emp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.form.ConsultCompEmpListForm;
import dream.consult.comp.emp.service.ConsultCompEmpListService;

/**
 * 사원 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/consultCompEmpList" name="consultCompEmpListForm"
 *                input="/dream/consult/comp/emp/consultCompEmpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompEmpList" path="/dream/consult/comp/emp/consultCompEmpList.jsp"
 *                        redirect="false"
 */
public class ConsultCompEmpListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int EMP_LIST_FIND   = 1001;
    /** 삭제 */
    public static final int EMP_LIST_DELETE = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompEmpListForm consultCompEmpListForm = (ConsultCompEmpListForm) form;
        
        switch (consultCompEmpListForm.getStrutsAction())
        {
            case ConsultCompEmpListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompEmpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompEmpListAction.EMP_LIST_FIND:
                findList(request, response, consultCompEmpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;      
            case ConsultCompEmpListAction.EMP_LIST_DELETE:
                deleteList(request, consultCompEmpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;                
            case ConsultCompEmpListAction.BASE_GRID_EXPORT:
            	findList(request, response, consultCompEmpListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompEmpList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompEmpListForm consultCompEmpListForm) throws IOException
    {
        super.setHeader(request, response, consultCompEmpListForm.getListId(), consultCompEmpListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompEmpListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultCompEmpListForm consultCompEmpListForm, boolean excelExport)  throws IOException
    {
    	ConsultCompEmpListService consultCompEmpListService = (ConsultCompEmpListService) getBean("consultCompEmpListService");        

    	ConsultCompEmpCommonDTO consultCompEmpCommonDTO = consultCompEmpListForm.getConsultCompEmpCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	consultCompEmpCommonDTO.setCompNo(consultCompEmpCommonDTO.getFilterCompNo());
    	
    	//Paging
        consultCompEmpCommonDTO.setIsLoadMaxCount("Y".equals(consultCompEmpListForm.getIsLoadMaxCount())?true:false);
        consultCompEmpCommonDTO.setFirstRow(consultCompEmpListForm.getFirstRow());
        consultCompEmpCommonDTO.setOrderBy(consultCompEmpListForm.getOrderBy());
        consultCompEmpCommonDTO.setDirection(consultCompEmpListForm.getDirection());

        //리스트를 조회한다.
        List resultList = consultCompEmpListService.findEmpList(consultCompEmpCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultCompEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompEmpListService.findTotalCount(consultCompEmpCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultCompEmpListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, ConsultCompEmpListForm consultCompEmpListForm) throws Exception
    {
        ConsultCompEmpListService consultCompEmpListService = (ConsultCompEmpListService) getBean("consultCompEmpListService");        

    	ConsultCompEmpCommonDTO consultCompEmpCommonDTO = consultCompEmpListForm.getConsultCompEmpCommonDTO();
    	
        String[] deleteRows = consultCompEmpListForm.getDeleteRows();    // sheet 내역
        
        consultCompEmpListService.deleteList(consultCompEmpCommonDTO.getFilterCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
}
