package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrMoldModHistListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistListDTO;
import dream.asset.list.service.MaEqMstrMoldModHistListService;

/**
 * 구성자재 목록
 * @author kim21017
 * @version $Id: MaEqMstrPartListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrMoldModHistListServiceTarget"
 * @spring.txbn id="maEqMstrMoldModHistListService"
 * @spring.property name="maEqMstrMoldModHistListDAO" ref="maEqMstrMoldModHistListDAO"
 */
public class MaEqMstrMoldModHistListServiceImpl implements MaEqMstrMoldModHistListService
{
    private MaEqMstrMoldModHistListDAO maEqMstrMoldModHistListDAO = null;


	public MaEqMstrMoldModHistListDAO getMaEqMstrMoldModHistListDAO() {
		return maEqMstrMoldModHistListDAO;
	}



	public void setMaEqMstrMoldModHistListDAO(
			MaEqMstrMoldModHistListDAO maEqMstrMoldModHistListDAO) {
		this.maEqMstrMoldModHistListDAO = maEqMstrMoldModHistListDAO;
	}



	public List findMoldProdList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO, User loginUser)
    {      
        return maEqMstrMoldModHistListDAO.findMoldModHistList(maEqMstrCommonDTO, maEqMstrMoldModHistListDTO, loginUser);
    }

	
	
	public int deleteMoldProdList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maEqMstrMoldModHistListDAO.deleteMoldModHistList(id, compNo);
            }
        
        return result;
    }
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO,User user)  throws Exception
    {
        return maEqMstrMoldModHistListDAO.findTotalCount(maEqMstrCommonDTO, maEqMstrMoldModHistListDTO, user);
    }
}

