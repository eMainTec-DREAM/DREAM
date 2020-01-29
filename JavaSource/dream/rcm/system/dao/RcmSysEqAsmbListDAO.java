package dream.rcm.system.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 설비부위 목록 dao
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEqAsmbListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmSysEqAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysFEnvListDTO
     * @return List
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqAsmbListDTO rcmSysFEnvListDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmSysEqAsmbListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String id);
    
    /**
     * input
     * @author  kim21017
     * @version $Id: RcmSysEqAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysFEnvListDTO
     * @param multiKey
     * @return
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqAsmbListDTO rcmSysFEnvListDTO, String multiKey);
}