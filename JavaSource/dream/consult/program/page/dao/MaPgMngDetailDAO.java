package dream.consult.program.page.dao;

import common.bean.User;
import dream.consult.program.page.dto.MaPgMngDetailDTO;

/**
 * 화면 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaPgMngDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaPgMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaPgMngDetailDTO findDetail(String pageId, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailDTO
     * @return
     */
    public int insertDetail(MaPgMngDetailDTO maPgMngDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailDTO
     * @return
     */
    public int updateDetail(MaPgMngDetailDTO maPgMngDetailDTO);
}