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
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.dto.MaWoResultPartListDTO;
import dream.work.list.form.MaWoResultPartListForm;
import dream.work.list.service.MaWoResultPartListService;

/**
 * 작업결과-투입자재 목록
 * @author  kim21017
 * @version $Id: MaWoResultPartListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultPartList" name="maWoResultPartListForm"
 *                input="/dream/work/list/maWoResultPartList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultMonthPartList" name="maWoResultPartListForm"
 *                input="/dream/work/list/month/maWoResultMonthPartList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRplPartList" name="maWoResultPartListForm"
 *                input="/dream/work/list/pmw/maWoResultPmRplPartList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRplPartList" name="maWoResultPartListForm"
 *                input="/dream/work/list/bm/maWoResultBmRplPartList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmOilPartList" name="maWoResultPartListForm"
 *                input="/dream/work/list/bm/maWoResultBmOilPartList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRplPartList" name="maWoResultPartListForm"
 *                input="/dream/work/list/cm/maWoResultCmRplPartList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultPartList" path="/dream/work/list/maWoResultPartList.jsp"
 *                        redirect="false"
 */
public class MaWoResultPartListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_PART_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_RESULT_PART_LIST_DELETE 			= 7001;
    /** 출고처리부품 입력 */
    public static final int WO_RESULT_ISS_PART_LIST_INPUT       = 5001;
    /** 부품 입력 */
    public static final int WO_RESULT_PART_LIST_INPUT           = 5002;
    /** 출고 입력 */
    public static final int WO_RESULT_ISS_LIST_INPUT            = 5004;
    /** LIST 저장  */
    public static final int EDIT_LIST_SAVE 						= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultPartListForm maWoResultPartListForm = (MaWoResultPartListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultPartListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (maWoResultPartListForm.getStrutsAction())
        {
        
            case MaWoResultPartListAction.WO_RESULT_PART_LIST_FIND:
                findPartList(request,response, maWoResultPartListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultPartListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maWoResultPartListForm.getListId(), maWoResultPartListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultPartListAction.WO_RESULT_PART_LIST_DELETE:
            	deletePartList(request,maWoResultPartListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultPartListAction.WO_RESULT_PART_LIST_INPUT:
                inputPartList(request,maWoResultPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoResultPartListAction.WO_RESULT_ISS_PART_LIST_INPUT:
                inputIssPartList(request,maWoResultPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoResultPartListAction.WO_RESULT_ISS_LIST_INPUT:
                inputPtIssList(request,maWoResultPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoResultPartListAction.EDIT_LIST_SAVE:
            	saveList(request,response,maWoResultPartListForm);
                returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaWoResultPartListAction.BASE_GRID_EXPORT:
            	findPartList(request,response, maWoResultPartListForm, true);
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
     * @version $Id: MaWoResultPartListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultPartListForm
     * @throws Exception
     */
    private void findPartList(HttpServletRequest request,HttpServletResponse response, MaWoResultPartListForm maWoResultPartListForm, boolean excelExport) throws Exception
    {
        MaWoResultPartListService maWoResultPartListService = (MaWoResultPartListService) getBean("maWoResultPartListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultPartListForm.getMaWoResultMstrCommonDTO();
        MaWoResultPartListDTO maWoResultPartListDTO = maWoResultPartListForm.getMaWoResultPartListDTO();
        
        //Paging
        maWoResultPartListDTO.setIsLoadMaxCount("Y".equals(maWoResultPartListForm.getIsLoadMaxCount())?true:false);
        maWoResultPartListDTO.setFirstRow(maWoResultPartListForm.getFirstRow());
        maWoResultPartListDTO.setOrderBy(maWoResultPartListForm.getOrderBy());
        maWoResultPartListDTO.setDirection(maWoResultPartListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maWoResultPartListService.findPartList(maWoResultMstrCommonDTO,maWoResultPartListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoResultPartListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultPartListService.findTotalCount(maWoResultMstrCommonDTO, maWoResultPartListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultPartListForm.getListId(),maWoResultPartListForm.getCurrentPageId(), maWoResultPartListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaWoResultPartListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultPartListForm
     * @throws Exception
     */
    private void deletePartList(HttpServletRequest request, MaWoResultPartListForm maWoResultPartListForm) throws Exception
    {
    	MaWoResultPartListService maWoResultPartListService = (MaWoResultPartListService) getBean("maWoResultPartListService");
        
    	String[] deleteRows = maWoResultPartListForm.getDeleteRows();
    
    	maWoResultPartListService.deletePartList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoResultPartListForm
     * @throws Exception
     */
    private void inputPartList(HttpServletRequest request, MaWoResultPartListForm maWoResultPartListForm) throws Exception
    {
        MaWoResultPartListService maWoResultPartListService = (MaWoResultPartListService) getBean("maWoResultPartListService");
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultPartListForm.getMaWoResultMstrCommonDTO();
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = maWoResultPartListForm.getMaWoResultPartDetailDTO();

        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultPartListService.inputPartList(maWoResultPartDetailDTO, maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoResultPartListForm
     * @throws Exception
     */
    private void inputIssPartList(HttpServletRequest request, MaWoResultPartListForm maWoResultPartListForm) throws Exception
    {
        MaWoResultPartListService maWoResultPartListService = (MaWoResultPartListService) getBean("maWoResultPartListService");
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultPartListForm.getMaWoResultMstrCommonDTO();
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = maWoResultPartListForm.getMaWoResultPartDetailDTO();

        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultPartListService.inputIssPartList(maWoResultPartDetailDTO, maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void inputPtIssList(HttpServletRequest request, MaWoResultPartListForm maWoResultPartListForm) throws Exception
    {
        MaWoResultPartListService maWoResultPartListService = (MaWoResultPartListService) getBean("maWoResultPartListService");
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultPartListForm.getMaWoResultMstrCommonDTO();
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = maWoResultPartListForm.getMaWoResultPartDetailDTO();
        maWoResultPartDetailDTO.setWkOrId(maWoResultMstrCommonDTO.getWkOrId());
        
        maWoResultPartListService.inputPtIssList(maWoResultPartDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void saveList(HttpServletRequest request,HttpServletResponse response, MaWoResultPartListForm maWoResultPartListForm) throws Exception
    {
        MaWoResultPartListService maWoResultPartListService = (MaWoResultPartListService) getBean("maWoResultPartListService");

        List<Map> gridList = CommonUtil.setGridMap(request);

        maWoResultPartListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
    
}