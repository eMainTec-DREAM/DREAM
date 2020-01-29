package dream.pers.priv.notice.action;

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
import dream.pers.priv.notice.form.PersPrivNoticeListForm;
import dream.pers.priv.notice.service.PersPrivNoticeListService;

/**
 * Notice 설정 - 목록 Action
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 * @struts:action path="/persPrivNoticeList" name="persPrivNoticeListForm"
 *                input="/dream/pers/priv/notice/persPrivNoticeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivNoticeList" path="/dream/pers/priv/notice/persPrivNoticeList.jsp"
 *                        redirect="false"
 */
public class PersPrivNoticeListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** EDIT */
    public static final int LIST_SAVE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PersPrivNoticeListForm persPrivNoticeListForm = (PersPrivNoticeListForm) form;
        
        switch (persPrivNoticeListForm.getStrutsAction())
        {
        
            case PersPrivNoticeListAction.LIST_FIND:
                findList(request, response, persPrivNoticeListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivNoticeListAction.BASE_GRID_EXPORT:
            	findList(request, response, persPrivNoticeListForm, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case PersPrivNoticeListAction.LIST_SAVE:
            	saveList(request, response, persPrivNoticeListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivNoticeListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, persPrivNoticeListForm.getListId(), persPrivNoticeListForm.getCurrentPageId());
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param persPrivNoticeListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, PersPrivNoticeListForm persPrivNoticeListForm, boolean excelExport) throws Exception
    {
        PersPrivNoticeListService persPrivNoticeListService = (PersPrivNoticeListService) getBean("persPrivNoticeListService");
        MaMyInfoCommonDTO maMyInfoCommonDTO = persPrivNoticeListForm.getMaMyInfoCommonDTO();
        
        //Paging
        maMyInfoCommonDTO.setIsLoadMaxCount("Y".equals(persPrivNoticeListForm.getIsLoadMaxCount())?true:false);
        maMyInfoCommonDTO.setFirstRow(persPrivNoticeListForm.getFirstRow());
        maMyInfoCommonDTO.setOrderBy(persPrivNoticeListForm.getOrderBy());
        maMyInfoCommonDTO.setDirection(persPrivNoticeListForm.getDirection());
        
        //Paging
        String totalCount = "";
        
        User user = getUser(request);
        
        List resultList = persPrivNoticeListService.findList(maMyInfoCommonDTO, user);
        
        if(Integer.parseInt(persPrivNoticeListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = persPrivNoticeListService.findTotalCount(maMyInfoCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, persPrivNoticeListForm);
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
 
    /**
     * List Save
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param persPrivNoticeListForm
     * @throws Exception
     */
    private void saveList(HttpServletRequest request, HttpServletResponse response, PersPrivNoticeListForm persPrivNoticeListForm) throws Exception
    {
    	PersPrivNoticeListService persPrivNoticeListService = (PersPrivNoticeListService) getBean("persPrivNoticeListService");
    	List<Map> gridList = CommonUtil.setGridMap(request);

    	User user = getUser(request);
    	
    	persPrivNoticeListService.saveList(gridList, user);
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
}