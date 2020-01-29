package dream.pers.priv.db.set.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.form.PersPrivDbSetListForm;
import dream.pers.priv.db.set.service.PersPrivDbSetListService;

/**
 * Guide Page - List Action
 * 
 * @author nhkim8548
 * @version $Id: PersPrivDbSetListAction.java,v 1.0 2018/08/01 15:58:40 nhkim8548 Exp $
 * @since 1.0
 * @struts:action path="/persPrivDbSetList" name="persPrivDbSetListForm"
 *                input="/dream/pers/priv/db/set/persPrivDbSetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivDbSetList" path="/dream/pers/priv/db/set/persPrivDbSetList.jsp"
 *                        redirect="false"
 */

public class PersPrivDbSetListAction extends AuthAction
{
	//일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PersPrivDbSetListForm persPrivDbSetListForm = (PersPrivDbSetListForm) form;
        
        switch (persPrivDbSetListForm.getStrutsAction())
        {
            case PersPrivDbSetListAction.BASE_SET_HEADER:
                setHeader(request, response, persPrivDbSetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivDbSetListAction.LIST_FIND:
                findList(request, response, persPrivDbSetListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PersPrivDbSetListAction.LIST_DELETE:
            	deleteList(request, persPrivDbSetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case PersPrivDbSetListAction.BASE_GRID_EXPORT:
            	findList(request, response, persPrivDbSetListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetListForm persPrivDbSetListForm) throws IOException
    {
        super.setHeader(request, response, persPrivDbSetListForm.getListId(), persPrivDbSetListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  nhkim8548
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param persPrivDbSetListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetListForm persPrivDbSetListForm, boolean excelExport) throws Exception
    {
    	PersPrivDbSetListService persPrivDbSetListService = (PersPrivDbSetListService) getBean("persPrivDbSetListService");
    	PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = persPrivDbSetListForm.getPersPrivDbSetCommonDTO();

    	//Paging
    	persPrivDbSetCommonDTO.setIsLoadMaxCount("Y".equals(persPrivDbSetListForm.getIsLoadMaxCount())?true:false);
    	persPrivDbSetCommonDTO.setFirstRow(persPrivDbSetListForm.getFirstRow());
    	persPrivDbSetCommonDTO.setOrderBy(persPrivDbSetListForm.getOrderBy());
    	persPrivDbSetCommonDTO.setDirection(persPrivDbSetListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = persPrivDbSetListService.findList(persPrivDbSetCommonDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(persPrivDbSetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = persPrivDbSetListService.findTotalCount(persPrivDbSetCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,persPrivDbSetListForm.getListId(),persPrivDbSetListForm.getCurrentPageId(), persPrivDbSetListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  nhkim8548
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param persPrivDbSetListForm
     */
    private void deleteList(HttpServletRequest request, PersPrivDbSetListForm persPrivDbSetListForm) throws Exception
    {
    	PersPrivDbSetListService persPrivDbSetListService = (PersPrivDbSetListService) getBean("persPrivDbSetListService");
        String[] deleteRows = persPrivDbSetListForm.getDeleteRows();
        
        User user = getUser(request);
    	
        persPrivDbSetListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
