package dream.consult.program.btn.dao;

import dream.consult.program.btn.dto.MaBtnMngDetailDTO;

/**
 * 버튼 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaBtnMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaBtnMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaBtnMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaBtnMngDetailDTO findDetail(String buttonId);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaBtnMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailDTO
     * @return
     */
    public int insertDetail(MaBtnMngDetailDTO maBtnMngDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaBtnMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailDTO
     * @return
     */
    public int updateDetail(MaBtnMngDetailDTO maBtnMngDetailDTO);
}