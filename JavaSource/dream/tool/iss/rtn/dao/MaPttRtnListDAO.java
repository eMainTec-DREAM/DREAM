package dream.tool.iss.rtn.dao;

import java.util.List;

import common.bean.User;
import dream.tool.iss.rtn.dto.MaPttRtnCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttRtnListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0 
     * 
     * @param maPttRtnCommonDTO
     * @return List
     */
    public List findList(MaPttRtnCommonDTO maPttRtnCommonDTO, User user);
    
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
    
    public int deleteList(String compNo, String ptRtnListId);

    public int deleteWoParts(String wopartId);

	public String findTotalCount(MaPttRtnCommonDTO maPttRtnCommonDTO, User user);
}