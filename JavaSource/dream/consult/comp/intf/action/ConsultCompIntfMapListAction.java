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
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;
import dream.consult.comp.intf.form.ConsultCompIntfMapListForm;
import dream.consult.comp.intf.service.ConsultCompIntfMapListService;

/**
 * Interface Log Page - List Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultCompIntfMapList" name="consultCompIntfMapListForm"
 *                input="/dream/consult/comp/intf/consultCompIntfMapList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompIntfMapList" path="/dream/consult/comp/intf/consultCompIntfMapList.jsp"
 *                        redirect="false"
 */

public class ConsultCompIntfMapListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompIntfMapListForm consultCompIntfMapListForm = (ConsultCompIntfMapListForm) form;
        
        switch (consultCompIntfMapListForm.getStrutsAction())
        {
            case ConsultCompIntfMapListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompIntfMapListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompIntfMapListAction.LIST_FIND:
                findList(request, response, consultCompIntfMapListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultCompIntfMapListAction.LIST_DELETE:
            	deleteList(request, consultCompIntfMapListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case ConsultCompIntfMapListAction.BASE_GRID_EXPORT:
            	findList(request, response, consultCompIntfMapListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("consultCompIntfMapList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfMapListForm consultCompIntfMapListForm) throws IOException
    {
        super.setHeader(request, response, consultCompIntfMapListForm.getListId(), consultCompIntfMapListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultCompIntfMapListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfMapListForm consultCompIntfMapListForm, boolean excelExport) throws Exception
    {
    	ConsultCompIntfMapListService consultCompIntfMapListService = (ConsultCompIntfMapListService) getBean("consultCompIntfMapListService");
    	ConsultCompIntfCommonDTO consultCompIntfCommonDTO = consultCompIntfMapListForm.getConsultCompIntfCommonDTO();
    	ConsultCompIntfMapListDTO consultCompIntfMapListDTO = consultCompIntfMapListForm.getConsultCompIntfMapListDTO();

    	//Paging
    	consultCompIntfMapListDTO.setIsLoadMaxCount("Y".equals(consultCompIntfMapListForm.getIsLoadMaxCount())?true:false);
    	consultCompIntfMapListDTO.setFirstRow(consultCompIntfMapListForm.getFirstRow());
    	consultCompIntfMapListDTO.setOrderBy(consultCompIntfMapListForm.getOrderBy());
    	consultCompIntfMapListDTO.setDirection(consultCompIntfMapListForm.getDirection());
    	
    	User user = getUser(request);
    	user.setCompNo(consultCompIntfCommonDTO.getCompNo());
    	
        List resultList = consultCompIntfMapListService.findList(consultCompIntfCommonDTO, consultCompIntfMapListDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(consultCompIntfMapListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompIntfMapListService.findTotalCount(consultCompIntfCommonDTO, consultCompIntfMapListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultCompIntfMapListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompIntfMapListForm
     */
    private void deleteList(HttpServletRequest request, ConsultCompIntfMapListForm consultCompIntfMapListForm) throws Exception
    {
    	ConsultCompIntfMapListService consultCompIntfMapListService = (ConsultCompIntfMapListService) getBean("consultCompIntfMapListService");
    	ConsultCompIntfCommonDTO consultCompIntfCommonDTO = consultCompIntfMapListForm.getConsultCompIntfCommonDTO();
        String[] deleteRows = consultCompIntfMapListForm.getDeleteRows();
        
    	User user = getUser(request);
    	user.setCompNo(consultCompIntfCommonDTO.getCompNo());
    	
        consultCompIntfMapListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
