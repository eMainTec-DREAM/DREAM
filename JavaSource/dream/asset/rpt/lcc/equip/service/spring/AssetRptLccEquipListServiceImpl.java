package dream.asset.rpt.lcc.equip.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.equip.dao.AssetRptLccEquipListDAO;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipCommonDTO;
import dream.asset.rpt.lcc.equip.service.AssetRptLccEquipListService;

/**
 * 고장TOP(설비) 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptLccEquipListServiceTarget"
 * @spring.txbn id="assetRptLccEquipListService"
 * @spring.property name="assetRptLccEquipListDAO" ref="assetRptLccEquipListDAO"
 */
public class AssetRptLccEquipListServiceImpl implements AssetRptLccEquipListService
{
    private AssetRptLccEquipListDAO assetRptLccEquipListDAO = null;
    
	public AssetRptLccEquipListDAO getAssetRptLccEquipListDAO()
    {
        return assetRptLccEquipListDAO;
    }
	
    public void setAssetRptLccEquipListDAO(
            AssetRptLccEquipListDAO assetRptLccEquipListDAO)
    {
        this.assetRptLccEquipListDAO = assetRptLccEquipListDAO;
    }
    
    public List findList(AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO, User loginUser)
    {
        return assetRptLccEquipListDAO.findList(assetRptLccEquipCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO, User user)
    {
        return assetRptLccEquipListDAO.findTotalCount(assetRptLccEquipCommonDTO, user);
    }
	
}

