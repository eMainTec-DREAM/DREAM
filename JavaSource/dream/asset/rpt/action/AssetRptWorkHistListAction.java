package dream.asset.rpt.action;


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
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
import dream.asset.rpt.form.AssetRptWorkHistListForm;
import dream.asset.rpt.service.AssetRptWorkHistListService;

/**
 * �����̷�(����) - List Action
 * 
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/assetRptWorkHistList" name="assetRptWorkHistListForm"
 *                input="/dream/asset/rpt/assetRptWorkHistList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetRptWorkHistList" path="/dream/asset/rpt/assetRptWorkHistList.jsp"
 *                        redirect="false"
 */

public class AssetRptWorkHistListAction extends AuthAction
{
	//�Ϲ� ������ ����� AuthAction ���� �����ؾ��մϴ�
    /** ��ȸ */
    public static final int LIST_FIND 		= 1001;
    /** ���� */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptWorkHistListForm assetRptWorkHistListForm = (AssetRptWorkHistListForm) form;

        switch (assetRptWorkHistListForm.getStrutsAction())
        {
            case AssetRptWorkHistListAction.BASE_SET_HEADER:
                setHeader(request, response, assetRptWorkHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptWorkHistListAction.LIST_FIND:
                findList(request, response, assetRptWorkHistListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetRptWorkHistListAction.LIST_DELETE:
            	deleteList(request, assetRptWorkHistListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssetRptWorkHistListAction.BASE_GRID_EXPORT:
            	findList(request, response, assetRptWorkHistListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assetRptWorkHistList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetRptWorkHistListForm assetRptWorkHistListForm) throws IOException
    {
        super.setHeader(request, response, assetRptWorkHistListForm.getListId(), assetRptWorkHistListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetRptWorkHistListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetRptWorkHistListForm assetRptWorkHistListForm, boolean excelExport) throws Exception
    {
    	AssetRptWorkHistListService assetRptWorkHistListService = (AssetRptWorkHistListService) getBean("assetRptWorkHistListService");
    	AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO = assetRptWorkHistListForm.getAssetRptWorkHistCommonDTO();

    	//Paging
    	assetRptWorkHistCommonDTO.setIsLoadMaxCount("Y".equals(assetRptWorkHistListForm.getIsLoadMaxCount())?true:false);
    	assetRptWorkHistCommonDTO.setFirstRow(assetRptWorkHistListForm.getFirstRow());
    	assetRptWorkHistCommonDTO.setOrderBy(assetRptWorkHistListForm.getOrderBy());
    	assetRptWorkHistCommonDTO.setDirection(assetRptWorkHistListForm.getDirection());
    	
    	//����Ʈ ���������� user��ü�� ȸ���ڵ尡 ��������ʽ��ϴ�. �� �������� ������ �������̱� ������ ���⿡�� ȸ���ڵ带 ���Ƿ� �����߽��ϴ�.
    	User user = getUser(request);
    	
        List resultList = assetRptWorkHistListService.findRptWorkHistList(assetRptWorkHistCommonDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(assetRptWorkHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptWorkHistListService.findTotalCount(assetRptWorkHistCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetRptWorkHistListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetRptWorkHistListForm
     */
    private void deleteList(HttpServletRequest request, AssetRptWorkHistListForm assetRptWorkHistListForm) throws Exception
    {
    	AssetRptWorkHistListService assetRptWorkHistListService = (AssetRptWorkHistListService) getBean("assetRptWorkHistListService");
        String[] deleteRows = assetRptWorkHistListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        assetRptWorkHistListService.deleteRptWorkHistList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
