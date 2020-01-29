package dream.tool.rec.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.tool.rec.dto.MaPttRecDetailDTO;

/**
 * 구매입고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface MaPttRecDetailDAO extends BaseJdbcDaoSupportIntf
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
    public MaPttRecDetailDTO findDetail(User user, String prRecListId);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public int insertDetail(MaPttRecDetailDTO maPttRecDetailDTO);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public int updateDetail(MaPttRecDetailDTO maPttRecDetailDTO);
    
    /**
     * detail 상태 Update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public int updatePtRecListStatus(MaPttRecDetailDTO maPttRecDetailDTO);
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPttRecDetailDTO maPttRecDetailDTO);
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public String findPrRecListStatus(String compNo, String prRecListId);
      
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPttRecDetailDTO maPttRecDetailDTO);
    
    /**
     * 입고완료 전에 SP_PT_INSTOCK 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtInstock(String compNo, String ptRecHistId) throws Exception;
}