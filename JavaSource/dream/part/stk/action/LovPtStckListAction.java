package dream.part.stk.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.stk.dto.LovPtStckListDTO;
import dream.part.stk.form.LovPtStckListForm;
import dream.part.stk.service.LovPtStckListService;

/**
 * 재고 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovPtStckAcList" name="lovPtStckListForm"
 *                input="/dream/part/stk/lovPtStckAcList.jsp" scope="request"
 *                validate="false"
 */
public class LovPtStckListAction extends AuthAction
{
    public static final int LOV_DEFAULT 		= 1001;
    public static final int LOV_AC_FIND		    = 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPtStckListForm lovPtStckListForm = (LovPtStckListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPtStckListForm.getStrutsAction())
        {
            case LovPtStckListAction.LOV_DEFAULT :
            	returnActionForward = mapping.getInputForward();
                break;
            case LovPtStckListAction.LOV_AC_FIND :
                findAcList(request, lovPtStckListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPtStckListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPtStckListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPtStckListForm lovPtStckListForm) throws IOException
    {
        super.setHeader(request, response, lovPtStckListForm.getListId(),lovPtStckListForm.getCurrentPageId()); 
    }
    
    /**
     * TAPTSTOCK AC LOV를 검색한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovPtStckListForm
     */
    private void findAcList(HttpServletRequest request,
            LovPtStckListForm lovPtStckListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
    	LovPtStckListService lovPtStckListService = (LovPtStckListService)getBean("lovPtStckListService", request);
        LovPtStckListDTO lovPtStckListDTO = lovPtStckListForm.getLovPtStckListDTO();
        
    	//Paging
        lovPtStckListDTO.setIsLoadMaxCount("Y".equals(lovPtStckListForm.getIsLoadMaxCount())?true:false);
        lovPtStckListDTO.setFirstRow(lovPtStckListForm.getFirstRow());
        lovPtStckListDTO.setOrderBy(lovPtStckListForm.getOrderBy());
        lovPtStckListDTO.setDirection(lovPtStckListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovPtStckListService.findAcList(lovPtStckListForm, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovPtStckListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovPtStckListService.findTotalCount(lovPtStckListForm,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovPtStckListForm.getListId(),lovPtStckListForm.getCurrentPageId(), lovPtStckListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}