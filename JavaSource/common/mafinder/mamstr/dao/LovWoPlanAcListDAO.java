package common.mafinder.mamstr.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dto.LovWoPlanAcListDTO;

/**
 * 작업 팝업
 * @author  kim21017
 * @version $Id: LovWoPlanAcListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovWoPlanAcListDAO
{
    /**
     * 작업 검색
     * @author  kim21017
     * @version $Id: LovWoPlanAcListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovWoPlanAcListDTO
     * @param loginUser
     * @return
     */
    public List findWoPlanAcList(LovWoPlanAcListDTO lovWoPlanAcListDTO, User loginUser);

	public List findWoAcList(LovWoPlanAcListDTO lovWoPlanAcListDTO, User user, Map<String, String> conditionMap);
	
	public String findWoListTotalCount(LovWoPlanAcListDTO lovWoPlanAcListDTO, User user, Map<String, String> conditionMap);

}