package dream.consult.comp.cdsys.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.cdsys.dto.CdSysCodeParentLovDTO;

/**
 * 시스템코드 부모 LOV - List DAO
 * @author kim21017
 * @version $Id: CdSysCodeParentLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface CdSysCodeParentLovDAO
{
	/**
	 * FIND LIST
	 * @param cdSysCodeParentLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(CdSysCodeParentLovDTO cdSysCodeParentLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param cdSysCodeParentLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(CdSysCodeParentLovDTO cdSysCodeParentLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}