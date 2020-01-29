package dream.rcm.funceq.dao;

import dream.rcm.funceq.dto.RcmFuncEqItemDetailDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * 답변 상세 dao
 * @author  kim21017
 * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFuncEqItemDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemListDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public RcmFuncEqItemDetailDTO findDetail(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public int updateDetail(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public int insertDetail(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO);
    
    /**
     *
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param woPartId
     * @return
     */
    public String[] getRcmEq(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO);
    
    /**
    *
    * @author  jung7126
    * @version $Id:$
    * @since   1.0
    * 
    * @param woPartId
    * @return
    */
   public String[] getRcmAsmb(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public int insertRcmEq(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public int insertRcmAsmb(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO, String rcmEqAsmbId);
}