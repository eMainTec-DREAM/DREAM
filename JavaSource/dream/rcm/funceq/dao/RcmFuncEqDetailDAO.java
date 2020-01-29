package dream.rcm.funceq.dao;

import common.spring.BaseJdbcDaoSupportIntf;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqDetailDTO;

/**
 * 질의 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: RcmFuncEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface RcmFuncEqDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFuncEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public RcmFuncEqDetailDTO findDetail(RcmFuncEqCommonDTO rcmFuncEqCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqDetailDTO
     * @return
     */
    public int insertDetail(RcmFuncEqDetailDTO rcmFuncEqDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFuncEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqDetailDTO
     * @return
     */
    public int updateDetail(RcmFuncEqDetailDTO rcmFuncEqDetailDTO);


	public int insertRcmFunc(RcmFuncEqDetailDTO rcmFuncEqDetailDTO);
	
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFuncEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqDetailDTO
     * @return
     */
    public int updateRcmFunc(RcmFuncEqDetailDTO rcmFuncEqDetailDTO);
	
}