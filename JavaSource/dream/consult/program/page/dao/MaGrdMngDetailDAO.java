package dream.consult.program.page.dao;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdMngDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author jung7126
 * @version $Id: MaGrdMngDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 */
public interface MaGrdMngDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaGrdMngDetailDTO findDetail(String pgGridId,User user);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDetailDTO
     * @return
     */
    public int insertDetail(MaGrdMngDetailDTO maGrdMngDetailDTO);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDetailDTO
     * @return
     */
    public int updateDetail(MaGrdMngDetailDTO maGrdMngDetailDTO);
}