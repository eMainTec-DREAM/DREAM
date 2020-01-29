package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.ass.asset.service.AssAssetDetailService;
import dream.asset.list.dao.AssetListAssListDAO;
import dream.asset.list.dto.AssetListAssListDTO;
import dream.asset.list.service.AssetListAssListService;

/**
 * AssetListAss Page - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="assetListAssListServiceTarget"
 * @spring.txbn id="assetListAssListService"
 * @spring.property name="assetListAssListDAO" ref="assetListAssListDAO"
 */
public class AssetListAssListServiceImpl implements AssetListAssListService
{
    private AssetListAssListDAO assetListAssListDAO = null;

    public List findList(AssetListAssListDTO assetListAssListDTO, User user) throws Exception
    {      
        AssAssetDetailService assAssetDetailService = (AssAssetDetailService)CommonUtil.getBean("assAssetDetailService", user);
        
        assetListAssListDTO.setEquipId(assAssetDetailService.getLastVersionEquipId(assetListAssListDTO.getEquipId(), user.getCompNo()));
        return assetListAssListDAO.findList(assetListAssListDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                //int chkDelRow = assetListAssListDAO.chkDelRow(id, user);
               // System.out.println("!!!!!"+chkDelRow);
                //if(chkDelRow == 0) {
                    result = result + assetListAssListDAO.deleteList(id, user);
               // } else {
                //    result = 0;
               // }
                
            }
        return result;
    }

    public AssetListAssListDAO getAssetListAssListDAO() {
        return assetListAssListDAO;
    }

    public void setAssetListAssListDAO(AssetListAssListDAO assetListAssListDAO) {
        this.assetListAssListDAO = assetListAssListDAO;
    }    
    
    public String findTotalCount(AssetListAssListDTO assetListAssListDTO,User user)  throws Exception
    {
        AssAssetDetailService assAssetDetailService = (AssAssetDetailService)CommonUtil.getBean("assAssetDetailService", user);
        
        assetListAssListDTO.setEquipId(assAssetDetailService.getLastVersionEquipId(assetListAssListDTO.getEquipId(), user.getCompNo()));
        
        return assetListAssListDAO.findTotalCount(assetListAssListDTO, user);
    }
}
