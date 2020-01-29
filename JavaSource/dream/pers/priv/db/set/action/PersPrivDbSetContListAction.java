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
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;
import dream.pers.priv.db.set.form.PersPrivDbSetContListForm;
import dream.pers.priv.db.set.service.PersPrivDbSetContListService;

/**
 * PersPrivDbSetCont Page - List Action
 * 
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContListAction.java,v 1.0 2018/08/03 12:46:40 nhkim8548 Exp $
 * @since 1.0
 * @struts:action path="/persPrivDbSetContList" name="persPrivDbSetContListForm"
 *                input="/dream/pers/priv/db/set/persPrivDbSetContList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivDbSetContList" path="/dream/pers/priv/db/set/persPrivDbSetContList.jsp"
 *                        redirect="false"
 */
public class PersPrivDbSetContListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND               = 1001;
    /** 삭제 */
    public static final int LIST_DELETE             = 7002;
    /** 입력 */
    public static final int CNTS_LOV_LIST_INPUT     = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PersPrivDbSetContListForm persPrivDbSetContListForm = (PersPrivDbSetContListForm) form;
        
        switch (persPrivDbSetContListForm.getStrutsAction())
        {
            case PersPrivDbSetContListAction.BASE_SET_HEADER:
                setHeader(request, response, persPrivDbSetContListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivDbSetContListAction.BASE_GRID_EXPORT:
                findList(request, response, persPrivDbSetContListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case PersPrivDbSetContListAction.LIST_FIND:
                findList(request, response, persPrivDbSetContListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PersPrivDbSetContListAction.LIST_DELETE:
                deleteList(request, persPrivDbSetContListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PersPrivDbSetContListAction.CNTS_LOV_LIST_INPUT:
                insertCntsList(request, response, persPrivDbSetContListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("persPrivDbSetContList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetContListForm persPrivDbSetContListForm) throws IOException
    {
        super.setHeader(request, response, persPrivDbSetContListForm.getListId(), persPrivDbSetContListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  nhkim8548
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param persPrivDbSetContListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetContListForm persPrivDbSetContListForm, boolean excelExport) throws Exception
    {
        PersPrivDbSetContListService persPrivDbSetContListService = (PersPrivDbSetContListService) getBean("persPrivDbSetContListService");
        PersPrivDbSetContListDTO persPrivDbSetContListDTO = persPrivDbSetContListForm.getPersPrivDbSetContListDTO();
        PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = persPrivDbSetContListForm.getPersPrivDbSetCommonDTO();
      
        //Paging
        persPrivDbSetContListDTO.setIsLoadMaxCount("Y".equals(persPrivDbSetContListForm.getIsLoadMaxCount())?true:false);
        persPrivDbSetContListDTO.setFirstRow(persPrivDbSetContListForm.getFirstRow());
        persPrivDbSetContListDTO.setOrderBy(persPrivDbSetContListForm.getOrderBy());
        persPrivDbSetContListDTO.setDirection(persPrivDbSetContListForm.getDirection());
        
        User user = getUser(request);
        List resultList = persPrivDbSetContListService.findList(persPrivDbSetCommonDTO, persPrivDbSetContListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(persPrivDbSetContListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = persPrivDbSetContListService.findTotalCount(persPrivDbSetCommonDTO,persPrivDbSetContListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,persPrivDbSetContListForm.getListId(),persPrivDbSetContListForm.getCurrentPageId(), persPrivDbSetContListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  nhkim8548
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param persPrivDbSetContListForm
     */
    private void deleteList(HttpServletRequest request, PersPrivDbSetContListForm persPrivDbSetContListForm) throws Exception
    {
        PersPrivDbSetContListService persPrivDbSetContListService = (PersPrivDbSetContListService) getBean("persPrivDbSetContListService");
        String[] deleteRows = persPrivDbSetContListForm.getDeleteRows();
        
        User user = getUser(request);
   
        persPrivDbSetContListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    /**
     * INSERT CONTENTS LIST
     * @param request
     * @param response
     * @param persPrivDbSetDetailForm
     * @throws Exception
     */
    private void insertCntsList(HttpServletRequest request, HttpServletResponse response, PersPrivDbSetContListForm persPrivDbSetContListForm) throws Exception
    {
        PersPrivDbSetContListService persPrivDbSetContListService = (PersPrivDbSetContListService)getBean("persPrivDbSetContListService");
        PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO = persPrivDbSetContListForm.getPersPrivDbSetContDetailDTO();
        PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = persPrivDbSetContListForm.getPersPrivDbSetCommonDTO();
        User user = getUser(request);
        persPrivDbSetContListService.insertCntsList(persPrivDbSetCommonDTO, persPrivDbSetContDetailDTO, user);
        setAjaxStatus(request);
    }
}