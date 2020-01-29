package dream.mgr.lang.action;


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
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.form.MgrLangMenuForm;
import dream.mgr.lang.service.MgrLangMenuService;

/**
 * 다국어사용된메뉴 action
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrLangMenuList" name="mgrLangMenuForm"
 *                input="/dream/mgr/lang/mgrLangMenuList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrLangMenuList" path="/dream/mgr/lang/mgrLangMenuList.jsp"
 *                        redirect="false"
 */
public class MgrLangMenuAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrLangMenuForm mgrLangMenuForm = (MgrLangMenuForm) form;
        
        switch (mgrLangMenuForm.getStrutsAction())
        {
            case MgrLangMenuAction.LIST_FIND:
            	findList(request, mgrLangMenuForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrLangMenuAction.BASE_GRID_EXPORT:
            	findList(request, mgrLangMenuForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MgrLangMenuAction.BASE_SET_HEADER:
                setHeader(request, response, mgrLangMenuForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrLangMenuForm mgrLangMenuForm) throws IOException
    {
        super.setHeader(request, response, mgrLangMenuForm.getListId(), mgrLangMenuForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrLangMenuForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrLangMenuForm mgrLangMenuForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrLangMenuService mgrLangMenuService = (MgrLangMenuService) getBean("mgrLangMenuService");        

    	MaResCommonDTO maResCommonDTO = mgrLangMenuForm.getMaResCommonDTO();
    	User user = getUser(request);

    	maResCommonDTO.setCompNo(user.getCompNo());
    	
    	maResCommonDTO.setIsLoadMaxCount("Y".equals(mgrLangMenuForm.getIsLoadMaxCount())?true:false);
    	maResCommonDTO.setFirstRow(mgrLangMenuForm.getFirstRow());
    	maResCommonDTO.setOrderBy(mgrLangMenuForm.getOrderBy());
    	maResCommonDTO.setDirection(mgrLangMenuForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrLangMenuService.findList(maResCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrLangMenuForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrLangMenuService.findTotalCount(maResCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrLangMenuForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
