package dream.mgr.msgrec.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import dream.mgr.msgrec.dto.LovMsgCategListDTO;
import dream.mgr.msgrec.form.LovMsgCategListForm;
import dream.mgr.msgrec.service.LovMsgCategListService;

/**
 * 메시지타입 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovMsgCategList" name="lovMsgCategListForm"
 *                input="/dream/mgr/msgrec/lovMsgCategList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovMsgCategList" path="/dream/mgr/msgrec/lovMsgCategList.jsp"
 *                        redirect="false"
 */
public class LovMsgCategListAction extends SuperAuthAction
{
    public static final int LOV_DEFAULT 	= 1001;
    
    public static final int LOV_AC_FIND		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovMsgCategListForm lovMsgCategListForm = (LovMsgCategListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovMsgCategListForm.getStrutsAction())
        {
	        case LovMsgCategListAction.LOV_DEFAULT :
	            returnActionForward = mapping.findForward("lovMsgCategList");
	            break;
	        case LovMsgCategListAction.LOV_AC_FIND :
	        	findMsgCategAcList(request, lovMsgCategListForm,response, false);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;                
            case LovMsgCategListAction.BASE_SET_HEADER:
                setHeader(request, response, lovMsgCategListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovMsgCategListForm lovMsgCategListForm) throws IOException
    {
        super.setHeader(request, response, lovMsgCategListForm.getListId(),lovMsgCategListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovMsgCategListForm
     */
    private void findMsgCategAcList(HttpServletRequest request, LovMsgCategListForm lovMsgCategListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {        
        LovMsgCategListService lovMsgCategListService = (LovMsgCategListService)getBean("lovMsgCategListService");
        
        LovMsgCategListDTO lovMsgCategListDTO = lovMsgCategListForm.getLovMsgCategListDTO();
        
        //Paging
        lovMsgCategListDTO.setIsLoadMaxCount("Y".equals(lovMsgCategListForm.getIsLoadMaxCount()));
        lovMsgCategListDTO.setFirstRow(lovMsgCategListForm.getFirstRow());
        lovMsgCategListDTO.setOrderBy(lovMsgCategListForm.getOrderBy());
        lovMsgCategListDTO.setDirection(lovMsgCategListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovMsgCategListService.findMsgCategAcList(lovMsgCategListDTO,getUser(request), lovMsgCategListForm);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovMsgCategListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovMsgCategListService.findTotalCount(lovMsgCategListDTO, user, lovMsgCategListForm);

        if(excelExport) super.makeGridExport(resultList, request, response, lovMsgCategListForm.getListId(), lovMsgCategListForm.getCurrentPageId(), lovMsgCategListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }


}