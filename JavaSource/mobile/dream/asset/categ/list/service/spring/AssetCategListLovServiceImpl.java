package mobile.dream.asset.categ.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import mobile.dream.asset.categ.list.dao.AssetCategListLovDAO;
import mobile.dream.asset.categ.list.dto.AssetCategListLovDTO;
import mobile.dream.asset.categ.list.service.AssetCategListLovService;


/**
 * 설비종류팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="assetCategListLovServiceTarget"
 * @spring.txbn id="assetCategListLovService"
 * @spring.property name="assetCategListLovDAO" ref="assetCategListLovDAO"
 */
public class AssetCategListLovServiceImpl implements AssetCategListLovService
{
    /** 설비종류팝업 DAO */
    private AssetCategListLovDAO assetCategListLovDAO = null;

    public AssetCategListLovDAO getAssetCategListLovDAO() 
    {
		return assetCategListLovDAO;
	}

	public void setAssetCategListLovDAO(AssetCategListLovDAO assetCategListLovDAO) 
	{
		this.assetCategListLovDAO = assetCategListLovDAO;
	}

	public List findEqCtgList(AssetCategListLovDTO assetCategListLovDTO, User loginUser)
    {
        List list = null;
        list = assetCategListLovDAO.findEqCtgList(assetCategListLovDTO, loginUser);
        
        /*List resultList = new ArrayList();

        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            String locId = String.valueOf(resultM.get("EQCTGID"));
            String pLocId = String.valueOf(resultM.get("PEQCTGID"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LEVEL"));
            
            if(curLvl.equals(minLvl))
            {
                List subList = getSubList(locId, list, "PEQCTGID", "EQCTGID");
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