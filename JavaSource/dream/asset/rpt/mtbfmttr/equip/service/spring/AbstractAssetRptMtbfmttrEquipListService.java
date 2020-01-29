package dream.asset.rpt.mtbfmttr.equip.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;
import dream.asset.rpt.mtbfmttr.equip.service.AssetRptMtbfmttrEquipListService;

/**
 * MTBF,MTTR(설비) 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractAssetRptMtbfmttrEquipListService implements AssetRptMtbfmttrEquipListService
{     
	protected AssetRptMtbfmttrEquipListService assetRptMtbfmttrEquipListService;
	
	public AbstractAssetRptMtbfmttrEquipListService(AssetRptMtbfmttrEquipListService assetRptMtbfmttrEquipListService)
	{
		this.assetRptMtbfmttrEquipListService = assetRptMtbfmttrEquipListService;
	}

    public abstract List findList(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User loginUser);
    public abstract String findTotalCount(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user);
    
}
