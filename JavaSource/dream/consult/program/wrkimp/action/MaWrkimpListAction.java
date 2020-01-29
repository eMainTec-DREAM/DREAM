package dream.consult.program.wrkimp.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;
import dream.consult.program.wrkimp.form.MaWrkimpListForm;
import dream.consult.program.wrkimp.service.MaWrkimpListService;

/**
 * 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maWrkImpList" name="maWrkimpListForm"
 *                input="/dream/consult/program/wrkimp/maWrkimpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWrkimpList" path="/dream/consult/program/wrkimp/maWrkImpList.jsp"
 *                        redirect="false"
 */
public class MaWrkimpListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int WRKIMP_LIST_FIND   = 1001;
    /** 삭제 */
    public static final int WRKIMP_LIST_DELETE = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWrkimpListForm maWrkimpListForm = (MaWrkimpListForm) form;

        switch (maWrkimpListForm.getStrutsAction())
        {
            case MaWrkimpListAction.BASE_SET_HEADER:
                setHeader(request, response, maWrkimpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWrkimpListAction.WRKIMP_LIST_FIND:
                findList(request, response, maWrkimpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;      
            case MaWrkimpListAction.WRKIMP_LIST_DELETE:
                deleteList(request, maWrkimpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;                
            case MaWrkimpListAction.BASE_GRID_EXPORT:
            	findList(request, response, maWrkimpListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maWrkimpList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWrkimpListForm maWrkimpListForm) throws IOException
    {
        super.setHeader(request, response, maWrkimpListForm.getListId(), maWrkimpListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maWrkimpListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaWrkimpListForm maWrkimpListForm, boolean excelExport)  throws IOException
    {
    	MaWrkimpListService maWrkimpListService = (MaWrkimpListService) getBean("maWrkimpListService");        

    	MaWrkimpCommonDTO maWrkimpCommonDTO = maWrkimpListForm.getMaWrkimpCommonDTO();
    	
    	//Paging
        maWrkimpCommonDTO.setIsLoadMaxCount("Y".equals(maWrkimpListForm.getIsLoadMaxCount())?true:false);
        maWrkimpCommonDTO.setFirstRow(maWrkimpListForm.getFirstRow());
        maWrkimpCommonDTO.setOrderBy(maWrkimpListForm.getOrderBy());
        maWrkimpCommonDTO.setDirection(maWrkimpListForm.getDirection());
        
        User user = getUser(request);

        //리스트를 조회한다.
        List resultList = maWrkimpListService.findWrkimpList(maWrkimpCommonDTO,getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWrkimpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWrkimpListService.findTotalCount(maWrkimpCommonDTO,user);

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maWrkimpListForm); 
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
        // 조회한 List 를 form에 셋팅한다.
    }
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWrkimpListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaWrkimpListForm maWrkimpListForm) throws Exception
    {
        MaWrkimpListService maWrkimpListService = (MaWrkimpListService) getBean("maWrkimpListService");        

        String[] deleteRows = maWrkimpListForm.getDeleteRows();    // sheet 내역
        
        maWrkimpListService.deleteList(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
