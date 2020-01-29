package dream.consult.comp.time.dao;

import common.bean.User;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDetailDTO;

/**
 * 가동시간설정 - 상세
 * 
 * @author kim21017
 * @version $Id: MaLineTimeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaLineTimeDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaLineTimeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeCommonDTO
     * @param compNo
     * @return
     */
    public MaLineTimeDetailDTO findDetail(MaLineTimeCommonDTO maLineTimeCommonDTO, User user);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaLineTimeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDetailDTO
     * @return
     */
    public int updateDetail(MaLineTimeDetailDTO maLineTimeDetailDTO);

    public int insertDetail(MaLineTimeDetailDTO maLineTimeDetailDTO, User user);
}