package dream.tool.adj.dao;

import java.util.List;

import common.bean.User;
import dream.tool.adj.dto.MaPttDisCommonDTO;

/**
 * dao
 * @author  kim21017
 * @version $Id: MaPttDisListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPttDisListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPttDisListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisCommonDTO
     * @return List
     */
    public List findDisList(MaPttDisCommonDTO maPttDisCommonDTO, User user);
    
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public String findPtDisListStatus(String compNo, String ptdisuselistId);

    /**
     * delete
     * @author kim21017
     * @version $Id: MaAppLineListDAO.java,v 1.0 2015   5/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteDis(String compNo, String id);


	public String findTotalCount(MaPttDisCommonDTO maPttDisCommonDTO, User user);
}