package dream.part.adj.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.adj.dto.MaPtFcRecDetailDTO;

/**
 * �����԰� - �� dao
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
     * detail ���� Update
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
     * ���� ��ȸ
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     */
    public String findFcRecListStatus(String compNo, String fcRecListId);
      
    /**
     * �԰� History insert
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