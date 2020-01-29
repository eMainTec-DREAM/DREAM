package dream.consult.comp.warehouse.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.comp.warehouse.dto.LovWhListDTO;
import dream.consult.comp.warehouse.form.LovWhListForm;
import dream.consult.comp.warehouse.service.LovWhListService;

/**
 * 사용창고 팝업
 * @author  kim21017
 * @version $Id: LovWhListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovWhList" name="lovWhListForm"
 *                input="/dream/consult/comp/warehouse/lovWhList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultCompWarehouseLov" name="lovWhListForm"
 *                input="/dream/consult/comp/warehouse/consultCompWarehouseLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWhPopup" path="/dream/consult/comp/warehouse/lovWhPopup.jsp"
 *                        redirect="false"
 */
public class LovWhListAction extends AuthAction
{
    public static final int LOV_WH_DEFAULT 	= 1001;
    public static final int LOV_WH_FIND 		= 1002;
    
    public static final int LOV_WH_AC_FIND         = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWhListForm lovWhListForm = (LovWhListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWhListForm.getStrutsAction())
        {
            case LovWhListAction.LOV_WH_DEFAULT :
                returnActionForward = mapping.findForward("lovWhPopup");
                break;
            case LovWhListAction.LOV_WH_FIND :
                findWhList(request, lovWhListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWhListAction.LOV_WH_AC_FIND :
                findWhACList(request, lovWhListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWhListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWhListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWhListForm lovWhListForm) throws IOException
    {
        super.setHeader(request, response, lovWhListForm.getListId(),lovWhListForm.getCurrentPageId()); 
    }

    /**
     * 사용창고 리스트 검색.
     * @author  kim21017
     * @version $Id: LovWhListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovWhListForm
     */
    private void findWhList(HttpServletRequest request,
            LovWhListForm lovWhListForm,HttpServletResponse response) throws IOException
    {
        LovWhListService lovWhListService = (LovWhListService)getBean("lovWhListService");
        
        LovWhListDTO lovWhListDTO = lovWhListForm.getLovWhListDTO();
        List resultList = lovWhListService.findWhList(lovWhListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovWhListForm.getListId());
    	
    }
    
    private void findWhACList(HttpServletRequest request,
            LovWhListForm lovWhListForm,HttpServletResponse response,boolean excelExport) throws IOException
    {
        LovWhListService lovWhListService = (LovWhListService)getBean("lovWhListService");        
        LovWhListDTO lovWhListDTO = lovWhListForm.getLovWhListDTO();
        
        //Paging
        lovWhListDTO.setIsLoadMaxCount("Y".equals(lovWhListForm.getIsLoadMaxCount())?true:false);
        lovWhListDTO.setFirstRow(lovWhListForm.getFirstRow());
        lovWhListDTO.setOrderBy(lovWhListForm.getOrderBy());
        lovWhListDTO.setDirection(lovWhListForm.getDirection());
        
        List resultList = lovWhListService.findWhACList(lovWhListDTO, getUser(request), lovWhListForm);
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(lovWhListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovWhListService.findTotalCount(lovWhListDTO,getUser(request),lovWhListForm);
                
        if(excelExport) super.makeGridExport(resultList, request, response, lovWhListForm.getListId(),lovWhListForm.getCurrentPageId(), lovWhListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }

}