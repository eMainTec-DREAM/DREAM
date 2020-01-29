package dream.consult.comp.list.action;


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
import dream.consult.comp.list.dto.MaCompMngCommonDTO;
import dream.consult.comp.list.form.MaCompMngListForm;
import dream.consult.comp.list.service.MaCompMngListService;

/**
 * 회사설정 - 목록 action
 * @author  kim21017
 * @version $Id: MaCompMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maCompMngList" name="maCompMngListForm"
 *                input="/dream/consult/comp/list/maCompMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCompMngList" path="/dream/consult/comp/list/maCompMngList.jsp"
 *                        redirect="false"
 */
public class MaCompMngListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int COMP_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int COMP_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaCompMngListForm maCompMngListForm = (MaCompMngListForm) form;
        
        switch (maCompMngListForm.getStrutsAction())
        {
            case MaCompMngListAction.COMP_LIST_FIND:
                findCompList(request, maCompMngListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaCompMngListAction.BASE_SET_HEADER:
                setHeader(request, response, maCompMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaCompMngListAction.COMP_LIST_DELETE:
            	deleteComp(request, maCompMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaCompMngListAction.BASE_GRID_EXPORT:
            	findCompList(request, maCompMngListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maCompMngList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaCompMngListForm maCompMngListForm) throws IOException
    {
        super.setHeader(request, response, maCompMngListForm.getListId(),maCompMngListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaCompMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maCompMngListForm
     * @param response
     * @throws Exception
     */
    private void findCompList(HttpServletRequest request, MaCompMngListForm maCompMngListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaCompMngListService maCompMngListService = (MaCompMngListService) getBean("maCompMngListService");        

    	MaCompMngCommonDTO maCompMngCommonDTO = maCompMngListForm.getMaCompMngCommonDTO();

    	//Paging
        maCompMngCommonDTO.setIsLoadMaxCount("Y".equals(maCompMngListForm.getIsLoadMaxCount())?true:false);
        maCompMngCommonDTO.setFirstRow(maCompMngListForm.getFirstRow());
        maCompMngCommonDTO.setOrderBy(maCompMngListForm.getOrderBy());
        maCompMngCommonDTO.setDirection(maCompMngListForm.getDirection());
        
        User user = getUser(request);
        
        //리스트를 조회한다.
        List resultList = maCompMngListService.findCompList(maCompMngCommonDTO,user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maCompMngListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maCompMngListService.findTotalCount(maCompMngCommonDTO,user);

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maCompMngListForm); 
        else CommonUtil.makeJsonResult(resultList, request, response, maCompMngListForm.getListId());
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaCompMngListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maCompMngListForm
     */
    private void deleteComp(HttpServletRequest request, MaCompMngListForm maCompMngListForm) throws Exception
    {
    	MaCompMngListService maCompMngListService = (MaCompMngListService) getBean("maCompMngListService");        

    	String[] deleteRows = maCompMngListForm.getDeleteRows();
    	
    	maCompMngListService.deleteComp(deleteRows);
        
        setAjaxStatus(request);
    }
}
