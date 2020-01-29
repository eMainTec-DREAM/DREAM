package dream.work.list.dao;

import dream.work.list.dto.MaWoResultClnDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 설비 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultClnDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultClnDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woEquipId
     * @param compNo
     * @return
     */
    public MaWoResultClnDetailDTO findDetail(String wkOrId, String woEquipId, String compNo);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultClnDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultClnDetailDTO maWoResultClnDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultClnDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(MaWoResultClnDetailDTO maWoResultClnDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
}