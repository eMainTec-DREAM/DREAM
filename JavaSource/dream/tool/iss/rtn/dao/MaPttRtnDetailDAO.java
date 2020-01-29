package dream.tool.iss.rtn.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.tool.iss.rtn.dto.MaPttRtnDetailDTO;

/**
 * ���ⱸ�ݳ� - �� dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface MaPttRtnDetailDAO extends BaseJdbcDaoSupportIntf
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
    public MaPttRtnDetailDTO findDetail(User user, String ptRtnListId);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public int insertDetail(MaPttRtnDetailDTO maPttRtnDetailDTO);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public int updateDetail(MaPttRtnDetailDTO maPttRtnDetailDTO);
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPttRtnDetailDTO maPttRtnDetailDTO);
    
    /**
     * ���� ��ȸ
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public String findPtRtnListStatus(String compNo, String ptRtnListId);
      
    /**
     * �ݳ� History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPttRtnDetailDTO maPttRtnDetailDTO);
    
    public int updatePtRtnList(MaPttRtnDetailDTO maPttRtnDetailDTO);
    
    public int insertPtIssHist(MaPttRtnDetailDTO maPttRtnDetailDTO, String ptisshistId, String ptissMode);
    
    public void updatePtStock(MaPttRtnDetailDTO maPttRtnDetailDTO);
    
    public void cancelPtStock(MaPttRtnDetailDTO maPttRtnDetailDTO);

    public int insertPtRentStock(MaPttRtnDetailDTO maPttRtnDetailDTO);
    
    public int cancelPtRentStock(MaPttRtnDetailDTO maPttRtnDetailDTO);
}