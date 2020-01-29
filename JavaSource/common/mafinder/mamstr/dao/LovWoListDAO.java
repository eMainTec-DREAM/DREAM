package common.mafinder.mamstr.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dto.LovWoListDTO;

/**
 * 작업 팝업
 * @author  kim21017
 * @version $Id: LovWoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovWoListDAO
{
    /**
     * 작업 검색
     * @author  kim21017
     * @version $Id: LovWoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovWoListDTO
     * @param loginUser
     * @return
     */
    public List findWoList(LovWoListDTO lovWoListDTO, User loginUser);

	public List findWoAcList(LovWoListDTO lovWoListDTO, User user, Map<String, String> conditionMap);
	
	public String findWoListTotalCount(LovWoListDTO lovWoListDTO, User user, Map<String, String> conditionMap);
}