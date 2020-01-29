package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrMoldProdListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdListDTO;
import dream.asset.list.service.MaEqMstrMoldProdListService;

/**
 * 구성자재 목록
 * @author kim21017
 * @version $Id: MaEqMstrPartListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrMoldProdListServiceTarget"
 * @spring.txbn id="maEqMstrMoldProdListService"
 * @spring.property name="maEqMstrMoldProdListDAO" ref="maEqMstrMoldProdListDAO"
 */
public class MaEqMstrMoldProdListServiceImpl implements MaEqMstrMoldProdListService
{
    private MaEqMstrMoldProdListDAO maEqMstrMoldProdListDAO = null;


    
	public MaEqMstrMoldProdListDAO getMaEqMstrMoldProdListDAO() {
		return maEqMstrMoldProdListDAO;
	}



	public void setMaEqMstrMoldProdListDAO(
			MaEqMstrMoldProdListDAO maEqMstrMoldProdListDAO) {
		this.maEqMstrMoldProdListDAO = maEqMstrMoldProdListDAO;
	}



	public List findMoldProdList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO, User loginUser)
    {      
        return maEqMstrMoldProdListDAO.findMoldProdList(maEqMstrCommonDTO, maEqMstrMoldProdListDTO, loginUser);
    }

	
	
	public int deleteMoldProdList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maEqMstrMoldProdListDAO.deleteMoldProdList(id, compNo);
            }
        
        return result;
    }
	
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO,User user)  throws Exception
    {
        return maEqMstrMoldProdListDAO.findTotalCount(maEqMstrCommonDTO, maEqMstrMoldProdListDTO, user);
    }
}

