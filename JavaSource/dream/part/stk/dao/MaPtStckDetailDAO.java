package dream.part.stk.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;

/**
 * 자재재고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPtStckDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 */
public interface MaPtStckDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckCommonDTO
     * @return
     */
    public MaPtStckDetailDTO findDetail(MaPtStckCommonDTO maPtStckCommonDTO);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public int insertPtStock(MaPtStckDetailDTO maPtStckDetailDTO,String stockQty,String partGrade);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public int insertPtSaftyStock(MaPtStckDetailDTO maPtStckDetailDTO);
    
    /**
     * detail stock
     * stock_qty는 업데이트 하지 않는다. 입고확정,취소시 프로시져 통해 변경됨. 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public int updatePtStock(MaPtStckDetailDTO maPtStckDetailDTO, String partGrade, User loginUser);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public int updatePtSaftyStock(MaPtStckDetailDTO maPtStckDetailDTO);
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String validPtStck(MaPtStckDetailDTO maPtStckDetailDTO);
    
    /**
     * find stockQty
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String getStockQty(MaPtStckDetailDTO maPtStckDetailDTO, String partGrade);

    /**
     * find all stockQty
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String getAllStockQty(MaPtStckDetailDTO maPtStckDetailDTO);
    
    /**
     * find stockGrade
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String[] getStockGrade(MaPtStckDetailDTO maPtStckDetailDTO);
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String validPtSaftyStck(MaPtStckDetailDTO maPtStckDetailDTO);
    
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
            MaPtStckDetailDTO maPtStckDetailDTO, User loginUser);
    
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
            MaPtStckDetailDTO maPtStckDetailDTO, User loginUser);
    
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

    public List findReportPtDetail(MaPtStckDetailDTO maPtStckDetailDTO,User user);

    public List findReportPartList(MaPtStckDetailDTO maPtStckDetailDTO,User user);
}