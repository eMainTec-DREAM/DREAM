package dream.asset.loc.list.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.loc.list.dao.MaEqLocListDAO;
import dream.asset.loc.list.dto.MaEqLocCommonDTO;
import dream.asset.loc.list.service.MaEqLocListService;

/**
 * 설비위치 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaEqLocListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqLocListServiceTarget"
 * @spring.txbn id="maEqLocListService"
 * @spring.property name="maEqLocListDAO" ref="maEqLocListDAO"
 */
public class MaEqLocListServiceImpl implements MaEqLocListService
{
    private MaEqLocListDAO maEqLocListDAO = null;

    public MaEqLocListDAO getMaEqLocListDAO() {
		return maEqLocListDAO;
	}

	public void setMaEqLocListDAO(MaEqLocListDAO maEqLocListDAO) {
		this.maEqLocListDAO = maEqLocListDAO;
	}

	public List findEqLocList(MaEqLocCommonDTO maEqLocCommonDTO, User user, boolean excelExport)
    {      
        List list =  maEqLocListDAO.findEqLocList(maEqLocCommonDTO, user);
        
        return CommonUtil.makeTreeList(list, "PEQLOCID", "EQLOCID", excelExport);
    }
	
	public int deleteEqLoc(String[] deleteRows, MaEqLocCommonDTO maEqLocCommonDTO, User user) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
        	for(String id : deleteRows)
            {
                result = result + maEqLocListDAO.deleteEqLoc(id,maEqLocCommonDTO, user);
            }
        	
        	maEqLocListDAO.resetFullDesc(maEqLocCommonDTO, user);
        }
            
        return result;
    }
}

