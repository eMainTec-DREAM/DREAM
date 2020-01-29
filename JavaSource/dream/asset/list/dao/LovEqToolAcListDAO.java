package dream.asset.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.list.dto.LovEqToolAcListDTO;

/**
 * LOV - List DAO
 * @author youngjoo38
 * @version $Id: LovEqToolAcListDAO.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface LovEqToolAcListDAO
{
	/**
	 * FIND LIST
	 * @param lovEqToolAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(LovEqToolAcListDTO lovEqToolAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param lovEqToolAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(LovEqToolAcListDTO lovEqToolAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}