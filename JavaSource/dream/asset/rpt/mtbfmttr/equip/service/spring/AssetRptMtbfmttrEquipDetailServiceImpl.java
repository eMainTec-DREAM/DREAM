package dream.asset.rpt.mtbfmttr.equip.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.equip.dao.AssetRptMtbfmttrEquipDetailDAO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipDetailDTO;
import dream.asset.rpt.mtbfmttr.equip.service.AssetRptMtbfmttrEquipDetailService;

/**
 * MTBF,MTTR(설비) 상세 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptMtbfmttrEquipDetailServiceTarget"
 * @spring.txbn id="assetRptMtbfmttrEquipDetailService"
 * @spring.property name="assetRptMtbfmttrEquipDetailDAO" ref="assetRptMtbfmttrEquipDetailDAO"
 */
public class AssetRptMtbfmttrEquipDetailServiceImpl implements AssetRptMtbfmttrEquipDetailService
{
    private AssetRptMtbfmttrEquipDetailDAO assetRptMtbfmttrEquipDetailDAO = null;
    
    public AssetRptMtbfmttrEquipDetailDAO getAssetRptMtbfmttrEquipDetailDAO()
    {
        return assetRptMtbfmttrEquipDetailDAO;
    }
    
    public void setAssetRptMtbfmttrEquipDetailDAO(
            AssetRptMtbfmttrEquipDetailDAO assetRptMtbfmttrEquipDetailDAO)
    {
        this.assetRptMtbfmttrEquipDetailDAO = assetRptMtbfmttrEquipDetailDAO;
    }
    
    public List findDetail(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO, User loginUser) throws Exception
    {
        return assetRptMtbfmttrEquipDetailDAO.findDetail(assetRptMtbfmttrEquipCommonDTO, assetRptMtbfmttrEquipDetailDTO, loginUser);
        
    }

    @Override
    public String findTotalCount(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO, User user) throws Exception
    {
        return assetRptMtbfmttrEquipDetailDAO.findTotalCount(assetRptMtbfmttrEquipCommonDTO, assetRptMtbfmttrEquipDetailDTO, user);
    }

}

