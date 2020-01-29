package dream.part.pur.buy.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.pur.buy.dto.LovPartPurBuyPnListDTO;
import dream.part.pur.buy.form.LovPartPurBuyPnListForm;
import dream.part.pur.buy.service.LovPartPurBuyPnListService;

/**
 * 현장신청부품 선택 Lov
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovPartPurBuyPnList" name="lovPartPurBuyPnListForm"
 *                input="/dream/part/pur/buy/lovPartPurBuyPnList.jsp" scope="request"
 *                validate="false"
 */
public class LovPartPurBuyPnListAction extends AuthAction
{
    public static final int LOV_DEFAULT 		= 1001;
    public static final int LOV_AC_FIND		    = 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPartPurBuyPnListForm lovPartPurBuyPnListForm = (LovPartPurBuyPnListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPartPurBuyPnListForm.getStrutsAction())
        {
            case LovPartPurBuyPnListAction.LOV_DEFAULT :
            	returnActionForward = mapping.getInputForward();
                break;
            case LovPartPurBuyPnListAction.LOV_AC_FIND :
                findAcList(request, lovPartPurBuyPnListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPartPurBuyPnListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPartPurBuyPnListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPartPurBuyPnListForm lovPartPurBuyPnListForm) throws IOException
    {
        super.setHeader(request, response, lovPartPurBuyPnListForm.getListId(),lovPartPurBuyPnListForm.getCurrentPageId()); 
    }
    
    /**
     * 현장신청부품 Lov 목록
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovPartPurBuyPnListForm
     */
    private void findAcList(HttpServletRequest request,
            LovPartPurBuyPnListForm lovPartPurBuyPnListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
    	LovPartPurBuyPnListService lovPartPurBuyPnListService = (LovPartPurBuyPnListService)getBean("lovPartPurBuyPnListService", request);
        LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO = lovPartPurBuyPnListForm.getLovPartPurBuyPnListDTO();
        
    	//Paging
        lovPartPurBuyPnListDTO.setIsLoadMaxCount("Y".equals(lovPartPurBuyPnListForm.getIsLoadMaxCount())?true:false);
        lovPartPurBuyPnListDTO.setFirstRow(lovPartPurBuyPnListForm.getFirstRow());
        lovPartPurBuyPnListDTO.setOrderBy(lovPartPurBuyPnListForm.getOrderBy());
        lovPartPurBuyPnListDTO.setDirection(lovPartPurBuyPnListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovPartPurBuyPnListService.findAcList(lovPartPurBuyPnListForm, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovPartPurBuyPnListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovPartPurBuyPnListService.findTotalCount(lovPartPurBuyPnListForm,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovPartPurBuyPnListForm.getListId(),lovPartPurBuyPnListForm.getCurrentPageId(), lovPartPurBuyPnListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}