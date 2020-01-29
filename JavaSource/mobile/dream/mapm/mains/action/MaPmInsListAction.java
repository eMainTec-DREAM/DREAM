package mobile.dream.mapm.mains.action;


import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.MobileAuthAction;
import mobile.dream.mapm.mains.dto.MaPmInsCommonDTO;
import mobile.dream.mapm.mains.form.MaPmInsListForm;
import mobile.dream.mapm.mains.service.MaPmInsListService;

/**
 * 예방점검 - 목록 action
 * @author  jung7126
 * @version $Id: MaPmInsListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maPmInsList" name="maPmInsListForm"
 *                input="/mobile/dream/mapm/mains/maPmInsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmInsList" path="/mobile/dream/mapm/mains/maPmInsList.jsp"
 *                        redirect="false"
 */
public class MaPmInsListAction extends MobileAuthAction
{
    /** 조회 */
    public static final int PM_INS_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmInsListForm maPmInsListForm = (MaPmInsListForm) form;
        
        switch (maPmInsListForm.getStrutsAction())
        {
            case MaPmInsListAction.PM_INS_LIST_FIND:
            	findPmInsList(request, maPmInsListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmInsListAction.BASE_QUICK_SEARCH:
            	findPmInsList(request, maPmInsListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maPmInsList");
                break;
        }

        return returnActionForward;
    }

    /**e
     * grid find
     * @author  kim2107
     * @version $Id: MaPmInsListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmInsListForm
     * @throws Exception
     */
    private void findPmInsList(HttpServletRequest request, MaPmInsListForm maPmInsListForm, HttpServletResponse response) throws IOException
    {
    	MaPmInsListService maPmInsListService = (MaPmInsListService) getBean("maPmInsListService");        

    	MaPmInsCommonDTO maPmInsCommonDTO = maPmInsListForm.getMaPmInsCommonDTO();
    	maPmInsCommonDTO.setCompNo(getUser(request).getCompNo());

    	maPmInsCommonDTO.setIsLoadMaxCount("Y".equals(maPmInsListForm.getIsLoadMaxCount())?true:false);
    	maPmInsCommonDTO.setFirstRow(maPmInsListForm.getFirstRow());
        
    	String totalCount = "";
        if(Integer.parseInt(maPmInsListForm.getIsTotalCount()) == 0) totalCount = maPmInsListService.findTotalCount(maPmInsListForm,getUser(request));
        
    	//리스트를 조회한다.
        List resultList = maPmInsListService.findList(maPmInsCommonDTO,getUser(request));
        super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
