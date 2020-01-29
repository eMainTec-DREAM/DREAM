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
import dream.mgr.lang.form.MgrLangPgFieldForm;
import dream.mgr.lang.service.MgrLangPgFieldService;

/**
 * 다국어사용된메뉴 action
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrLangPgFieldList" name="mgrLangPgFieldForm"
 *                input="/dream/mgr/lang/mgrLangPgFieldList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrLangPgFieldList" path="/dream/mgr/lang/mgrLangPgFieldList.jsp"
 *                        redirect="false"
 */
public class MgrLangPgFieldAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrLangPgFieldForm mgrLangPgFieldForm = (MgrLangPgFieldForm) form;
        
        switch (mgrLangPgFieldForm.getStrutsAction())
        {
            case MgrLangPgFieldAction.LIST_FIND:
            	findList(request, mgrLangPgFieldForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrLangPgFieldAction.BASE_GRID_EXPORT:
            	findList(request, mgrLangPgFieldForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MgrLangPgFieldAction.BASE_SET_HEADER:
                setHeader(request, response, mgrLangPgFieldForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrLangPgFieldForm mgrLangPgFieldForm) throws IOException
    {
        super.setHeader(request, response, mgrLangPgFieldForm.getListId(), mgrLangPgFieldForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrLangPgFieldForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrLangPgFieldForm mgrLangPgFieldForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrLangPgFieldService mgrLangPgFieldService = (MgrLangPgFieldService) getBean("mgrLangPgFieldService");        

    	MaResCommonDTO maResCommonDTO = mgrLangPgFieldForm.getMaResCommonDTO();
    	User user = getUser(request);

    	maResCommonDTO.setCompNo(user.getCompNo());
    	
    	maResCommonDTO.setIsLoadMaxCount("Y".equals(mgrLangPgFieldForm.getIsLoadMaxCount())?true:false);
    	maResCommonDTO.setFirstRow(mgrLangPgFieldForm.getFirstRow());
    	maResCommonDTO.setOrderBy(mgrLangPgFieldForm.getOrderBy());
    	maResCommonDTO.setDirection(mgrLangPgFieldForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrLangPgFieldService.findList(maResCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrLangPgFieldForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrLangPgFieldService.findTotalCount(maResCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrLangPgFieldForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
