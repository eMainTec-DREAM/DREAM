package dream.budget.account.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.budget.account.dto.LovAccountListDTO;
import dream.budget.account.form.LovAccountListForm;
import dream.budget.account.service.LovAccountListService;

/**
 * 예산계정 LOV
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovAccountList" name="lovAccountListForm"
 *                input="/dream/budget/account/lovAccountList.jsp" scope="request"
 *                validate="false"
 */
public class LovAccountListAction extends AuthAction
{
    public static final int LOV_AC_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovAccountListForm lovAccountListForm = (LovAccountListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovAccountListForm.getStrutsAction())
        {
            case LovAccountListAction.LOV_AC_FIND :
                findList(request, lovAccountListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovAccountListAction.BASE_SET_HEADER:
                setHeader(request, response, lovAccountListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward =mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovAccountListForm lovAccountListForm) throws IOException
    {
        super.setHeader(request, response, lovAccountListForm.getListId(),lovAccountListForm.getCurrentPageId()); 
    }

    private void findList(HttpServletRequest request,LovAccountListForm lovAccountListForm,HttpServletResponse response, boolean excelExport) throws IOException
    {
        LovAccountListService lovAccountListService = (LovAccountListService)getBean("lovAccountListService");
        LovAccountListDTO lovAccountListDTO = lovAccountListForm.getLovAccountListDTO();
        
        //Paging
        lovAccountListDTO.setIsLoadMaxCount("Y".equals(lovAccountListForm.getIsLoadMaxCount()));
        lovAccountListDTO.setFirstRow(lovAccountListForm.getFirstRow());
        lovAccountListDTO.setOrderBy(lovAccountListForm.getOrderBy());
        lovAccountListDTO.setDirection(lovAccountListForm.getDirection());
        
        List resultList = lovAccountListService.findList(lovAccountListDTO, getUser(request), lovAccountListForm);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovAccountListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovAccountListService.findTotalCount(lovAccountListDTO,getUser(request),lovAccountListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response, lovAccountListForm.getListId(), lovAccountListForm.getCurrentPageId(), lovAccountListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}