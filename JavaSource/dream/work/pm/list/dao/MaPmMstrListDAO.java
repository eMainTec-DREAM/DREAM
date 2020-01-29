package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;

/**
 * 설비종류 - 목록 dao
 * @author  jung7126
 * @version $Id: MaPmMstrListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface MaPmMstrListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaPmMstrListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @return List
     */
    public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public List findWorkList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public List findInsList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public List findTrList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public List findCalList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;

    /**
     * delete
     * @author jung7126
     * @version $Id: MaPmMstrListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] updateListDeleteTag(List<MaPmMstrDetailDTO> list, User user);
    
    public int insertCopyDetail(String newId, String oldId, String revStat, String revhistId, User user, boolean revFlag);

    public int insertCopyEquip(String newId, String oldId, User user);

    public int insertCopyPoint(String newId, String oldId, User user);
    
    public int insertCopySched(String newId, String oldId, User user);

    public int insertCopyMsTime(String newId, String oldId, User user);
    
    public int deleteQrCode(User loginUser, String fileName) throws Exception;
    
    public int insertQrCode(String id, String fileName, User loginUser);

    public int insertListQrCode(MaPmMstrCommonDTO maPmMstrCommonDTO, String fileName, User loginUser);
    public String[] selectPmEquipMaps(String id, User loginUser);
    public int insertCopyOneEquip(String newPmId, String newPmEquipId, String oldPmEquipId, User loginUser);
    public int insertCopyPart(String newId, String oldId, User loginUser);
    public int updatePmEquipId(String newPmId, String newPmEquipId, String oldPmEquipId, User loginUser);
	public String getData(User user, MaPmMstrCommonDTO maPmMstrCommonDTO);
}