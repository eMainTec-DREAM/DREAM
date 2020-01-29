package mobile.dream.asset.loc.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import mobile.dream.asset.loc.list.dao.AssetLocListLovDAO;
import mobile.dream.asset.loc.list.dto.AssetLocListLovDTO;
import mobile.dream.asset.loc.list.service.AssetLocListLovService;


/**
 * 위치분류팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="assetLocListLovServiceTarget"
 * @spring.txbn id="assetLocListLovService"
 * @spring.property name="assetLocListLovDAO" ref="assetLocListLovDAO"
 */
public class AssetLocListLovServiceImpl implements AssetLocListLovService
{
    /** 위치분류팝업 DAO */
    private AssetLocListLovDAO assetLocListLovDAO = null;

    public AssetLocListLovDAO getAssetLocListLovDAO() 
    {
		return assetLocListLovDAO;
	}

	public void setAssetLocListLovDAO(AssetLocListLovDAO assetLocListLovDAO) 
	{
		this.assetLocListLovDAO = assetLocListLovDAO;
	}

	public List findEqLocList(AssetLocListLovDTO assetLocListLovDTO, User loginUser)
    {
        List list = assetLocListLovDAO.findEqLocList(assetLocListLovDTO, loginUser);
        
//        List resultList = new ArrayList();

        /*if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String locId = String.valueOf(resultM.get("EQLOCID"));
            String pLocId = String.valueOf(resultM.get("PEQLOCID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(locId, list, "PEQLOCID", "EQLOCID");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultList.add(resultM);
            }
            
        }*/
        return list;
    }
	
	public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
	{
	    List subList = new ArrayList();
	    Map rMap = null;
	    //Parent Equip Location Code 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String eqLocId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(eqLocId, resultList, pCodeCol, codeCol);
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
	}
}