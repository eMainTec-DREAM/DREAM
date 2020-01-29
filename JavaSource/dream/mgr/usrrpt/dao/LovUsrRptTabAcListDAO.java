package dream.mgr.usrrpt.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.usrrpt.dto.LovUsrRptTabAcListDTO;

/**
 * LOV - List DAO
 * @author youngjoo38
 * @version $Id: LovUsrRptTabAcListDAO.java,v 1.0 2017/09/12 16:14:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface LovUsrRptTabAcListDAO
{
	/**
	 * FIND LIST
	 * @param lovUsrRptTabAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param lovUsrRptTabAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}