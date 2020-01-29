package dream.consult.comp.user.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.user.dto.LovUsrGrpAcListDTO;

/**
 * LOV - List DAO
 * @author youngjoo38
 * @version $Id: LovUsrGrpAcListDAO.java,v 1.0 2017/09/12 16:14:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface LovUsrGrpAcListDAO
{
	/**
	 * FIND LIST
	 * @param lovUsrGrpAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(LovUsrGrpAcListDTO lovUsrGrpAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param lovUsrGrpAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(LovUsrGrpAcListDTO lovUsrGrpAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}