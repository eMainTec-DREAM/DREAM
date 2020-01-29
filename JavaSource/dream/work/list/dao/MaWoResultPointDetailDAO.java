package dream.work.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointDetailDTO;

/**
 * 작업결과-검사항목 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultPointDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPointId
     * @param compNo
     * @return
     */
    public MaWoResultPointDetailDTO findDetail(String wkOrId, String woPointId, String pmPointId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPointDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultPointDetailDTO maWoResultPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, boolean inputFlag, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPointDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(MaWoResultPointDetailDTO maWoResultPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
}