package dream.asset.categ.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.categ.list.dao.MaEqCatalogListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.service.MaEqCatalogDetailService;
import dream.asset.categ.list.service.MaEqCatalogListService;

/**
 * 설비종류 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaEqCatalogListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqCatalogListServiceTarget"
 * @spring.txbn id="maEqCatalogListService"
 * @spring.property name="maEqCatalogListDAO" ref="maEqCatalogListDAO"
 */
public class MaEqCatalogListServiceImpl implements MaEqCatalogListService
{
    private MaEqCatalogListDAO maEqCatalogListDAO = null;

    public MaEqCatalogListDAO getMaEqCatalogListDAO() {
		return maEqCatalogListDAO;
	}

	public void setMaEqCatalogListDAO(MaEqCatalogListDAO maEqCatalogListDAO) {
		this.maEqCatalogListDAO = maEqCatalogListDAO;
	}

	public List findEqCatalogList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, User loginUser)
    {      
		return maEqCatalogListDAO.findEqCatalogList(maEqCatalogCommonDTO,loginUser);
    }
	
	public int deleteEqCatalog(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maEqCatalogListDAO.deleteEqCatalog(id,user.getCompNo());
            }
        
        MaEqCatalogDetailService maEqCatalogDetailService = (MaEqCatalogDetailService)CommonUtil.getBean("maEqCatalogDetailService", user);
        maEqCatalogDetailService.updateEqCtgFullDesc(user);
        
        return result;
    }
	
	public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
    {
        List subList = new ArrayList();
        List clonedList = new ArrayList();
        Map rMap = null;
        
        clonedList.addAll(resultList);
        
        //Parent Equip Location Code 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String eqLocId = String.valueOf(rMap.get(codeCol));
                clonedList.remove(rMap);
                List list = getSubList(eqLocId, clonedList, pCodeCol, codeCol);
                
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
    }
}

