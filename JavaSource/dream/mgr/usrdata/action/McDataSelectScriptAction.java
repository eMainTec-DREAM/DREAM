package dream.mgr.usrdata.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.form.McDataSelectScriptForm;
import dream.mgr.usrdata.service.McDataSelectListService;
import dream.mgr.usrdata.service.McDataSelectScriptService;


/**
 * 메뉴 - 목록 action
 * @author  kim21017
 * @version $Id: McDataSelectListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/mcDataSelectScript" name="mcDataSelectScriptForm"
 *                input="/dream/mgr/usrdata/mcDataSelectScript.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcDataSelectScript" path="/dream/mgr/usrdata/mcDataSelectScript.jsp"
 *                        redirect="false"
 */
public class McDataSelectScriptAction extends AuthAction
{
    /** 조회 */
    public static final int DATA_LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int DATA_LIST_DELETE 	= 1002;
    /** 조회 */
    public static final int DATA_SCRIPT_FIND 	= 8003;
    /** 수정 */
    public static final int DATA_SCRIPT_UPDATE 	= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        McDataSelectScriptForm mcDataSelectScriptForm = (McDataSelectScriptForm) form;
        
        super.updateAudit(mcDataSelectScriptForm.getMcDataSelectCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mcDataSelectScriptForm.getStrutsAction())
        {
            case McDataSelectScriptAction.DATA_LIST_FIND:
                findMenuList(request, mcDataSelectScriptForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case McDataSelectScriptAction.BASE_SET_HEADER:
                setHeader(request, response, mcDataSelectScriptForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case McDataSelectScriptAction.DATA_LIST_DELETE:
            	deleteMenu(request, mcDataSelectScriptForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case McDataSelectScriptAction.DATA_SCRIPT_FIND:
            	findScript(request, mcDataSelectScriptForm);
                returnActionForward = mapping.findForward("mcDataSelectScript");
                break;
            case McDataSelectScriptAction.DATA_SCRIPT_UPDATE:
            	updateScript(request, mcDataSelectScriptForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case McDataSelectScriptAction.BASE_GRID_EXPORT:
            	findMenuList(request, mcDataSelectScriptForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mcDataSelectScript");
                break;
        }

        return returnActionForward;
    }

    private void findScript(HttpServletRequest request, McDataSelectScriptForm mcDataSelectScriptForm) {
		// TODO Auto-generated method stub
    	McDataSelectScriptService mcDataSelectScriptService = (McDataSelectScriptService) getBean("mcDataSelectScriptService");        

    	McDataSelectCommonDTO mcDataSelectCommonDTO = mcDataSelectScriptForm.getMcDataSelectCommonDTO();
        // 조회된 상세 부분
    	McDataSelectCommonDTO resultDTO = mcDataSelectScriptService.findScript(mcDataSelectCommonDTO);
        // 조회된 상세  셋팅한다.
        mcDataSelectScriptForm.setMcDataSelectCommonDTO(resultDTO);
	}

	private void updateScript(HttpServletRequest request, McDataSelectScriptForm mcDataSelectScriptForm) 
    {
    	McDataSelectScriptService mcDataSelectScriptService = (McDataSelectScriptService) getBean("mcDataSelectScriptService");        

    	mcDataSelectScriptService.updateScript(mcDataSelectScriptForm.getMcDataSelectCommonDTO(), getUser(request));
        
        setAjaxStatus(request);
	}

    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, McDataSelectScriptForm mcDataSelectScriptForm) throws IOException
    {
        super.setHeader(request, response, mcDataSelectScriptForm.getListId(),mcDataSelectScriptForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: McDataSelectListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mcDataSelectScriptForm
     * @throws Exception
     */
    private void findMenuList(HttpServletRequest request, McDataSelectScriptForm mcDataSelectScriptForm, HttpServletResponse response) throws IOException
    {
    	McDataSelectListService mcDataSelectListService = (McDataSelectListService) getBean("mcDataSelectListService");        

    	McDataSelectCommonDTO mcDataSelectCommonDTO = mcDataSelectScriptForm.getMcDataSelectCommonDTO();

        //리스트를 조회한다.
        List resultList = mcDataSelectListService.findMenuList(mcDataSelectCommonDTO,getUser(request));

        super.makeJsonResult(resultList, request, response, mcDataSelectScriptForm.getListId());
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: McDataSelectListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngListForm
     * @param request
     */
    private void deleteMenu(HttpServletRequest request, McDataSelectScriptForm mcDataSelectScriptForm) throws Exception
    {
    	McDataSelectListService mcDataSelectListService = (McDataSelectListService) getBean("mcDataSelectListService");        

    	String[] deleteRows = mcDataSelectScriptForm.getDeleteRows();
        
        mcDataSelectListService.deleteMenu(deleteRows);
        
        setAjaxStatus(request);
    }
}
