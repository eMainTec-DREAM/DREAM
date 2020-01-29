package dream.rcm.pmtask.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.rcm.pmtask.dto.RcmPmtaskAcListDTO;

/**
 * LOV - List DAO
 * @author kim21017
 * @version $Id: RcmPmtaskAcListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface RcmPmtaskAcListDAO
{
	/**
	 * FIND LIST
	 * @param rcmPmtaskAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(RcmPmtaskAcListDTO rcmPmtaskAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param rcmPmtaskAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(RcmPmtaskAcListDTO rcmPmtaskAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}