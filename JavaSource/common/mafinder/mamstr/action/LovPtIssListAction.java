package common.mafinder.mamstr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.mafinder.mamstr.dto.LovPtIssListDTO;
import common.mafinder.mamstr.form.LovPtIssListForm;
import common.mafinder.mamstr.service.LovPtIssListService;
import common.struts.SuperAuthAction;

/**
 * 출고부품 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovPtIssAcList" name="lovPtIssListForm"
 *                input="/common/mafinder/mamstr/lovPtIssAcList.jsp" scope="request"
 *                validate="false"
 */
public class LovPtIssListAction extends SuperAuthAction
{
    public static final int LOV_PT_ISS_DEFAULT 	    = 1001;
    
    public static final int LOV_PT_ISS_AC_FIND 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPtIssListForm lovPtIssListForm = (LovPtIssListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPtIssListForm.getStrutsAction())
        {
            case LovPtIssListAction.LOV_PT_ISS_DEFAULT :
                returnActionForward = mapping.getInputForward();
                break;
            case LovPtIssListAction.LOV_PT_ISS_AC_FIND :
                findPtIssAcList(request, lovPtIssListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPtIssListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPtIssListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findPtIssAcList(HttpServletRequest request, LovPtIssListForm lovPtIssListForm, HttpServletResponse response,boolean excelExport) throws IOException {
    	LovPtIssListService lovPtIssListService = (LovPtIssListService)getBean("lovPtIssListService");
        
        LovPtIssListDTO lovPtIssListDTO = lovPtIssListForm.getLovPtIssListDTO();
        
        //Paging
        lovPtIssListDTO.setIsLoadMaxCount("Y".equals(lovPtIssListForm.getIsLoadMaxCount())?true:false);
        lovPtIssListDTO.setFirstRow(lovPtIssListForm.getFirstRow());
        lovPtIssListDTO.setOrderBy(lovPtIssListForm.getOrderBy());
        lovPtIssListDTO.setDirection(lovPtIssListForm.getDirection());
        
        List resultList = lovPtIssListService.findPtIssAcList(lovPtIssListDTO, getUser(request), lovPtIssListForm);
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(lovPtIssListForm.getIsTotalCount()) == 0)
            totalCount = lovPtIssListService.findPtIssListTotalCount(lovPtIssListDTO, getUser(request), lovPtIssListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response, lovPtIssListForm.getListId(),lovPtIssListForm.getCurrentPageId(), lovPtIssListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPtIssListForm lovPtIssListForm) throws IOException
    {
        super.setHeader(request, response, lovPtIssListForm.getListId(),lovPtIssListForm.getCurrentPageId()); 
    }
}