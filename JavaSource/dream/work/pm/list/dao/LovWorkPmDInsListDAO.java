package dream.work.pm.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.work.pm.list.dto.LovWorkPmDInsListDTO;

/**
 * 점검항목 팝업
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 */
public interface LovWorkPmDInsListDAO
{
    /**
     * 검색
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWorkPmDInsListDTO
     * @param loginUser
     * @return
     */
 
	public List findEqCtgPartAcList(LovWorkPmDInsListDTO lovWorkPmDInsListDTO, User user, Map<String, String> conditionMap) throws Exception;
	public String findTotalCount(LovWorkPmDInsListDTO lovWorkPmDInsListDTO, User user, Map<String, String> conditionMap) throws Exception;
}