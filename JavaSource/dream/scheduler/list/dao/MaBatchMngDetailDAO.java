package dream.scheduler.list.dao;

import dream.scheduler.list.dto.MaBatchMngCommonDTO;
import dream.scheduler.list.dto.MaBatchMngDetailDTO;

/**
 * 버튼 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaBatchMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaBatchMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaBatchMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngCommonDTO
     * @return
     */
    public MaBatchMngDetailDTO findDetail(MaBatchMngCommonDTO maBatchMngCommonDTO);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaBatchMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngDetailDTO
     * @return
     */
    public int insertDetail(MaBatchMngDetailDTO maBatchMngDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaBatchMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngDetailDTO
     * @return
     */
    public int updateDetail(MaBatchMngDetailDTO maBatchMngDetailDTO);
}