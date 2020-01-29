package dream.part.rep.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;

/**
 * ��ǰ���� - �� dao
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
   * �԰� History insert
   * @author ssong
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRecDetailDTO
   * @return
   */
  public int insertRecHistory(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser);
  
  /**
   * �԰�Ϸ� ���� SP_PT_INSTOCK ���ν��� ȣ�� 
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
   * �԰����  SP_PT_OUTSTOCK ���ν��� ȣ�� 
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
   * �԰� History insert
   * @author ssong
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRecDetailDTO
   * @return
   */
  public int insertEqHistory(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser, String status);
}