package dream.rcm.system.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqListDTO;

/**
 * 설비설정  dao
 * @author  kim21017
 * @version $Id: RcmSysEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEqListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmSysEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo);
    public int deleteAsmbList(String id, String compNo);
    
    /**
     * input
     * @author  kim21017
     * @version $Id: RcmSysEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysEqListDTO
     * @return int
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, String multiKey);

	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqListDTO rcmSysEqListDTO, User user);
}