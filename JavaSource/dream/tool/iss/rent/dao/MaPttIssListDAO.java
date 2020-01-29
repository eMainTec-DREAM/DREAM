package dream.tool.iss.rent.dao;

import java.util.List;

import common.bean.User;
import dream.tool.iss.rent.dto.MaPttIssCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttIssListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0 
     * 
     * @param maPttIssCommonDTO
     * @return List
     */
    public List findList(MaPttIssCommonDTO maPttIssCommonDTO,User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePtIss(String compNo, String wcodeId, String partId, String partGrade);
    

    public int deleteList(String compNo, String ptIssListId);

    public int deleteWoParts(String wopartId);

	public String findTotalCount(MaPttIssCommonDTO maPttIssCommonDTO, User user);
}