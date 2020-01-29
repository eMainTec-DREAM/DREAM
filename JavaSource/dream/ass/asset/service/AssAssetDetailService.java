package dream.ass.asset.service;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetDetailDTO;

/**
 * AssAsset Page - Detail Service
 * @author youngjoo38
 * @version $Id: AssAssetDetailService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface AssAssetDetailService
{
    /**
     * FIND ASS ASSET DETAIL
     * @param assAssetCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public AssAssetDetailDTO findDetail(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception;
    /**
     * INSERT ASS ASSET 
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    /**
     * UPDATE ASS ASSET 
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    /**
     * ASS COMPLETED
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int asscompletedDetail(AssAssetCommonDTO assAssetCommonDTO, AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
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
    public String findGrade(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE SCORE
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String updateScore(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE SCORE
     * @param assAssetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateEqGrade(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    public String makePMMstr(AssAssetCommonDTO assAssetCommonDTO, AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception;
    
    public String getLastVersionEquipId(String equipId, String compNo);
    

}
