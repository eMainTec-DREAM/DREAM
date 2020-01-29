package dream.tool.rec.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.tool.rec.dto.MaPttRecDetailDTO;

/**
 * �����԰� - �� dao
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
     * detail ���� Update
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
     * ���� ��ȸ
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public String findPrRecListStatus(String compNo, String prRecListId);
      
    /**
     * �԰� History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPttRecDetailDTO maPttRecDetailDTO);
    
    /**
     * �԰�Ϸ� ���� SP_PT_INSTOCK ���ν��� ȣ�� 
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