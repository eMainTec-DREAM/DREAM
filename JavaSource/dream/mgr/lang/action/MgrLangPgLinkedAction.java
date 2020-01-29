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
import dream.mgr.lang.form.MgrLangPgLinkedForm;
import dream.mgr.lang.service.MgrLangPgLinkedService;

/**
 * 다국어사용된메뉴 action
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrLangPgLinkedList" name="mgrLangPgLinkedForm"
 *                input="/dream/mgr/lang/mgrLangPgLinkedList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrLangPgLinkedList" path="/dream/mgr/lang/mgrLangPgLinkedList.jsp"
 *                        redirect="false"
 */
public class MgrLangPgLinkedAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrLangPgLinkedForm mgrLangPgLinkedForm = (MgrLangPgLinkedForm) form;
        
        switch (mgrLangPgLinkedForm.getStrutsAction())
        {
            case MgrLangPgLinkedAction.LIST_FIND:
            	findList(request, mgrLangPgLinkedForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrLangPgLinkedAction.BASE_GRID_EXPORT:
            	findList(request, mgrLangPgLinkedForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MgrLangPgLinkedAction.BASE_SET_HEADER:
                setHeader(request, response, mgrLangPgLinkedForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrLangPgLinkedForm mgrLangPgLinkedForm) throws IOException
    {
        super.setHeader(request, response, mgrLangPgLinkedForm.getListId(), mgrLangPgLinkedForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrLangPgLinkedForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrLangPgLinkedForm mgrLangPgLinkedForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrLangPgLinkedService mgrLangPgLinkedService = (MgrLangPgLinkedService) getBean("mgrLangPgLinkedService");        

    	MaResCommonDTO maResCommonDTO = mgrLangPgLinkedForm.getMaResCommonDTO();
    	User user = getUser(request);

    	maResCommonDTO.setCompNo(user.getCompNo());
    	
    	maResCommonDTO.setIsLoadMaxCount("Y".equals(mgrLangPgLinkedForm.getIsLoadMaxCount())?true:false);
    	maResCommonDTO.setFirstRow(mgrLangPgLinkedForm.getFirstRow());
    	maResCommonDTO.setOrderBy(mgrLangPgLinkedForm.getOrderBy());
    	maResCommonDTO.setDirection(mgrLangPgLinkedForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrLangPgLinkedService.findList(maResCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrLangPgLinkedForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrLangPgLinkedService.findTotalCount(maResCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrLangPgLinkedForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
