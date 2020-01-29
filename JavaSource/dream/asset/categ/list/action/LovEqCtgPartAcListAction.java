package dream.asset.categ.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import common.util.CommonUtil;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.asset.categ.list.form.LovEqCtgPartAcListForm;
import dream.asset.categ.list.service.LovEqCtgPartAcListService;
import dream.work.let.categ.action.WorkLetCategListAction;

/**
 * 설비종류별부품 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovEqCtgPartAcList" name="lovEqCtgPartAcListForm"
 *                input="/dream/asset/categ/list/lovEqCtgPartAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEqCtgPartAcList" path="/dream/asset/categ/list/lovEqCtgPartAcList.jsp"
 *                        redirect="false"
 */
public class LovEqCtgPartAcListAction extends SuperAuthAction
{
    public static final int LOV_EQCTGPART_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEqCtgPartAcListForm lovEqCtgPartAcListForm = (LovEqCtgPartAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEqCtgPartAcListForm.getStrutsAction())
        {
            case LovEqCtgPartAcListAction.LOV_EQCTGPART_AC_FIND :
                findEqCtgPartAcAcList(request, response, lovEqCtgPartAcListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqCtgPartAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEqCtgPartAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetCategListAction.BASE_GRID_EXPORT:
            	findEqCtgPartAcAcList(request, response, lovEqCtgPartAcListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default :
            	returnActionForward =  mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEqCtgPartAcListForm lovEqCtgPartAcListForm) throws IOException
    {
        super.setHeader(request, response, lovEqCtgPartAcListForm.getListId(),lovEqCtgPartAcListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovEqCtgPartAcListForm
	 * @throws Exception 
     */
    private void findEqCtgPartAcAcList(HttpServletRequest request, HttpServletResponse response, LovEqCtgPartAcListForm lovEqCtgPartAcListForm,
            boolean excelExport) throws Exception {
        
        LovEqCtgPartAcListService lovEqCtgPartAcListService = (LovEqCtgPartAcListService)getBean("lovEqCtgPartAcListService");
        
        LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO = lovEqCtgPartAcListForm.getLovEqCtgPartAcListDTO();
        
        //Paging
        lovEqCtgPartAcListDTO.setIsLoadMaxCount("Y".equals(lovEqCtgPartAcListForm.getIsLoadMaxCount())?true:false);
        lovEqCtgPartAcListDTO.setFirstRow(lovEqCtgPartAcListForm.getFirstRow());
        lovEqCtgPartAcListDTO.setOrderBy(lovEqCtgPartAcListForm.getOrderBy());
        lovEqCtgPartAcListDTO.setDirection(lovEqCtgPartAcListForm.getDirection());
        
        User user = getUser(request);
        
        List resultList = lovEqCtgPartAcListService.findEqCtgPartAcAcList(lovEqCtgPartAcListDTO,getUser(request), lovEqCtgPartAcListForm);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovEqCtgPartAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovEqCtgPartAcListService.findTotalCount(lovEqCtgPartAcListDTO,user,lovEqCtgPartAcListForm);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, lovEqCtgPartAcListForm); 
        else super.makeJsonResult(resultList, request, response, totalCount);
    }


}