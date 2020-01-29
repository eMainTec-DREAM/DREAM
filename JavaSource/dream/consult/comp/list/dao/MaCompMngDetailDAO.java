package dream.consult.comp.list.dao;

import common.bean.User;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;
import dream.consult.comp.list.dto.MaCompMngDetailDTO;

/**
 * 회사설정 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaCompMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaCompMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaCompMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaCompMngDetailDTO findDetail(MaCompMngCommonDTO maCompMngCommonDTO, User user);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaCompMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngDetailDTO
     * @return
     */
    public int insertDetail(MaCompMngDetailDTO maCompMngDetailDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCompMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngDetailDTO
     * @return
     */
    public int updateDetail(MaCompMngDetailDTO maCompMngDetailDTO, User user);
}