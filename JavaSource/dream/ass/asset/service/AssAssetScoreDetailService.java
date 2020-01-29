package dream.ass.asset.service;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreDetailDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;
/**
 * 평가점수 - Detail Service
 * @author youngjoo38
 * @version $Id: AssAssetScoreDetailService.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface AssAssetScoreDetailService
{    
	/**
	 * FIND DETAIL
	 * @param assAssetCommonDTO
	 * @param assAssetScoreListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public AssAssetScoreDetailDTO findDetail(AssAssetCommonDTO assAssetCommonDTO,AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param assAssetCommonDTO
	 * @param assAssetScoreDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(AssAssetCommonDTO assAssetCommonDTO,AssAssetScoreDetailDTO assAssetScoreDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param assAssetCommonDTO
     * @param assAssetScoreDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(AssAssetCommonDTO assAssetCommonDTO,AssAssetScoreDetailDTO assAssetScoreDetailDTO, User user) throws Exception;
    
}
