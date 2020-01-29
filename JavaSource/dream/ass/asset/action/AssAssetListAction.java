package dream.ass.asset.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.form.AssAssetListForm;
import dream.ass.asset.service.AssAssetListService;

/**
 * AssAsset Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/assAssetList" name="assAssetListForm"
 *                input="/dream/ass/asset/assAssetList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assAssetTlList" name="assAssetListForm"
 *                input="/dream/ass/asset/assAssetTlList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assAssetList" path="/dream/ass/asset/assAssetList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="assAssetTlList" path="/dream/ass/asset/assAssetTlList.jsp"
 *                        redirect="false"
 */
public class AssAssetListAction extends AuthAction
{
    /** 조회하기 */
    public static final int LIST_FIND       	= 1001;
    /** 삭제하기 */
    public static final int LIST_DELETE     	= 7002;
    /** 계획투자생성 */
    public static final int CREATE_INVT_LIST	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssAssetListForm assAssetListForm = (AssAssetListForm) form;

        super.updateAudit(assAssetListForm.getAssAssetCommonDTO().getAuditKey()==""?assAssetListForm.getAssAssetCommonDTO().getAuditKey():assAssetListForm.getAssAssetCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assAssetListForm.getStrutsAction())
        {
            case AssAssetListAction.BASE_SET_HEADER:
                setHeader(request, response, assAssetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssAssetListAction.LIST_FIND:
                findList(request, response, assAssetListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssAssetListAction.LIST_DELETE:
                deleteList(request, assAssetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssAssetListAction.CREATE_INVT_LIST:
            	createInvtList(request, assAssetListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;       
            case AssAssetListAction.BASE_GRID_EXPORT:
                findList(request, response, assAssetListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssAssetListForm assAssetListForm) throws IOException
    {
        super.setHeader(request, response, assAssetListForm.getListId(), assAssetListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assAssetListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssAssetListForm assAssetListForm, boolean excelExport) throws Exception
    {
        AssAssetListService assAssetListService = (AssAssetListService) getBean("assAssetListService",request);
        AssAssetCommonDTO assAssetCommonDTO = assAssetListForm.getAssAssetCommonDTO();
      
        //Paging
        assAssetCommonDTO.setIsLoadMaxCount("Y".equals(assAssetListForm.getIsLoadMaxCount())?true:false);
        assAssetCommonDTO.setFirstRow(assAssetListForm.getFirstRow());
        assAssetCommonDTO.setOrderBy(assAssetListForm.getOrderBy());
        assAssetCommonDTO.setDirection(assAssetListForm.getDirection());
        
        User user = getUser(request);
        List resultList = assAssetListService.findList(assAssetCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assAssetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assAssetListService.findTotalCount(assAssetCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, assAssetListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assAssetListForm
     */
    private void deleteList(HttpServletRequest request, AssAssetListForm assAssetListForm) throws Exception
    {
        AssAssetListService assAssetListService = (AssAssetListService) getBean("assAssetListService",request);
        String[] deleteRows = assAssetListForm.getDeleteRows();
        
        User user = getUser(request);
        assAssetListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    private void createInvtList(HttpServletRequest request, AssAssetListForm assAssetListForm) throws Exception
    {
    	AssAssetListService assAssetListService = (AssAssetListService) getBean("assAssetListService",request);
    	AssAssetCommonDTO assAssetCommonDTO = assAssetListForm.getAssAssetCommonDTO();
    	String[] selectRows = assAssetListForm.getSelectRows();
    	
    	User user = getUser(request);
    	assAssetListService.createInvtList(selectRows, assAssetCommonDTO, user);
    	setAjaxStatus(request);
    }
    
    
}