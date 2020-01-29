package dream.part.adj.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.adj.dto.MaPtFcRecDetailDTO;

/**
 * 무상입고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface MaPtFcRecDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param fcRecListId
     * @return
     */
    public MaPtFcRecDetailDTO findDetail(User user, String fcRecListId);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     */
    public int insertDetail(MaPtFcRecDetailDTO maPtFcRecDetailDTO);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateDetail(MaPtFcRecDetailDTO maPtFcRecDetailDTO);
    
    /**
     * detail 상태 Update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updatePtFcRecListStatus(MaPtFcRecDetailDTO maPtRecDetailDTO);
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     */
    public String validFcRecListNo(MaPtFcRecDetailDTO maPtFcRecDetailDTO);
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     */
    public String findFcRecListStatus(String compNo, String fcRecListId);
      
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPtFcRecDetailDTO maPtFcRecDetailDTO);

    public int updateGrDate(MaPtFcRecDetailDTO maPtFcRecDetailDTO, String compNo);

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