package dream.mgr.usrrpt.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.form.MaUserRptListForm;
import dream.mgr.usrrpt.service.MaUserRptListService;
import net.sf.dynamicreports.report.exception.DRException;


/**
 * 메뉴 - 목록 action
 * @author  kim21017
 * @version $Id: MaUserRptListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maUserRptList" name="maUserRptListForm"
 *                input="/dream/mgr/usrrpt/maUserRptList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptList" path="/dream/mgr/usrrpt/maUserRptList.jsp"
 *                        redirect="false"
 */
public class MaUserRptListAction extends AuthAction
{
    /** 조회 */
    public static final int USER_RPT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int USER_RPT_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptListForm maUserRptListForm = (MaUserRptListForm) form;
        
        switch (maUserRptListForm.getStrutsAction())
        {
            case MaUserRptListAction.USER_RPT_LIST_FIND:
                findMenuList(request, maUserRptListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptListAction.BASE_SET_HEADER:
                setHeader(request, response, maUserRptListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptListAction.USER_RPT_LIST_DELETE:
            	deleteMenu(request, maUserRptListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaUserRptListAction.BASE_GRID_EXPORT:
            	findMenuList(request, maUserRptListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUserRptList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaUserRptListForm maUserRptListForm) throws IOException
    {
        super.setHeader(request, response, maUserRptListForm.getListId(),maUserRptListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaUserRptListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maUserRptListForm
     * @throws DRException 
     * @throws Exception
     */
    private void findMenuList(HttpServletRequest request, MaUserRptListForm maUserRptListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaUserRptListService maUserRptListService = (MaUserRptListService) getBean("maUserRptListService");        

    	MaUserRptCommonDTO maUserRptCommonDTO = maUserRptListForm.getMaUserRptCommonDTO();
    	maUserRptCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        User user = getUser(request);
        
    	//Paging
        maUserRptCommonDTO.setIsLoadMaxCount("Y".equals(maUserRptListForm.getIsLoadMaxCount())?true:false);
        maUserRptCommonDTO.setFirstRow(maUserRptListForm.getFirstRow());
        maUserRptCommonDTO.setOrderBy(maUserRptListForm.getOrderBy());
        maUserRptCommonDTO.setDirection(maUserRptListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maUserRptListService.findMenuList(maUserRptCommonDTO,getUser(request));

//		JasperFactory report = new JasperFactory("이메인텍", getImgPath("test.png"));
//		report.setMainTitle("타이틀");
//		report.makeReport(resultList);
//		report.view();
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maUserRptListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maUserRptListService.findTotalCount(maUserRptCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maUserRptListForm.getListId(),maUserRptListForm.getCurrentPageId(), maUserRptListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaUserRptListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngListForm
     * @param request
     */
    private void deleteMenu(HttpServletRequest request, MaUserRptListForm maUserRptListForm) throws Exception
    {
    	MaUserRptListService maUserRptListService = (MaUserRptListService) getBean("maUserRptListService");        

    	String[] deleteRows = maUserRptListForm.getDeleteRows();
        
        maUserRptListService.deleteMenu(deleteRows);
        
        setAjaxStatus(request);
    }
}
