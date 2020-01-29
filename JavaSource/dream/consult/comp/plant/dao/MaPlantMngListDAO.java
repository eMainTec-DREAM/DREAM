package dream.consult.comp.plant.dao;

import java.util.List;

import common.bean.User;

import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;

/**
 * 회사설정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPlantMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngCommonDTO
     * @return List
     */
    public List findPlantList(MaPlantMngCommonDTO maPlantMngCommonDTO, User user);

    /**
     * delete
     * @author kim21017
     * @version $Id: MaPlantMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deletePlant(String compNo, String plant, User user);

	public String findTotalCount(MaPlantMngCommonDTO maPlantMngCommonDTO, User user);
}