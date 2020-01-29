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
import dream.work.list.dto.MaWoResultPointListDTO;
import dream.work.list.form.MaWoResultPointListForm;
import dream.work.list.service.MaWoResultPointListService;

/**
 * 작업결과-검사항목 목록
 * @author  kim21017
 * @version $Id: MaWoResultPointListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultPointList" name="maWoResultPointListForm"
 *                input="/dream/work/list/maWoResultPointList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultMonthPointList" name="maWoResultPointListForm"
 *                input="/dream/work/list/month/maWoResultMonthPointList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmInsPointList" name="maWoResultPointListForm"
 *                input="/dream/work/list/pmi/maWoResultPmInsPointList.jsp" scope="request"
 *                validate="false"
 */
public class MaWoResultPointListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_POINT_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_RESULT_POINT_LIST_DELETE 		= 7002;
    /** LIST 저장  */
    public static final int WO_RESULT_POINT_LIST_SAVE 			= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultPointListForm maWoResultPointListForm = (MaWoResultPointListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultPointListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (maWoResultPointListForm.getStrutsAction())
        {
        
            case MaWoResultPointListAction.WO_RESULT_POINT_LIST_FIND:
                findPointList(request,response, maWoResultPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultPointListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maWoResultPointListForm.getListId(), maWoResultPointListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultPointListAction.WO_RESULT_POINT_LIST_DELETE:
            	deletePointList(request,maWoResultPointListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultPointListAction.WO_RESULT_POINT_LIST_SAVE:
                savePointList(request,response, maWoResultPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultPointListAction.BASE_GRID_EXPORT:
            	findPointList(request,response, maWoResultPointListForm, true);
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
     * @version $Id: MaWoResultPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultPointListForm
     * @throws Exception
     */
    private void findPointList(HttpServletRequest request,HttpServletResponse response, MaWoResultPointListForm maWoResultPointListForm, boolean excelExport) throws Exception
    {
        MaWoResultPointListService maWoResultPointListService = (MaWoResultPointListService) getBean("maWoResultPointListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultPointListForm.getMaWoResultMstrCommonDTO();
        MaWoResultPointListDTO maWoResultPointListDTO = maWoResultPointListForm.getMaWoResultPointListDTO();
        
        //Paging
        maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultPointListForm.getIsLoadMaxCount())?true:false);
        maWoResultMstrCommonDTO.setFirstRow(maWoResultPointListForm.getFirstRow());
        maWoResultMstrCommonDTO.setOrderBy(maWoResultPointListForm.getOrderBy());
        maWoResultMstrCommonDTO.setDirection(maWoResultPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maWoResultPointListService.findPointList(maWoResultMstrCommonDTO,maWoResultPointListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoResultPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultPointListService.findTotalCount(maWoResultMstrCommonDTO,maWoResultPointListDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultPointListForm.getListId(),maWoResultPointListForm.getCurrentPageId(), maWoResultPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaWoResultPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultPointListForm
     * @throws Exception
     */
    private void deletePointList(HttpServletRequest request, MaWoResultPointListForm maWoResultPointListForm) throws Exception
    {
    	MaWoResultPointListService maWoResultPointListService = (MaWoResultPointListService) getBean("maWoResultPointListService");
        
    	String[] deleteRows = maWoResultPointListForm.getDeleteRows();
    
    	maWoResultPointListService.deletePointList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    private void savePointList(HttpServletRequest request, HttpServletResponse response, MaWoResultPointListForm maWoResultPointListForm) throws Exception 
    {
    	MaWoResultPointListService maWoResultPointListService = (MaWoResultPointListService) getBean("maWoResultPointListService");
        
        List<Map> gridList = CommonUtil.setGridMap(request);

        maWoResultPointListService.savePointList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
	}
}