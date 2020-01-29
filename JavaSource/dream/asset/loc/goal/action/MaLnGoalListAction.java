package dream.asset.loc.goal.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;
import dream.asset.loc.goal.form.MaLnGoalListForm;
import dream.asset.loc.goal.service.MaLnGoalListService;

/**
 * ��� action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maLnGoalList" name="maLnGoalListForm"
 *                input="/dream/asset/loc/goal/maLnGoalList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLnGoalList" path="/dream/asset/loc/goal/maLnGoalList.jsp"
 *                        redirect="false"
 */
public class MaLnGoalListAction extends AuthAction
{
    /** ��ȸ */
    public static final int MTGOAL_LIST_FIND    = 1001;
    /** ���� */
    public static final int MTGOAL_LIST_DELETE  = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaLnGoalListForm maLnGoalListForm = (MaLnGoalListForm) form;
        
        switch (maLnGoalListForm.getStrutsAction())
        {
            case MaLnGoalListAction.BASE_SET_HEADER:
                setHeader(request, response, maLnGoalListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLnGoalListAction.MTGOAL_LIST_FIND:
                findList(request, response, maLnGoalListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaLnGoalListAction.MTGOAL_LIST_DELETE:
            	deleteList(request, maLnGoalListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            default:
                returnActionForward = mapping.findForward("maLnGoalList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaLnGoalListForm maLnGoalListForm) throws IOException
    {
        super.setHeader(request, response, maLnGoalListForm.getListId(), maLnGoalListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maLnGoalListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaLnGoalListForm maLnGoalListForm) throws IOException
    {
    	MaLnGoalListService maLnGoalListService = (MaLnGoalListService) getBean("maLnGoalListService");        

    	MaLnGoalCommonDTO maLnGoalCommonDTO = maLnGoalListForm.getMaLnGoalCommonDTO();

    	// �α� comp_no �� �����Ѵ�.
//    	maLnGoalCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
        
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maLnGoalListService.findList(maLnGoalCommonDTO, getUser(request));

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response, maLnGoalListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaLnGoalListForm maLnGoalListForm) throws Exception
    {
    	MaLnGoalListService maLnGoalListService = (MaLnGoalListService) getBean("maLnGoalListService");        

        String[] deleteRows = maLnGoalListForm.getDeleteRows();    // sheet ����
        
        maLnGoalListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
