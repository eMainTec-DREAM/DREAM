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
import dream.mgr.lang.form.MgrLangFailcodeForm;
import dream.mgr.lang.service.MgrLangFailcodeService;

/**
 * 다국어사용된메뉴 action
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrLangFailcodeList" name="mgrLangFailcodeForm"
 *                input="/dream/mgr/lang/mgrLangFailcodeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrLangFailcodeList" path="/dream/mgr/lang/mgrLangFailcodeList.jsp"
 *                        redirect="false"
 */
public class MgrLangFailcodeAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrLangFailcodeForm mgrLangFailcodeForm = (MgrLangFailcodeForm) form;
        
        switch (mgrLangFailcodeForm.getStrutsAction())
        {
            case MgrLangFailcodeAction.LIST_FIND:
            	findList(request, mgrLangFailcodeForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrLangFailcodeAction.BASE_GRID_EXPORT:
            	findList(request, mgrLangFailcodeForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MgrLangFailcodeAction.BASE_SET_HEADER:
                setHeader(request, response, mgrLangFailcodeForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrLangFailcodeForm mgrLangFailcodeForm) throws IOException
    {
        super.setHeader(request, response, mgrLangFailcodeForm.getListId(), mgrLangFailcodeForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrLangFailcodeForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrLangFailcodeForm mgrLangFailcodeForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrLangFailcodeService mgrLangFailcodeService = (MgrLangFailcodeService) getBean("mgrLangFailcodeService");        

    	MaResCommonDTO maResCommonDTO = mgrLangFailcodeForm.getMaResCommonDTO();
    	User user = getUser(request);

    	maResCommonDTO.setCompNo(user.getCompNo());
    	
    	maResCommonDTO.setIsLoadMaxCount("Y".equals(mgrLangFailcodeForm.getIsLoadMaxCount())?true:false);
    	maResCommonDTO.setFirstRow(mgrLangFailcodeForm.getFirstRow());
    	maResCommonDTO.setOrderBy(mgrLangFailcodeForm.getOrderBy());
    	maResCommonDTO.setDirection(mgrLangFailcodeForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrLangFailcodeService.findList(maResCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrLangFailcodeForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrLangFailcodeService.findTotalCount(maResCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrLangFailcodeForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
