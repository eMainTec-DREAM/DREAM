package dream.asset.rpt.mtbfmttr.usdept.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.usdept.dao.AssetRptMtbfmttrUsDeptDetailDAO;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptDetailDTO;
import dream.asset.rpt.mtbfmttr.usdept.service.AssetRptMtbfmttrUsDeptDetailService;

/**
 * MTBF,MTTR(사용부서) 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptMtbfmttrUsDeptDetailServiceTarget"
 * @spring.txbn id="assetRptMtbfmttrUsDeptDetailService"
 * @spring.property name="assetRptMtbfmttrUsDeptDetailDAO" ref="assetRptMtbfmttrUsDeptDetailDAO"
 */
public class AssetRptMtbfmttrUsDeptDetailServiceImpl implements AssetRptMtbfmttrUsDeptDetailService
{
    private AssetRptMtbfmttrUsDeptDetailDAO assetRptMtbfmttrUsDeptDetailDAO = null;
    
    public AssetRptMtbfmttrUsDeptDetailDAO getAssetRptMtbfmttrUsDeptDetailDAO()
    {
        return assetRptMtbfmttrUsDeptDetailDAO;
    }
    
    public void setAssetRptMtbfmttrUsDeptDetailDAO(
            AssetRptMtbfmttrUsDeptDetailDAO assetRptMtbfmttrUsDeptDetailDAO)
    {
        this.assetRptMtbfmttrUsDeptDetailDAO = assetRptMtbfmttrUsDeptDetailDAO;
    }
    
    public List findDetail(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, AssetRptMtbfmttrUsDeptDetailDTO assetRptMtbfmttrUsDeptDetailDTO, User loginUser)
    {
        return assetRptMtbfmttrUsDeptDetailDAO.findDetail(assetRptMtbfmttrUsDeptDetailDTO, loginUser);
        
    }
	
}

