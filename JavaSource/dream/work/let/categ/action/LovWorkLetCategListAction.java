package dream.work.let.categ.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import dream.work.let.categ.dto.LovWorkLetCategListDTO;
import dream.work.let.categ.form.LovWorkLetCategListForm;
import dream.work.let.categ.service.LovWorkLetCategListService;

/**
 * 안전작업유형 팝업
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovWorkLetCategList" name="lovWorkLetCategListForm"
 *                input="/dream/work/let/categ/lovWorkLetCategList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWorkLetCategList" path="/dream/work/let/categ/lovWorkLetCategList.jsp"
 *                        redirect="false"
 */
public class LovWorkLetCategListAction extends SuperAuthAction
{
    public static final int LOV_WORK_LET_CATEG_DEFAULT 	    = 1001;
    
    public static final int LOV_WORK_LET_CATEG_AC_FIND		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWorkLetCategListForm lovWorkLetCategListForm = (LovWorkLetCategListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWorkLetCategListForm.getStrutsAction())
        {
	        case LovWorkLetCategListAction.LOV_WORK_LET_CATEG_DEFAULT :
	            returnActionForward = mapping.findForward("lovWorkLetCategList");
	            break;
	        case LovWorkLetCategListAction.LOV_WORK_LET_CATEG_AC_FIND :
	        	findWorkLetCategAcList(request, lovWorkLetCategListForm,response, false);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;                
            case LovWorkLetCategListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWorkLetCategListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWorkLetCategListForm lovWorkLetCategListForm) throws IOException
    {
        super.setHeader(request, response, lovWorkLetCategListForm.getListId(),lovWorkLetCategListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovWorkLetCategListForm
     */
    private void findWorkLetCategAcList(HttpServletRequest request, LovWorkLetCategListForm lovWorkLetCategListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {        
        LovWorkLetCategListService lovWorkLetCategListService = (LovWorkLetCategListService)getBean("lovWorkLetCategListService");
        
        LovWorkLetCategListDTO lovWorkLetCategListDTO = lovWorkLetCategListForm.getLovWorkLetCategListDTO();
        
        //Paging
        lovWorkLetCategListDTO.setIsLoadMaxCount("Y".equals(lovWorkLetCategListForm.getIsLoadMaxCount()));
        lovWorkLetCategListDTO.setFirstRow(lovWorkLetCategListForm.getFirstRow());
        lovWorkLetCategListDTO.setOrderBy(lovWorkLetCategListForm.getOrderBy());
        lovWorkLetCategListDTO.setDirection(lovWorkLetCategListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovWorkLetCategListService.findWorkLetCategAcList(lovWorkLetCategListDTO,getUser(request), lovWorkLetCategListForm);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovWorkLetCategListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovWorkLetCategListService.findTotalCount(lovWorkLetCategListDTO, user, lovWorkLetCategListForm);

        if(excelExport) super.makeGridExport(resultList, request, response, lovWorkLetCategListForm.getListId(), lovWorkLetCategListForm.getCurrentPageId(), lovWorkLetCategListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }


}