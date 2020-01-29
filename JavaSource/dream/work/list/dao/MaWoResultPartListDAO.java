package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartListDTO;

/**
 * 작업결과 투입자재 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultPartListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultPartListDTO
     * @param loginUser
     * @return List
     */
    public List findPartList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPartListDTO maWoResultPartListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultPartListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePartList(String id, String compNo);

	public int updateEmgPart(String woPartId, String compNo);
	
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPartListDTO maWoResultPartListDTO, User user) throws Exception;
}