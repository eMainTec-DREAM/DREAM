package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.MaWoResultStPointListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointListDTO;
import dream.work.list.service.MaWoResultStPointListService;

/**
 * 작업결과 작업필수검사항목 목록
 * @author kim21017
 * @version $Id: MaWoResultStPointListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultStPointListServiceTarget"
 * @spring.txbn id="maWoResultStPointListService"
 * @spring.property name="maWoResultStPointListDAO" ref="maWoResultStPointListDAO"
 */
public class MaWoResultStPointListServiceImpl implements MaWoResultStPointListService
{
    private MaWoResultStPointListDAO maWoResultStPointListDAO = null;


	public List findStPointList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultStPointListDTO maWoResultStPointListDTO, User loginUser)
    {      
        return maWoResultStPointListDAO.findStPointList(maWoResultMstrCommonDTO,maWoResultStPointListDTO, loginUser);
    }

	public MaWoResultStPointListDAO getMaWoResultStPointListDAO() {
		return maWoResultStPointListDAO;
	}

	public void setMaWoResultStPointListDAO(MaWoResultStPointListDAO maWoResultStPointListDAO) {
		this.maWoResultStPointListDAO = maWoResultStPointListDAO;
	}
	
	public int deleteStPointList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWoResultStPointListDAO.deleteStPointList(id, compNo);
            }
        
        return result;
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultStPointListDTO maWoResultStPointListDTO, User loginUser) throws Exception 
	{
		return maWoResultStPointListDAO.findTotalCount(maWoResultMstrCommonDTO, maWoResultStPointListDTO, loginUser);
	}
}

