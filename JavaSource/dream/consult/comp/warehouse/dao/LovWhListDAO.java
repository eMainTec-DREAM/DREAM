package dream.consult.comp.warehouse.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.warehouse.dto.LovWhListDTO;

/**
 * 사용창고 팝업
 * @author  kim21017
 * @version $Id: LovWhListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovWhListDAO
{
    /**
     * 사용창고 검색
     * @author  kim21017
     * @version $Id: LovWhListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovWhListDTO
     * @param loginUser
     * @return
     */
    public List findWhList(LovWhListDTO lovWhListDTO, User loginUser);

    public List findWhACList(LovWhListDTO lovWhListDTO, User user,
            Map<String, String> conditionMap);
    
    public String findTotalCount(LovWhListDTO lovWhListDTO, User user, Map<String, String> conditionMap);
}