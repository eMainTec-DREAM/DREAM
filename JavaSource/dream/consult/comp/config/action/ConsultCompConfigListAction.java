package dream.consult.comp.config.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;
import dream.consult.comp.config.form.ConsultCompConfigListForm;
import dream.consult.comp.config.service.ConsultCompConfigListService;

/**
 * 시스템 환경변수 - 목록 action
 * @author  syyang
 * @version $Id: ConsultCompConfigListAction.java,v 1.0 2015/12/02 09:10:21 syyang Exp $
 * @since   1.0
 * @struts:action path="/consultCompConfigList" name="consultCompConfigListForm"
 *                input="/dream/consult/comp/config/consultCompConfigList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompConfigList" path="/dream/consult/comp/config/consultCompConfigList.jsp"
 *                        redirect="false"
 */
public class ConsultCompConfigListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int CONFIG_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int CONFIG_LIST_DELETE	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompConfigListForm consultCompConfigListForm = (ConsultCompConfigListForm) form;
        
        switch (consultCompConfigListForm.getStrutsAction())
        {
            case ConsultCompConfigListAction.CONFIG_LIST_FIND:
                findConfigList(request, consultCompConfigListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompConfigListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompConfigListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompConfigListAction.CONFIG_LIST_DELETE:
                deleteConfig(request, consultCompConfigListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultCompConfigListAction.BASE_GRID_EXPORT:
            	findConfigList(request, consultCompConfigListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompConfigList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompConfigListForm consultCompConfigListForm) throws IOException
    {
        super.setHeader(request, response, consultCompConfigListForm.getListId(),consultCompConfigListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: ConsultCompConfigListAction.java,v 1.0 2015/12/02 09:10:21 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param consultCompConfigListForm
     * @throws Exception
     */
    private void findConfigList(HttpServletRequest request, ConsultCompConfigListForm consultCompConfigListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	ConsultCompConfigListService consultCompConfigListService = (ConsultCompConfigListService) getBean("consultCompConfigListService");        

    	ConsultCompConfigCommonDTO consultCompConfigCommonDTO = consultCompConfigListForm.getConsultCompConfigCommonDTO();
    	
        //Paging
        consultCompConfigCommonDTO.setIsLoadMaxCount("Y".equals(consultCompConfigListForm.getIsLoadMaxCount())?true:false);
        consultCompConfigCommonDTO.setFirstRow(consultCompConfigListForm.getFirstRow());
        consultCompConfigCommonDTO.setOrderBy(consultCompConfigListForm.getOrderBy());
        consultCompConfigCommonDTO.setDirection(consultCompConfigListForm.getDirection());
        
        User user = getUser(request);

        //리스트를 조회한다.
        List resultList = consultCompConfigListService.findConfigList(consultCompConfigCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultCompConfigListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompConfigListService.findTotalCount(consultCompConfigCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultCompConfigListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    	
    }
    /**
     * delete
     * @author  syyang
     * @version $Id: ConsultCompConfigListAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigListForm
     * @param request
     */
    private void deleteConfig(HttpServletRequest request, ConsultCompConfigListForm consultCompConfigListForm) throws Exception
    {
    	ConsultCompConfigListService consultCompConfigListService = (ConsultCompConfigListService) getBean("consultCompConfigListService");
        
    	String[] configIdRows = consultCompConfigListForm.getDeleteRows();    // sheet 내역
    	String[] compNoRows = consultCompConfigListForm.getDeleteRowsExt();    // sheet 내역
        
        consultCompConfigListService.deleteConfig(configIdRows,  compNoRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
