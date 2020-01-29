package dream.rcm.system.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEmpListDTO;

/**
 * ºÐ¼®ÀÚ  dao
 * @author  kim21017
 * @version $Id: RcmSysEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEmpListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmSysEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmSysEmpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo);
    
    /**
     * input
     * @author  kim21017
     * @version $Id: RcmSysEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysEmpListDTO
     * @return List
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, String multiKey);

	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEmpListDTO rcmSysEmpListDTO, User user);
}