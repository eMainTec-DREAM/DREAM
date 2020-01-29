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
import dream.asset.categ.list.dto.LovEqCtgSpecAcListDTO;
import dream.asset.categ.list.form.LovEqCtgSpecAcListForm;
import dream.asset.categ.list.service.LovEqCtgSpecAcListService;

/**
 * 설비종류별제원 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovEqCtgSpecAcList" name="lovEqCtgSpecAcListForm"
 *                input="/dream/asset/categ/list/lovEqCtgSpecAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEqCtgSpecAcList" path="/dream/asset/categ/list/lovEqCtgSpecAcList.jsp"
 *                        redirect="false"
 */
public class LovEqCtgSpecAcListAction extends SuperAuthAction
{
    public static final int LOV_EQCTGPART_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEqCtgSpecAcListForm lovEqCtgSpecAcListForm = (LovEqCtgSpecAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEqCtgSpecAcListForm.getStrutsAction())
        {
            case LovEqCtgSpecAcListAction.LOV_EQCTGPART_AC_FIND :
                findEqCtgSpecAcAcList(request, lovEqCtgSpecAcListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqCtgSpecAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEqCtgSpecAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward =  mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEqCtgSpecAcListForm lovEqCtgSpecAcListForm) throws IOException
    {
        super.setHeader(request, response, lovEqCtgSpecAcListForm.getListId(),lovEqCtgSpecAcListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovEqCtgSpecAcListForm
	 * @throws Exception 
     */
    private void findEqCtgSpecAcAcList(HttpServletRequest request, LovEqCtgSpecAcListForm lovEqCtgSpecAcListForm,
            HttpServletResponse response, boolean excelExport) throws Exception {
        
        LovEqCtgSpecAcListService lovEqCtgSpecAcListService = (LovEqCtgSpecAcListService)getBean("lovEqCtgSpecAcListService");
        
        LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO = lovEqCtgSpecAcListForm.getLovEqCtgSpecAcListDTO();
        
        //Paging
        lovEqCtgSpecAcListDTO.setIsLoadMaxCount("Y".equals(lovEqCtgSpecAcListForm.getIsLoadMaxCount())?true:false);
        lovEqCtgSpecAcListDTO.setFirstRow(lovEqCtgSpecAcListForm.getFirstRow());
        lovEqCtgSpecAcListDTO.setOrderBy(lovEqCtgSpecAcListForm.getOrderBy());
        lovEqCtgSpecAcListDTO.setDirection(lovEqCtgSpecAcListForm.getDirection());
        
        User user = getUser(request);
        
        List resultList = lovEqCtgSpecAcListService.findEqCtgSpecAcAcList(lovEqCtgSpecAcListDTO,getUser(request), lovEqCtgSpecAcListForm);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovEqCtgSpecAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovEqCtgSpecAcListService.findTotalCount(lovEqCtgSpecAcListDTO,user,lovEqCtgSpecAcListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovEqCtgSpecAcListForm.getListId(),lovEqCtgSpecAcListForm.getCurrentPageId(), lovEqCtgSpecAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }


}