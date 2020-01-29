package dream.mgr.usrrpt.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.form.MaUserRptDesignForm;
import dream.mgr.usrrpt.service.MaUserRptDesignService;
import dream.mgr.usrrpt.service.MaUserRptListService;


/**
 * 메뉴 - 목록 action
 * @author  kim21017
 * @version $Id: MaUserRptListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maUserRptDesign" name="maUserRptDesignForm"
 *                input="/dream/mgr/usrrpt/maUserRptDesign.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptDesign" path="/dream/mgr/usrrpt/maUserRptDesign.jsp"
 *                        redirect="false"
 */
public class MaUserRptDesignAction extends AuthAction
{
    /** 조회 */
    public static final int USER_RPT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int USER_RPT_LIST_DELETE 	= 1002;
    /** 조회 */
    public static final int USER_RPT_SCRIPT_FIND 	= 1003;
    /** 수정 */
    public static final int USER_RPT_SCRIPT_UPDATE 	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptDesignForm maUserRptDesignForm = (MaUserRptDesignForm) form;
        
        switch (maUserRptDesignForm.getStrutsAction())
        {
            case MaUserRptDesignAction.USER_RPT_LIST_FIND:
               // findMenuList(request, maUserRptDesignForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptDesignAction.BASE_SET_HEADER:
                setHeader(request, response, maUserRptDesignForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptDesignAction.USER_RPT_LIST_DELETE:
            	deleteMenu(request, maUserRptDesignForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaUserRptDesignAction.USER_RPT_SCRIPT_FIND:
            	//findScript(request, maUserRptDesignForm);
                returnActionForward = mapping.findForward("maUserRptDesign");
                break;
            case MaUserRptDesignAction.USER_RPT_SCRIPT_UPDATE:
            	updateScript(request, maUserRptDesignForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaUserRptDesignAction.BASE_GRID_EXPORT:
            	findMenuList(request, maUserRptDesignForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUserRptDesign");
                break;
        }

        return returnActionForward;
    }

    private void findScript(HttpServletRequest request, MaUserRptDesignForm maUserRptDesignForm) {
		// TODO Auto-generated method stub
    	MaUserRptDesignService maUserRptDesignService = (MaUserRptDesignService) getBean("maUserRptDesignService");        

    	MaUserRptCommonDTO mcDataSelectCommonDTO = maUserRptDesignForm.getMaUserRptCommonDTO();
        // 조회된 상세 부분
    	MaUserRptCommonDTO resultDTO = maUserRptDesignService.findScript(mcDataSelectCommonDTO);
        // 조회된 상세  셋팅한다.
        maUserRptDesignForm.setMaUserRptCommonDTO(resultDTO);
	}

	private void updateScript(HttpServletRequest request, MaUserRptDesignForm maUserRptDesignForm) 
    {
    	MaUserRptDesignService maUserRptDesignService = (MaUserRptDesignService) getBean("maUserRptDesignService");        

    	maUserRptDesignService.updateScript(maUserRptDesignForm.getMaUserRptCommonDTO(), getUser(request));
        
        setAjaxStatus(request);
	}

    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, MaUserRptDesignForm maUserRptDesignForm) throws IOException
    {
        super.setHeader(request, response, maUserRptDesignForm.getListId(),maUserRptDesignForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaUserRptListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maUserRptDesignForm
     * @throws Exception
     */
    private void findMenuList(HttpServletRequest request, MaUserRptDesignForm maUserRptDesignForm, HttpServletResponse response) throws IOException
    {
    	MaUserRptListService mcDataSelectListService = (MaUserRptListService) getBean("mcDataSelectListService");        

    	MaUserRptCommonDTO mcDataSelectCommonDTO = maUserRptDesignForm.getMaUserRptCommonDTO();

        //리스트를 조회한다.
        List resultList = mcDataSelectListService.findMenuList(mcDataSelectCommonDTO,getUser(request));

        super.makeJsonResult(resultList, request, response, maUserRptDesignForm.getListId());
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
    private void deleteMenu(HttpServletRequest request, MaUserRptDesignForm maUserRptDesignForm) throws Exception
    {
    	MaUserRptListService mcDataSelectListService = (MaUserRptListService) getBean("mcDataSelectListService");        

    	String[] deleteRows = maUserRptDesignForm.getDeleteRows();
        
        mcDataSelectListService.deleteMenu(deleteRows);
        
        setAjaxStatus(request);
    }
}
