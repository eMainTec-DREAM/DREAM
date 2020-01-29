package dream.tool.stk.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.dto.MaPttStckDetailDTO;

/**
 * 자재재고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPttStckDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 */
public interface MaPttStckDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckCommonDTO
     * @return
     */
    public MaPttStckDetailDTO findDetail(MaPttStckCommonDTO maPttStckCommonDTO);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public int insertPtStock(MaPttStckDetailDTO maPttStckDetailDTO,String stockQty,String partGrade);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public int insertPtSaftyStock(MaPttStckDetailDTO maPttStckDetailDTO);
    
    /**
     * detail stock
     * stock_qty는 업데이트 하지 않는다. 입고확정,취소시 프로시져 통해 변경됨. 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public int updatePtStock(MaPttStckDetailDTO maPttStckDetailDTO, String partGrade);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public int updatePtSaftyStock(MaPttStckDetailDTO maPttStckDetailDTO);
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String validPtStck(MaPttStckDetailDTO maPttStckDetailDTO);
    
    /**
     * find stockQty
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String getStockQty(MaPttStckDetailDTO maPttStckDetailDTO, String partGrade);

    /**
     * find all stockQty
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String getAllStockQty(MaPttStckDetailDTO maPttStckDetailDTO);
    
    /**
     * find stockGrade
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String[] getStockGrade(MaPttStckDetailDTO maPttStckDetailDTO);
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String validPtSaftyStck(MaPttStckDetailDTO maPttStckDetailDTO);
    
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param recQty
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertPtRecHistory(String ptRecHistId, String recQty, String partGrade, 
            MaPttStckDetailDTO maPttStckDetailDTO, User loginUser);
    
    /**
     * 출고  History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param ptIssHistId
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertPtIssHistory(String ptIssHistId, String issQty, String partGrade,
            MaPttStckDetailDTO maPttStckDetailDTO, User loginUser);
    
    /**
     *  SP_PT_INSTOCK 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtInStock(String compNo, String ptRecHistId) throws Exception;
    
    /**
     *  SP_PT_INSTOCK 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtOutStock(String compNo, String ptIssHistId) throws Exception;
}