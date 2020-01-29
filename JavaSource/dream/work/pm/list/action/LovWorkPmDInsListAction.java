package dream.work.pm.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import dream.work.pm.list.dto.LovWorkPmDInsListDTO;
import dream.work.pm.list.form.LovWorkPmDInsListForm;
import dream.work.pm.list.service.LovWorkPmDInsListService;
import dream.work.pm.std.action.LovStdPointListAction;

/**
 * 점검항목 팝업
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovWorkPmDInsList" name="lovWorkPmDInsListForm"
 *                input="/dream/work/pm/list/lovWorkPmDInsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWorkPmDInsList" path="/dream/work/pm/list/lovWorkPmDInsList.jsp"
 *                        redirect="false"
 */
public class LovWorkPmDInsListAction extends SuperAuthAction
{
    public static final int LOV_STDPOINT_DEFAULT 	  = 1001;
    public static final int LOV_EQCTGPART_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWorkPmDInsListForm lovWorkPmDInsListForm = (LovWorkPmDInsListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWorkPmDInsListForm.getStrutsAction())
        {
        	case LovStdPointListAction.LOV_STDPOINT_DEFAULT :
	            returnActionForward = mapping.findForward("lovWorkPmDInsList");
	            break;
            case LovWorkPmDInsListAction.LOV_EQCTGPART_AC_FIND :
                findEqCtgPartAcAcList(request, lovWorkPmDInsListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWorkPmDInsListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWorkPmDInsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward =  mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWorkPmDInsListForm lovWorkPmDInsListForm) throws IOException
    {
        super.setHeader(request, response, lovWorkPmDInsListForm.getListId(),lovWorkPmDInsListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovWorkPmDInsListForm
	 * @throws Exception 
     */
    private void findEqCtgPartAcAcList(HttpServletRequest request, LovWorkPmDInsListForm lovWorkPmDInsListForm,
            HttpServletResponse response, boolean excelExport) throws Exception {
        
        LovWorkPmDInsListService lovWorkPmDInsListService = (LovWorkPmDInsListService)getBean("lovWorkPmDInsListService");
        LovWorkPmDInsListDTO lovWorkPmDInsListDTO = lovWorkPmDInsListForm.getLovWorkPmDInsListDTO();
        
        //Paging
        lovWorkPmDInsListDTO.setIsLoadMaxCount("Y".equals(lovWorkPmDInsListForm.getIsLoadMaxCount())?true:false);
        lovWorkPmDInsListDTO.setFirstRow(lovWorkPmDInsListForm.getFirstRow());
        lovWorkPmDInsListDTO.setOrderBy(lovWorkPmDInsListForm.getOrderBy());
        lovWorkPmDInsListDTO.setDirection(lovWorkPmDInsListForm.getDirection());

        List resultList = lovWorkPmDInsListService.findEqCtgPartAcList(lovWorkPmDInsListDTO,getUser(request), lovWorkPmDInsListForm);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovWorkPmDInsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovWorkPmDInsListService.findTotalCount(lovWorkPmDInsListDTO,getUser(request),lovWorkPmDInsListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovWorkPmDInsListForm.getListId(),lovWorkPmDInsListForm.getCurrentPageId(), lovWorkPmDInsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}