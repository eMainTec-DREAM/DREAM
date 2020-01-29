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
import dream.mgr.lang.form.MgrLangPgBtnForm;
import dream.mgr.lang.service.MgrLangPgBtnService;

/**
 * 다국어사용된메뉴 action
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrLangPgBtnList" name="mgrLangPgBtnForm"
 *                input="/dream/mgr/lang/mgrLangPgBtnList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrLangPgBtnList" path="/dream/mgr/lang/mgrLangPgBtnList.jsp"
 *                        redirect="false"
 */
public class MgrLangPgBtnAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrLangPgBtnForm mgrLangPgBtnForm = (MgrLangPgBtnForm) form;
        
        switch (mgrLangPgBtnForm.getStrutsAction())
        {
            case MgrLangPgBtnAction.LIST_FIND:
            	findList(request, mgrLangPgBtnForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrLangPgBtnAction.BASE_GRID_EXPORT:
            	findList(request, mgrLangPgBtnForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MgrLangPgBtnAction.BASE_SET_HEADER:
                setHeader(request, response, mgrLangPgBtnForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrLangPgBtnForm mgrLangPgBtnForm) throws IOException
    {
        super.setHeader(request, response, mgrLangPgBtnForm.getListId(), mgrLangPgBtnForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrLangPgBtnForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrLangPgBtnForm mgrLangPgBtnForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrLangPgBtnService mgrLangPgBtnService = (MgrLangPgBtnService) getBean("mgrLangPgBtnService");        

    	MaResCommonDTO maResCommonDTO = mgrLangPgBtnForm.getMaResCommonDTO();
    	User user = getUser(request);

    	maResCommonDTO.setCompNo(user.getCompNo());
    	
    	maResCommonDTO.setIsLoadMaxCount("Y".equals(mgrLangPgBtnForm.getIsLoadMaxCount())?true:false);
    	maResCommonDTO.setFirstRow(mgrLangPgBtnForm.getFirstRow());
    	maResCommonDTO.setOrderBy(mgrLangPgBtnForm.getOrderBy());
    	maResCommonDTO.setDirection(mgrLangPgBtnForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrLangPgBtnService.findList(maResCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrLangPgBtnForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrLangPgBtnService.findTotalCount(maResCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrLangPgBtnForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
