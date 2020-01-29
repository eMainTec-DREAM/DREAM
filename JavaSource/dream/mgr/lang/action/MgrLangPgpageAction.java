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
import dream.mgr.lang.form.MgrLangPgpageForm;
import dream.mgr.lang.service.MgrLangPgpageService;

/**
 * 다국어사용된메뉴 action
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrLangPgpageList" name="mgrLangPgpageForm"
 *                input="/dream/mgr/lang/mgrLangPgpageList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrLangPgpageList" path="/dream/mgr/lang/mgrLangPgpageList.jsp"
 *                        redirect="false"
 */
public class MgrLangPgpageAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrLangPgpageForm mgrLangPgpageForm = (MgrLangPgpageForm) form;
        
        switch (mgrLangPgpageForm.getStrutsAction())
        {
            case MgrLangPgpageAction.LIST_FIND:
            	findList(request, mgrLangPgpageForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrLangPgpageAction.BASE_GRID_EXPORT:
            	findList(request, mgrLangPgpageForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MgrLangPgpageAction.BASE_SET_HEADER:
                setHeader(request, response, mgrLangPgpageForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrLangPgpageForm mgrLangPgpageForm) throws IOException
    {
        super.setHeader(request, response, mgrLangPgpageForm.getListId(), mgrLangPgpageForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrLangPgpageForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrLangPgpageForm mgrLangPgpageForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrLangPgpageService mgrLangPgpageService = (MgrLangPgpageService) getBean("mgrLangPgpageService");        

    	MaResCommonDTO maResCommonDTO = mgrLangPgpageForm.getMaResCommonDTO();
    	User user = getUser(request);

    	maResCommonDTO.setCompNo(user.getCompNo());
    	
    	maResCommonDTO.setIsLoadMaxCount("Y".equals(mgrLangPgpageForm.getIsLoadMaxCount())?true:false);
    	maResCommonDTO.setFirstRow(mgrLangPgpageForm.getFirstRow());
    	maResCommonDTO.setOrderBy(mgrLangPgpageForm.getOrderBy());
    	maResCommonDTO.setDirection(mgrLangPgpageForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrLangPgpageService.findList(maResCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrLangPgpageForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrLangPgpageService.findTotalCount(maResCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrLangPgpageForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
