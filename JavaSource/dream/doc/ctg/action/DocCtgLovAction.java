package dream.doc.ctg.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import dream.doc.ctg.dto.DocCtgLovDTO;
import dream.doc.ctg.form.DocCtgLovForm;
import dream.doc.ctg.service.DocCtgLovService;

/**
 * 문서분류체계 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/docCtgLov" name="docCtgLovForm"
 *                input="/dream/doc/ctg/docCtgLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docCtgLov" path="/dream/doc/ctg/docCtgLov.jsp"
 *                        redirect="false"
 */
public class DocCtgLovAction extends SuperAuthAction
{
    /** 조회 */
    public static final int DOCCTG_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int DOCCTG_LIST_DELETE = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        DocCtgLovForm docCtgLovForm = (DocCtgLovForm) form;
        
        switch (docCtgLovForm.getStrutsAction())
        {
            case DocCtgLovAction.DOCCTG_LIST_FIND:
            	findPtWhList(request, docCtgLovForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case DocCtgLovAction.BASE_SET_HEADER:
                setHeader(request, response, docCtgLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("docCtgLov");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, DocCtgLovForm docCtgLovForm) throws IOException
    {
        super.setHeader(request, response, docCtgLovForm.getListId(), docCtgLovForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param docCtgLovForm
     * @throws Exception
     */
    private void findPtWhList(HttpServletRequest request, DocCtgLovForm docCtgLovForm, HttpServletResponse response) throws IOException
    {
    	DocCtgLovService docCtgLovService = (DocCtgLovService) getBean("docCtgLovService");        

    	DocCtgLovDTO docCtgLovDTO  = docCtgLovForm.getDocCtgLovDTO();
    	
    	User user = getUser(request);
    	
        //리스트를 조회한다.
        List resultList = docCtgLovService.findList(docCtgLovDTO, docCtgLovForm, user);

        super.makeTreeJsonResult(resultList, request, response, docCtgLovForm.getListId());
        
    }
}
