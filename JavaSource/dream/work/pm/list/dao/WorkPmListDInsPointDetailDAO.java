package dream.work.pm.list.dao;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.dto.WorkPmListDInsPointDetailDTO;

/**
 * WorkPmListDInsPoint Page - Detail DAO
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointDetailDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmListDInsPointDetailDAO
{
    /**
     * FIND DETAIL
     * @param workPmListDInsPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmListDInsPointDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workPmListDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workPmListDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, User user) throws Exception;
    
    public int insertLovDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
}
