package dream.ass.asset.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.ass.asset.dao.AssAssetScoreListDAO;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreDetailDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;
import dream.ass.asset.service.AssAssetScoreDetailService;
import dream.ass.asset.service.AssAssetScoreListService;

/**
 * AssAssetScore Page - List Service implements
 * @author youngjoo38
 * @version $Id: AssAssetScoreListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="assAssetScoreListServiceTarget"
 * @spring.txbn id="assAssetScoreListService"
 * @spring.property name="assAssetScoreListDAO" ref="assAssetScoreListDAO"
 * @spring.property name="assAssetScoreDetailService" ref="assAssetScoreDetailService"
 */
public class AssAssetScoreListServiceImpl implements AssAssetScoreListService
{
    private AssAssetScoreListDAO assAssetScoreListDAO = null;

    private AssAssetScoreDetailService assAssetScoreDetailService = null;
    
    public AssAssetScoreDetailService getAssAssetScoreDetailService() {
		return assAssetScoreDetailService;
	}

	public void setAssAssetScoreDetailService(AssAssetScoreDetailService assAssetScoreDetailService) {
		this.assAssetScoreDetailService = assAssetScoreDetailService;
	}

	public List findList(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception
    {      
        return assAssetScoreListDAO.findList(assAssetCommonDTO,assAssetScoreListDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assAssetScoreListDAO.deleteList(id, user);
            }
        return result;
    }

    public AssAssetScoreListDAO getAssAssetScoreListDAO() {
        return assAssetScoreListDAO;
    }

    public void setAssAssetScoreListDAO(AssAssetScoreListDAO assAssetScoreListDAO) {
        this.assAssetScoreListDAO = assAssetScoreListDAO;
    }    
    
    public String findTotalCount(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user)  throws Exception
    {
        return assAssetScoreListDAO.findTotalCount(assAssetCommonDTO, assAssetScoreListDTO, user);
    }

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception 
	{
		AssAssetScoreDetailDTO assAssetScoreDetailDTO = null;
		AssAssetCommonDTO assAssetCommonDTO = null;
		
		for(Map map : gridList)
        {
			assAssetScoreDetailDTO = (AssAssetScoreDetailDTO)CommonUtil.makeDTO(map, AssAssetScoreDetailDTO.class);
			assAssetCommonDTO = (AssAssetCommonDTO)CommonUtil.makeDTO(map, AssAssetCommonDTO.class);
			
			switch(assAssetScoreDetailDTO.getNativeeditor())
			{
			case "inserted":
				break;
			case "updated" : assAssetScoreDetailService.updateDetail(assAssetCommonDTO, assAssetScoreDetailDTO, user);
				break;
			case "deleted" : assAssetScoreListDAO.deleteList(assAssetScoreDetailDTO.getEqAssPValId(), user);
				break;
			}
        }
	}

	@Override
	public int updateList(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception
	{
		return assAssetScoreListDAO.updateList(assAssetCommonDTO, assAssetScoreListDTO, user);
	}
}
