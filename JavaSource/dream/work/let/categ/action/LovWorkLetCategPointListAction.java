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
import dream.work.let.categ.dto.LovWorkLetCategPointListDTO;
import dream.work.let.categ.form.LovWorkLetCategPointListForm;
import dream.work.let.categ.service.LovWorkLetCategPointListService;

/**
 * 안전작업허가서 표준점검항목 Lov
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovWorkLetCategPointList" name="lovWorkLetCategPointListForm"
 *                input="/dream/work/let/categ/lovWorkLetCategPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWorkLetCategPointList" path="/dream/work/let/categ/lovWorkLetCategPointList.jsp"
 *                        redirect="false"
 */
public class LovWorkLetCategPointListAction extends SuperAuthAction
{
    public static final int LOV_WO_LET_CATEG_POINT_DEFAULT 	    = 1001;
    
    public static final int LOV_WO_LET_CATEG_POINT_AC_FIND		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWorkLetCategPointListForm lovWorkLetCategPointListForm = (LovWorkLetCategPointListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWorkLetCategPointListForm.getStrutsAction())
        {
	        case LovWorkLetCategPointListAction.LOV_WO_LET_CATEG_POINT_DEFAULT :
	            returnActionForward = mapping.findForward("lovWorkLetCategPointList");
	            break;
	        case LovWorkLetCategPointListAction.LOV_WO_LET_CATEG_POINT_AC_FIND :
	        	findWorkLetCategPointAcList(request, lovWorkLetCategPointListForm,response, false);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;                
            case LovWorkLetCategPointListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWorkLetCategPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWorkLetCategPointListForm lovWorkLetCategPointListForm) throws IOException
    {
        super.setHeader(request, response, lovWorkLetCategPointListForm.getListId(),lovWorkLetCategPointListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovWorkLetCategPointListForm
     */
    private void findWorkLetCategPointAcList(HttpServletRequest request, LovWorkLetCategPointListForm lovWorkLetCategPointListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {        
        LovWorkLetCategPointListService lovWorkLetCategPointListService = (LovWorkLetCategPointListService)getBean("lovWorkLetCategPointListService");
        
        LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO = lovWorkLetCategPointListForm.getLovWorkLetCategPointListDTO();
        
        //Paging
        lovWorkLetCategPointListDTO.setIsLoadMaxCount("Y".equals(lovWorkLetCategPointListForm.getIsLoadMaxCount()));
        lovWorkLetCategPointListDTO.setFirstRow(lovWorkLetCategPointListForm.getFirstRow());
        lovWorkLetCategPointListDTO.setOrderBy(lovWorkLetCategPointListForm.getOrderBy());
        lovWorkLetCategPointListDTO.setDirection(lovWorkLetCategPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovWorkLetCategPointListService.findWorkLetCategPointAcList(lovWorkLetCategPointListDTO, getUser(request), lovWorkLetCategPointListForm);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovWorkLetCategPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovWorkLetCategPointListService.findTotalCount(lovWorkLetCategPointListDTO, user, lovWorkLetCategPointListForm);

        if(excelExport) super.makeGridExport(resultList, request, response, lovWorkLetCategPointListForm.getListId(), lovWorkLetCategPointListForm.getCurrentPageId(), lovWorkLetCategPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }


}