package dream.part.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import dream.part.list.dto.LovPartListBinListDTO;
import dream.part.list.form.LovPartListBinListForm;
import dream.part.list.service.LovPartListBinListService;
import dream.work.pm.std.action.LovStdPointListAction;

/**
 * 점검항목 팝업
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovPartListBinList" name="lovPartListBinListForm"
 *                input="/dream/part/list/lovPartListBinList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovPartListBinList" path="/dream/part/list/lovPartListBinList.jsp"
 *                        redirect="false"
 */
public class LovPartListBinListAction extends SuperAuthAction
{
    public static final int LOV_STDPOINT_DEFAULT 	  = 1001;
    public static final int LOV_EQCTGPART_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPartListBinListForm lovPartListBinListForm = (LovPartListBinListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPartListBinListForm.getStrutsAction())
        {
        	case LovStdPointListAction.LOV_STDPOINT_DEFAULT :
	            returnActionForward = mapping.findForward("lovPartListBinList");
	            break;
            case LovPartListBinListAction.LOV_EQCTGPART_AC_FIND :
                findEqCtgPartAcAcList(request, lovPartListBinListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPartListBinListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPartListBinListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward =  mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPartListBinListForm lovPartListBinListForm) throws IOException
    {
        super.setHeader(request, response, lovPartListBinListForm.getListId(),lovPartListBinListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovPartListBinListForm
	 * @throws Exception 
     */
    private void findEqCtgPartAcAcList(HttpServletRequest request, LovPartListBinListForm lovPartListBinListForm,
            HttpServletResponse response, boolean excelExport) throws Exception {
        
        LovPartListBinListService lovPartListBinListService = (LovPartListBinListService)getBean("lovPartListBinListService");
        LovPartListBinListDTO lovPartListBinListDTO = lovPartListBinListForm.getLovPartListBinListDTO();
        
        //Paging
        lovPartListBinListDTO.setIsLoadMaxCount("Y".equals(lovPartListBinListForm.getIsLoadMaxCount())?true:false);
        lovPartListBinListDTO.setFirstRow(lovPartListBinListForm.getFirstRow());
        lovPartListBinListDTO.setOrderBy(lovPartListBinListForm.getOrderBy());
        lovPartListBinListDTO.setDirection(lovPartListBinListForm.getDirection());

        List resultList = lovPartListBinListService.findEqCtgPartAcList(lovPartListBinListDTO,getUser(request), lovPartListBinListForm);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovPartListBinListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovPartListBinListService.findTotalCount(lovPartListBinListDTO,getUser(request),lovPartListBinListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovPartListBinListForm.getListId(),lovPartListBinListForm.getCurrentPageId(), lovPartListBinListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}