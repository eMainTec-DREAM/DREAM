package dream.pers.priv.info.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.form.PersPrivInfoMsgEmpListForm;
import dream.pers.priv.info.service.PersPrivInfoMsgEmpListService;

/**
 * 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/persPrivInfoMsgEmpList" name="persPrivInfoMsgEmpListForm"
 *                input="/dream/pers/priv/info/persPrivInfoMsgEmpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivInfoMsgEmpList" path="/dream/pers/priv/info/persPrivInfoMsgEmpList.jsp"
 *                        redirect="false"
 */
public class PersPrivInfoMsgEmpListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    /** EDIT */
    public static final int LIST_SAVE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PersPrivInfoMsgEmpListForm persPrivInfoMsgEmpListForm = (PersPrivInfoMsgEmpListForm) form;
        
        switch (persPrivInfoMsgEmpListForm.getStrutsAction())
        {
        
            case PersPrivInfoMsgEmpListAction.LIST_FIND:
                findList(request,response, persPrivInfoMsgEmpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivInfoMsgEmpListAction.BASE_GRID_EXPORT:
            	findList(request,response, persPrivInfoMsgEmpListForm, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case PersPrivInfoMsgEmpListAction.LIST_SAVE:
            	saveList(request,response,persPrivInfoMsgEmpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivInfoMsgEmpListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, persPrivInfoMsgEmpListForm.getListId(), persPrivInfoMsgEmpListForm.getCurrentPageId());
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case PersPrivInfoMsgEmpListAction.LIST_DELETE:
            	deleteList(request,persPrivInfoMsgEmpListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param persPrivInfoMsgEmpListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, PersPrivInfoMsgEmpListForm persPrivInfoMsgEmpListForm, boolean excelExport) throws Exception
    {
        PersPrivInfoMsgEmpListService persPrivInfoMsgEmpListService = (PersPrivInfoMsgEmpListService) getBean("persPrivInfoMsgEmpListService");
        MaMyInfoCommonDTO maMyInfoCommonDTO = persPrivInfoMsgEmpListForm.getMaMyInfoCommonDTO();
        
        //Paging
        maMyInfoCommonDTO.setIsLoadMaxCount("Y".equals(persPrivInfoMsgEmpListForm.getIsLoadMaxCount())?true:false);
        maMyInfoCommonDTO.setFirstRow(persPrivInfoMsgEmpListForm.getFirstRow());
        maMyInfoCommonDTO.setOrderBy(persPrivInfoMsgEmpListForm.getOrderBy());
        maMyInfoCommonDTO.setDirection(persPrivInfoMsgEmpListForm.getDirection());
        
        User user = getUser(request);
        List resultList = persPrivInfoMsgEmpListService.findList(maMyInfoCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(persPrivInfoMsgEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = persPrivInfoMsgEmpListService.findTotalCount(maMyInfoCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,persPrivInfoMsgEmpListForm.getListId(),persPrivInfoMsgEmpListForm.getCurrentPageId(), persPrivInfoMsgEmpListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, persPrivInfoMsgEmpListForm.getListId());
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param persPrivInfoMsgEmpListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, PersPrivInfoMsgEmpListForm persPrivInfoMsgEmpListForm) throws Exception
    {
    	PersPrivInfoMsgEmpListService persPrivInfoMsgEmpListService = (PersPrivInfoMsgEmpListService) getBean("persPrivInfoMsgEmpListService");
    	String[] deleteRows = persPrivInfoMsgEmpListForm.getDeleteRows();
    
    	User user = getUser(request);
    	persPrivInfoMsgEmpListService.deleteList(deleteRows, user);
    	
    	setAjaxStatus(request);
    }
    
    /**
     * List Save
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param persPrivInfoMsgEmpListForm
     * @throws Exception
     */
    private void saveList(HttpServletRequest request, HttpServletResponse response, PersPrivInfoMsgEmpListForm persPrivInfoMsgEmpListForm) throws Exception
    {
    	PersPrivInfoMsgEmpListService persPrivInfoMsgEmpListService = (PersPrivInfoMsgEmpListService) getBean("persPrivInfoMsgEmpListService");
    	List<Map> gridList = CommonUtil.setGridMap(request);

    	User user = getUser(request);
    	persPrivInfoMsgEmpListService.saveList(gridList, user);
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
}