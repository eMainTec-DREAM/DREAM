package dream.doc.file.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.form.DocFileLovForm;
import dream.doc.file.service.DocFileLovService;

/**
 * 첨부문서 LOV - 목록 action
 * @author  jung7126
 * @version $Id: DocFileLovAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/docFileLov" name="docFileLovForm"
 *                input="/dream/doc/file/docFileLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docFileLov" path="/dream/doc/file/docFileLov.jsp"
 *                        redirect="false"
 */
public class DocFileLovAction extends SuperAuthAction
{
    /** 조회 */
    public static final int DOCLIB_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        DocFileLovForm docFileLovForm = (DocFileLovForm) form;
        
        switch (docFileLovForm.getStrutsAction())
        {
            case DocFileLovAction.DOCLIB_LIST_FIND:
                findDocList(request, docFileLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case DocFileLovAction.BASE_SET_HEADER:
                super.setHeader(request, response, docFileLovForm.getListId(),docFileLovForm.getCurrentPageId()); 
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: DocFileLovAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param docFileLovForm
     * @throws Exception
     */
    private void findDocList(HttpServletRequest request, DocFileLovForm docFileLovForm, HttpServletResponse response) throws IOException
    {
    	DocFileLovService docFileLovService = (DocFileLovService) getBean("docFileLovService");        

    	MaDocLibCommonDTO maDocLibCommonDTO = docFileLovForm.getMaDocLibCommonDTO();
    	maDocLibCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = docFileLovService.findList(maDocLibCommonDTO,getUser(request));

        super.makeJsonResult(resultList, request, response, docFileLovForm.getListId());
    }
}
