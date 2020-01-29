package dream.part.pur.buy.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.part.pur.buy.dto.LovPtprAcListDTO;
import dream.part.pur.buy.form.LovPtprAcListForm;
import dream.part.pur.buy.service.LovPtprAcListService;

/**
 * 설비투자 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovPtprAcList" name="lovPtprAcListForm"
 *                input="/dream/part/pur/buy/lovPtprAcList.jsp" scope="request"
 *                validate="false"
 * 
 */
public class LovPtprAcListAction extends SuperAuthAction
{
    public static final int LOV_AC_FIND 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPtprAcListForm lovPtprAcListForm = (LovPtprAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPtprAcListForm.getStrutsAction())
        {
            case LovPtprAcListAction.LOV_AC_FIND :
                findTaskMapList(request, lovPtprAcListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPtprAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPtprAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPtprAcListForm lovPtprAcListForm) throws IOException
    {
        super.setHeader(request, response, lovPtprAcListForm.getListId(),lovPtprAcListForm.getCurrentPageId()); 
    }

    /**
     * 설비투자를 검색한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovTaskMapListForm
     */
    private void findTaskMapList(HttpServletRequest request,
            LovPtprAcListForm lovPtprAcListForm,HttpServletResponse response, boolean excelExport) throws IOException
    {
        LovPtprAcListService lovPtprAcListService = (LovPtprAcListService)getBean("lovPtprAcListService", request);
        
        LovPtprAcListDTO lovPtprAcListDTO = lovPtprAcListForm.getLovPtprAcListDTO();
        
        //Paging
        lovPtprAcListDTO.setIsLoadMaxCount("Y".equals(lovPtprAcListForm.getIsLoadMaxCount())?true:false);
        lovPtprAcListDTO.setFirstRow(lovPtprAcListForm.getFirstRow());
        lovPtprAcListDTO.setOrderBy(lovPtprAcListForm.getOrderBy());
        lovPtprAcListDTO.setDirection(lovPtprAcListForm.getDirection());
        
        List resultList = lovPtprAcListService.findTaskMapAcList(lovPtprAcListDTO,getUser(request),lovPtprAcListForm );
                
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(lovPtprAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovPtprAcListService.findTotalCount(lovPtprAcListDTO,getUser(request),lovPtprAcListForm);
                
        if(excelExport) super.makeGridExport(resultList, request, response, lovPtprAcListForm.getListId(),lovPtprAcListForm.getCurrentPageId(), lovPtprAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }

}