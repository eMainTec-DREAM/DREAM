package dream.consult.program.table.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.program.table.dto.TableValLovDTO;
import dream.consult.program.table.form.TableValLovForm;
import dream.consult.program.table.service.TableValLovService;

/**
 * 테이블 LOV- List Action
 * 
 * @author kim21017
 * @version $Id: TableValLovAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/tableValLov" name="tableValLovForm"
 *                input="/dream/consult/program/table/tableValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="tableValLov" path="/dream/consult/program/table/tableValLov.jsp"
 *                        redirect="false"
 */

public class TableValLovAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        TableValLovForm tableValLovForm = (TableValLovForm) form;
        
        switch (tableValLovForm.getStrutsAction())
        {
            case TableValLovAction.BASE_SET_HEADER:
                setHeader(request, response, tableValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case TableValLovAction.LIST_FIND:
                findList(request, response, tableValLovForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("tableValLov");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, TableValLovForm tableValLovForm) throws IOException
    {
        super.setHeader(request, response, tableValLovForm.getListId(), tableValLovForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param tableValLovForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, TableValLovForm tableValLovForm, boolean excelExport) throws Exception
    {
    	TableValLovService tableValLovService = (TableValLovService) getBean("tableValLovService");
    	TableValLovDTO tableValLovDTO = tableValLovForm.getTableValLovDTO();
    	
    	//Paging
    	tableValLovDTO.setIsLoadMaxCount("Y".equals(tableValLovForm.getIsLoadMaxCount())?true:false);
    	tableValLovDTO.setFirstRow(tableValLovForm.getFirstRow());
    	tableValLovDTO.setOrderBy(tableValLovForm.getOrderBy());
    	tableValLovDTO.setDirection(tableValLovForm.getDirection());
    	
        List resultList = tableValLovService.findList(tableValLovForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(tableValLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = tableValLovService.findTotalCount(tableValLovForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,tableValLovForm.getListId(),tableValLovForm.getCurrentPageId(), tableValLovForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
