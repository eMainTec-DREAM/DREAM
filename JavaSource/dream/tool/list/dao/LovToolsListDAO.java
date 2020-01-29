package dream.tool.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.tool.list.dto.LovToolsListDTO;

/**
 * 자재검색 팝업
 * @author  kim21017
 * @version $Id: LovToolsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovToolsListDAO
{
    /**
     * 자재 검색
     * @author  kim21017
     * @version $Id: LovToolsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovToolsListDTO
     * @param loginUser
     * @return
     */
    public List findPartsList(LovToolsListDTO lovToolsListDTO, User loginUser);
    public List findToolAcList(LovToolsListDTO lovToolsListDTO, User loginUser, Map<String, String> conditionMap);
    public String findTotalCount(LovToolsListDTO lovToolsListDTO, User loginUser, Map<String, String> conditionMap) throws Exception;
}