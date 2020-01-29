package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.dto.WorkPmListDInsPointDetailDTO;

/**
 * WorkPmListDInsPoint Page - List Service
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmListDInsPointListService
{
    /**
     * FIND LIST
     * @param workPmListDInsPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    /**
     * DELETE LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmListDInsPointListDTO
     * @return
     */
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
    public int insertLovDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;

}
