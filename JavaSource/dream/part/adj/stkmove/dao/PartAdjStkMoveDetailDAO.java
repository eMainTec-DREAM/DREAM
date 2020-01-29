package dream.part.adj.stkmove.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveDetailDTO;

/**
 * 재고이동 - 상세 dao
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface PartAdjStkMoveDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveCommonDTO
     * @return
     */
    public PartAdjStkMoveDetailDTO findDetail(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user) throws Exception;
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailDTO
     * @return
     */
    public int insertDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailDTO
     * @return
     */
    public int updateDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception;
    
    /**
     * 출고  History insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param ptIssHistId
     * @param issMode 
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertPtIssHistory(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, String ptIssHistId, String issMode, User user) throws Exception;
    
    /**
     *  SP_PT_OUTSTOCK 프로시져 호출 
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtOutStock(String ptIssHistId, User user) throws Exception;
    
    /**
     * 입고 History insert
     * @author ghlee
     * @version $Id:$
     * @param recMode 
     * @since   1.0
     * 
     * @param recQty
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertPtRecHistory(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, String ptRecHistId, String recMode, User user) throws Exception;
    
    /**
     *  SP_PT_INSTOCK 프로시져 호출 
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtInStock(String ptRecHistId, User user) throws Exception;
    
    
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailDTO
     * @param user 
     * @return
     */
    public int confirmDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception;

    public int cancelDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception;
    
}