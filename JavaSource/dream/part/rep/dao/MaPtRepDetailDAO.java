package dream.part.rep.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;

/**
 * 부품수리 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface MaPtRepDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param loginUser
     * @return
     */
    public MaPtRepDetailDTO findDetail(MaPtRepCommonDTO maPtRepCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public int insertDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser);
    
    /**
     * detail 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public int updateDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser);
    
    /**
     * update 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public int updatePtRepairListStatus(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser);
    
    /**
     * find status 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public String findPtRepairListStatus(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser);
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public String validPtRepairListNo(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser);
    
  /**
   * 입고 History insert
   * @author ssong
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRecDetailDTO
   * @return
   */
  public int insertRecHistory(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser);
  
  /**
   * 입고완료 전에 SP_PT_INSTOCK 프로시져 호출 
   * @author  ssong
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRepDetailDTO
   * @param loginUser
   * @return
   * @throws Exception
   */
  public int executeSpPtInstock(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception;

  /**
   * 입고취소  SP_PT_OUTSTOCK 프로시져 호출 
   * @author  kim21017
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRepDetailDTO
   * @param loginUser
   * @return
   * @throws Exception
   */
  public int executeSpPtOutstock(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception;
  
  /**
   * update 
   * @author ssong
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRepDetailDTO
   * @return
   */
  public int updateEquipment(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser, String status);
  
  /**
   * 입고 History insert
   * @author ssong
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRecDetailDTO
   * @return
   */
  public int insertEqHistory(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser, String status);
}