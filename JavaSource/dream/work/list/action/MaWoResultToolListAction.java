package dream.work.list.action;

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
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;
import dream.work.list.dto.MaWoResultToolListDTO;
import dream.work.list.form.MaWoResultToolListForm;
import dream.work.list.service.MaWoResultToolListService;

/**
 * 작업결과-투입공기구 목록
 * @author  kim21017
 * @version $Id: MaWoResultToolListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultToolList" name="maWoResultToolListForm"
 *                input="/dream/work/list/maWoResultToolList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRplToolList" name="maWoResultToolListForm"
 *                input="/dream/work/list/bm/maWoResultBmRplToolList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmOilToolList" name="maWoResultToolListForm"
 *                input="/dream/work/list/bm/maWoResultBmOilToolList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultToolList" path="/dream/work/list/maWoResultToolList.jsp"
 *                        redirect="false"
 */
public class MaWoResultToolListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_TOOL_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_RESULT_TOOL_LIST_DELETE 			= 7001;
    /** 부품 입력 */
    public static final int WO_RESULT_TOOL_LIST_INPUT           = 5002;
    /** LIST 저장  */
    public static final int EDIT_LIST_SAVE 						= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultToolListForm maWoResultToolListForm = (MaWoResultToolListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultToolListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (maWoResultToolListForm.getStrutsAction())
        {
        
            case MaWoResultToolListAction.WO_RESULT_TOOL_LIST_FIND:
                findToolList(request,response, maWoResultToolListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultToolListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maWoResultToolListForm.getListId(), maWoResultToolListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultToolListAction.WO_RESULT_TOOL_LIST_DELETE:
            	deleteToolList(request,maWoResultToolListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultToolListAction.WO_RESULT_TOOL_LIST_INPUT:
                inputToolList(request,maWoResultToolListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoResultToolListAction.EDIT_LIST_SAVE:
            	saveList(request,response,maWoResultToolListForm);
                returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaWoResultToolListAction.BASE_GRID_EXPORT:
            	findToolList(request,response, maWoResultToolListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoResultToolListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultToolListForm
     * @throws Exception
     */
    private void findToolList(HttpServletRequest request,HttpServletResponse response, MaWoResultToolListForm maWoResultToolListForm, boolean excelExport) throws Exception
    {
        MaWoResultToolListService maWoResultToolListService = (MaWoResultToolListService) getBean("maWoResultToolListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultToolListForm.getMaWoResultMstrCommonDTO();
        MaWoResultToolListDTO maWoResultToolListDTO = maWoResultToolListForm.getMaWoResultToolListDTO();
        
        //Paging
        maWoResultToolListDTO.setIsLoadMaxCount("Y".equals(maWoResultToolListForm.getIsLoadMaxCount())?true:false);
        maWoResultToolListDTO.setFirstRow(maWoResultToolListForm.getFirstRow());
        maWoResultToolListDTO.setOrderBy(maWoResultToolListForm.getOrderBy());
        maWoResultToolListDTO.setDirection(maWoResultToolListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maWoResultToolListService.findToolList(maWoResultMstrCommonDTO,maWoResultToolListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoResultToolListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultToolListService.findTotalCount(maWoResultMstrCommonDTO, maWoResultToolListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultToolListForm.getListId(),maWoResultToolListForm.getCurrentPageId(), maWoResultToolListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaWoResultToolListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultToolListForm
     * @throws Exception
     */
    private void deleteToolList(HttpServletRequest request, MaWoResultToolListForm maWoResultToolListForm) throws Exception
    {
    	MaWoResultToolListService maWoResultToolListService = (MaWoResultToolListService) getBean("maWoResultToolListService");
        
    	String[] deleteRows = maWoResultToolListForm.getDeleteRows();
    
    	maWoResultToolListService.deleteToolList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoResultToolListForm
     * @throws Exception
     */
    private void inputToolList(HttpServletRequest request, MaWoResultToolListForm maWoResultToolListForm) throws Exception
    {
        MaWoResultToolListService maWoResultToolListService = (MaWoResultToolListService) getBean("maWoResultToolListService");
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultToolListForm.getMaWoResultMstrCommonDTO();
        MaWoResultToolDetailDTO maWoResultToolDetailDTO = maWoResultToolListForm.getMaWoResultToolDetailDTO();

        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultToolListService.inputToolList(maWoResultToolDetailDTO, maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    
    private void saveList(HttpServletRequest request,HttpServletResponse response, MaWoResultToolListForm maWoResultToolListForm) throws Exception
    {
        MaWoResultToolListService maWoResultToolListService = (MaWoResultToolListService) getBean("maWoResultToolListService");

        List<Map> gridList = CommonUtil.setGridMap(request);

        maWoResultToolListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
    
}