package dream.rcm.taskmap.dao;

import common.spring.BaseJdbcDaoSupportIntf;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapDetailDTO;

/**
 * 질의 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: RcmTaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface RcmTaskMapDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmTaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapCommonDTO
     * @return
     */
    public RcmTaskMapDetailDTO findDetail(RcmTaskMapCommonDTO rcmTaskMapCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmTaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapDetailDTO
     * @return
     */
    public int insertDetail(RcmTaskMapDetailDTO rcmTaskMapDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmTaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapDetailDTO
     * @return
     */
    public int updateDetail(RcmTaskMapDetailDTO rcmTaskMapDetailDTO);

	
}