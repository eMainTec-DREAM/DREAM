package dream.part.adj.stktake.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 부품실사 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface PartAdjStkTakeDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeCommonDTO
     * @return
     */
    public PartAdjStkTakeDetailDTO findDetail(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO,User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     */
    public int insertDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO);
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     */
    public int updateDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO);

    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     */
    public String validPtSaftyStck(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO);
    

    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     */
    public int insertPtSaftyStock(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     */
    public int updatePtSaftyStock(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO);
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @param user 
     * @since   1.0
     * 
     * @param partAdjStkTakeListDTO
     * @return List
     */
    public List findItemList(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user);
    
    
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
            PartAdjStkTakeDetailDTO partAdjStkTakeDetailDAO, User loginUser, String ptStkTakeItemId);
    
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
    public int updateItemRecHistory(String ptRecHistId, String recQty, String partGrade, 
            PartAdjStkTakeDetailDTO partAdjStkTakeDetailDAO, User loginUser, String ptStkTakeItemId);
    
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
            PartAdjStkTakeDetailDTO partAdjStkTakeDetailDAO, User loginUser, String ptStkTakeItemId);
    
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
    public int updateItemIssHistory(String ptIssHistId, String issQty, String partGrade,
            PartAdjStkTakeDetailDTO partAdjStkTakeDetailDAO, User loginUser, String ptStkTakeItemId);
    
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
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @param user 
     * @return
     */
    public int confirmDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user) throws Exception;

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
    
}