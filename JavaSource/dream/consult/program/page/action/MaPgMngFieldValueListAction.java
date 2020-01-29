package dream.consult.program.page.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;
import dream.consult.program.page.form.MaPgMngFieldValueListForm;
import dream.consult.program.page.service.MaPgMngFieldValueListService;

/**
 * 화면별 필드 기본값 목록
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPgMngFieldValueList" name="maPgMngFieldValueListForm"
 *                input="/dream/consult/program/page/maPgMngFieldValueList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngFieldValueList" path="/dream/consult/program/page/maPgMngFieldValueList.jsp"
 *                        redirect="false"
 */
public class MaPgMngFieldValueListAction extends ConsultAuthAction
{
    /** 검색 */
    public static final int FIND_LIST					= 1001;
    /** 삭제 */
    public static final int DELETE_LIST					= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngFieldValueListForm maPgMngFieldValueListForm = (MaPgMngFieldValueListForm) form;
        
        switch (maPgMngFieldValueListForm.getStrutsAction())
        {
        
            case MaPgMngFieldValueListAction.FIND_LIST:
                findList(request,response, maPgMngFieldValueListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngFieldValueListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPgMngFieldValueListForm.getListId(), maPgMngFieldValueListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngFieldValueListAction.DELETE_LIST:
            	deleteList(request,maPgMngFieldValueListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngFieldValueListAction.BASE_GRID_EXPORT:
            	findList(request,response, maPgMngFieldValueListForm, true);
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
     * @version $Id: MaPgMngFieldValueListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngFieldValueListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaPgMngFieldValueListForm maPgMngFieldValueListForm, boolean excelExport) throws Exception 
    {
        MaPgMngFieldValueListService maPgMngFieldValueListService = (MaPgMngFieldValueListService) getBean("maPgMngFieldValueListService");
        
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngFieldValueListForm.getMaPgMngCommonDTO();
        MaPgMngFieldListDTO maPgMngFieldListDTO = maPgMngFieldValueListForm.getMaPgMngFieldListDTO();
        MaPgMngFieldDetailDTO maPgMngFieldDetailDTO = maPgMngFieldValueListForm.getMaPgMngFieldDetailDTO();
        MaPgMngFieldValueListDTO maPgMngFieldValueListDTO = maPgMngFieldValueListForm.getMaPgMngFieldValueListDTO();
        
        //Paging
        maPgMngFieldValueListDTO.setIsLoadMaxCount("Y".equals(maPgMngFieldValueListForm.getIsLoadMaxCount())?true:false);
        maPgMngFieldValueListDTO.setFirstRow(maPgMngFieldValueListForm.getFirstRow());
        maPgMngFieldValueListDTO.setOrderBy(maPgMngFieldValueListForm.getOrderBy());
        maPgMngFieldValueListDTO.setDirection(maPgMngFieldValueListForm.getDirection());
        
        List resultList = maPgMngFieldValueListService.findList(maPgMngCommonDTO, maPgMngFieldListDTO, maPgMngFieldDetailDTO, maPgMngFieldValueListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maPgMngFieldValueListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPgMngFieldValueListService.findTotalCount(maPgMngCommonDTO, maPgMngFieldListDTO, maPgMngFieldDetailDTO, maPgMngFieldValueListDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPgMngFieldValueListForm.getListId(),maPgMngFieldValueListForm.getCurrentPageId(), maPgMngFieldValueListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPgMngFieldValueListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngFieldValueListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaPgMngFieldValueListForm maPgMngFieldValueListForm) throws Exception
    {
    	MaPgMngFieldValueListService maPgMngFieldValueListService = (MaPgMngFieldValueListService) getBean("maPgMngFieldValueListService");
        
    	String[] deleteRows = maPgMngFieldValueListForm.getDeleteRows();
    
    	maPgMngFieldValueListService.deleteList(deleteRows, getUser(request));
        
    	setAjaxStatus(request);
    }
}