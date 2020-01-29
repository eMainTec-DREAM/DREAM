package dream.comm.revision.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import dream.comm.revision.dto.CommRevHistCommonDTO;
import dream.comm.revision.form.CommRevHistListForm;
import dream.comm.revision.service.CommRevHistListService;

/**
 * 제/개정 변경이력  Action
 * @author  kim21017
 * @version $Id: CommRevHistListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/commRevHistList" name="commRevHistListForm"
 *                input="/dream/comm/revision/hist/commRevHistList.jsp" scope="request"
 *                validate="false"
 */
public class CommRevHistListAction extends SuperAuthAction
{
    /** 조회 */
    public static final int REV_HISTORY_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CommRevHistListForm commRevHistListForm = (CommRevHistListForm) form;
        
        switch (commRevHistListForm.getStrutsAction())
        {
            case CommRevHistListAction.REV_HISTORY_LIST_FIND:
            	findList(request, commRevHistListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CommRevHistListAction.BASE_SET_HEADER:
                setHeader(request, response, commRevHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CommRevHistListAction.BASE_GRID_EXPORT:
            	findList(request, commRevHistListForm, response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, CommRevHistListForm commRevHistListForm) throws IOException
    {
        super.setHeader(request, response, commRevHistListForm.getListId(),commRevHistListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: CommRevHistListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param commRevHistListForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, CommRevHistListForm commRevHistListForm, HttpServletResponse response) throws IOException
    {
    	CommRevHistListService commRevHistListService = (CommRevHistListService) getBean("commRevHistListService");        

    	CommRevHistCommonDTO commRevHistCommonDTO = commRevHistListForm.getCommRevHistCommonDTO();
    	
    	commRevHistCommonDTO.setCompNo(getUser(request).getCompNo());
    	commRevHistCommonDTO.setUserLang(getUser(request).getLangId());
    	
    	User user = getUser(request);
    	
        //리스트를 조회한다.
        List resultList = commRevHistListService.findList(commRevHistCommonDTO, user);

        super.makeJsonResult(resultList, request, response, commRevHistListForm.getListId());
	}
}
