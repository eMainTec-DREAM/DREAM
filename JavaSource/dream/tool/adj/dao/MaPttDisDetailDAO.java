package dream.tool.adj.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.tool.adj.dto.MaPttDisDetailDTO;

/**
 * ���ⱸ�ݳ� - �� dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface MaPttDisDetailDAO extends BaseJdbcDaoSupportIntf
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
    public MaPttDisDetailDTO findDetail(User user, String ptdisuseListId);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     */
    public int insertDetail(MaPttDisDetailDTO maPttDisDetailDTO);
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     */
    public int updateDetail(MaPttDisDetailDTO maPttDisDetailDTO);
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPttDisDetailDTO maPttDisDetailDTO);
    
    /**
     * ���� ��ȸ
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     */
    public String findPtDisListStatus(String compNo, String ptdisuseListId);
    
    public int updatePtDisList(MaPttDisDetailDTO maPttDisDetailDTO);
    
    public int insertPtIssHist(String ptIssHistListId, MaPttDisDetailDTO maPttDisDetailDTO,  String ptissMode);
    
    public void updatePtStock(String partId,String disQty,String wcodeId,String compNo);
    
    public void cancelPtStock(MaPttDisDetailDTO maPttDisDetailDTO);
    
    public List findItemList(MaPttDisDetailDTO maPttDisDetailDTO);
    
    /**
     *  SP_PT_INSTOCK ���ν��� ȣ�� 
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
}