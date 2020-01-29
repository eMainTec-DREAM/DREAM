package dream.asset.rpt.mtbfmttr.equip.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipDetailDTO;
import dream.asset.rpt.mtbfmttr.equip.service.AssetRptMtbfmttrEquipDetailService;

/**
 * MTBF,MTTR(설비) 상세 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractAssetRptMtbfmttrEquipDetailService implements AssetRptMtbfmttrEquipDetailService
{     
	protected AssetRptMtbfmttrEquipDetailService assetRptMtbfmttrEquipDetailService;
	
	public AbstractAssetRptMtbfmttrEquipDetailService(AssetRptMtbfmttrEquipDetailService assetRptMtbfmttrEquipDetailService)
	{
		this.assetRptMtbfmttrEquipDetailService = assetRptMtbfmttrEquipDetailService;
	}
	
    public abstract List findDetail(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO, User loginUser);
    
}
