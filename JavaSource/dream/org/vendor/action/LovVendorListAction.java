package dream.org.vendor.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.vendor.dto.LovVendorListDTO;
import dream.org.vendor.form.LovVendorListForm;
import dream.org.vendor.service.LovVendorListService;

/**
 * 거래처 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovVendorList" name="lovVendorListForm"
 *                input="/dream/org/vendor/lovVendorPopup.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/lovVendorAcList" name="lovVendorListForm"
 *                input="/dream/org/vendor/lovVendorAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovVendorPopup" path="/dream/org/vendor/lovVendorPopup.jsp"
 *                        redirect="false"
 */
public class LovVendorListAction extends AuthAction
{
    public static final int LOV_VENDOR_DEFAULT 	= 1001;
    public static final int LOV_VENDOR_FIND     = 1002;
    
    public static final int LOV_VENDOR_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovVendorListForm lovVendorListForm = (LovVendorListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovVendorListForm.getStrutsAction())
        {
            case LovVendorListAction.LOV_VENDOR_DEFAULT :
                returnActionForward = mapping.findForward("lovVendorPopup");
                break;
            case LovVendorListAction.LOV_VENDOR_FIND :
                findVendorList(request, lovVendorListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovVendorListAction.LOV_VENDOR_AC_FIND :
                findVendorAcList(request, lovVendorListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovVendorListAction.BASE_SET_HEADER:
                setHeader(request, response, lovVendorListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	 returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findVendorAcList(HttpServletRequest request, LovVendorListForm lovVendorListForm,
			HttpServletResponse response, boolean excelExport) throws IOException {
    	LovVendorListService lovVendorListService = (LovVendorListService)getBean("lovVendorListService");
        
        LovVendorListDTO lovVendorListDTO = lovVendorListForm.getLovVendorListDTO();

    	//Paging
        lovVendorListDTO.setIsLoadMaxCount("Y".equals(lovVendorListForm.getIsLoadMaxCount())?true:false);
        lovVendorListDTO.setFirstRow(lovVendorListForm.getFirstRow());
        lovVendorListDTO.setOrderBy(lovVendorListForm.getOrderBy());
        lovVendorListDTO.setDirection(lovVendorListForm.getDirection());
        
        List resultList = lovVendorListService.findVendorAcList(lovVendorListDTO, getUser(request), lovVendorListForm);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovVendorListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovVendorListService.findTotalCount(lovVendorListForm,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovVendorListForm.getListId(),lovVendorListForm.getCurrentPageId(), lovVendorListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovVendorListForm lovVendorListForm) throws IOException
    {
        super.setHeader(request, response, lovVendorListForm.getListId(),lovVendorListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovVendorListForm
     */
    private void findVendorList(HttpServletRequest request,
            LovVendorListForm lovVendorListForm,HttpServletResponse response) throws IOException
    {
        LovVendorListService lovVendorListService = (LovVendorListService)getBean("lovVendorListService");
        
        LovVendorListDTO lovVendorListDTO = lovVendorListForm.getLovVendorListDTO();
        List resultList = lovVendorListService.findVendorList(lovVendorListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovVendorListForm.getListId());
    	
    }

}