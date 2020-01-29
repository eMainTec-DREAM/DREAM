package dream.mgr.plant.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;

/**
 * 공장설정 - 목록 dao
 * @author  euna0207
 * @version $Id: MgrPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 */
public interface MgrPlantMngListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngCommonDTO
     * @return List
     */
    public List findPlantList(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user);

    /**
     * delete
     * @author euna0207
     * @version $Id: MgrPlantMngListDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deletePlant(String plant, User user);

	public String findTotalCount(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user);
}