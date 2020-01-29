package dream.tool.list.service;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.LovWoReqAcListDTO;
import dream.req.work.form.LovWoReqAcListForm;
import dream.tool.list.dto.LovToolsListDTO;
import dream.tool.list.form.LovToolsListForm;

/**
 * 자재팝업 Service
 * @author  kim21017
 * @version $Id: LovToolsListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovToolsListService
{

    /**
     * 자재검색
     * @author  kim21017
     * @version $Id: LovToolsListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovToolsListDTO
     * @param loginUser
     * @return
     */
    List findPartsList(LovToolsListDTO lovToolsListDTO, User loginUser);
    
    List findToolAcList(LovToolsListDTO lovToolsListDTO, User loginUser, LovToolsListForm lovToolsListForm);
    public String findTotalCount(LovToolsListDTO lovToolsListDTO, User loginUser, LovToolsListForm lovToolsListForm) throws Exception;
}