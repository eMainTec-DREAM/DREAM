package dream.consult.program.wrkimp.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;

import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.form.MaDocLibListForm;
import dream.doc.file.service.MaDocLibListService;

/**
 * ÷�ι��� - ��� action
 * @author  jung7126
 * @version $Id: MaCnDocListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWrkImpCnDocList" name="maDocLibListForm"
 *                input="/dream/consult/program/wrkimp/maWrkImpCnDocList.jsp" scope="request"
 *                validate="false"
 */
public class MaCnDocListAction extends ConsultAuthAction
{
    /** ��ȸ */
    public static final int DOCLIB_LIST_FIND 		= 1001;
    /** ���� */
    public static final int DOCLIB_LIST_DELETE 	    = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaDocLibListForm maDocLibListForm = (MaDocLibListForm) form;
        
        switch (maDocLibListForm.getStrutsAction())
        {
            case MaCnDocListAction.DOCLIB_LIST_FIND:
                findMenuList(request, maDocLibListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaCnDocListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maDocLibListForm.getListId(),maDocLibListForm.getCurrentPageId()); 
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaCnDocListAction.DOCLIB_LIST_DELETE:
                deleteMenu(request, maDocLibListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaCnDocListAction.BASE_GRID_EXPORT:
                findMenuList(request, maDocLibListForm,response);
                returnActionForward = new ActionForward("/gridExport");
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
     * @version $Id: MaDocLibListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDocLibListForm
     * @throws Exception
     */
    private void findMenuList(HttpServletRequest request, MaDocLibListForm maDocLibListForm, HttpServletResponse response) throws IOException
    {
        MaDocLibListService maDocLibListService = (MaDocLibListService) getBean("maDocLibListService");        

        MaDocLibCommonDTO maDocLibCommonDTO = maDocLibListForm.getMaDocLibCommonDTO();
        maDocLibCommonDTO.setCompNo(getUser(request).getCompNo());
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maDocLibListService.findList(maDocLibCommonDTO,getUser(request));

        super.makeJsonResult(resultList, request, response, maDocLibListForm.getListId());
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaDocLibListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngListForm
     * @param request
     */
    private void deleteMenu(HttpServletRequest request, MaDocLibListForm maDocLibListForm) throws Exception
    {
        MaDocLibListService maDocLibListService = (MaDocLibListService) getBean("maDocLibListService");        

        String[] deleteRows = maDocLibListForm.getDeleteRows();
        MaDocLibCommonDTO maDocLibCommonDTO = maDocLibListForm.getMaDocLibCommonDTO();
        
        maDocLibListService.deleteList(deleteRows, maDocLibCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}