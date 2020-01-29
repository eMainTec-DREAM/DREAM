package dream.mgr.intf.dao;

import common.bean.User;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfDetailDTO;

/**
 * Interface Page - Detail DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrIntfDetailDAO
{
    /**
     * FIND DETAIL
     * @param mgrIntfCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrIntfDetailDTO findDetail(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param mgrIntfDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MgrIntfDetailDTO mgrIntfDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param mgrIntfDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrIntfDetailDTO mgrIntfDetailDTO, User user) throws Exception;
    
}