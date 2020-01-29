package dream.ass.asset.service.spring;

import common.bean.User;
import dream.ass.asset.dao.AssAssetScoreDetailDAO;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreDetailDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;
import dream.ass.asset.service.AssAssetScoreDetailService;

/**
 * 평가점수 - Detail Service implements
 * @author youngjoo38
 * @version $Id: AssAssetScoreDetailServiceImpl.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="assAssetScoreDetailServiceTarget"
 * @spring.txbn id="assAssetScoreDetailService"
 * @spring.property name="assAssetScoreDetailDAO" ref="assAssetScoreDetailDAO"
 */
public class AssAssetScoreDetailServiceImpl implements AssAssetScoreDetailService
{
    private AssAssetScoreDetailDAO assAssetScoreDetailDAO = null;
    
    public AssAssetScoreDetailDTO findDetail(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception
    {
    	return assAssetScoreDetailDAO.findDetail(assAssetCommonDTO,assAssetScoreListDTO, user);
    }
    
    public int insertDetail(AssAssetCommonDTO assAssetCommonDTO,AssAssetScoreDetailDTO assAssetScoreDetailDTO, User user) throws Exception
    {
     	return assAssetScoreDetailDAO.insertDetail(assAssetCommonDTO,assAssetScoreDetailDTO, user);
    }
    
    public int updateDetail(AssAssetCommonDTO assAssetCommonDTO,AssAssetScoreDetailDTO assAssetScoreDetailDTO, User user) throws Exception
    {
     	return assAssetScoreDetailDAO.updateDetail(assAssetCommonDTO,assAssetScoreDetailDTO, user);
    }

	public AssAssetScoreDetailDAO getAssAssetScoreDetailDAO() {
		return assAssetScoreDetailDAO;
	}

	public void setAssAssetScoreDetailDAO(AssAssetScoreDetailDAO assAssetScoreDetailDAO) {
		this.assAssetScoreDetailDAO = assAssetScoreDetailDAO;
	}
    

}
