package dream.consult.program.table.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.program.table.dto.TableColValLovDTO;
import dream.consult.program.table.form.TableColValLovForm;
import dream.consult.program.table.service.TableColValLovService;

/**
 * 테이블 Column LOV- List Action
 * 
 * @author kim21017
 * @version $Id: TableColValLovAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/tableColValLov" name="tableColValLovForm"
 *                input="/dream/consult/program/table/tableColValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="tableColValLov" path="/dream/consult/program/table/tableColValLov.jsp"
 *                        redirect="false"
 */

public class TableColValLovAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        TableColValLovForm tableColValLovForm = (TableColValLovForm) form;
        
        switch (tableColValLovForm.getStrutsAction())
        {
            case TableColValLovAction.BASE_SET_HEADER:
                setHeader(request, response, tableColValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case TableColValLovAction.LIST_FIND:
                findList(request, response, tableColValLovForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("tableColValLov");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, TableColValLovForm tableColValLovForm) throws IOException
    {
        super.setHeader(request, response, tableColValLovForm.getListId(), tableColValLovForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param tableColValLovForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, TableColValLovForm tableColValLovForm, boolean excelExport) throws Exception
    {
    	TableColValLovService tableColValLovService = (TableColValLovService) getBean("tableColValLovService");
    	TableColValLovDTO tableColValLovDTO = tableColValLovForm.getTableColValLovDTO();
    	
    	//Paging
    	tableColValLovDTO.setIsLoadMaxCount("Y".equals(tableColValLovForm.getIsLoadMaxCount())?true:false);
    	tableColValLovDTO.setFirstRow(tableColValLovForm.getFirstRow());
    	tableColValLovDTO.setOrderBy(tableColValLovForm.getOrderBy());
    	tableColValLovDTO.setDirection(tableColValLovForm.getDirection());
    	
        List resultList = tableColValLovService.findList(tableColValLovForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(tableColValLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = tableColValLovService.findTotalCount(tableColValLovForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,tableColValLovForm.getListId(),tableColValLovForm.getCurrentPageId(), tableColValLovForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
