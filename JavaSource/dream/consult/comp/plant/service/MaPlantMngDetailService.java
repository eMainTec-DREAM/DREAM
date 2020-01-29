package dream.consult.comp.plant.service;

import common.bean.User;

import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;
import dream.consult.comp.plant.dto.MaPlantMngDetailDTO;

/**
 * 회사설정 - 상세 service
 *
 * @author kim21017
 * @version $Id: MaPlantMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaPlantMngDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPlantMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     * @throws Exception
     */
    public MaPlantMngDetailDTO findDetail(MaPlantMngCommonDTO maPlantMngCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPlantMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPlantMngDetailDTO maPlantMngDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPlantMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPlantMngDetailDTO maPlantMngDetailDTO, User user) throws Exception;

    public int valPlantNo(MaPlantMngDetailDTO maPlantMngDetailDTO, User user) throws Exception;
}
