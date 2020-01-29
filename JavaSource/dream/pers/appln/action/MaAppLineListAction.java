package dream.pers.appln.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.form.MaAppLineListForm;
import dream.pers.appln.service.MaAppLineListService;

/**
 * 질의 - 목록 action
 * @author  kim21017
 * @version $Id: MaAppLineListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maAppLineList" name="maAppLineListForm"
 *                input="/dream/pers/appln/maAppLineList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maAppLinePopupList" name="maAppLineListForm"
 *                input="/dream/pers/appln/maAppLinePopupList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maAppLineList" path="/dream/pers/appln/maAppLineList.jsp"
 *                        redirect="false"
 */
public class MaAppLineListAction extends AuthAction
{
    /** 조회 */
    public static final int QNA_LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int QNA_LIST_DELETE 	= 7002;
    /** 결재선 입력 */
    public static final int APP_LINE_INPUT      = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaAppLineListForm maAppLineListForm = (MaAppLineListForm) form;
        
        super.updateAudit(maAppLineListForm.getMaAppLineCommonDTO().getAuditKey()==""?maAppLineListForm.getMaAppLineCommonDTO().getAuditKey():maAppLineListForm.getMaAppLineCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maAppLineListForm.getStrutsAction())
        {
            case MaAppLineListAction.QNA_LIST_FIND:
                findQnaList(request, maAppLineListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaAppLineListAction.BASE_SET_HEADER:
                setHeader(request, response, maAppLineListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaAppLineListAction.QNA_LIST_DELETE:
                deleteQna(request, maAppLineListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaAppLineListAction.APP_LINE_INPUT:
                insertLine(request, maAppLineListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaAppLineListAction.BASE_GRID_EXPORT:
            	findQnaList(request, maAppLineListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    private void insertLine(HttpServletRequest request, MaAppLineListForm maAppLineListForm)
    {
        MaAppLineListService maAppLineListService = (MaAppLineListService) getBean("maAppLineListService");        

        MaAppLineCommonDTO maAppLineCommonDTO =  maAppLineListForm.getMaAppLineCommonDTO();
        maAppLineCommonDTO.setCompNo(getUser(request).getCompNo());
        String[] deleteRows = maAppLineListForm.getDeleteRows();    // sheet 내역
        
        maAppLineListService.insertLine(deleteRows,getUser(request), maAppLineCommonDTO);
        
        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaAppLineListForm maAppLineListForm) throws IOException
    {
        super.setHeader(request, response, maAppLineListForm.getListId(), maAppLineListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaAppLineListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maAppLineListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findQnaList(HttpServletRequest request, MaAppLineListForm maAppLineListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	MaAppLineListService maAppLineListService = (MaAppLineListService) getBean("maAppLineListService");        

    	MaAppLineCommonDTO maAppLineCommonDTO = maAppLineListForm.getMaAppLineCommonDTO();
    	maAppLineCommonDTO.setCompNo(getUser(request).getCompNo());
    	maAppLineCommonDTO.setEnterBy(getUser(request).getEmpId());

    	//Paging
    	maAppLineCommonDTO.setIsLoadMaxCount("Y".equals(maAppLineListForm.getIsLoadMaxCount())?true:false);
    	maAppLineCommonDTO.setFirstRow(maAppLineListForm.getFirstRow());
    	maAppLineCommonDTO.setOrderBy(maAppLineListForm.getOrderBy());
    	maAppLineCommonDTO.setDirection(maAppLineListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maAppLineListService.findQnaList(maAppLineCommonDTO, getUser(request));
 
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maAppLineListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maAppLineListService.findTotalCount(maAppLineCommonDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, maAppLineListForm.getListId(),maAppLineListForm.getCurrentPageId(), maAppLineListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaAppLineListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineListForm
     * @param request
     */
    private void deleteQna(HttpServletRequest request, MaAppLineListForm maAppLineListForm) throws Exception
    {
    	MaAppLineListService maAppLineListService = (MaAppLineListService) getBean("maAppLineListService");        

    	String[] deleteRows = maAppLineListForm.getDeleteRows();    // sheet 내역
        
    	maAppLineListService.deleteQna(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
