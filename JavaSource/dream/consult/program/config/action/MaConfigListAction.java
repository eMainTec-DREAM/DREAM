package dream.consult.program.config.action;


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
import dream.consult.program.config.dto.MaConfigCommonDTO;
import dream.consult.program.config.form.MaConfigListForm;
import dream.consult.program.config.service.MaConfigListService;

/**
 * 시스템 환경변수 - 목록 action
 * @author  kim21017
 * @version $Id: MaConfigListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maConfigList" name="maConfigListForm"
 *                input="/dream/consult/program/config/maConfigList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maConfigList" path="/dream/consult/program/config/maConfigList.jsp"
 *                        redirect="false"
 */
public class MaConfigListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int CONFIG_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int CONFIG_LIST_DELETE	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaConfigListForm maConfigListForm = (MaConfigListForm) form;
        
        switch (maConfigListForm.getStrutsAction())
        {
            case MaConfigListAction.CONFIG_LIST_FIND:
                findConfigList(request, maConfigListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaConfigListAction.BASE_SET_HEADER:
                setHeader(request, response, maConfigListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaConfigListAction.CONFIG_LIST_DELETE:
                deleteConfig(request, maConfigListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaConfigListAction.BASE_GRID_EXPORT:
            	findConfigList(request, maConfigListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maConfigList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaConfigListForm maConfigListForm) throws IOException
    {
        super.setHeader(request, response, maConfigListForm.getListId(),maConfigListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaConfigListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maConfigListForm
     * @throws Exception
     */
    private void findConfigList(HttpServletRequest request, MaConfigListForm maConfigListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaConfigListService maConfigListService = (MaConfigListService) getBean("maConfigListService");        

    	MaConfigCommonDTO maConfigCommonDTO = maConfigListForm.getMaConfigCommonDTO();
    	
    	maConfigCommonDTO.setIsLoadMaxCount("Y".equals(maConfigListForm.getIsLoadMaxCount())?true:false);
    	maConfigCommonDTO.setFirstRow(maConfigListForm.getFirstRow());
    	maConfigCommonDTO.setOrderBy(maConfigListForm.getOrderBy());
    	maConfigCommonDTO.setDirection(maConfigListForm.getDirection());
    	
    	User user = getUser(request);
    	
    	//리스트를 조회한다.
        List resultList = maConfigListService.findConfigList(maConfigCommonDTO, getUser(request));
        
        String totalCount = "";
        
        if(Integer.parseInt(maConfigListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maConfigListService.findTotalCount(maConfigCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,maConfigListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    	
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaConfigListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigListForm
     * @param request
     */
    private void deleteConfig(HttpServletRequest request, MaConfigListForm maConfigListForm) throws Exception
    {
    	MaConfigListService maConfigListService = (MaConfigListService) getBean("maConfigListService");
        
    	String[] configIdRows = maConfigListForm.getDeleteRows();    // sheet 내역
        
        maConfigListService.deleteConfig(configIdRows,  getUser(request));
        
        setAjaxStatus(request);
    }
}
