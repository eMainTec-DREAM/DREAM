package dream.ass.asset.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetDetailDTO;

/**
 * AssAsset Page - Detail DAO
 * @author youngjoo38
 * @version $Id: AssAssetDetailDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface AssAssetDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @param assAssetCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public AssAssetDetailDTO findDetail(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    /**
     * INSERT POINT
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertPoint(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    
    /**
     * UPDATE DETAIL
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    public int insertEvalPointList(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    public int deleteEvalPointList(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    /**
     * ASSCOMPLETED DETAIL
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int asscompletedDetail(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE EQUIP GRADE
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateEqGrade(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    /**
     * CHECK DETAIL
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String checkDetail(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE GRADE
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String updateGrade(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE SCORE
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String updateScore(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    public String findPmitypePeriod(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    public String isExistPm(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    public String checkEquip(AssAssetDetailDTO assAssetDetailDTO, User user);

    public String getLastVersionEquipId(String equipId, String compNo);
    
}
