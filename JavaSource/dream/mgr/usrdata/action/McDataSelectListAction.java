package dream.mgr.usrdata.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.form.McDataSelectListForm;
import dream.mgr.usrdata.service.McDataSelectListService;


/**
 * 메뉴 - 목록 action
 * @author  kim21017
 * @version $Id: McDataSelectListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/mcDataSelectList" name="mcDataSelectListForm"
 *                input="/dream/mgr/usrdata/mcDataSelectList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcDataSelectList" path="/dream/mgr/usrdata/mcDataSelectList.jsp"
 *                        redirect="false"
 */
public class McDataSelectListAction extends AuthAction
{
    /** 조회 */
    public static final int DATA_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int DATA_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        McDataSelectListForm mcDataSelectListForm = (McDataSelectListForm) form;
        
        switch (mcDataSelectListForm.getStrutsAction())
        {
            case McDataSelectListAction.DATA_LIST_FIND:
                findMenuList(request, mcDataSelectListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case McDataSelectListAction.BASE_SET_HEADER:
                setHeader(request, response, mcDataSelectListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case McDataSelectListAction.DATA_LIST_DELETE:
            	deleteMenu(request, mcDataSelectListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case McDataSelectListAction.BASE_GRID_EXPORT:
            	findMenuList(request, mcDataSelectListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mcDataSelectList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, McDataSelectListForm mcDataSelectListForm) throws IOException
    {
        super.setHeader(request, response, mcDataSelectListForm.getListId(),mcDataSelectListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: McDataSelectListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mcDataSelectListForm
     * @throws Exception
     */
    private void findMenuList(HttpServletRequest request, McDataSelectListForm mcDataSelectListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	McDataSelectListService mcDataSelectListService = (McDataSelectListService) getBean("mcDataSelectListService");        

    	McDataSelectCommonDTO mcDataSelectCommonDTO = mcDataSelectListForm.getMcDataSelectCommonDTO();
    	mcDataSelectCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        User user = getUser(request);
        
    	//Paging
        mcDataSelectCommonDTO.setIsLoadMaxCount("Y".equals(mcDataSelectListForm.getIsLoadMaxCount())?true:false);
        mcDataSelectCommonDTO.setFirstRow(mcDataSelectListForm.getFirstRow());
        mcDataSelectCommonDTO.setOrderBy(mcDataSelectListForm.getOrderBy());
        mcDataSelectCommonDTO.setDirection(mcDataSelectListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = mcDataSelectListService.findMenuList(mcDataSelectCommonDTO,getUser(request));

//		JasperFactory report = new JasperFactory("이메인텍", getImgPath("test.png"));
//		report.setMainTitle("타이틀");
//		report.makeReport(resultList);
//		report.view();

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mcDataSelectListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mcDataSelectListService.findTotalCount(mcDataSelectCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mcDataSelectListForm.getListId(),mcDataSelectListForm.getCurrentPageId(), mcDataSelectListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    
        
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
    private void deleteMenu(HttpServletRequest request, McDataSelectListForm mcDataSelectListForm) throws Exception
    {
    	McDataSelectListService mcDataSelectListService = (McDataSelectListService) getBean("mcDataSelectListService");        

    	String[] deleteRows = mcDataSelectListForm.getDeleteRows();
        
        mcDataSelectListService.deleteMenu(deleteRows);
        
        setAjaxStatus(request);
    }
}
