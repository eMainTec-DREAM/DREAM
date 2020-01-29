package dream.consult.comp.plant.service;

import java.util.List;

import common.bean.User;

import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;
import dream.consult.comp.plant.dto.MaPlantMngDetailDTO;

/**
 * 회사설정 - 목록 service
 * @author  kim21017
 * @version $Id: MaPlantMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPlantMngListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPlantMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maPlantMngCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findPlantList(MaPlantMngCommonDTO maPlantMngCommonDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPlantMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deletePlant(String[] deleteRows, String[] deleteRowsExt, User user) throws Exception;
	public String findTotalCount(MaPlantMngCommonDTO maPlantMngCommonDTO, User user);
}
