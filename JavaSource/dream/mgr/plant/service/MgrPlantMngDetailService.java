package dream.mgr.plant.service;

import common.bean.User;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;
import dream.mgr.plant.dto.MgrPlantMngDetailDTO;

/**
 * 공장설정 - 상세 service
 *
 * @author euna0207
 * @version $Id: MgrPlantMngDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 */
public interface MgrPlantMngDetailService
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrPlantMngDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     * @throws Exception
     */
    public MgrPlantMngDetailDTO findDetail(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrPlantMngDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrPlantMngDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user) throws Exception;
    public int valPlantDesc(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user) throws Exception;
}
