package dream.consult.consult.menu.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.consult.menu.dto.EhMenuValLovDTO;

/**
 * 컨설트 메뉴 LOV - List DAO
 * @author kim21017
 * @version $Id: EhMenuValLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface EhMenuValLovDAO
{
	/**
	 * FIND LIST
	 * @param ehMenuValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(EhMenuValLovDTO ehMenuValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    
}