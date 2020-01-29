package dream.work.let.categ.action;


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
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.form.WorkLetCategListForm;
import dream.work.let.categ.service.WorkLetCategListService;

/**
 * �����۾����� ����Ʈ ������ - List Action
 * 
 * @author euna0207
 * @version $Id: WorkLetCategListAction.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/workLetCategList" name="workLetCategListForm"
 *                input="/dream/work/let/categ/workLetCategList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetCategList" path="/dream/work/let/categ/workLetCategList.jsp"
 *                        redirect="false"

 */

public class WorkLetCategListAction extends AuthAction
{
    /** ��ȸ */
    public static final int LIST_FIND 		= 1001;
    /** ���� */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        //* jsp form (form-bean���� �����ص� ���ν�Ų form�� �ҷ���)
        WorkLetCategListForm workLetCategListForm = (WorkLetCategListForm) form;
        
        //super.updateAudit(workLetCategListForm.getWorkLetCategCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetCategListForm.getStrutsAction())
        {
            case WorkLetCategListAction.BASE_SET_HEADER:
                setHeader(request, response, workLetCategListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetCategListAction.LIST_FIND:
                findList(request, response, workLetCategListForm, false);
                //findForward�޼ҵ�� struts-config.xml���� ������ page�� �������ϰ� �Ǿ��ִ�
                //Global Forward Definition.
                //struts-config�� ������, name=jsonPage�� ���� path�� �����Ǿ��ִ�. 
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkLetCategListAction.LIST_DELETE:
            	deleteList(request, workLetCategListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
                //BaseAction�� ����Ǿ�����. ���� 
                //�ش� ���� LIST�� DAOŸ�� �ҷ��� ��, gridExport���� �׸� ���� ��, POI ���̺귯������ ���� �ٿ�ε� ó��
            case WorkLetCategListAction.BASE_GRID_EXPORT:
            	findList(request, response, workLetCategListForm,true);
            							//�����Ǿ��ִ� servlet ����
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
            	//getInputForward :  struts-config�� action�±׿� �����ص� <forward>�� ������ ������ ������ 
                //returnActionForward = mapping.findForward("workLetCategList");
                break;
        }
        // action�� return���� success ó���� �Ǹ� forward�±��� action���� �´� JSP�� �̵��ϰԲ� �����صξ��� (struts-config����)
        // <action>�±׿� ���� <forward>�� findForward������ <forward>�±׿� ����
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkLetCategListForm workLetCategListForm) throws IOException
    {
        super.setHeader(request, response, workLetCategListForm.getListId(), workLetCategListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workLetCategListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkLetCategListForm workLetCategListForm, boolean excelExport) throws Exception
    {
    	//applicationContext-txbn.xml�� ����ִ� Bean�� �� service bean�����°�
    	//getBean�޼���� ObjectŸ���̹Ƿ� Ÿ�� ��ȯ ������
    	WorkLetCategListService workLetCategListService = (WorkLetCategListService) getBean("workLetCategListService");
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategListForm.getWorkLetCategCommonDTO();

    	//BaseDTO
    	//��ũ�� load max��
    	workLetCategCommonDTO.setIsLoadMaxCount("Y".equals(workLetCategListForm.getIsLoadMaxCount())?true:false);
    	//���� ��ũ��ó���� ������ �� ���� load max�� �����;��ϹǷ� ù �� �˾ƾ��Ѵ�.
    	workLetCategCommonDTO.setFirstRow(workLetCategListForm.getFirstRow());
    	//����
    	workLetCategCommonDTO.setOrderBy(workLetCategListForm.getOrderBy());
    	//ascending, descending
    	workLetCategCommonDTO.setDirection(workLetCategListForm.getDirection());
    	
    	User user = getUser(request);
        List resultList = workLetCategListService.findList(workLetCategCommonDTO, user);

        //Paging 
        String totalCount = "";
        //gird list totalCount ó��
        if(Integer.parseInt(
        		workLetCategListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workLetCategListService.findTotalCount(workLetCategCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workLetCategListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workLetCategListForm
     */
    private void deleteList(HttpServletRequest request, WorkLetCategListForm workLetCategListForm) throws Exception
    {
    	WorkLetCategListService workLetCategListService = (WorkLetCategListService) getBean("workLetCategListService");
        String[] deleteRows = workLetCategListForm.getDeleteRows();
        
    	User user = getUser(request);
        workLetCategListService.deleteList(deleteRows, user);
        // AJAX ó�� ���¸� ����
        setAjaxStatus(request);
    }
    
}
