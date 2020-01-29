package dream.asset.categ.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgAsmbListDTO;

/**
 * 설비종류 작업부위 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovEqCtgAsmbListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgAsmbListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgAsmbList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User loginUser);

    public List findEqCtgAsmbACList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User loginUser, Map<String, String> conditionMap);
    
    /**
     * FIND TOTAL LIST
     * @param lovEqCtgAsmbListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User user) throws Exception;

}