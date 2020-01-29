package dream.asset.rpt.mtbfmttr.equip.service.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.StringUtil;
import dream.asset.rpt.mtbfmttr.equip.dao.AssetRptMtbfmttrEquipListDAO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;
import dream.asset.rpt.mtbfmttr.equip.service.AssetRptMtbfmttrEquipListService;

/**
 * MTBF,MTTR(설비) 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptMtbfmttrEquipListServiceTarget"
 * @spring.txbn id="assetRptMtbfmttrEquipListService"
 * @spring.property name="assetRptMtbfmttrEquipListDAO" ref="assetRptMtbfmttrEquipListDAO"
 */
public class AssetRptMtbfmttrEquipListServiceImpl implements AssetRptMtbfmttrEquipListService
{
    private AssetRptMtbfmttrEquipListDAO assetRptMtbfmttrEquipListDAO = null;
    
	public AssetRptMtbfmttrEquipListDAO getAssetRptMtbfmttrEquipListDAO()
    {
        return assetRptMtbfmttrEquipListDAO;
    }
	
    public void setAssetRptMtbfmttrEquipListDAO(
            AssetRptMtbfmttrEquipListDAO assetRptMtbfmttrEquipListDAO)
    {
        this.assetRptMtbfmttrEquipListDAO = assetRptMtbfmttrEquipListDAO;
    }
    
    public List findList(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user) throws Exception
    {
        return assetRptMtbfmttrEquipListDAO.findList(assetRptMtbfmttrEquipCommonDTO, user);
    }
    
    public List findMtbfList(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user) throws Exception
    {
        List<Map> resultList = new ArrayList();
        assetRptMtbfmttrEquipCommonDTO.setSelectType("MTBF");
        assetRptMtbfmttrEquipCommonDTO.setOrderBy("MTBF");
        assetRptMtbfmttrEquipCommonDTO.setDirection("ASC");
        assetRptMtbfmttrEquipCommonDTO.setIsLoadMaxCount(true);
        assetRptMtbfmttrEquipCommonDTO.setFirstRow("0");
        List<Map> list = assetRptMtbfmttrEquipListDAO.findList(assetRptMtbfmttrEquipCommonDTO, user);
        Collections.sort(list, new Comparator<Map>(){
            @Override
            public int compare(Map o1, Map o2)
            {
                float mtbf1 = "".equals(StringUtil.valueOf(o1.get("MTBF")))?Float.MAX_VALUE:Float.parseFloat(StringUtil.valueOf(o1.get("MTBF")));
                float mtbf2 = "".equals(StringUtil.valueOf(o2.get("MTBF")))?Float.MAX_VALUE:Float.parseFloat(StringUtil.valueOf(o2.get("MTBF")));
                if(mtbf1>mtbf2){
                    return 1;
                }
                else if(mtbf1<mtbf2){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        });
        int cnt=1;
        for(Map map:list){
            if("".equals(StringUtil.valueOf(map.get("MTBF")))) break;
            if(cnt>10) break;
            resultList.add(map);
            cnt++;
        }
        return resultList;
    }
    
    public List findMttrList(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user) throws Exception
    {
        List<Map> resultList = new ArrayList();
        assetRptMtbfmttrEquipCommonDTO.setSelectType("MTTR");
        assetRptMtbfmttrEquipCommonDTO.setOrderBy("MTTR");
        assetRptMtbfmttrEquipCommonDTO.setDirection("DESC");
        assetRptMtbfmttrEquipCommonDTO.setIsLoadMaxCount(true);
        assetRptMtbfmttrEquipCommonDTO.setFirstRow("0");
        List<Map> list = assetRptMtbfmttrEquipListDAO.findList(assetRptMtbfmttrEquipCommonDTO, user);
        Collections.sort(list, new Comparator<Map>(){
            @Override
            public int compare(Map o1, Map o2)
            {
                float mttr1 = "".equals(StringUtil.valueOf(o1.get("MTTR")))?Float.MIN_VALUE:Float.parseFloat(StringUtil.valueOf(o1.get("MTTR")));
                float mttr2 = "".equals(StringUtil.valueOf(o2.get("MTTR")))?Float.MIN_VALUE:Float.parseFloat(StringUtil.valueOf(o2.get("MTTR")));
                if(mttr1>mttr2){
                    return -1;
                }
                else if(mttr1<mttr2){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        });
        int cnt=1;
        for(Map map:list){
            if("".equals(StringUtil.valueOf(map.get("MTTR")))) break;
            if(cnt>10) break;
            resultList.add(map);
            cnt++;
        }
        return resultList;
    }

    @Override
    public String findTotalCount(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user) throws Exception
    {
        return assetRptMtbfmttrEquipListDAO.findTotalCount(assetRptMtbfmttrEquipCommonDTO, user);
    }
	
}

