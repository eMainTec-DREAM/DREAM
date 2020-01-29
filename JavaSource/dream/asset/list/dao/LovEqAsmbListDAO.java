package dream.asset.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.list.dto.LovEqAsmbListDTO;

/**
 * 설비부위 팝업
 * @author  hyosun
 * @version $Id: LovEqAsmbListDAO.java,v 1.0 2016/01/18 00:16:44 hyosun Exp $
 * @since   1.0
 */
public interface LovEqAsmbListDAO
{

    /**
     * 설비부위 검색
     * @author  hyosun
     * @version $Id: LovEqAsmbListDAO.java,v 1.0 2016/01/18 00:16:44 hyosun Exp $
     * @since   1.0
     * 
     * @param lovEqAsmbListDTO
     * @param user
     * @param conditionMap
     * @return
     */
    public List findEqAsmbAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User user, Map<String, String> conditionMap);

    public List findEqAsmbByPmAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User user, Map<String, String> conditionMap);
}