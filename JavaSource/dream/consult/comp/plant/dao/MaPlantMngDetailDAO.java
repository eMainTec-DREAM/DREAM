package dream.consult.comp.plant.dao;

import common.bean.User;

import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;
import dream.consult.comp.plant.dto.MaPlantMngDetailDTO;

/**
 * 회사설정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: MaPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaPlantMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MaPlantMngDetailDTO findDetail(MaPlantMngCommonDTO maPlantMngCommonDTO, User user);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngDetailDTO
     * @return
     */
    public int insertDetail(MaPlantMngDetailDTO maPlantMngDetailDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngDetailDTO
     * @return
     */
    public int updateDetail(MaPlantMngDetailDTO maPlantMngDetailDTO, User user);
}