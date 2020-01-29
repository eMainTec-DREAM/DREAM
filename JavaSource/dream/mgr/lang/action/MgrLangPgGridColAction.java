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
import dream.mgr.lang.form.MgrLangPgGridColForm;
import dream.mgr.lang.service.MgrLangPgGridColService;

/**
 * 다국어사용된메뉴 action
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrLangPgGridColList" name="mgrLangPgGridColForm"
 *                input="/dream/mgr/lang/mgrLangPgGridColList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrLangPgGridColList" path="/dream/mgr/lang/mgrLangPgGridColList.jsp"
 *                        redirect="false"
 */
public class MgrLangPgGridColAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrLangPgGridColForm mgrLangPgGridColForm = (MgrLangPgGridColForm) form;
        
        switch (mgrLangPgGridColForm.getStrutsAction())
        {
            case MgrLangPgGridColAction.LIST_FIND:
            	findList(request, mgrLangPgGridColForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrLangPgGridColAction.BASE_GRID_EXPORT:
            	findList(request, mgrLangPgGridColForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MgrLangPgGridColAction.BASE_SET_HEADER:
                setHeader(request, response, mgrLangPgGridColForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrLangPgGridColForm mgrLangPgGridColForm) throws IOException
    {
        super.setHeader(request, response, mgrLangPgGridColForm.getListId(), mgrLangPgGridColForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrLangPgGridColForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrLangPgGridColForm mgrLangPgGridColForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrLangPgGridColService mgrLangPgGridColService = (MgrLangPgGridColService) getBean("mgrLangPgGridColService");        

    	MaResCommonDTO maResCommonDTO = mgrLangPgGridColForm.getMaResCommonDTO();
    	User user = getUser(request);

    	maResCommonDTO.setCompNo(user.getCompNo());
    	
    	maResCommonDTO.setIsLoadMaxCount("Y".equals(mgrLangPgGridColForm.getIsLoadMaxCount())?true:false);
    	maResCommonDTO.setFirstRow(mgrLangPgGridColForm.getFirstRow());
    	maResCommonDTO.setOrderBy(mgrLangPgGridColForm.getOrderBy());
    	maResCommonDTO.setDirection(mgrLangPgGridColForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrLangPgGridColService.findList(maResCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrLangPgGridColForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrLangPgGridColService.findTotalCount(maResCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrLangPgGridColForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
