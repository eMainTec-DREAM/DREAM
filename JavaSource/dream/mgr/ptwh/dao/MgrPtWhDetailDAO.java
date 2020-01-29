package dream.mgr.ptwh.dao;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhDetailDTO;

/**
 * 부품창고 - Detail DAO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public interface MgrPtWhDetailDAO
{
    /**
     * FIND DETAIL
     * @param mgrPtWhCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrPtWhDetailDTO findDetail(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param mgrPtWhDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updatePtWhDetail(MgrPtWhDetailDTO mgrPtWhDetailDTO, User user) throws Exception;
    
}