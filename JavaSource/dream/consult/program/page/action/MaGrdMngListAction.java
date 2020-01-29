package dream.consult.program.page.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.form.MaGrdMngListForm;
import dream.consult.program.page.service.MaGrdMngListService;

/**
 * 화면 - 목록 action
 * @author  jung7126
 * @version $Id: MaGrdMngListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maGrdMngList" name="maGrdMngListForm"
 *                input="/dream/consult/program/page/maGrdMngList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPgMngGrdList" name="maGrdMngListForm"
 *                input="/dream/consult/program/page/maPgMngGrdList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maGrdMngList" path="/dream/consult/program/page/maGrdMngList.jsp"
 *                        redirect="false"
 */
public class MaGrdMngListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int GRD_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int GRD_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaGrdMngListForm maGrdMngListForm = (MaGrdMngListForm) form;
        
        switch (maGrdMngListForm.getStrutsAction())
        {
            case MaGrdMngListAction.GRD_LIST_FIND:
            	findPgList(request, maGrdMngListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaGrdMngListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maGrdMngListForm.getListId(), maGrdMngListForm.getCurrentPageId()); 
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaGrdMngListAction.GRD_LIST_DELETE:
            	deletePage(request, maGrdMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaGrdMngListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdMngListForm
     * @throws Exception
     */
    private void findPgList(HttpServletRequest request, MaGrdMngListForm maGrdMngListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaGrdMngListService maGrdMngListService = (MaGrdMngListService) getBean("maGrdMngListService");        

    	MaPgMngCommonDTO maPgMngCommonDTO = maGrdMngListForm.getMaPgMngCommonDTO();
    	MaGrdMngCommonDTO maGrdMngCommonDTO = maGrdMngListForm.getMaGrdMngCommonDTO();
    	maGrdMngCommonDTO.setUserLang(getUser(request).getLangId());

    	//BaseDTO
    	//스크롤 load max값
    	maPgMngCommonDTO.setIsLoadMaxCount("Y".equals(maGrdMngListForm.getIsLoadMaxCount())?true:false);
    	//다음 스크롤처리시 어디부터 또 다음 load max값 가져와야하므로 첫 줄 알아야한다.
    	maPgMngCommonDTO.setFirstRow(maGrdMngListForm.getFirstRow());
    	//순서
    	maPgMngCommonDTO.setOrderBy(maGrdMngListForm.getOrderBy());
    	//ascending, descending
    	maPgMngCommonDTO.setDirection(maGrdMngListForm.getDirection());
    	
        List resultList = maGrdMngListService.findList(maPgMngCommonDTO, maGrdMngCommonDTO);

        //Paging 
        String totalCount = "";
        //gird list totalCount 처리
        if(Integer.parseInt(
        		maGrdMngListForm.getIsTotalCount()) == 0 && !excelExport) totalCount =  maGrdMngListService.findTotalCount(maPgMngCommonDTO, maGrdMngCommonDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maGrdMngListForm.getListId(),maGrdMngListForm.getCurrentPageId(), maGrdMngListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  jung7126
     * @version $Id: MaGrdMngListAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngListForm
     * @param request
     */
    private void deletePage(HttpServletRequest request, MaGrdMngListForm maGrdMngListForm) throws Exception
    {
    	MaGrdMngListService maGrdMngListService = (MaGrdMngListService) getBean("maGrdMngListService");
        
    	String[] deleteRows = maGrdMngListForm.getDeleteRows();    // sheet 내역
        
        maGrdMngListService.deleteList(deleteRows);
        
        setAjaxStatus(request);
    }
}
