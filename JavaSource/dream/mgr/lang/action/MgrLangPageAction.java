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
import dream.mgr.lang.form.MgrLangPageForm;
import dream.mgr.lang.service.MgrLangPageService;

/**
 * 다국어사용된메뉴 action
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrLangPageList" name="mgrLangPageForm"
 *                input="/dream/mgr/lang/mgrLangPageList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrLangPageList" path="/dream/mgr/lang/mgrLangPageList.jsp"
 *                        redirect="false"
 */
public class MgrLangPageAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrLangPageForm mgrLangPageForm = (MgrLangPageForm) form;
        
        switch (mgrLangPageForm.getStrutsAction())
        {
            case MgrLangPageAction.LIST_FIND:
            	findList(request, mgrLangPageForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrLangPageAction.BASE_GRID_EXPORT:
            	findList(request, mgrLangPageForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MgrLangPageAction.BASE_SET_HEADER:
                setHeader(request, response, mgrLangPageForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrLangPageForm mgrLangPageForm) throws IOException
    {
        super.setHeader(request, response, mgrLangPageForm.getListId(), mgrLangPageForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrLangPageForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrLangPageForm mgrLangPageForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrLangPageService mgrLangPageService = (MgrLangPageService) getBean("mgrLangPageService");        

    	MaResCommonDTO maResCommonDTO = mgrLangPageForm.getMaResCommonDTO();
    	User user = getUser(request);

    	maResCommonDTO.setCompNo(user.getCompNo());
    	
    	maResCommonDTO.setIsLoadMaxCount("Y".equals(mgrLangPageForm.getIsLoadMaxCount())?true:false);
    	maResCommonDTO.setFirstRow(mgrLangPageForm.getFirstRow());
    	maResCommonDTO.setOrderBy(mgrLangPageForm.getOrderBy());
    	maResCommonDTO.setDirection(mgrLangPageForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrLangPageService.findList(maResCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrLangPageForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrLangPageService.findTotalCount(maResCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrLangPageForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
