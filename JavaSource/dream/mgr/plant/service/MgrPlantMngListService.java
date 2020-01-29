package dream.mgr.plant.service;

import java.util.List;

import common.bean.User;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;

/**
 * 공장설정 - 목록 service
 * @author  euna0207
 * @version $Id: MgrPlantMngListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since   1.0
 */
public interface MgrPlantMngListService
{
    /**
     *  grid find
     * @author  euna0207
     * @version $Id: MgrPlantMngListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @param mgrPlantMngCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findPlantList(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user);
    /**
     * delete
     * @author euna0207
     * @version $Id: MgrPlantMngListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deletePlant(String[] deleteRows, User user) throws Exception;
	public String findTotalCount(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user);
}
