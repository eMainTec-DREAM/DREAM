package dream.pers.appr.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.pers.appr.dto.PersApprHistCommonDTO;
import dream.pers.appr.form.PersApprHistListForm;
import dream.pers.appr.service.PersApprHistListService;

/**
 * �����̷� - List Action
 * 
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/persApprHistList" name="persApprHistListForm"
 *                input="/dream/pers/appr/persApprHistList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/persReqHistList" name="persApprHistListForm"
 *                input="/dream/pers/appr/persReqHistList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/persApprovedHistList" name="persApprHistListForm"
 *                input="/dream/pers/appr/persApprovedHistList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persApprHistList" path="/dream/pers/appr/persApprHistList.jsp"
 *                        redirect="false"
 */

public class PersApprHistListAction extends AuthAction
{
	//�Ϲ� ������ ����� AuthAction ���� �����ؾ��մϴ�.
    /** ��ȸ */
    public static final int LIST_FIND 		= 1001;
    /** ���� */
    public static final int LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PersApprHistListForm persApprHistListForm = (PersApprHistListForm) form;
        
        switch (persApprHistListForm.getStrutsAction())
        {
            case PersApprHistListAction.BASE_SET_HEADER:
                setHeader(request, response, persApprHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersApprHistListAction.LIST_FIND:
                findList(request, response, persApprHistListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PersApprHistListAction.LIST_DELETE:
            	deleteList(request, persApprHistListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case PersApprHistListAction.BASE_GRID_EXPORT:
            	findList(request, response, persApprHistListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PersApprHistListForm persApprHistListForm) throws IOException
    {
        super.setHeader(request, response, persApprHistListForm.getListId(), persApprHistListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param persApprHistListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, PersApprHistListForm persApprHistListForm, boolean excelExport) throws Exception
    {
    	PersApprHistListService persApprHistListService = (PersApprHistListService) getBean("persApprHistListService");
    	PersApprHistCommonDTO persApprHistCommonDTO = persApprHistListForm.getPersApprHistCommonDTO();

    	//Paging
    	persApprHistCommonDTO.setIsLoadMaxCount("Y".equals(persApprHistListForm.getIsLoadMaxCount())?true:false);
    	persApprHistCommonDTO.setFirstRow(persApprHistListForm.getFirstRow());
    	persApprHistCommonDTO.setOrderBy(persApprHistListForm.getOrderBy());
    	persApprHistCommonDTO.setDirection(persApprHistListForm.getDirection());
    	
    	//����Ʈ ���������� user��ü�� ȸ���ڵ尡 ��������ʽ��ϴ�. �� �������� ������ �������̱� ������ ���⿡�� ȸ���ڵ带 ���Ƿ� �����߽��ϴ�.
    	User user = getUser(request);
    	
        List resultList = persApprHistListService.findApprHistList(persApprHistCommonDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(persApprHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = persApprHistListService.findTotalCount(persApprHistCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,persApprHistListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param persApprHistListForm
     */
    private void deleteList(HttpServletRequest request, PersApprHistListForm persApprHistListForm) throws Exception
    {
    	PersApprHistListService persApprHistListService = (PersApprHistListService) getBean("persApprHistListService");
        String[] deleteRows = persApprHistListForm.getDeleteRows();
        
        //����Ʈ ���������� user��ü�� ȸ���ڵ尡 ��������ʽ��ϴ�. �� �������� ������ �������̱� ������ ���⿡�� ȸ���ڵ带 ���Ƿ� �����߽��ϴ�.
    	User user = getUser(request);
        
        persApprHistListService.deletePgmGuideList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
