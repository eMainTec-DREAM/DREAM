package dream.work.pm.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.work.pm.list.dto.LovWorkPmListUInsListDTO;
import dream.work.pm.list.form.LovWorkPmListUInsListForm;
import dream.work.pm.list.service.LovWorkPmListUInsListService;

/**
 * 에너지 측정기준주기 Lov Action
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovWorkPmListUInsList" name="lovWorkPmListUInsListForm"
 *                input="/dream/work/pm/list/ins/lovWorkPmListUInsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWorkPmListUInsList" path="/dream/work/pm/list/ins/lovWorkPmListUInsList.jsp"
 *                        redirect="false"
 */
public class LovWorkPmListUInsListAction extends SuperAuthAction
{
    public static final int LOV_PM_UINS_LIST_DEFAULT 	  = 1001;
    public static final int LOV_PM_UINS_LIST_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWorkPmListUInsListForm lovWorkPmListUInsListForm = (LovWorkPmListUInsListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWorkPmListUInsListForm.getStrutsAction())
        {
        	case LovWorkPmListUInsListAction.LOV_PM_UINS_LIST_DEFAULT :
	            returnActionForward = mapping.findForward("lovWorkPmListUInsList");
	            break;
            case LovWorkPmListUInsListAction.LOV_PM_UINS_LIST_AC_FIND :
                findPmUInsListAcList(request, lovWorkPmListUInsListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWorkPmListUInsListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWorkPmListUInsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward =  mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWorkPmListUInsListForm lovWorkPmListUInsListForm) throws IOException
    {
        super.setHeader(request, response, lovWorkPmListUInsListForm.getListId(),lovWorkPmListUInsListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovWorkPmListUInsListForm
	 * @throws Exception 
     */
    private void findPmUInsListAcList(HttpServletRequest request, LovWorkPmListUInsListForm lovWorkPmListUInsListForm,
            HttpServletResponse response, boolean excelExport) throws Exception {
        
        LovWorkPmListUInsListService lovWorkPmListUInsListService = (LovWorkPmListUInsListService)getBean("lovWorkPmListUInsListService");
        LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO = lovWorkPmListUInsListForm.getLovWorkPmListUInsListDTO();
        
        //Paging
        lovWorkPmListUInsListDTO.setIsLoadMaxCount("Y".equals(lovWorkPmListUInsListForm.getIsLoadMaxCount())?true:false);
        lovWorkPmListUInsListDTO.setFirstRow(lovWorkPmListUInsListForm.getFirstRow());
        lovWorkPmListUInsListDTO.setOrderBy(lovWorkPmListUInsListForm.getOrderBy());
        lovWorkPmListUInsListDTO.setDirection(lovWorkPmListUInsListForm.getDirection());

        List resultList = lovWorkPmListUInsListService.findPmUInsListAcList(lovWorkPmListUInsListDTO,getUser(request), lovWorkPmListUInsListForm);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovWorkPmListUInsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovWorkPmListUInsListService.findTotalCount(lovWorkPmListUInsListDTO,getUser(request),lovWorkPmListUInsListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovWorkPmListUInsListForm.getListId(),lovWorkPmListUInsListForm.getCurrentPageId(), lovWorkPmListUInsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}