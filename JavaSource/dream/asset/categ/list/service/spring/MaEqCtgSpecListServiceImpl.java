package dream.asset.categ.list.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.categ.list.dao.MaEqCtgSpecListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;
import dream.asset.categ.list.service.MaEqCtgSpecListService;
import dream.asset.list.service.MaEqMstrSpecListService;

/**
 * 설비종류별 표준제원 목록
 * @author syyang
 * @version $Id: MaEqCtgSpecListServiceImpl.java,v 1.0 2015/12/02 09:12:51 syyang Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqCtgSpecListServiceTarget"
 * @spring.txbn id="maEqCtgSpecListService"
 * @spring.property name="maEqCtgSpecListDAO" ref="maEqCtgSpecListDAO"
 */
public class MaEqCtgSpecListServiceImpl implements MaEqCtgSpecListService
{
    private MaEqCtgSpecListDAO maEqCtgSpecListDAO = null;


	public MaEqCtgSpecListDAO getMaEqCtgSpecListDAO() {
		return maEqCtgSpecListDAO;
	}

	public void setMaEqCtgSpecListDAO(MaEqCtgSpecListDAO maEqCtgSpecListDAO) {
		this.maEqCtgSpecListDAO = maEqCtgSpecListDAO;
	}
	
	public List findSpecList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgSpecListDTO maEqCtgSpecListDTO, User loginUser)
	{      
		return maEqCtgSpecListDAO.findSpecList(maEqCatalogCommonDTO, maEqCtgSpecListDTO, loginUser);
	}
	public int deleteSpecList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                //1. 관련 설비제원의 공통제원여부 N
                MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) CommonUtil.getBean("maEqMstrSpecListService", compNo);
                User user = new User();
                user.setCompNo(compNo);
                result = result + maEqMstrSpecListService.undoEqCtgSpec(id, user);
                //2. 종류별제원 데이타 삭제
            	result = result + maEqCtgSpecListDAO.deleteSpecList(id, compNo);
            }
        
        return result;
    }
	
	public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgSpecListDTO maEqCtgSpecListDTO, User user) throws Exception
    {      
        return maEqCtgSpecListDAO.findTotalCount(maEqCatalogCommonDTO,maEqCtgSpecListDTO,user);
    }
}

