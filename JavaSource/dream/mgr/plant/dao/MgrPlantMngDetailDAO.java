package dream.mgr.plant.dao;

import common.bean.User;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;
import dream.mgr.plant.dto.MgrPlantMngDetailDTO;

/**
 * 공장설정 - 상세 dao
 *
 * @author euna0207
 * @version $Id: MgrPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
 * @since 1.0
 */
public interface MgrPlantMngDetailDAO
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MgrPlantMngDetailDTO findDetail(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user);

    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngDetailDTO
     * @return
     */
    public int insertDetail(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user);

    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngDetailDTO
     * @return
     */
    public int updateDetail(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user);
}