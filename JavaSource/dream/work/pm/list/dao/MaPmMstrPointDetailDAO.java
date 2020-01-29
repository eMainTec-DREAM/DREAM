package dream.work.pm.list.dao;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;

/**
 * 검사항목 상세 dao
 * @author  jung7126
 * @version $Id: MaPmMstrPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 */
public interface MaPmMstrPointDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param pmPointId
     * @param compNo
     * @return
     */
    public MaPmMstrPointDetailDTO findDetail(String wkOrId, String pmPointId, User user);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmMstrPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmMstrPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int insertDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user);
    
    public int insertLovDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user);
}