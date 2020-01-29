package dream.ass.asset.action;

import java.io.IOException;
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
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;
import dream.ass.asset.form.AssAssetScoreListForm;
import dream.ass.asset.service.AssAssetScoreListService;

/**
 * AssAssetScore Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: AssAssetScoreListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/assAssetScoreList" name="assAssetScoreListForm"
 *                input="/dream/ass/asset/assAssetScoreList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assAssetTlScoreList" name="assAssetScoreListForm"
 *                input="/dream/ass/asset/assAssetTlScoreList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assAssetScoreList" path="/dream/ass/asset/assAssetScoreList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="assAssetTlScoreList" path="/dream/ass/asset/assAssetTlScoreList.jsp"
 *                        redirect="false"
 */
public class AssAssetScoreListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 7002;
    /** LIST 저장 */
    public static final int LIST_SAVE     	= 1003;
    /** 평가점수복사 */
    public static final int LIST_UPDATE		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssAssetScoreListForm assAssetScoreListForm = (AssAssetScoreListForm) form;
        
        //super.updateAudit(assAssetScoreListForm.getAssAssetScoreListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assAssetScoreListForm.getStrutsAction())
        {
            case AssAssetScoreListAction.BASE_SET_HEADER:
                setHeader(request, response, assAssetScoreListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssAssetScoreListAction.LIST_FIND:
                findList(request, response, assAssetScoreListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssAssetScoreListAction.LIST_SAVE:
            	saveList(request, response, assAssetScoreListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AssAssetScoreListAction.LIST_UPDATE:
            	updateList(request, response, assAssetScoreListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AssAssetScoreListAction.LIST_DELETE:
                deleteList(request, assAssetScoreListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssAssetScoreListAction.BASE_GRID_EXPORT:
                findList(request, response, assAssetScoreListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssAssetScoreListForm assAssetScoreListForm) throws IOException
    {
        super.setHeader(request, response, assAssetScoreListForm.getListId(), assAssetScoreListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assAssetScoreListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssAssetScoreListForm assAssetScoreListForm, boolean excelExport) throws Exception
    {
        AssAssetScoreListService assAssetScoreListService = (AssAssetScoreListService) getBean("assAssetScoreListService");
        AssAssetScoreListDTO assAssetScoreListDTO = assAssetScoreListForm.getAssAssetScoreListDTO();
        AssAssetCommonDTO assAssetCommonDTO = assAssetScoreListForm.getAssAssetCommonDTO();
      
        //Paging
        assAssetCommonDTO.setIsLoadMaxCount("Y".equals(assAssetScoreListForm.getIsLoadMaxCount())?true:false);
        assAssetCommonDTO.setFirstRow(assAssetScoreListForm.getFirstRow());
        assAssetCommonDTO.setOrderBy(assAssetScoreListForm.getOrderBy());
        assAssetCommonDTO.setDirection(assAssetScoreListForm.getDirection());
        
        User user = getUser(request);
        List resultList = assAssetScoreListService.findList(assAssetCommonDTO, assAssetScoreListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assAssetScoreListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assAssetScoreListService.findTotalCount(assAssetCommonDTO,assAssetScoreListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,assAssetScoreListForm.getListId(),assAssetScoreListForm.getCurrentPageId(), assAssetScoreListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void saveList(HttpServletRequest request, HttpServletResponse response, AssAssetScoreListForm assAssetScoreListForm) throws Exception
    {
    	AssAssetScoreListService assAssetScoreListService = (AssAssetScoreListService) getBean("assAssetScoreListService");

    	List<Map> gridList = CommonUtil.setGridMap(request);
    	
    	User user = getUser(request);
    	assAssetScoreListService.saveList(gridList, user);
    	
    	JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assAssetScoreListForm
     */
    private void deleteList(HttpServletRequest request, AssAssetScoreListForm assAssetScoreListForm) throws Exception
    {
        AssAssetScoreListService assAssetScoreListService = (AssAssetScoreListService) getBean("assAssetScoreListService");
        String[] deleteRows = assAssetScoreListForm.getDeleteRows();
        
        User user = getUser(request);
        assAssetScoreListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    /**
     * 평가점수복사
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assAssetScoreListForm
     * @throws Exception
     */
    private void updateList(HttpServletRequest request, HttpServletResponse response, AssAssetScoreListForm assAssetScoreListForm) throws Exception
    {
    	AssAssetScoreListService assAssetScoreListService = (AssAssetScoreListService) getBean("assAssetScoreListService");
        
    	AssAssetCommonDTO assAssetCommonDTO = assAssetScoreListForm.getAssAssetCommonDTO();
    	AssAssetScoreListDTO assAssetScoreListDTO = assAssetScoreListForm.getAssAssetScoreListDTO();
        
    	assAssetScoreListService.updateList(assAssetCommonDTO, assAssetScoreListDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    
}