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
import dream.mgr.lang.form.MgrLangSyscodeForm;
import dream.mgr.lang.service.MgrLangSyscodeService;

/**
 * 다국어사용된메뉴 action
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrLangSyscodeList" name="mgrLangSyscodeForm"
 *                input="/dream/mgr/lang/mgrLangSyscodeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrLangSyscodeList" path="/dream/mgr/lang/mgrLangSyscodeList.jsp"
 *                        redirect="false"
 */
public class MgrLangSyscodeAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrLangSyscodeForm mgrLangSyscodeForm = (MgrLangSyscodeForm) form;
        
        switch (mgrLangSyscodeForm.getStrutsAction())
        {
            case MgrLangSyscodeAction.LIST_FIND:
            	findList(request, mgrLangSyscodeForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrLangSyscodeAction.BASE_GRID_EXPORT:
            	findList(request, mgrLangSyscodeForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MgrLangSyscodeAction.BASE_SET_HEADER:
                setHeader(request, response, mgrLangSyscodeForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrLangSyscodeForm mgrLangSyscodeForm) throws IOException
    {
        super.setHeader(request, response, mgrLangSyscodeForm.getListId(), mgrLangSyscodeForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrLangSyscodeForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrLangSyscodeForm mgrLangSyscodeForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrLangSyscodeService mgrLangSyscodeService = (MgrLangSyscodeService) getBean("mgrLangSyscodeService");        

    	MaResCommonDTO maResCommonDTO = mgrLangSyscodeForm.getMaResCommonDTO();
    	User user = getUser(request);

    	maResCommonDTO.setCompNo(user.getCompNo());
    	
    	maResCommonDTO.setIsLoadMaxCount("Y".equals(mgrLangSyscodeForm.getIsLoadMaxCount())?true:false);
    	maResCommonDTO.setFirstRow(mgrLangSyscodeForm.getFirstRow());
    	maResCommonDTO.setOrderBy(mgrLangSyscodeForm.getOrderBy());
    	maResCommonDTO.setDirection(mgrLangSyscodeForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrLangSyscodeService.findList(maResCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrLangSyscodeForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrLangSyscodeService.findTotalCount(maResCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrLangSyscodeForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
