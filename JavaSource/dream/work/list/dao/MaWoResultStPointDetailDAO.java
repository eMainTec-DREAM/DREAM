package dream.work.list.dao;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointDetailDTO;

/**
 * 작업결과-작업필수검사항목 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultStPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultStPointDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultStPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPointId
     * @param compNo
     * @return
     */
    public MaWoResultStPointDetailDTO findDetail(String wkOrId, String woStPointId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultStPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultStPointDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultStPointDetailDTO maWoResultStPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
}