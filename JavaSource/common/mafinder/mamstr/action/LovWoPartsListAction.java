package common.mafinder.mamstr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.mafinder.mamstr.dto.LovWoPartsListDTO;
import common.mafinder.mamstr.form.LovWoPartsListForm;
import common.mafinder.mamstr.service.LovWoPartsListService;
import common.struts.SuperAuthAction;

/**
 * 작업부품 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovWoPartsAcList" name="lovWoPartsListForm"
 *                input="/common/mafinder/mamstr/lovWoPartsAcList.jsp" scope="request"
 *                validate="false"
 */
public class LovWoPartsListAction extends SuperAuthAction
{
    public static final int LOV_WO_PARTS_DEFAULT 	    = 1001;
    
    public static final int LOV_WO_PARTS_AC_FIND 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWoPartsListForm lovWoPartsListForm = (LovWoPartsListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWoPartsListForm.getStrutsAction())
        {
            case LovWoPartsListAction.LOV_WO_PARTS_DEFAULT :
                returnActionForward = mapping.getInputForward();
                break;
            case LovWoPartsListAction.LOV_WO_PARTS_AC_FIND :
                findWoPartsAcList(request, lovWoPartsListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWoPartsListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWoPartsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findWoPartsAcList(HttpServletRequest request, LovWoPartsListForm lovWoPartsListForm, HttpServletResponse response,boolean excelExport) throws IOException {
    	LovWoPartsListService lovWoPartsListService = (LovWoPartsListService)getBean("lovWoPartsListService");
        
        LovWoPartsListDTO lovWoPartsListDTO = lovWoPartsListForm.getLovWoPartsListDTO();
        
        //Paging
        lovWoPartsListDTO.setIsLoadMaxCount("Y".equals(lovWoPartsListForm.getIsLoadMaxCount())?true:false);
        lovWoPartsListDTO.setFirstRow(lovWoPartsListForm.getFirstRow());
        lovWoPartsListDTO.setOrderBy(lovWoPartsListForm.getOrderBy());
        lovWoPartsListDTO.setDirection(lovWoPartsListForm.getDirection());
        
        List resultList = lovWoPartsListService.findWoPartsAcList(lovWoPartsListDTO, getUser(request), lovWoPartsListForm);
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(lovWoPartsListForm.getIsTotalCount()) == 0)
            totalCount = lovWoPartsListService.findWoPartsListTotalCount(lovWoPartsListDTO, getUser(request), lovWoPartsListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response, lovWoPartsListForm.getListId(),lovWoPartsListForm.getCurrentPageId(), lovWoPartsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWoPartsListForm lovWoPartsListForm) throws IOException
    {
        super.setHeader(request, response, lovWoPartsListForm.getListId(),lovWoPartsListForm.getCurrentPageId()); 
    }
}