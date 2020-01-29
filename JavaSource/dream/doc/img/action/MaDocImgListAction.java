package dream.doc.img.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.doc.img.form.MaDocImgListForm;
import dream.doc.img.service.MaDocImgListService;

/**
 * 사진파일 - 목록 action
 * @author  jung7126
 * @version $Id: MaDocImgListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maDocImgList" name="maDocImgListForm"
 *                input="/dream/doc/img/maDocImgList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maDocImgPopList" name="maDocImgListForm"
 *                input="/dream/doc/img/maDocImgPopList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maDocImgList" path="/dream/doc/img/maDocImgList.jsp"
 *                        redirect="false"
 */
public class MaDocImgListAction extends SuperAuthAction
{
    /** 조회 */
    public static final int DOCIMG_LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int DOCIMG_LIST_DELETE 	    = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaDocImgListForm maDocImgListForm = (MaDocImgListForm) form;
        
        super.updateAudit(maDocImgListForm.getMaDocImgCommonDTO().getAuditKey()==""?maDocImgListForm.getMaDocImgCommonDTO().getAuditKey():maDocImgListForm.getMaDocImgCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maDocImgListForm.getStrutsAction())
        {
            case MaDocImgListAction.DOCIMG_LIST_FIND:
                findList(request, maDocImgListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDocImgListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maDocImgListForm.getListId(),maDocImgListForm.getCurrentPageId()); 
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDocImgListAction.DOCIMG_LIST_DELETE:
            	deleteList(request, maDocImgListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaDocImgListAction.BASE_GRID_EXPORT:
            	findList(request, maDocImgListForm,response, true);
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
     * @version $Id: MaDocImgListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDocImgListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaDocImgListForm maDocImgListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaDocImgListService maDocImgListService = (MaDocImgListService) getBean("maDocImgListService");        

    	MaDocImgCommonDTO maDocImgCommonDTO = maDocImgListForm.getMaDocImgCommonDTO();
    	if("".equals(maDocImgCommonDTO.getCompNo())) maDocImgCommonDTO.setCompNo(getUser(request).getCompNo());

    	// Paging
    	maDocImgCommonDTO.setIsLoadMaxCount("Y".equals(maDocImgListForm.getIsLoadMaxCount())?true:false);
    	maDocImgCommonDTO.setFirstRow(maDocImgListForm.getFirstRow());
    	maDocImgCommonDTO.setOrderBy(maDocImgListForm.getOrderBy());
    	maDocImgCommonDTO.setDirection(maDocImgListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maDocImgListService.findList(maDocImgCommonDTO,getUser(request));

        // Paging
        String totalCount = "";
        if(Integer.parseInt(maDocImgListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maDocImgListService.findTotalCount(maDocImgCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maDocImgListForm.getListId(),maDocImgListForm.getCurrentPageId(), maDocImgListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaDocImgListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaDocImgListForm maDocImgListForm) throws Exception
    {
    	MaDocImgListService maDocImgListService = (MaDocImgListService) getBean("maDocImgListService");        

    	String[] deleteRows = maDocImgListForm.getDeleteRows();
    	MaDocImgCommonDTO maDocImgCommonDTO = maDocImgListForm.getMaDocImgCommonDTO();
        
        maDocImgListService.deleteList(deleteRows, maDocImgCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}
