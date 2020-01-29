package dream.consult.program.config.dao;

import common.bean.User;
import dream.consult.program.config.dto.MaConfigCommonDTO;
import dream.consult.program.config.dto.MaConfigDetailDTO;

/**
 * 시스템환경변수 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaConfigDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param configName
     * @return
     */
    public MaConfigDetailDTO findDetail(MaConfigCommonDTO maConfigCommonDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailDTO
     * @return
     */
    public int updateDetail(MaConfigDetailDTO maConfigDetailDTO, User user);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailDTO
     * @return
     */
    public int insertDetail(MaConfigDetailDTO maConfigDetailDTO, User user);
}