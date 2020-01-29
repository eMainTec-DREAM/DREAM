package dream.rcm.system.dao;

import common.bean.User;

import dream.rcm.system.dto.RcmSysEqAsmbDetailDTO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 설비부위 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEqAsmbDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysEqAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbListDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public RcmSysEqAsmbDetailDTO findDetail(RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO, RcmSysCommonDTO rcmSysCommonDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysEqAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbDetailDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public int updateDetail(RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO, RcmSysCommonDTO rcmSysCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEqAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbDetailDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public int insertDetail(RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO, RcmSysCommonDTO rcmSysCommonDTO);
}