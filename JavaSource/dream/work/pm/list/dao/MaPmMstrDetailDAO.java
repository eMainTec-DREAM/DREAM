package dream.work.pm.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;

/**
 * 상세 dao
 * @author jung7126
 * @version $Id: MaPmMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 */
public interface MaPmMstrDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public MaPmMstrDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailDTO
     * @return
     */
    public int insertDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User loginUser);
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailDTO
     * @return
     */
    public int updateDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User loginUser) throws Exception;
    
    
    public int updatePmRinsScheduleDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;
    public int updatePmDinsScheduleDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;
    public int updatePmPatrlScheduleDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;
    public int updatePmWoScheduleDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;
    
    public int updateLastSchedDate(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;

    public String checkDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user);
    
    
    public int createPmSchedule(String compNo, String Pmid, String enterBy) throws Exception;
    
    public int createWorkOrder(String compNo, String Pmid) throws Exception;
    
    public int deletePmScheduleAll(String compNo, String pmId) throws Exception;
    
    public int insertRevisionHist(MaPmMstrDetailDTO maPmMstrDetailDTO, User user, String histId);
	public int updatePmMstrLastVersion(MaPmMstrDetailDTO maPmMstrDetailDTO, User user, String histId);

    public int updatePmEquip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, MaWoResultPmCalDTO maWoResultPmCalDTO, String pmId);

    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public MaPmMstrDetailDTO findEquipDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
    /**
     * 주기#가 변경되었을시에 중복되는지 체크
     * @author js.lee
     * @since   1.0
     *
     * @param maPmMstrDetailDTO
     * @param user
     * @return
     */
    public String pmNoCheck(MaPmMstrDetailDTO maPmMstrDetailDTO, User user);
    
    
}