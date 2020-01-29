package dream.consult.comp.time.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.time.dto.LovLnWrkAcListDTO;

/**
 * 가동달력 LOV - List DAO
 * @author youngjoo38
 * @version $Id: LovLnWrkAcListDAO.java,v 1.0 2017/12/04 11:08:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface LovLnWrkAcListDAO
{
	/**
	 * FIND LIST
	 * @param lovLnWrkAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(LovLnWrkAcListDTO lovLnWrkAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param lovLnWrkAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(LovLnWrkAcListDTO lovLnWrkAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}