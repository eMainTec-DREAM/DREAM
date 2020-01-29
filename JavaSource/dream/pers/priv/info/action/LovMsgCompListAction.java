package dream.pers.priv.info.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import dream.pers.priv.info.dto.LovMsgCompListDTO;
import dream.pers.priv.info.form.LovMsgCompListForm;
import dream.pers.priv.info.service.LovMsgCompListService;

/**
 * 메시지타입 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovMsgCompList" name="lovMsgCompListForm"
 *                input="/dream/pers/priv/info/lovMsgCompList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovMsgCompList" path="/dream/pers/priv/info/lovMsgCompList.jsp"
 *                        redirect="false"
 */
public class LovMsgCompListAction extends SuperAuthAction
{
    public static final int LOV_DEFAULT 	= 1001;
    
    public static final int LOV_AC_FIND		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovMsgCompListForm lovMsgCompListForm = (LovMsgCompListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovMsgCompListForm.getStrutsAction())
        {
	        case LovMsgCompListAction.LOV_DEFAULT :
	            returnActionForward = mapping.findForward("lovMsgCompList");
	            break;
	        case LovMsgCompListAction.LOV_AC_FIND :
	        	findMsgCompAcList(request, lovMsgCompListForm,response, false);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;                
            case LovMsgCompListAction.BASE_SET_HEADER:
                setHeader(request, response, lovMsgCompListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovMsgCompListForm lovMsgCompListForm) throws IOException
    {
        super.setHeader(request, response, lovMsgCompListForm.getListId(),lovMsgCompListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovMsgCompListForm
     */
    private void findMsgCompAcList(HttpServletRequest request, LovMsgCompListForm lovMsgCompListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {        
        LovMsgCompListService lovMsgCompListService = (LovMsgCompListService)getBean("lovMsgCompListService");
        
        LovMsgCompListDTO lovMsgCompListDTO = lovMsgCompListForm.getLovMsgCompListDTO();
        
        //Paging
        lovMsgCompListDTO.setIsLoadMaxCount("Y".equals(lovMsgCompListForm.getIsLoadMaxCount()));
        lovMsgCompListDTO.setFirstRow(lovMsgCompListForm.getFirstRow());
        lovMsgCompListDTO.setOrderBy(lovMsgCompListForm.getOrderBy());
        lovMsgCompListDTO.setDirection(lovMsgCompListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovMsgCompListService.findMsgCompAcList(lovMsgCompListDTO,getUser(request), lovMsgCompListForm);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovMsgCompListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovMsgCompListService.findTotalCount(lovMsgCompListDTO, user, lovMsgCompListForm);

        if(excelExport) super.makeGridExport(resultList, request, response, lovMsgCompListForm.getListId(), lovMsgCompListForm.getCurrentPageId(), lovMsgCompListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }


}