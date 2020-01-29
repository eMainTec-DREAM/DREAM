package dream.asset.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrHistListDTO;
import dream.asset.list.form.MaEqMstrHistListForm;
import dream.asset.list.service.MaEqMstrHistListService;

/**
 * 설비변경이력 - 목록 action
 * @author  kim21017
 * @version $Id: MaEqMstrHistListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrHistList" name="maEqMstrHistListForm"
 *                input="/dream/asset/list/maEqMstrHistList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrHistList" path="/dream/asset/list/maEqMstrHistList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrHistListAction extends AuthAction
{
    /** 조회 */
    public static final int EQ_MSTR_HIST_LIST_DEFAUL 	= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_HIST_LIST_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrHistListForm maEqMstrHistListForm = (MaEqMstrHistListForm) form;
        
        switch (maEqMstrHistListForm.getStrutsAction())
        {
        	case MaEqMstrHistListAction.EQ_MSTR_HIST_LIST_DEFAUL:
        		returnActionForward = mapping.findForward("maEqMstrHistList");
        		break;
            case MaEqMstrHistListAction.EQ_MSTR_HIST_LIST_FIND:
            	findEqMstrHistList(request, maEqMstrHistListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrHistListAction.BASE_SET_HEADER:
                setHeader(request, response, maEqMstrHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaEqMstrHistListForm maEqMstrHistListForm) throws IOException
    {
        super.setHeader(request, response, maEqMstrHistListForm.getListId(), maEqMstrHistListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrHistListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrHistListForm
     * @throws Exception
     */
    private void findEqMstrHistList(HttpServletRequest request, MaEqMstrHistListForm maEqMstrHistListForm, HttpServletResponse response) throws IOException
    {
    	MaEqMstrHistListService maEqMstrHistListService = (MaEqMstrHistListService) getBean("maEqMstrHistListService");        

    	MaEqMstrHistListDTO maEqMstrHistListDTO = maEqMstrHistListForm.getMaEqMstrHistListDTO();
    	maEqMstrHistListDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maEqMstrHistListService.findEqMstrHistList(maEqMstrHistListDTO,getUser(request));
        super.makeJsonResult(resultList, request, response, maEqMstrHistListForm.getListId());
    }
}
