package dream.work.pm.list.dao;

import common.bean.User;

import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;

/**
 * 사용자재 상세 dao
 * @author  kim21017
 * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmListEquipDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public WorkPmListEquipDetailDTO findDetail(String pmId, String pmEquipId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
    public int updateLastSchedDate(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
    public int updateSchedEquipDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
    /**
     * InitWrkDate update
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateInitWrkDate(String initWrkDate, String compNo, String pmId);
    
    public int deletePmSchedulePmEQuip(String compNo, String pmEquipId);
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
    public int mergeEqPmCycle(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
    /**
     * pm에 딸린 설비가 있는지 count
     * @author js.lee
     * @since   1.0
     *
     * @param maPmMstrCommonDTO
     * @param user
     * @return
     */
    public String countPmEquip(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
}