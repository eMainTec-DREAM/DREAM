package dream.mgr.usrcd.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.usrcd.dto.CdUsrCodeParentLovDTO;

/**
 * 사용자코드 부모 LOV - List DAO
 * @author kim21017
 * @version $Id: CdUsrCodeParentLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface CdUsrCodeParentLovDAO
{
	/**
	 * FIND LIST
	 * @param cdUsrCodeParentLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param cdUsrCodeParentLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}