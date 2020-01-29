package dream.rcm.system.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysFDefListDTO;

/**
 * 기능정의  dao
 * @author  kim21017
 * @version $Id: RcmSysFDefListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysFDefListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmSysFDefListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFDefListDTO rcmSysFDefListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmSysFDefListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo);

	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFDefListDTO rcmSysFDefListDTO, User loginUser);
}