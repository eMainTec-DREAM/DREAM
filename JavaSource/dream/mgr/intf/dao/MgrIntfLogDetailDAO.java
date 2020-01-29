package dream.mgr.intf.dao;

import common.bean.User;
import dream.mgr.intf.dto.MgrIntfLogDetailDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;

/**
 * Interface Log Page - Detail DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrIntfLogDetailDAO
{
    /**
     * FIND DETAIL
     * @param mgrIntfLogListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrIntfLogDetailDTO findDetail(MgrIntfLogListDTO mgrIntfLogListDTO, User user) throws Exception;
}