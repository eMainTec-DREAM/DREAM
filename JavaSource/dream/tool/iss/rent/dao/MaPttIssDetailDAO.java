package dream.tool.iss.rent.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.tool.iss.rent.dto.MaPttIssDetailDTO;

/**
 * 구매입고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface MaPttIssDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param prRecListId
     * @return
     */
    public MaPttIssDetailDTO findDetail(User user, String ptIssListId);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public int insertDetail(MaPttIssDetailDTO maPttIssDetailDTO);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public int updateDetail(MaPttIssDetailDTO maPttIssDetailDTO);
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPttIssDetailDTO maPttIssDetailDTO);
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public String findPtIssListStatus(String compNo, String ptIssListId);
      
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPttIssDetailDTO maPttIssDetailDTO);
    
    public int updatePtIssList(MaPttIssDetailDTO maPttIssDetailDTO);
    
    public int insertPtIssHist(MaPttIssDetailDTO maPttIssDetailDTO, String ptisshistId, String ptissMode);
    
    public void execSP_PT_OUTSTOCK(MaPttIssDetailDTO maPttIssDetailDTO, String ptisshistId);
    
    public int insertPtRentStock(MaPttIssDetailDTO maPttIssDetailDTO);
    
    public int updatePtRentStock(MaPttIssDetailDTO maPttIssDetailDTO);
}