package dream.asset.rpt.lcc.equip.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.equip.dao.AssetRptLccEquipDetailDAO;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipDetailDTO;
import dream.asset.rpt.lcc.equip.service.AssetRptLccEquipDetailService;

/**
 * 고장TOP(설비) 상세 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptLccEquipDetailServiceTarget"
 * @spring.txbn id="assetRptLccEquipDetailService"
 * @spring.property name="assetRptLccEquipDetailDAO" ref="assetRptLccEquipDetailDAO"
 */
public class AssetRptLccEquipDetailServiceImpl implements AssetRptLccEquipDetailService
{
    private AssetRptLccEquipDetailDAO assetRptLccEquipDetailDAO = null;
    
    public AssetRptLccEquipDetailDAO getAssetRptLccEquipDetailDAO()
    {
        return assetRptLccEquipDetailDAO;
    }
    
    public void setAssetRptLccEquipDetailDAO(
            AssetRptLccEquipDetailDAO assetRptLccEquipDetailDAO)
    {
        this.assetRptLccEquipDetailDAO = assetRptLccEquipDetailDAO;
    }
    
    public List findDetail(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User loginUser)
    {
        return assetRptLccEquipDetailDAO.findDetail(assetRptLccEquipDetailDTO, loginUser);
        
    }

    @Override
    public List findMo(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User user)
    {
        return assetRptLccEquipDetailDAO.findMo(assetRptLccEquipDetailDTO, user);
    }
    
    @Override
    public List findCa(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User user)
    {
        return assetRptLccEquipDetailDAO.findCa(assetRptLccEquipDetailDTO, user);
    }
    
    @Override
    public List findRe(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User user)
    {
        return assetRptLccEquipDetailDAO.findRe(assetRptLccEquipDetailDTO, user);
    }
	
}

