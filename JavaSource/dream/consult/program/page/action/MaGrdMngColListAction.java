package dream.consult.program.page.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.ass.base.action.AssBaseListAction;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.form.MaGrdMngColListForm;
import dream.consult.program.page.service.MaGrdMngColListService;

/**
 * 칼럼 목록
 * @author  kim21017
 * @version $Id: MaGrdMngColListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maGrdMngColList" name="maGrdMngColListForm"
 *                input="/dream/consult/program/page/maGrdMngColList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPgMngGrdColList" name="maGrdMngColListForm"
 *                input="/dream/consult/program/page/maPgMngGrdColList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPgGrdColMngList" name="maGrdMngColListForm"
 *                input="/dream/consult/program/page/maPgGrdColMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maGrdMngColList" path="/dream/consult/program/page/maGrdMngColList.jsp"
 *                        redirect="false"
 */
public class MaGrdMngColListAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int GRD_COL_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int GRD_COL_LIST_DELETE 		= 1002;
    /** 시스템셋팅Y */
    public static final int GRD_COL_LIST_SYSY         = 1003;
    /** 시스템셋팅N */
    public static final int GRD_COL_LIST_SYSN         = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaGrdMngColListForm maGrdMngColListForm = (MaGrdMngColListForm) form;
        
        switch (maGrdMngColListForm.getStrutsAction())
        {
        
            case MaGrdMngColListAction.GRD_COL_LIST_FIND:
                findColList(request,response, maGrdMngColListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaGrdMngColListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maGrdMngColListForm.getListId(), maGrdMngColListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaGrdMngColListAction.GRD_COL_LIST_DELETE:
            	deleteColList(request,maGrdMngColListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaGrdMngColListAction.GRD_COL_LIST_SYSY:
                sysYColList(request,maGrdMngColListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaGrdMngColListAction.GRD_COL_LIST_SYSN:
                sysNColList(request,maGrdMngColListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaGrdMngColListAction.BASE_GRID_EXPORT:
                findColList(request,response, maGrdMngColListForm, true);
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
     * @version $Id: MaGrdMngColListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdMngColListForm
     * @throws Exception
     */
    private void findColList(HttpServletRequest request,HttpServletResponse response, MaGrdMngColListForm maGrdMngColListForm, boolean excelExport) throws Exception
    {
        MaGrdMngColListService maGrdMngColListService = (MaGrdMngColListService) getBean("maGrdMngColListService");
        MaGrdMngCommonDTO maGrdMngCommonDTO = maGrdMngColListForm.getMaGrdMngCommonDTO();
        
        //Paging
        maGrdMngCommonDTO.setIsLoadMaxCount("Y".equals(maGrdMngColListForm.getIsLoadMaxCount())?true:false);
        maGrdMngCommonDTO.setFirstRow(maGrdMngColListForm.getFirstRow());
        maGrdMngCommonDTO.setOrderBy(maGrdMngColListForm.getOrderBy());
        maGrdMngCommonDTO.setDirection(maGrdMngColListForm.getDirection());
        
        List resultList = maGrdMngColListService.findColList(maGrdMngCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maGrdMngColListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maGrdMngColListService.findTotalCount(maGrdMngCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maGrdMngColListForm.getListId(),maGrdMngColListForm.getCurrentPageId(), maGrdMngColListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaGrdMngColListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdMngColListForm
     * @throws Exception
     */
    private void deleteColList(HttpServletRequest request, MaGrdMngColListForm maGrdMngColListForm) throws Exception
    {
    	MaGrdMngColListService maGrdMngColListService = (MaGrdMngColListService) getBean("maGrdMngColListService");
        
    	String[] deleteRows = maGrdMngColListForm.getDeleteRows();
    
    	maGrdMngColListService.deleteColList(deleteRows);
    	
    	setAjaxStatus(request);
    }
    /**
     * setSystemY
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maGrdMngColListForm
     * @throws Exception
     */
    private void sysYColList(HttpServletRequest request, MaGrdMngColListForm maGrdMngColListForm) throws Exception
    {
        MaGrdMngColListService maGrdMngColListService = (MaGrdMngColListService) getBean("maGrdMngColListService");
        
        String[] deleteRows = maGrdMngColListForm.getDeleteRows();
    
        maGrdMngColListService.sysYColList(deleteRows);
        
        setAjaxStatus(request);
    }
    /**
     * setSystemN
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maGrdMngColListForm
     * @throws Exception
     */
    private void sysNColList(HttpServletRequest request, MaGrdMngColListForm maGrdMngColListForm) throws Exception
    {
        MaGrdMngColListService maGrdMngColListService = (MaGrdMngColListService) getBean("maGrdMngColListService");
        
        String[] deleteRows = maGrdMngColListForm.getDeleteRows();
    
        maGrdMngColListService.sysNColList(deleteRows);
        
        setAjaxStatus(request);
    }
}