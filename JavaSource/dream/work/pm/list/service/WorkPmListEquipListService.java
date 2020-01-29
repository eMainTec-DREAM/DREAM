package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방설비 목록
 * @author  kim21017
 * @version $Id: WorkPmListEquipListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmListEquipListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkPmListEquipListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findEqList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: WorkPmListEquipListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteEqList(String[] deleteRows, User loginUser) throws Exception;

    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;

}
