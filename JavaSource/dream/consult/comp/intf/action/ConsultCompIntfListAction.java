package dream.consult.comp.intf.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.form.ConsultCompIntfListForm;
import dream.consult.comp.intf.service.ConsultCompIntfListService;

/**
 * Interface Page - List Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultCompIntfList" name="consultCompIntfListForm"
 *                input="/dream/consult/comp/intf/consultCompIntfList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consult/compIntfList" path="/dream/consultComp/intf/consultCompIntfList.jsp"
 *                        redirect="false"
 */

public class ConsultCompIntfListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompIntfListForm consultCompIntfListForm = (ConsultCompIntfListForm) form;
        
        switch (consultCompIntfListForm.getStrutsAction())
        {
            case ConsultCompIntfListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompIntfListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompIntfListAction.LIST_FIND:
                findList(request, response, consultCompIntfListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultCompIntfListAction.LIST_DELETE:
            	deleteList(request, consultCompIntfListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case ConsultCompIntfListAction.BASE_GRID_EXPORT:
            	findList(request, response, consultCompIntfListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("consultCompIntfList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfListForm consultCompIntfListForm) throws IOException
    {
        super.setHeader(request, response, consultCompIntfListForm.getListId(), consultCompIntfListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultCompIntfListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfListForm consultCompIntfListForm, boolean excelExport) throws Exception
    {
    	ConsultCompIntfListService consultCompIntfListService = (ConsultCompIntfListService) getBean("consultCompIntfListService");
    	ConsultCompIntfCommonDTO consultCompIntfCommonDTO = consultCompIntfListForm.getConsultCompIntfCommonDTO();

    	//Paging
    	consultCompIntfCommonDTO.setIsLoadMaxCount("Y".equals(consultCompIntfListForm.getIsLoadMaxCount())?true:false);
    	consultCompIntfCommonDTO.setFirstRow(consultCompIntfListForm.getFirstRow());
    	consultCompIntfCommonDTO.setOrderBy(consultCompIntfListForm.getOrderBy());
    	consultCompIntfCommonDTO.setDirection(consultCompIntfListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = consultCompIntfListService.findList(consultCompIntfCommonDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(consultCompIntfListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompIntfListService.findTotalCount(consultCompIntfCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultCompIntfListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompIntfListForm
     */
    private void deleteList(HttpServletRequest request, ConsultCompIntfListForm consultCompIntfListForm) throws Exception
    {
    	ConsultCompIntfListService consultCompIntfListService = (ConsultCompIntfListService) getBean("consultCompIntfListService");
    	ConsultCompIntfCommonDTO consultCompIntfCommonDTO = consultCompIntfListForm.getConsultCompIntfCommonDTO();

        String[] deleteRows = consultCompIntfListForm.getDeleteRows();
        
    	User user = getUser(request);
        user.setCompNo(consultCompIntfCommonDTO.getFilterCompNo());
        
        consultCompIntfListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
